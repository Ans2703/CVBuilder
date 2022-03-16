package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import models.*;

public class UserController extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/users/Profile.jsp");
    User currentUser = App.getCurrentUser(session);

    String id = request.getParameter("id");
    if (id != null && !id.isEmpty()) {
      currentUser = User.getById(Integer.parseInt(id));
      view = request.getRequestDispatcher("/WEB-INF/views/users/ViewProfile.jsp");
    }

    if (currentUser.resourceType.equals("candidate")) {
      request.setAttribute("user", (Candidate)currentUser);
    } else if (currentUser.resourceType.equals("company")) {
      request.setAttribute("user", (Company)currentUser);
    }

    view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id              = request.getParameter("id");
    // String password        = request.getParameter("password");
    // String confirmPassword = request.getParameter("confirm_password");
    String phone           = request.getParameter("phone");
    String address         = request.getParameter("address");
    HttpSession session = request.getSession();

    // if (!password.equals(confirmPassword)) {
    //   response.sendRedirect("/profile?fail=confirmPassword");
    //   return;
    // }

    User user = User.getById(Integer.parseInt(id));
    // user.password = password;
    user.phone    = phone;
    user.address  = address;

    User newResource = null;
    if (user.resourceType.equals("candidate")) {
      String firstName = request.getParameter("first_name");
      String lastName = request.getParameter("last_name");
      newResource = new Candidate(user.resourceId, firstName, lastName, user);
    } else if (user.resourceType.equals("company")) {
      String name = request.getParameter("name");
      String description = request.getParameter("description");
      newResource = new Company(user.resourceId, name, description, user);
    }
    newResource.save();

    response.sendRedirect("/dashboard");
  }
}
