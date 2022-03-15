package controllers;

import models.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CV_Controller extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/cvs/CreateNewCV.jsp");
    HttpSession session = request.getSession();

    request.setAttribute("candidate", ((Candidate)App.getCurrentUser(session)));
    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    if (uri.contains("create-new-cv")) {
      CV_Controller.postCreateCV(request);
    }

    response.sendRedirect("/dashboard");
  }

  private static void postCreateCV(HttpServletRequest request) {
    String title      = request.getParameter("title");
    String[] skills   = request.getParameterValues("skills");
    String experience = request.getParameter("experience");
    String about      = request.getParameter("about");
    String education  = request.getParameter("education");
    HttpSession session = request.getSession();

    User currentUser = App.getCurrentUser(session);

    models.CV cv = new models.CV(-1, title, new ArrayList<String>(Arrays.asList(skills)), experience, about, education, 1, currentUser.id);
    cv.save();
  }
}