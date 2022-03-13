<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag import="models.App" %>
<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html>
  <head>
    <jsp:invoke fragment="head" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://cdn.tailwindcss.com"></script>
  </head>
  <body>
    <nav>
      <a href="/">Home</a>
      <c:if test="${sessionScope.current_user_id == null}">
        <a href="/login">Login/Register</a>
      </c:if>
      <c:if test="${sessionScope.current_user_id != null}">
        <a href="/login?action=logout">Logout</a>
      </c:if>
      <a href="/about">About</a>
    </nav>
    <main>
      <jsp:invoke fragment="header"/>
      <jsp:doBody/>
    </main>
    <footer>
      <jsp:invoke fragment="footer"/>
    </footer>
  </body>
</html>