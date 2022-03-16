package controllers;

import models.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class Job extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/jobs/CreateNewJob.jsp");
    HttpSession session = request.getSession();

    String id = request.getParameter("id");
    models.Job j = null;
    if (id != null) {
      j = models.Job.getById(Integer.parseInt(id));
    }
    User currentUser = App.getCurrentUser(session);

    if (uri.contains("edit-job")) {
      if (j == null || !j.userId.equals(currentUser.id)) {
        response.sendRedirect("/dashboard?error=invalid-job");
        return;
      }

      request.setAttribute("job", j);
      view = request.getRequestDispatcher("/WEB-INF/views/jobs/EditJob.jsp");
    } else if (uri.contains("delete-job")) {
      if (j == null || !j.userId.equals(currentUser.id)) {
        response.sendRedirect("/dashboard?error=invalid-job");
        return;
      }

      request.setAttribute("job", j);
      view = request.getRequestDispatcher("/WEB-INF/views/jobs/DeleteJob.jsp");
    } else if (uri.contains("apply")) {
      if (currentUser == null) {
        response.sendRedirect("/?error=login-required");
        return;
      }
      if (j == null) {
        response.sendRedirect("/?error=invalid-job");
        return;
      }

      if (currentUser.resourceType.equals("company")) {
        response.sendRedirect("/?error=company-disallowed");
        return;
      }

      request.setAttribute("job", j);
      request.setAttribute("cvs", ((Candidate)currentUser).cvs);
      view = request.getRequestDispatcher("/WEB-INF/views/jobs/ApplyToJob.jsp");
    }

    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    if (uri.contains("create-new-job")) {
      Job.postCreateJob(request);
    } else if (uri.contains("apply")) {
      Job.postApplyJob(request);
    } else if (uri.contains("delete-job")) {
      String _id = request.getParameter("id");
      Integer id = Integer.parseInt(_id);
      models.Job.deleteById(id);
    }

    response.sendRedirect("/dashboard");
  }

  private static void postCreateJob(HttpServletRequest request) {
    String title          = request.getParameter("title");
    String description    = request.getParameter("description");
    Double salary         = Double.parseDouble(request.getParameter("salary"));
    String salaryCurrency = request.getParameter("salary_currency");
    String country        = request.getParameter("country");
    String location       = request.getParameter("location");
    HttpSession session = request.getSession();

    User currentUser = App.getCurrentUser(session);

    models.Job job = new models.Job(-1, currentUser.id, title, description, salary, salaryCurrency, country, location);
    job.save();
  }

  private static void postApplyJob(HttpServletRequest request) {
    String jobId = request.getParameter("job_id");
    String cvId = request.getParameter("cv_id");
    Integer jid = Integer.parseInt(jobId);
    Integer cid = Integer.parseInt(cvId);

    JobCV jobCV = new JobCV(models.Job.getById(jid), CV.getById(cid), java.time.LocalDateTime.now());
    jobCV.save();
  }
}
