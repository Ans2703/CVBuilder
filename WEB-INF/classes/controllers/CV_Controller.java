package controllers;

import models.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class CV_Controller extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 RequestDispatcher view = request.getRequestDispatcher("WEB-INF/CreateNewCV.jsp");
      view.forward(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String title = request.getParameter("title");
    String experience= request.getParameter("experience");
    String about= request.getParameter("about");
    String education = request.getParameter("education");
    HttpSession session = request.getSession();

    User currentUser = App.getCurrentUser(session);


    models.CV cv = new models.CV(-1,title,new ArrayList<Skill>(),experience,about,education,-1,currentUser.id);

  }

  }
