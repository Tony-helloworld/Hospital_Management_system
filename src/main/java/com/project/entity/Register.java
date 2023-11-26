package com.project.entity;

import javax.persistence.Id;

public class Register {
    private String id;	//foreign key, consists eid and pid
    private String role;
    @Id
    private String username;	//primary key
    private String password;

    Register(){}

    public Register(String username, String password)
    {
        super();
        this.id = username;
        this.role="patient";
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }
    public String getRole() {
        return role;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Register [id=" + id + ", role=" + role + ", username=" + username + ", password=" + password + "]";
    }

}
