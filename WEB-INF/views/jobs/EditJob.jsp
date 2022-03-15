<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Editing Job ${job.title}</title>
  </jsp:attribute>

  <jsp:body>
    <section class="p-10 bg-blue-300 text-center">
      <h1 class="text-3xl font-bold">Edit Job <span class="underline">${job.title}</span></h1>
      <!-- <p class="mt-2 text-lg font-serif italic">...</p> -->
    </section>

    <section class="max-w-xl m-auto mt-5">
      <form method="POST" action="/edit-job">
        <div class="mb-4">
          <input name="title" value="${job.title}" type="text" placeholder="Title of Job" title="Title of Job"
            class="shadow appearance-none border w-full py-2 px-3" />
        </div>
        <div class="mb-4">
          <textarea name="description" rows="8" cols="50" placeholder="Detailed job description. You can use HTML for rich formatting." title="Description"
            class="shadow appearance-none border w-full py-2 px-3">${job.description}</textarea>
        </div>
        <div class="mb-4">
          <input name="salary" value="${job.salary}" type="number" placeholder="Salary (yearly)" title="Salary"
            class="shadow appearance-none border w-full py-2 px-3" min="0" required />
        </div>
        <div class="mb-4">
          <select name="salary_currency" class="shadow appearance-none border w-full py-2 px-3" title="Salary Currency">
            <option value="CAD">CAD</option>
            <option value="USD">USD</option>
            <option value="JPY">JPY</option>
            <option value="PKR">PKR</option>
          </select>
        </div>
        <div class="mb-4">
          <select name="country" class="shadow appearance-none border w-full py-2 px-3" title="Country">
            <option value="Canada">Canada</option>
            <option value="Japan">Japan</option>
            <option value="Pakistan">Pakistan</option>
            <option value="UAE">UAE</option>
            <option value="USA">United States</option>
          </select>
        </div>
        <div class="mb-4">
          <input name="location" value="${job.location}" type="text" placeholder="Location (office address or Remote)" title="Location"
            class="shadow appearance-none border w-full py-2 px-3" />
        </div>
        <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Update Job</button>
      </form>
    </section>
  </jsp:body>
</t:layout>