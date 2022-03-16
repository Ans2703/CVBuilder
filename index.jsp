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
    <section class="p-10 bg-blue-200 text-center">
      <h1 class="text-3xl font-bold">Job Posts</h1>
      <p class="mt-2 text-lg font-serif italic">Find your dream job at your dream company</p>
    </section>

    <section class="flex p-5">
      <c:forEach var="job" items="${Job.getAll()}">
        <div class="max-w-lg mr-5 text-center overflow-hidden shadow-lg hover:bg-gray-100">
          <!-- <img class="w-full" src="/img/card-top.jpg" alt="Sunset in the mountains"> -->
          <div class="px-6 py-4">
            <h3 class="font-bold text-xl mb-2">${job.title} <small>${job.salaryFormatted} ${job.salaryCurrency}/year</small></h3>
            <p class="text-gray-700 text-base">
              ${job.descriptionFormatted}
            </p>
          </div>
          <div class="px-6 pt-4 pb-2">
            <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${job.location.equals("null") || job.location.isEmpty() ? "N/A" : job.location}</span>
            <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">${job.country.equals("null") || job.country.isEmpty() ? "N/A" : job.country}</span>
            <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm text-gray-700 mr-2 mb-2">by <a href="/profile?id=${job.company.id}" target="_blank" class="font-bold text-purple-500">${job.company.name}</a></span>
          </div>
          <div class="px-6 pt-4 pb-2">
            <a class="inline-block bg-purple-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 hover:bg-purple-400" href="/apply?id=${job.id}">Apply</a>
          </div>
        </div>
      </c:forEach>
    </section>
  </jsp:body>
</t:layout>