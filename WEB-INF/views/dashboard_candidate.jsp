<!-- <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section>
  <h1>Welcome ${name}</h1>
  <h1>Statistics</h1>
  <div>
    ${cv_count} CVs
  </div>
</section>
<section>
  <h1>My CVs</h1>
  <div>
    <a href="/cv" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Create New CV</a>
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
<section>
  Jobs applied to
</section>