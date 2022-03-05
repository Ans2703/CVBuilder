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
  </head>
  <body>
    <nav>
      <a href="/">Home</a>
      <a href="/login">Login/Register</a>
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