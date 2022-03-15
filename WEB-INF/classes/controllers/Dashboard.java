package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import models.*;

public class Dashboard extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String page = request.getParameter("page");
    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/users/dashboard.jsp");
    HttpSession session = request.getSession();

    User currentUser = App.getCurrentUser(session);
    if (currentUser == null){
      response.sendRedirect("/login?error=login-required&redirect=dashboard");
      return;
    }

    request.setAttribute("user_email", currentUser.email);
    request.setAttribute("user_type", currentUser.resourceType);
    
    if (currentUser.resourceType.equals("candidate")) {
      request.setAttribute("candidate", ((Candidate)currentUser));
      request.setAttribute("cvs", ((Candidate)currentUser).cvs);
    } else if (currentUser.resourceType.equals("company")) {
      request.setAttribute("name", ((Company)currentUser).name);
      request.setAttribute("company", (Company)currentUser);
      request.setAttribute("jobs", ((Company)currentUser).jobs);
    }

    view.forward(request, response);
  }
}
