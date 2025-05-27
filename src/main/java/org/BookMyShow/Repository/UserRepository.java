package org.BookMyShow.Repository;

import org.BookMyShow.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
