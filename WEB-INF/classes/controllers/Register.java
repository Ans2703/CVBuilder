package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import models.*;

public class Register extends HttpServlet {
  // no get method needed, as we use same page for login and register

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email           = request.getParameter("email");
    String password        = request.getParameter("password");
    String confirmPassword = request.getParameter("confirm_password");
    String phone           = request.getParameter("phone");
    String resourceType    = request.getParameter("resource_type");
    HttpSession session = request.getSession();

    User newUser = null;
    if (resourceType.equals("candidate")) {
      String firstName = request.getParameter("first_name");
      String lastName = request.getParameter("last_name");
      newUser = new Candidate(firstName, lastName);
    } else if (resourceType.equals("company")) {
      String name = request.getParameter("name");
      newUser = new Company(name, "");
    }
    newUser.save();

    // set session (login)
    if (newUser == null) {
      response.sendRedirect("/login?fail");
      return;
    }

    App.setCurrentUser(session, newUser.id);

    response.sendRedirect("/dashboard");
  }
}
