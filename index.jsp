<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="models.Job" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Home</title>
    <meta name="description" content="Build your CVs and Resumes using modern designs." />
  </jsp:attribute>
  <jsp:attribute name="footer">
  </jsp:attribute>
  <jsp:body>
    <h1 class="text-3xl font-bold">Job Posts</h1>
    <c:forEach var="job" items="${Job.getAll()}">
      <h3>${job.title} <small>${job.salary} ${job.salaryCurrency}</small> @ ${job.location}</h3>
      <p>${job.description}</p>
    </c:forEach>
  </jsp:body>
</t:layout>