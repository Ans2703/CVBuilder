package controllers;

import models.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CV_Controller extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/cvs/CreateNewCV.jsp");
    HttpSession session = request.getSession();

    String id = request.getParameter("id");
    CV cv = null;
    if (id != null) {
      cv = CV.getById(Integer.parseInt(id));
    }
    User currentUser = App.getCurrentUser(session);

    if (uri.contains("edit-cv")) {
      request.setAttribute("cv", cv);
      view = request.getRequestDispatcher("/WEB-INF/views/cvs/EditCV.jsp");
    }

    request.setAttribute("candidate", (Candidate)currentUser);
    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    if (uri.contains("create-new-cv") || uri.contains("edit-cv")) {
      CV_Controller.postCreateCV(request);
    }

    response.sendRedirect("/dashboard");
  }

  private static void postCreateCV(HttpServletRequest request) {
    String _id         = request.getParameter("id");
    String title      = request.getParameter("title");
    String[] skills   = request.getParameterValues("skills");
    String experience = request.getParameter("experience");
    String about      = request.getParameter("about");
    String education  = request.getParameter("education");
    HttpSession session = request.getSession();

    User currentUser = App.getCurrentUser(session);

    Integer id = -1;
    if (_id != null) {
      id = Integer.parseInt(_id);
    }

    models.CV cv = new models.CV(id, title, new ArrayList<String>(Arrays.asList(skills)), experience, about, education, 1, currentUser.id);
    cv.save();
  }
}