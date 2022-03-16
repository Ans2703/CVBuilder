package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.*;
import java.util.stream.Collectors;

public class CV {
  public Integer id;
  public String title;
  public ArrayList<String> skills;
  public String about;
  public String education;
  public String experience;
  public Integer designNumber;
  public Integer user_id;

  public CV(Integer id, String title, ArrayList<String> skills, String experience, String about, String education, Integer designNumber,Integer user_id) {
    this.id           = id;
    this.title        = title;
    this.about        = about;
    this.experience   = experience;
    this.education    = education;
    this.designNumber = designNumber;
    this.user_id = user_id;

    this.skills = skills;
  }

  public Integer getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getAbout() {
    return this.about;
  }
  public String getAboutFormatted() {
    return this.about;
  }

  public String getExperience() {
    return this.experience;
  }
  public String getExperienceFormatted() {
    return this.experience;
  }

  public String getEducation() {
    return this.education;
  }
  public String getEducationFormatted() {
    return this.education;
  }

  public ArrayList<String> getSkills() {
    return this.skills;
  }

  public Candidate getCandidate() {
    return (Candidate)User.getById(this.user_id);
  }

  public ArrayList<Job> getJobsAppliedTo() {
    return JobCV.getAllByUser(this.user_id).stream()
            .filter(job_cv -> job_cv.cv.id.equals(this.id))
            .map(job_cv -> job_cv.job)
            .collect(Collectors.toCollection(ArrayList::new));
  }

  public static CV getById(Integer id) {
    CV cv = null;
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "SELECT * FROM cvs WHERE id = %d";
      String q = String.format(t, id);
      ResultSet rs = statement.executeQuery(q);

      // rs.next();
      cv = new CV(
        id,
        rs.getString("title"),
        CV.parseSkills(rs.getString("skills")),
        rs.getString("experience"),
        rs.getString("about"),
        rs.getString("education"),
        1,
        rs.getInt("user_id")
      );
      connection.close();
    } catch(SQLException e) {
      App.log("CV::getById " + e.toString());
      e.printStackTrace();
    }
    return cv;
  }

  public static ArrayList<CV> getAll() {
    ArrayList<CV> cvs = new ArrayList<CV>();
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();
      String q ="select * from cvs";
      ResultSet resultSet = statement.executeQuery(q);

      while (resultSet.next()) {
        CV cv = new CV(
          resultSet.getInt("id"),
          resultSet.getString("title"),
          CV.parseSkills(resultSet.getString("skills")),
          resultSet.getString("experience"),
          resultSet.getString("about"),
          resultSet.getString("education"),
          1,
          resultSet.getInt("user_id")
        );
        cvs.add(cv);
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

  public CV save() {
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String q = null;
      if (this.id == null || this.id.equals(-1)) {
        String t = "INSERT INTO cvs (title, skills, experience, education, about, user_id) VALUES('%s', '%s', '%s','%s', '%s', %d)";
        q = String.format(t, this.title, String.join(",", this.skills), this.experience, this.education, this.about, this.user_id);
      } else {
        String t = "UPDATE cvs SET title='%s', skills='%s', experience='%s', education='%s', about='%s', user_id=%d WHERE id=%d";
        q = String.format(t, this.title, String.join(",", this.skills), this.experience, this.education, this.about, this.user_id, this.id);
      }

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

  public static void deleteById(Integer id) {
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "DELETE FROM cvs WHERE id=%d";
      String q = String.format(t, id);

      int affectedRows = statement.executeUpdate(q);

      // Also delete entries in jobs_cvs
      if (affectedRows == 1) {
        t = "DELETE FROM jobs_cvs WHERE cv_id=%d";
        q = String.format(t, id);

        affectedRows = statement.executeUpdate(q);
      }

      connection.close();
    } catch(SQLException e) {
      App.log("CV::deleteById " + e.toString());
    }
  }

  private static ArrayList<String> parseSkills(String skills) {
    ArrayList<String> arr = new ArrayList<String>();
    if (skills != null)
      arr = new ArrayList<String>(Arrays.asList(skills.split(",")));
    
    return arr;
  }
}
