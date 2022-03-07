package models;

import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.*;
import java.io.IOException;

public class App {
  public static void setCurrentUser(HttpSession session, Integer userId) {
    session.setAttribute("current_user_id", userId);
  }

  public static User getCurrentUser(HttpSession session) {
    User u = User.getById((Integer)session.getAttribute("current_user_id"));
    return u;
  }

  public static Connection getDBConnection() {
    try {
      // Class.forName("com.mysql.jdbc.Driver");
      Class.forName("org.sqlite.JDBC");
      Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");
      return conn;
    } catch(Exception e) {
      App.log(e.toString());
      return null;
    }
  }

  // for debugging (check logs/catalina.out)
  public static void log(String msg) {
    System.out.println(msg);
  }
}
