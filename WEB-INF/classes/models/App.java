package models;

import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.*;
import java.io.IOException;
import java.util.HashMap;

public class App {
  public static void setCurrentUser(HttpSession session, Integer userId) {
    session.setAttribute("current_user_id", userId);
  }

  public static User getCurrentUser(HttpSession session) {
    return User.getById((Integer)session.getAttribute("current_user_id"));
  }

  private static Connection connection = null;
  public static Connection getDBConnection() {
    // if (App.connection != null) return App.connection;

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

  public static String getMappedError(String error) {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("invalid-password", "Username or password does not match!");
    map.put("company-disallowed", "Company accounts cannot apply to a job!");

    return map.get(error);
  }

  // for debugging (check logs/catalina.out)
  public static void log(String msg) {
    System.out.println(msg);
  }
}
