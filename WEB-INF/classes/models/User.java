package models;

import java.util.ArrayList;
import java.sql.*;

public class User {
  public Integer id;
  public String email;
  public String phone;
  public String address;

  public String resourceType;
  public Integer resourceId;

  public ArrayList<CV> cvs;

  public User() {
  }

  public User(String email, String phone, String address) {
    this.email = email;
    this.phone = phone;
    this.address = address;

    this.cvs = new ArrayList<CV>();
  }

  // Fetch from DB
  public static User[] getAllUsers() {
    User users[] = {
      new User("a1@gmail.com", "1231231", "123 Hemingway street")
    };

    users[0].cvs.add(new CV("Software dev"));

    return users;
  }
  
  public User save() {
    Connection connection = App.getDBConnection();
    return new User();
  }

  public static User getById(Integer id) {
    // App.
    return new User();
  }

}