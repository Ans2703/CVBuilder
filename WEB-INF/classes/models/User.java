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

  // public ArrayList<CV> cvs;

  public User() {
  }

  public User(Integer id, String email, String password, String phone, String address) {
    this.id       = id;
    this.email    = email;
    this.password = password;
    this.phone    = phone;
    this.address  = address;

    // this.cvs = new ArrayList<CV>();
  }

  public String getEmail() {
    return this.email;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getAddress() {
    return this.address;
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
      connection.close();
    } catch(SQLException e) {
      App.log("User::getAllUsers " + e.toString());
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
      connection.close();
    } catch(SQLException e) {
      App.log(e.toString());
      return null;
    }
    return this;
  }

  public Candidate getCandidate() {
    if (!this.resourceType.equals("candidate")) {
      return null;
    }
    return Candidate.getByUser(this);
  }

  public static User getById(Integer id) {
    User user = null;
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "SELECT * FROM users WHERE id = %d";
      String q = String.format(t, id);
      ResultSet rs = statement.executeQuery(q);

      // rs.next();
        user = new User(
          id,
          rs.getString("email"),
          rs.getString("password"),
          rs.getString("phone"),
          rs.getString("address")
        );
        user.resourceType = rs.getString("resource_type");
        user.resourceId = rs.getInt("resource_id");
        connection.close();

        if (user.resourceType.equals("candidate")) {
          user = Candidate.getByUser(user);
        } else if (user.resourceType.equals("company")) {
          user = Company.getByUser(user);
        }
      
    } catch(SQLException e) {
      App.log("User::getById " + e.toString());
      e.printStackTrace();
    }
    return user;
  }

  public static User findByEmail(String email) {
    User user = null;
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "SELECT * FROM users WHERE email = '%s'";
      String q = String.format(t, email);
      ResultSet rs = statement.executeQuery(q);

      while (rs.next()) {
        user = new User(
          rs.getInt("id"),
          rs.getString("email"),
          rs.getString("password"),
          rs.getString("phone"),
          rs.getString("address")
        );
        user.resourceType = rs.getString("resource_type");
        user.resourceId = rs.getInt("resource_id");
      }
      connection.close();
    } catch(SQLException e) {
      App.log("User::findByEmail " + e.toString());
    }

    return user;
  }
}