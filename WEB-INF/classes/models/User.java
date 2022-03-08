package models;

import java.util.ArrayList;
import java.sql.*;

public class User {
  public Integer id;
  public String email;
  public String phone;
  public String address;
  public String password;

  public String resourceType;
  public Integer resourceId;

  public ArrayList<CV> cvs;

  public User() {
  }

  public User(Integer id, String email, String password, String phone, String address) {
    this.id       = id;
    this.email    = email;
    this.password = password;
    this.phone    = phone;
    this.address  = address;

    this.cvs = new ArrayList<CV>();
  }

  public static ArrayList<User> getAllUsers() {
    ArrayList<User> users = new ArrayList<User>();

    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String q = "SELECT * FROM users";
      ResultSet rs = statement.executeQuery(q);

      while (rs.next()) {
        User u = new User(
          rs.getInt("id"),
          rs.getString("email"),
          rs.getString("password"),
          rs.getString("phone"),
          rs.getString("address")
        );
        users.add(u);
      }
    } catch(SQLException e) {
      App.log(e.toString());
    }

    return users;
  }
  
  public User save() {
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "INSERT INTO users (email, password, phone, address, resource_type, resource_id) VALUES('%s', '%s', '%s', '%s', '%s', %d)";
      String q = String.format(t, this.email, this.password, this.phone, this.address, this.resourceType, this.resourceId);
      int affectedRows = statement.executeUpdate(q);

      if (affectedRows == 1) {
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        this.id = rs.getInt(1);
      }
    } catch(SQLException e) {
      App.log(e.toString());
      return null;
    }

    return this;
  }

  public static User getById(Integer id) {
    User user = null;
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "SELECT * FROM users WHERE id = %d";
      String q = String.format(t, id);
      ResultSet rs = statement.executeQuery(q);

      while (rs.next()) {
        user = new User(
          id,
          rs.getString("email"),
          rs.getString("password"),
          rs.getString("phone"),
          rs.getString("address")
        );
        user.resourceType = rs.getString("resource_type");
        user.resourceId = rs.getInt("resource_id");
      }
    } catch(SQLException e) {
      App.log(e.toString());
    }

    return user;
  }

}