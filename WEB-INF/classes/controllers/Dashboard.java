package controllers;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.*;

public class Dashboard extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
    if (request.getParameter("page").equals("my-cvs")) {
      view = request.getRequestDispatcher("dashboard_my-cvs.jsp");
    }
    HttpSession session = request.getSession();

    request.setAttribute("name", "jojo");

    view.forward(request, response);
  }
}
