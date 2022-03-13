package models;

import java.sql.*;
import java.util.ArrayList;

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

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public Double getSalary() {
    return this.salary;
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

      String t = "INSERT INTO jobs (user_id, title, description, salary, salary_currency, country, location) VALUES(%d, '%s','%s', %d, '%s', '%s', '%s')";
      String q = String.format(t, this.userId, this.title, this.description, this.salary, this.salaryCurrency, this.country, this.location);
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

  public static ArrayList<Job> getAll() {
    ArrayList<Job> jobs = new ArrayList<Job>();
    jobs.add(new Job(-1, 1, "Software engineer", "need a html programmer", 2000.00, "USD", "US", "New york"));
    jobs.add(new Job(-1, 1, "Locomotive engineer", "mechanic job", 200.00, "USD", "US", "New york"));
    jobs.add(new Job(-1, 1, "Video engineer", "record and edit some videos", 5000.00, "USD", "US", "Remote"));
    return jobs;
  }
}
