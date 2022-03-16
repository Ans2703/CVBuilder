<!-- <%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="p-10 bg-blue-200 text-center">
  <h1 class="text-3xl font-bold">Welcome ${candidate.fullName}</h1>
  <p class="my-2 text-lg font-serif italic">Create and manage all your CVs and Resumes from here.</p>
  <a href="/create-new-cv" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Create New CV</a>
</section>
<section>
  <c:if test="${cvs.size() == 0}">
    <p class="text-gray-600 text-center p-10">It looks like you don't have any CVs right now. Please click "Create New CV" to create your first CV.</p>
  </c:if>
  <c:if test="${cvs.size() > 0}">
    <div class="flex flex-col md:flex-row">
      <div class="md:w-2/5 sm:w-full p-5">
        <h2 class="text-xl font-bold text-center">My CVs (${cvs.size()})</h2>
        
        <div class="cvs sm:columns-1 md:columns-2 mt-5">
          <jsp:include page="_cvs.jsp"></jsp:include>
        </div>
      </div>

      <div class="md:w-2/5 sm:w-full p-5">
        <h2 class="text-xl font-bold text-center mb-2">Jobs Applied To (${candidate.jobsAppliedTo.size()})</h2>
        <c:if test="${candidate.jobsAppliedTo.size() == 0}">
          <p class="text-gray-600 italic">It looks like you have not applied to any jobs yet. Go to job posts page to start applying to jobs using your CVs/Resumes.</p>
        </c:if>
        <c:if test="${candidate.jobsAppliedTo.size() > 0}">
          <c:forEach var="job_cv" items="${candidate.jobsAppliedTo}">
            <div class="p-5 shadow hover:bg-gray-200">
              <h3>Applied to ${job_cv.job.title}
                (<a href="/profile?id=${job_cv.job.company.id}" target="_blank" class="text-purple-600">${job_cv.job.company.name}</a>)
                using <a href="/view-cv?id=${job_cv.cv.id}" target="_blank" class="text-purple-600">${job_cv.cv.title}</a> at ${job_cv.appliedAtFormatted}</h3>
            </div>
          </c:forEach>
        </c:if>
      </div>
    </div>
  </c:if>
</section>