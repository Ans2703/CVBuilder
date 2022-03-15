package models;

import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.*;
import java.io.IOException;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
  public static void setCurrentUser(HttpSession session, Integer userId) {
    session.setAttribute("current_user_id", userId);
  }

  public static User getCurrentUser(HttpSession session) {
    return User.getById((Integer)session.getAttribute("current_user_id"));
  }

  private static Connection connection = null;
  public static Connection getDBConnection() {
    try {
      // Class.forName("com.mysql.jdbc.Driver");
      Class.forName("org.sqlite.JDBC");
      App.connection = DriverManager.getConnection("jdbc:sqlite:data.db");
      return connection;
    } catch(Exception e) {
      App.log("App::getDBConnection " + e.toString());
      return null;
    }
  }
  public static Connection getExistingDBConnection() {
    if (App.connection != null) return App.connection;
    else return App.getDBConnection();
  }

  public static String getMappedError(String error) {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("invalid-password", "Username or password does not match!");
    map.put("company-disallowed", "Company accounts cannot apply to a job!");
    map.put("invalid-job", "The job does not exist or you don't have required permissions!");
    map.put("invalid-cv", "The CV does not exist or you don't have required permissions!");
    map.put("login-required", "You need to login first!");

    return map.get(error);
  }

  public static String[] getSkills() {
    String[] skills = {
      "HTML", "CSS", "JavaScript", "Java", "C", "C++", "C#", "Objective C", "Swift", "Kotlin", "Lua",
      "Rust", "Ruby", "Ruby on Rails", ".NET", "ASP.NET", "Python", "Django", "ReactJS", "React Native"
    };
    return skills;
  }

  public static String formatDate(LocalDateTime datetime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return datetime.format(formatter);
  }

  // for debugging (check logs/catalina.out)
  public static void log(String msg) {
    System.out.println(msg);
  }
}
