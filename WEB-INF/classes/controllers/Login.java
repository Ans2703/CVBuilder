package controllers;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.*;
import models.User;
import models.App;

public class Login extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/login.jsp");
    HttpSession session = request.getSession();
    String action = request.getParameter("action");

    if (action != null && action.equals("logout")) {
      App.log("invalidating session");
      session.invalidate();
    }

    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    HttpSession session = request.getSession();

    User user = User.findByEmail(email);
    // check if credentials match
    if (user == null || !user.password.equals(password)) {
      response.sendRedirect(response.encodeRedirectURL("/login?fail=password"));
      return;
    }

    // set session
    App.setCurrentUser(session, user.id);

    response.sendRedirect(response.encodeRedirectURL("/dashboard"));
  }
}
