package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import models.*;

public class Dashboard extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String page = request.getParameter("page");
    RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
    HttpSession session = request.getSession();

    User currentUser = App.getCurrentUser(session);
    if (currentUser == null){
      response.sendRedirect("/login");
      return;
    }

    if (page != null && page.equals("my-cvs")) {
      view = request.getRequestDispatcher("dashboard_my-cvs.jsp");
      request.setAttribute("cvs", ((Candidate)currentUser).cvs);
    }

    request.setAttribute("user_email", currentUser.email);
    request.setAttribute("user_type", currentUser.resourceType);
    
    if (currentUser.resourceType.equals("candidate")) {
      request.setAttribute("name", ((Candidate)currentUser).getName());
      request.setAttribute("cv_count", ((Candidate)currentUser).cvs.size());
    } else if (currentUser.resourceType.equals("company")) {
      request.setAttribute("name", ((Company)currentUser).name);
    }

    view.forward(request, response);
  }
}
