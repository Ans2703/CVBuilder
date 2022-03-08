package models;

import java.util.ArrayList;
import java.io.*;
import java.sql.*;

public class Candidate extends User {
  public String firstName;
  public String lastName;

  public Integer userId;

  public ArrayList<CV> cvs;

  public Candidate() {
    this.resourceType = "candidate";
  }

  public Candidate(String firstName, String lastName, User user) {
    super(user.id, user.email, user.password, user.phone, user.address);
    this.resourceType = "candidate";

    this.firstName = firstName;
    this.lastName = lastName;
    this.userId = user.id;
  }

  public User save() {
    Connection connection = App.getDBConnection();

    try {
      connection.setAutoCommit(false);
      Statement statement = connection.createStatement();

      String t = "INSERT INTO candidates (first_name, last_name, user_id) VALUES('%s', '%s', %d)";
      String q = String.format(t, this.firstName, this.lastName, this.userId);
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
