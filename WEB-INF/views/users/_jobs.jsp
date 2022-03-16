<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="job" items="${jobs}">
  <div class="job max-w-sm mt-5 mr-5 text-center overflow-hidden shadow-lg hover:bg-gray-100">
    <!-- <img class="w-full" src="/img/card-top.jpg" alt="Sunset in the west"> -->
    <div class="px-6 py-4">
      <h3 class="font-bold text-xl mb-2">${job.title} <small>${job.salaryFormatted} ${job.salaryCurrency}/year</small>
      </h3>
      <p class="text-gray-700 text-base">
        ${job.descriptionFormatted}
      </p>
    </div>
    <div class="px-6 pt-4 pb-2">
      <span
        class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${job.location.equals("null")
        || job.location.isEmpty() ? "N/A" : job.location}</span>
      <span
        class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${job.country.equals("null")
        || job.country.isEmpty() ? "N/A" : job.country}</span>
      <c:if test='${!pageContext.request.requestURI.contains("Profile")}'>
        <a class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400"
          href="/edit-job?id=${job.id}">Edit</a>
        <a class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400"
          href="/delete-job?id=${job.id}">Delete</a>
      </c:if>
    </div>
  </div>
</c:forEach>