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
          <c:forEach var="cv" items="${cvs}">
            <div class="job max-w-sm mt-5 text-center overflow-hidden shadow-lg hover:bg-gray-100">
              <!-- <img class="w-full" src="/img/card-top.jpg" alt="Sunset in the west"> -->
              <div class="px-6 py-4">
                <h3 class="font-bold text-xl mb-2">${cv.title} <small>submitted to ${cv.jobsAppliedTo.size()} jobs</small></h3>
                <p class="text-gray-700 text-base">
                  ${cv.aboutFormatted}
                </p>
              </div>
              <c:if test="${cv.skills.size() > 0}">
                <div class="px-6 pt-4 pb-2">
                  <c:forEach var="skill" items="${cv.skills}">
                    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${skill}</span>
                  </c:forEach>
                </div>
              </c:if>
              <div class="px-6 pt-4 pb-2">
                <a class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400" href="/edit-cv?id=${cv.id}">Edit</a>
                <a class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400" href="/delete-cv?id=${cv.id}">Delete</a>
              </div>
            </div>
          </c:forEach>
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
              <h3>Applied to ${job_cv.job.title} using ${job_cv.cv.title} at ${job_cv.appliedAtFormatted}</h3>
            </div>
          </c:forEach>
        </c:if>
      </div>
    </div>
  </c:if>
</section>