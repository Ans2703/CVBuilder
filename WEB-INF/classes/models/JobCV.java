package models;

import java.sql.*;
import java.time.LocalDateTime;

public class JobCV {
  public Job job;
  public CV cv;
  public LocalDateTime appliedAt;

  public JobCV() {
  }

  public JobCV(Job job, CV cv, LocalDateTime appliedAt) {
    this.job = job;
    this.cv = cv;
    this.appliedAt = appliedAt;
  }

  public JobCV save() {
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();

      String t = "INSERT INTO jobs_cvs (job_id, cv_id, applied_at) VALUES(%d, %d, '%s')";
      String q = String.format(t, this.job.id, this.cv.id, this.appliedAt);
      int affectedRows = statement.executeUpdate(q);

      if (affectedRows == 1) {
        // inserted successfully
      }
      connection.close();
    } catch(SQLException e) {
      App.log(e.toString());
      return null;
    }

    return this;
  }
}
