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
      request.setAttribute("success_message", "Logged Out Successfully!");
      session.invalidate();
    }

    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String redirectTo = request.getParameter("redirect");
    HttpSession session = request.getSession();

    User user = User.findByEmail(email);
    // check if credentials match
    if (user == null || !user.password.equals(password)) {
      response.sendRedirect("/login?error=invalid-password&redirect=" + redirectTo);
      return;
    }

    // set session
    App.setCurrentUser(session, user.id);

    if (redirectTo == null || redirectTo.isEmpty()) {
      redirectTo = "/dashboard";
    }
    response.sendRedirect(redirectTo);
  }
}
