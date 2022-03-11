<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>My CVs</title>
  </jsp:attribute>

  <jsp:body>
    <aside>
      <a href="/dashboard">Dashboard</a>
      <a href="/dashboard?page=my-cvs">My CVs</a>
      <a href="/dashboard?page=job-applications">Job Applications</a>
    </aside>
    <section>
      <h1>My CVs</h1>
      <div>
        <a href="">Create New CV</a>
        <c:if test="${cvs.size() == 0}">
          It looks like you don't have any CVs right now. Please click "Create New CV" to create your first CV.
        </c:if>

        <c:forEach var="cv" items="${cvs}">
          <div>
            <h3>${cv.title} <small>submitted for 0 jobs.</small></h3>
          </div>
        </c:forEach>
      </div>
    </section>
  </jsp:body>
</t:layout>