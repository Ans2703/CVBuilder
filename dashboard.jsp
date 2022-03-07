<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="models.User" %>
<%
  User u = (User)session.getAttribute("user");
%>
<t:layout>
  <jsp:attribute name="head">
    <title>Dashboard</title>
  </jsp:attribute>

  <jsp:body>
    <aside>
      <a href="/dashboard">Dashboard</a>
      <a href="/dashboard?page=my-cvs">My CVs</a>
      <a href="/dashboard?page=job-applications">Job Applications</a>
    </aside>
    <section>
      <h1>Welcome ${u.email}</h1>
      <h1>Statistics</h1>
    </section>
  </jsp:body>
</t:layout>