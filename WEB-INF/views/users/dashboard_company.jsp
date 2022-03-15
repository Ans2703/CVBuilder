<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="bg-blue-200 p-10 text-center">
  <h1 class="text-3xl font-bold">Welcome ${name}</h1>
  <p class="p-2">Manage everything from here</p>
  <a href="/create-new-job" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Create New Job</a>
</section>

<section>
  <c:if test="${jobs.size() == 0}">
    <p>You have not created any jobs yet. After creating a job, it will be automatically listed on job posts page, and candidates will be able to submit their resumes and apply on your job.</p>
  </c:if>
  <c:if test="${jobs.size() > 0}">
    <div class="flex flex-col md:flex-row">
      <div class="md:w-2/5 sm:w-full p-5">
        <h2 class="text-xl font-bold text-center">Jobs Created (${jobs.size()})</h2>
        <div class="jobs sm:columns-1 md:columns-2 mt-5">
          <c:forEach var="job" items="${jobs}">
            <div class="job max-w-sm mt-5 text-center overflow-hidden shadow-lg hover:bg-gray-100">
              <!-- <img class="w-full" src="/img/card-top.jpg" alt="Sunset in the west"> -->
              <div class="px-6 py-4">
                <h3 class="font-bold text-xl mb-2">${job.title} <small>${job.salaryFormatted} ${job.salaryCurrency}/year</small></h3>
                <p class="text-gray-700 text-base">
                  ${job.descriptionFormatted}
                </p>
              </div>
              <div class="px-6 pt-4 pb-2">
                <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${job.location.equals("null") || job.location.isEmpty() ? "N/A" : job.location}</span>
                <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${job.country.equals("null") || job.country.isEmpty() ? "N/A" : job.country}</span>
                <a    class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400" href="/edit-job?id=${job.id}">Edit</a>
                <a    class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400" href="/delete-job?id=${job.id}">Delete</a>
              </div>
            </div>
          </c:forEach>
        </div>
      </div>

      <div class="md:w-2/5 sm:w-full p-5">
        <h2 class="text-xl font-bold text-center mb-2">CVs Recieved (${company.recievedCVs.size()})</h2>
        <c:if test="${company.recievedCVs.size() == 0}">
          <p class="text-gray-600 italic">You have not recieved any job applications yet. Look back later.</p>
        </c:if>
        <c:if test="${company.recievedCVs.size() > 0}">
          <c:forEach var="job_cv" items="${company.recievedCVs}">
            <div class="p-5 shadow hover:bg-gray-200">
              <h3>${job_cv.cv.candidate.fullName} applied to ${job_cv.job.title} using <a href="/view-cv?id=${job_cv.cv.id}" target="_blank" class="text-purple-600">${job_cv.cv.title}</a> at ${job_cv.appliedAtFormatted}</h3>
            </div>
          </c:forEach>
        </c:if>
      </div>
    </div>
  </c:if>
</section>