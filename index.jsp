<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="models.Job" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Home</title>
    <meta name="description" content="Build your CVs and Resumes using modern designs." />
  </jsp:attribute>
  <jsp:attribute name="footer">
  </jsp:attribute>
  <jsp:body>
    <h1 class="text-3xl text-center font-bold">Job Posts</h1>
    <c:forEach var="job" items="${Job.getAll()}">
      <div class="max-w-full text-center overflow-hidden shadow-lg">
        <!-- <img class="w-full" src="/img/card-top.jpg" alt="Sunset in the mountains"> -->
        <div class="px-6 py-4">
          <h3 class="font-bold text-xl mb-2">${job.title} <small>${job.salary} ${job.salaryCurrency}/year</small></h3>
          <p class="text-gray-700 text-base">
            ${job.description}
          </p>
        </div>
        <div class="px-6 pt-4 pb-2">
          <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${job.location}</span>
          <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${job.country}</span>
          <a    class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400" href="/apply?id=${job.id}">Apply</a>
        </div>
      </div>
    </c:forEach>
  </jsp:body>
</t:layout>