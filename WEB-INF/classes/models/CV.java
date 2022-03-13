package models;

import java.util.ArrayList;
import java.sql.*;
public class CV {
  public Integer id;
  public String title;
  public ArrayList<Skill> skills;
  public String about;
  public String education;
  public String experience;
  public Integer designNumber;
  public Integer user_id;

  

  public CV(Integer id, String title, ArrayList<Skill> skills, String experience, String about, String education, Integer designNumber,Integer user_id) {
    this.id           = id;
    this.title        = title;
    this.skills       = skills;
    this.about        = about;
    this.experience   = experience;
    this.education    = education;
    this.designNumber = designNumber;
    this.user_id = user_id;
  }
public static ArrayList getAll(){
  ArrayList<CV> cvs = new ArrayList<CV>();
  Connection connection = App.getDBConnection();

  try {

Statement statement=connection.createStatement();
String q ="select * from cvs";
ResultSet resultSet = statement.executeQuery(q);
while(resultSet.next()){

CV getAll = new CV(
resultSet.getInt("id"),
resultSet.getString("title"),
null,
resultSet.getString("education"),
resultSet.getString("experience"),
resultSet.getString("about"),
null,
resultSet.getInt("user_id")

);
cvs.add(getAll);
}
connection.close();
}
 catch (Exception e) {
e.printStackTrace();
 }

 return cvs;
}
  public static ArrayList<CV> getAllByUser(Integer id) {
    ArrayList<CV> cvs = CV.getAll();
    cvs.removeIf(cv -> !cv.user_id.equals(id));
    return cvs;
  }

  public String getTitle() {
    return this.title;
  }

  public CV save() {
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "INSERT INTO CVs (title, experience, education,about, user_id) VALUES('%s', '%s','%s', '%s', %d)";
      String q = String.format(t, this.title, this.experience,this.education, this.about, this.user_id);
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
}
