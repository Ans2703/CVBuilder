<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Applying to ${job.title}</title>
  </jsp:attribute>

  <jsp:body>
    <section>
        <h1>Apply to Job</h1>
        <form method="POST" action="/apply">
          <input name="job_id" type="hidden" value="${job.id}" />
          <div class="mb-4">
            <select name="cv_id" class="shadow appearance-none border w-full py-2 px-3">
              <c:forEach var="cv" items="${cvs}">

              </c:forEach>
              <option value="CAD">CAD</option>
              <option value="USD">USD</option>
              <option value="JPY">JPY</option>
              <option value="PKR">PKR</option>
            </select>
          </div>
          <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Apply</button>
        </form>
      </section>
  </jsp:body>
</t:layout>