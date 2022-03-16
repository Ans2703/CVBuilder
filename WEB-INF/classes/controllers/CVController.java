package controllers;

import models.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.URLEncoder;

public class CVController extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/cvs/CreateNewCV.jsp");
    HttpSession session = request.getSession();
    User currentUser = App.getCurrentUser(session);

    String id = request.getParameter("id");
    CV cv = null;
    if (id != null) {
      cv = CV.getById(Integer.parseInt(id));
    }

    if (uri.contains("edit-cv") || uri.contains("create-new-cv")) {
      if (currentUser == null){
        response.sendRedirect("/login?error=login-required&redirect=" + URLEncoder.encode(uri + "?" + request.getQueryString(), "UTF-8"));
        return;
      }

      request.setAttribute("candidate", (Candidate)currentUser);
      if (uri.contains("edit-cv")) {
        request.setAttribute("cv", cv);
        view = request.getRequestDispatcher("/WEB-INF/views/cvs/EditCV.jsp");
      }
    } else if (uri.contains("view-cv")) {
      request.setAttribute("cv", cv);
      request.setAttribute("candidate", (Candidate)User.getById(cv.user_id));
      view = request.getRequestDispatcher("/WEB-INF/views/cvs/ViewCV.jsp");
    } else if (uri.contains("delete-cv")) {
      if (cv == null || !cv.user_id.equals(currentUser.id)) {
        response.sendRedirect("/dashboard?error=invalid-cv");
        return;
      }

      request.setAttribute("cv", cv);
      view = request.getRequestDispatcher("/WEB-INF/views/cvs/DeleteCV.jsp");
    }

    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    HttpSession session = request.getSession();

    User currentUser = App.getCurrentUser(session);
    if (currentUser == null){
      response.sendRedirect("/login?error=login-required");
      return;
    }

    if (uri.contains("create-new-cv") || uri.contains("edit-cv")) {
      CVController.postCreateCV(request);
    } else if (uri.contains("delete-cv")) {
      String _id = request.getParameter("id");
      Integer id = Integer.parseInt(_id);
      CV.deleteById(id);
    }

    response.sendRedirect("/dashboard");
  }

  private static void postCreateCV(HttpServletRequest request) {
    String _id        = request.getParameter("id");
    String title      = request.getParameter("title");
    String[] skills   = request.getParameterValues("skills");
    String experience = request.getParameter("experience");
    String about      = request.getParameter("about");
    String education  = request.getParameter("education");
    HttpSession session = request.getSession();

    User currentUser = App.getCurrentUser(session);

    Integer id = -1;
    models.CV cv = null;
    if (_id != null) {
      id = Integer.parseInt(_id);

      cv = CV.getById(id);
      if (!cv.user_id.equals(currentUser.id)) {
        request.setAttribute("error", "invalid-cv");
        return;
      }
    }

    cv = new models.CV(id, title, new ArrayList<String>(Arrays.asList(skills)), experience, about, education, 1, currentUser.id);
    cv.save();
  }
}