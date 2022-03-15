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
    this.resourceId = id;

    this.id          = id;
    this.name        = name;
    this.description = description;
    this.userId      = user.id;

    this.jobs = Job.getAllByUser(user.id);
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public ArrayList<JobCV> getRecievedCVs() {
    return JobCV.getAllByUser(this.userId);
  }

  public User save() {
    Connection connection = App.getDBConnection();

    try {
      connection.setAutoCommit(false);
      Statement statement = connection.createStatement();

      String q = null;
      if (this.id == null || this.id == -1) {
        String t = "INSERT INTO companies (name, description, user_id) VALUES('%s', '%s', %d)";
        q = String.format(t, this.name, this.description, this.userId);
      } else {
        String t = "UPDATE companies SET name='%s', description='%s' WHERE id=%d";
        q = String.format(t, this.name, this.description, this.id);
      }

      int affectedRows = statement.executeUpdate(q);
      if (affectedRows == 1) {
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        App.log("com bef=" + this.resourceId);
        this.resourceId = rs.getInt(1);
        App.log("com after=" + this.resourceId);
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
      App.log("Company::save " + e.toString());
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
