package models;

public class User {
  public Integer id;
  public String email;
  public String phone;
  public String address;

  public String resource_type;
  public Integer resource_id;

  public User() {
  }

  public User(String email, String phone, String address) {
    this.email = email;
    this.phone = phone;
    this.address = address;
  }
}