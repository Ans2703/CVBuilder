<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="cv" items="${cvs}">
  <div class="job max-w-lg mt-5 mr-5 text-center overflow-hidden shadow-lg hover:bg-gray-100">
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
          <span
            class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${skill}</span>
        </c:forEach>
      </div>
    </c:if>
    <c:if test='${!pageContext.request.requestURI.contains("Profile")}'>
      <div class="px-6 pt-4 pb-2">
        <a class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400"
          href="/edit-cv?id=${cv.id}">Edit</a>
        <a class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400"
          href="/delete-cv?id=${cv.id}">Delete</a>
      </div>
    </c:if>
  </div>
</c:forEach>