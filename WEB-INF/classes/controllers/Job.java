package controllers;

import models.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class Job extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/CreateNewJob.jsp");

    if (uri.contains("delete-job")) {
      view = request.getRequestDispatcher("/WEB-INF/views/DeleteJob.jsp");
    }

    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    if (uri.contains("create-new-job")) {
      Job.postCreateJob(request);
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
  }

}
