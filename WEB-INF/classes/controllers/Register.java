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

    if (!password.equals(confirmPassword)) {
      response.sendRedirect("/login?fail=confirmPassword");
      return;
    }

    User user = new User(-1, email, password, phone, "");
    User newResource = null;
    if (resourceType.equals("candidate")) {
      String firstName = request.getParameter("first_name");
      String lastName = request.getParameter("last_name");
      newResource = new Candidate(firstName, lastName, user);
    } else if (resourceType.equals("company")) {
      String name = request.getParameter("name");
      newResource = new Company(name, "", user);
    }
    newResource.save();

    // set session (login)
    if (newResource == null) {
      response.sendRedirect("/login?fail");
      return;
    }

    App.setCurrentUser(session, newResource.id);

    response.sendRedirect("/dashboard");
  }
}
