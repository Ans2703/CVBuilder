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

  public Candidate(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.resourceType = "candidate";
  }

  public User save() {
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "INSERT INTO candidates (first_name, last_name, user_id) VALUES('%s', '%s', %d)";
      String q = String.format(t, this.firstName, this.lastName, this.userId);
      statement.executeUpdate(q);
    } catch(SQLException e) {
      App.log(e.toString());
    }

    return this;
  }
}
