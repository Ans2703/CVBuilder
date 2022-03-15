<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Delete Job ${job.title}?</title>
  </jsp:attribute>

  <jsp:body>
    <section>
      <section class="p-10 bg-blue-300 text-center">
        <h1 class="text-3xl font-bold">Deleting Job <span class="underline">${job.title}</span></h1>
        <p class="mt-2 text-lg font-serif italic">Are you sure you want to delete this job?</p>

        <form method="POST" action="/delete-job" class="mt-2">
          <input name="id" value="${job.id}" type="hidden" />
          <button type="submit" class="px-4 py-1 bg-red-400 hover:bg-red-600">Yes</button>
          <button href="/dashboard" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Cancel</button>
        </form>
      </section>
      </section>
  </jsp:body>
</t:layout>