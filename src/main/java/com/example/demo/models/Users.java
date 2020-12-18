package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
    @Id
    private String id;

    private String name;
    private String lastname;
    private String password;
    private String email;
    private Integer role_user;
    /* note for role user
    admin : 1
    user : 0
    */

    public Users() {

    }

    public Users(String name, String lastname, String password, String email, Integer role_user) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.role_user = role_user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole_user() {
        return role_user;
    }

    public void setRole_user(Integer role_user) {
        this.role_user = role_user;
    }

    @Override
    public String toString() {
        return "User [" + name + " ]";
    }
}
