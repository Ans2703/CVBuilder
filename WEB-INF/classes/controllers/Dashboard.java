package controllers;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.*;
import models.App;

public class Dashboard extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String page = request.getParameter("page");
    RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
    HttpSession session = request.getSession();

    if (App.getCurrentUser(session)==null){
      response.sendRedirect("/login");
      return;
    }

    if (page != null && page.equals("my-cvs")) {
      view = request.getRequestDispatcher("dashboard_my-cvs.jsp");
      request.setAttribute("cvs", App.getCurrentUser(session).cvs);
    }

    session.setAttribute("user_email", App.getCurrentUser(session).email);

    view.forward(request, response);
  }
}
