package models;

import java.util.ArrayList;
import java.io.*;
import java.sql.*;

public class Candidate extends User {
  public Integer id;
  public String firstName;
  public String lastName;

  public Integer userId;

  public ArrayList<CV> cvs;

  public Candidate() {
    this.resourceType = "candidate";
  }

  public Candidate(Integer id, String firstName, String lastName, User user) {
    super(user.id, user.email, user.password, user.phone, user.address);
    this.resourceType = "candidate";
    this.resourceId = id;

    this.id        = id;
    this.firstName = firstName;
    this.lastName  = lastName;
    this.userId    = user.id;

    this.cvs = CV.getAllByUser(user.id);
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }

  public ArrayList<JobCV> getJobsAppliedTo() {
    return JobCV.getAllByUser(this.userId);
  }

  public User save() {
    Connection connection = App.getDBConnection();

    try {
      connection.setAutoCommit(false);
      Statement statement = connection.createStatement();

      String q = null;
      if (this.id == null || this.id == -1) {
        String t = "INSERT INTO candidates (first_name, last_name, user_id) VALUES('%s', '%s', %d)";
        q = String.format(t, this.firstName, this.lastName, this.userId);
      } else {
        String t = "UPDATE candidates first_name='%s', last_name='%s' SET WHERE id=%d";
        q = String.format(t, this.firstName, this.lastName, this.id);
      }

      int affectedRows = statement.executeUpdate(q);

      if (affectedRows == 1) {
        ResultSet rs = statement.getGeneratedKeys();
        rs.next();
        App.log("c before_update = " + this.resourceId);
        this.resourceId = rs.getInt(1);
        App.log("c after_update = " + this.resourceId);
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

  public static Candidate getByUser(User user) {
    Candidate candidate = null;
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "SELECT * FROM candidates WHERE id = %d";
      String q = String.format(t, user.resourceId);
      ResultSet rs = statement.executeQuery(q);

      while (rs.next()) {
        candidate = new Candidate(
          user.resourceId,
          rs.getString("first_name"),
          rs.getString("last_name"),
          user
        );
      }
      connection.close();
    } catch(SQLException e) {
      App.log(e.toString());
    }

    return candidate;
  }
}
