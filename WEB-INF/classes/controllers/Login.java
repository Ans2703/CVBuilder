package controllers;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.*;

public class Login extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher("login.jsp");
    HttpSession session = request.getSession();

    request.setAttribute("name", "jojo");

    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // check if credentials match

    response.sendRedirect("/dashboard");
  }
}
