<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Applying to ${job.title}</title>
  </jsp:attribute>

  <jsp:body>
    <section class="p-10 bg-blue-300 text-center">
      <h1 class="text-3xl font-bold">Applying to ${job.title}</h1>
      <p class="mt-2 text-lg">Are you sure you want to apply to this job? Select a CV and then click Apply button.</p>
    </section>

    <section>
      <div class="w-2/5 m-auto p-5">
        <form method="POST" action="/apply">
          <input name="job_id" type="hidden" value="${job.id}" />
          <div class="mb-4">
            <label>Select a CV</label>
            <select name="cv_id" class="shadow appearance-none border w-full py-2 px-3">
              <c:forEach var="cv" items="${cvs}">
                <option value="${cv.id}">${cv.title}</option>
              </c:forEach>
            </select>
          </div>
          <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Apply</button>
        </form>
      </div>
    </section>
  </jsp:body>
</t:layout>