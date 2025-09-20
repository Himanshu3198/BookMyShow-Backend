Requirement Statement – Movie Ticket Booking System

The goal is to design and implement a Movie Ticket Booking System that enables users to browse, select, and book movie tickets seamlessly. The system should handle theater management, show scheduling, seat booking, payments, and notifications in a scalable and user-friendly manner.

Functional Requirements

User Management

User registration, login, and profile management.

Role-based access: Admin, Customer.

Movie & Show Management

Admin can add, update, and remove movies.

Admin can schedule shows in specific theaters at specific times.

Each show should have associated movie, theater, screen, and seat layout.

Theater & Seat Management

Theaters contain multiple screens.

Each screen has a configurable seating arrangement.

Seats should be categorized (e.g., Silver, Gold, Platinum).

Search & Browse

Users can search for movies by name, language, genre, location.

Users can view available shows and theaters for a movie.

Booking & Payment

Users can select seats for a chosen show.

Real-time seat availability check to prevent double booking.

Multiple payment options (UPI, Credit/Debit Card, Wallet).

Booking confirmation after successful payment.

Notifications

Email/SMS confirmation for booking.

Booking details include movie name, theater, time, seat numbers, and price.

Cancellation & Refunds

Users should be able to cancel tickets before showtime.

Refund processing (full/partial) as per policy.

Q. Why SQL for User, Show, Booking, Payment, Theater? Why NoSQL for Movie?
A. SQL for user/booking/payment/theater/show because those domains require ACID guarantees, relational integrity, and complex joins (user → bookings, payment → booking). Booking/payment need strong consistency for money and seat allocation. Theater/show benefit from normalized schemas and transactional updates.
NoSQL for Movie because movie metadata (tags, cast, descriptions, assets) is semi-structured and read-heavy: key-value or document stores (Mongo/Cassandra) give high read throughput, flexible schema, and cheaper horizontal scaling for media-heavy content. Tradeoff: eventual consistency for some movie attributes is acceptable.

Q. How will you handle DB sharding/replication for large-scale traffic (millions of users)?
A. Strategy by domain:

Read replicas for read-heavy DBs (Show, Movie search results, User read APIs). Use asynchronous replication.

Horizontal sharding for write-heavy tables (Booking, User): shard by user_id or booking_id (hash-based) to evenly distribute load. For booking-specific hotspots (popular shows), consider composite sharding: primary shard by show_id, sub-shard by seat-range to reduce contention.

Geo-partitioning: place shards/replicas near regional traffic to reduce latency (followed by cross-region replication for DR).

Automation: provision via managed DB (Aurora, Cloud SQL) or Kubernetes operators; re-sharding via consistent hashing or a shard manager.

Monitoring & autoscaling: track CPU/IO/latency and add shards or increase instance sizes before degradation.

Tradeoff: sharding increases complexity (joins across shards) — use denormalization and CQRS patterns to minimize cross-shard transactions.

Q. How long is a seat locked before payment confirmation?
A. Default: 5 minutes lock TTL. Implementation: write lock with TTL in Redis (SET key EX 300 NX) and create a pending booking record in DB with expiration timestamp. If payment not completed by TTL, background job or Redis key expiry triggers seat release and booking cancelled. Can make TTL dynamic for VIPs or special events.

Q. You used Search Service but no Search Index DB (e.g., Elasticsearch). How will free-text search be handled efficiently?
A. Add Elasticsearch/OpenSearch as Search Index:

Keep ES updated via CDC → Kafka → indexer (Debezium or custom connector) to push Show/Movie/Theater changes.

Maintain separate search indices for movies, theaters, and locations with appropriate analyzers (tokenization, n-grams).

Use autocomplete indices for suggestions and geo-indexing for location-based queries.

For consistency, use near-real-time indexing (ms → sec) and fall back to primary DB for critical reads if index is stale.

Q. If Kafka goes down, do you have retry + DLQ (Dead Letter Queue) strategy?
A. Yes:

Use Kafka Connect/producer acks and retries; producers configured with retries and idempotence.

Consumers implement at-least-once processing with idempotency on handlers.

Failed messages after N retries go to a DLQ topic; have separate consumers to examine DLQ and perform compensating actions or manual review.

For downtime, use replication.factor > 1 and multiple brokers across AZs, and ensure Zookeeper/KRaft HA.

Have fallback: if Kafka unavailable, write events to Outbox in DB; a background process will publish when Kafka recovers (Outbox pattern).

Q. What if Payment Service succeeds but Booking Service fails → how do you maintain consistency? (Saga pattern, Outbox pattern).
A. Use Saga + Outbox:

Orchestrated Saga or Choreography: Booking starts, writes pending booking + outbox event booking:pending. Payment tries to charge with reference to booking_id. If payment succeeds, Payment publishes payment:confirmed. Booking consumes and moves to confirmed.

If Payment succeeds but Booking fails to confirm (consumer crash), outbox + idempotent consumers ensure eventual recovery: Payment publishes event; Booking reprocesses the event and marks confirmed.

If Booking cannot be completed (seat not available), Payment must be compensated (refund) via a refund/compensating saga step.

Outbox ensures events are not lost even if Kafka is down. Use idempotency keys on payment gateway calls to prevent duplicated charges.

Q. Booking Service will be heavily used during peak load (blockbuster releases). How will you scale horizontally?
A. Approaches:

Stateless booking API instances behind LB and autoscaling (scale based on CPU/requests/queue length).

Partition booking traffic by show (shard by show_id) so each instance set handles subset of shows — reduces contention.

Use rate limiting & queueing (token bucket / sliding window) to throttle spikes and provide graceful degradation.

Move read-heavy operations to caches (CDN/Redis) and serve precomputed seat maps.

Pre-warm caches and autoscaling groups prior to release windows (scheduled scaling).

Use asynchronous workflows: accept booking request into high-throughput queue, then workers pick up and process at scale.

Q. How do you prevent Redis becoming a single point of failure? (Replication, Cluster, Sentinel).
A. Use Redis Cluster with multiple master shards and replicas. Combined with Sentinel or managed Redis (AWS Elasticache Redis Cluster) for automatic failover. Additional measures:

Enable persistence (AOF/RDB) and periodic backups.

Put Redis in multiple AZs for HA (but ensure network locality considerations).

Implement client-side retry and fallback: if Redis unavailable, the Booking Service can attempt a pessimistic DB lock or short DB-based fallback to avoid total outage.

Monitor Redis latencies and set alerts.

Q. Supports async notifications, but do you plan multi-channel support (Email, SMS, Push, WhatsApp)?
A. Yes: Notification Service as a pluggable multi-channel system. Architecture: notification producer → Kafka topic → Notification Service (dispatcher) → channel adapters (Email provider, SMS gateway, Push service, WhatsApp API). Preferences per user stored in User DB. Channel selection resolved at dispatch time. Use templates and localization.

Q. Retry logic if notification fails?
A. Yes: implement retries with exponential backoff, limited attempts, then route to notification DLQ for manual inspection. Persist notification attempts in DB (audit log). For critical notifications (ticket booked), try alternate channel if primary fails (prefer SMS if email failed).

Q. Good for throttling, but how do you handle geo-distributed clients (India, US, Europe)?
A. Techniques:

Edge API Gateways / CDN for static assets and caching.

Regional clusters (deploy app + read replicas in each region) with regional API endpoints. Use geo-DNS routing to direct users to nearest region.

Cross-region replication for user data where needed and eventual consistency for non-critical data. For strongly consistent operations (payments/booking), route them to a central region or implement a cross-region transactional pattern with higher latency tolerance.

Use rate-limiting per-region and global quotas to protect backend services.

Q. Do you plan Edge API Gateways close to users?
A. Yes — use Cloud CDN/Edge (CloudFront, Cloudflare Workers, or regional API Gateway) to host API caching layers and pre-auth checks. Edge gateways reduce latency for auth and serve cached search responses. They also handle WAF, TLS termination, and geo-rate-limiting.

Q. 10M concurrent users, how does the system behave?
A. High-level capacity plan:

Stateless app layer horizontally autoscaled across regions; use connection pooling and short-lived connections.

Massive caching: CDN for static, Redis/Memcached for frequently accessed dynamic data (show, seat maps).

Sharded DBs: bookings & users partitioned to avoid hot spots.

Kafka clusters scaled with partitions to support event throughput.

Backpressure: Use rate-limiting and queuing to flatten spikes.

Observability: distributed tracing & metrics for bottleneck detection.

Expect tradeoffs: some operations may be async/ eventually consistent to maintain availability under massive load.

Q. What if Redis cluster crashes mid-booking?
A. Recovery & fallback:

Client detects Redis failure and switches to DB fallback (pessimistic DB lock or application-level seat reservation in transactional DB).

Use idempotent operations and the Outbox to ensure post-failure reconciliation: at recovery, reconcile pending bookings using DB outbox and release/confirm seats accordingly.

Run health-check and automated failover; have monitoring & runbooks to repair cluster quickly.

Q. Scaling vs tradeoff?
A. Key tradeoffs to state in interview:

Consistency vs availability: Strong consistency (transactions, locks) limits throughput; eventual consistency allows higher scale. Choose strong consistency for money/booking, eventual for search or recommendations.

Latency vs durability: synchronous writes are durable but slower; async writes faster but risk message loss (mitigated with outbox).

Complexity vs simplicity: patterns like Saga/Outbox add complexity but are needed for distributed transactions. Simpler approaches (single DB) scale poorly.

Cost vs performance: more replicas/shards/regions increase availability and latency improvement but cost more. Optimize hotspots first.