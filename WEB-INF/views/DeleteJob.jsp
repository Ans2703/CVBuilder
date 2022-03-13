<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Delete Job?</title>
  </jsp:attribute>

  <jsp:body>
    <section>
        <h1>Are you sure you want to delete this job?</h1>
        <form method="POST" action="/delete-job">
          <input name="id" type="hidden" />
          <button type="submit">Yes</button>
          <a href="/dashboard">Cancel</a>
        </form>
      </section>
  </jsp:body>
</t:layout>