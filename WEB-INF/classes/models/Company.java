package models;

import java.util.ArrayList;

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
}
