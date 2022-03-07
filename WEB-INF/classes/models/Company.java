package models;

import java.util.ArrayList;

public class Company extends User {
  public String name;
  public String description;
  public ArrayList<Job> jobs;

  public Company() {
    this.resourceType = "company";
  }

  public Company(String name, String description) {
    this.name = name;
    this.description = description;
    this.resourceType = "company";
  }
}
