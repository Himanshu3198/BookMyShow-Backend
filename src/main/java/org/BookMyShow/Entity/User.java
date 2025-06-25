package org.BookMyShow.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email_id")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_password")
    private String password;

    @Column(name = "wallet_balance")
    private Double walletBalance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWalletBalance(Double balance){
        this.walletBalance = balance;
    }
    public Double getWalletBalance(){
        return walletBalance;
    }
}
