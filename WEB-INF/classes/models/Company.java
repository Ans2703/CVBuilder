package models;

import java.util.ArrayList;
import java.sql.*;

public class Company extends User {
  public String name;
  public String description;

  public Integer userId;

  public ArrayList<Job> jobs;

  public Company() {
    this.resourceType = "company";
  }

  public Company(String name, String description, User user) {
    super(user.id, user.email, user.password, user.phone, user.address);
    this.resourceType = "company";

    this.name = name;
    this.description = description;
    this.userId = user.id;
  }

  public User save() {
    Connection connection = App.getDBConnection();

    try {
      connection.setAutoCommit(false);
      Statement statement = connection.createStatement();

      String t = "INSERT INTO companies (name, description, user_id) VALUES('%s', '%s', %d)";
      String q = String.format(t, this.name, this.description, this.userId);
      int affectedRows = statement.executeUpdate(q);

      if (affectedRows == 1) {
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        this.resourceId = rs.getInt(1);
      }

      User user = super.save();
      if (user == null) {
        connection.rollback();
        return null;
      }
      this.userId = user.id; // not really needed
      connection.commit();
      // TODO: turn on auto commit?
    } catch(SQLException e) {
      App.log(e.toString());
      return null;
    }

    // connection.close();
    return this;
  }
}
