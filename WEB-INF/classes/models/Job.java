package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Job {
  public Integer id;
  public Integer userId;
  public String title;
  public String description;
  public Double salary;
  public String salaryCurrency;
  public String country;
  public String location;

  public Job() {
  }

  public Job(Integer id, Integer userId, String title, String description, Double salary, String salaryCurrency, String country, String location) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.description = description;
    this.salary = salary;
    this.salaryCurrency = salaryCurrency;
    this.country = country;
    this.location = location;
  }

  public Integer getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }
  public String getDescriptionFormatted() {
    return this.description.replace("\n", "</br>");
  }

  public Double getSalary() {
    return this.salary;
  }
  public String getSalaryFormatted() {
    return String.format("%,.2f", this.salary);
  }

  public String getSalaryCurrency() {
    return this.salaryCurrency;
  }

  public String getCountry() {
    return this.country;
  }

  public String getLocation() {
    return this.location;
  }

  public Company getCompany() {
    return (Company)User.getById(this.userId);
  }

  public Job save() {
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "INSERT INTO jobs (user_id, title, description, salary, salary_currency, country, location) VALUES(%d, '%s','%s', %f, '%s', '%s', '%s')";
      String q = String.format(t, this.userId, this.title, this.description, this.salary, this.salaryCurrency, this.country, this.location);
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

  public static Job getById(Integer id) {
    Job job = null;
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "SELECT * FROM jobs WHERE id = %d";
      String q = String.format(t, id);
      ResultSet rs = statement.executeQuery(q);

      // rs.next();
      job = new Job(
        id,
        rs.getInt("user_id"),
        rs.getString("title"),
        rs.getString("description"),
        rs.getDouble("salary"),
        rs.getString("salary_currency"),
        rs.getString("country"),
        rs.getString("location")
      );
      connection.close();
    } catch(SQLException e) {
      App.log("Job::getById " + e.toString());
      e.printStackTrace();
    }
    return job;
  }

  public static ArrayList<Job> getAll() {
    ArrayList<Job> jobs = new ArrayList<Job>();
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();
      String q ="select * from jobs";
      ResultSet rs = statement.executeQuery(q);

      while (rs.next()) {
        Job job = new Job(
          rs.getInt("id"),
          rs.getInt("user_id"),
          rs.getString("title"),
          rs.getString("description"),
          rs.getDouble("salary"),
          rs.getString("salary_currency"),
          rs.getString("country"),
          rs.getString("location")
        );
        jobs.add(job);
      }
      connection.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return jobs;
  }

  public static ArrayList<Job> getAllByUser(Integer id) {
    ArrayList<Job> jobs = Job.getAll();
    jobs.removeIf(j -> !j.userId.equals(id));
    return jobs;
  }
}
