package com.appsdeveloperblog.tutorials.junit.io;

import javax.persistence.*;
import java.io.Serializable;

// This class contains the table and field for database.

/* How work Spring Framework:
1. Scan all classes and depending of the  annotation will create an object as a bean into
   Spring application context.
2. When find class with @Entity,it will treat as a persistent entity.
 */
@Entity // marks this class as persistency entity using Spring data JPA Framework
@Table(name="users") // if not exist table "users", Spring framework will create it in the database.
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5313493413859894403L;

    @Id
    @GeneratedValue // autogenerate id value, start with value 1
    private long id;

    @Column(nullable=false, unique=true) // unique value (no duplicated)
    private String userId;

    @Column(nullable=false, length=50) // max 50 characters
    private String firstName;

    @Column(nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=120)
    private String email;

    @Column(nullable=false)
    private String encryptedPassword;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
