package models;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

  public Job getJob() {
    return this.job;
  }

  public CV getCv() {
    return this.cv;
  }

  public LocalDateTime getAppliedAt() {
    return this.appliedAt;
  }

  public String getAppliedAtFormatted() {
    return App.formatDate(this.appliedAt);
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

  public static ArrayList<JobCV> getAllByUser(Integer userId) {
    User user = User.getById(userId);

    String q = null;
    if (user.resourceType.equals("candidate")) {
      ArrayList<Integer> _cvIds = ((Candidate)user).cvs.stream()
                                  .map(cv -> cv.id)
                                  .collect(Collectors.toCollection(ArrayList::new));
      String cvIds = _cvIds.stream().map(Object::toString).collect(Collectors.joining(", "));
      String t = "SELECT * FROM jobs_cvs WHERE cv_id IN (%s)";
      q = String.format(t, cvIds);
    } else if (user.resourceType.equals("company")) {
      ArrayList<Integer> _jobIds = ((Company)user).jobs.stream()
                                  .map(job -> job.id)
                                  .collect(Collectors.toCollection(ArrayList::new));
      String jobIds = _jobIds.stream().map(Object::toString).collect(Collectors.joining(", "));
      String t = "SELECT * FROM jobs_cvs WHERE job_id IN (%s)";
      q = String.format(t, jobIds);
    }

    ArrayList<JobCV> job_cvs = new ArrayList<JobCV>();
    Connection connection = App.getDBConnection();

    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(q);

      while (rs.next()) {
        Integer cvId = rs.getInt("cv_id");
        Integer jobId = rs.getInt("job_id");
        CV cv = !user.resourceType.equals("candidate") ? CV.getById(cvId) : ((Candidate)user).cvs.stream().filter(_cv -> _cv.id.equals(cvId)).findAny().orElse(null);
        Job job = !user.resourceType.equals("company") ? Job.getById(jobId) : ((Company)user).jobs.stream().filter(_job -> _job.id.equals(jobId)).findAny().orElse(null);
        
        JobCV job_cv = new JobCV(
          job,
          cv,
          LocalDateTime.parse(rs.getString("applied_at"))
        );
        job_cvs.add(job_cv);
      }
      connection.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return job_cvs;
  }
}
