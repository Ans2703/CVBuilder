package models;

import java.util.ArrayList;
import java.sql.*;

public class Company extends User {
  public Integer id;
  public String name;
  public String description;

  public Integer userId;

  public ArrayList<Job> jobs;

  public Company() {
    this.resourceType = "company";
  }

  public Company(Integer id, String name, String description, User user) {
    super(user.id, user.email, user.password, user.phone, user.address);
    this.resourceType = "company";

    this.id          = id;
    this.name        = name;
    this.description = description;
    this.userId      = user.id;

    this.jobs = Job.getAllByUser(user.id);
  }

  public String getName() {
    return this.name;
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
      connection.close();

    } catch(SQLException e) {
      App.log(e.toString());
      return null;
    }

    return this;
  }

  public static Company getByUser(User user) {
    Company company = null;
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "SELECT * FROM companies WHERE id = %d";
      String q = String.format(t, user.resourceId);
      ResultSet rs = statement.executeQuery(q);

      while (rs.next()) {
        company = new Company(
          user.resourceId,
          rs.getString("name"),
          rs.getString("description"),
          user
        );
      }
      connection.close();
    } catch(SQLException e) {
      App.log(e.toString());
    }

    return company;
  }
}
