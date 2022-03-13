<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Create New Job</title>
  </jsp:attribute>

  <jsp:body>
    <section>
        <h1>Create Job Post</h1>
        <form method="POST" action="/create-new-job">
          <div class="mb-4">
            <input name="title" type="text" placeholder="Title of Job" class="shadow appearance-none border w-full py-2 px-3" />
          </div>
          <div class="mb-4">
            <textarea name="description" rows="4" cols="50" class="shadow appearance-none border w-full py-2 px-3"></textarea>
          </div>
          <div class="mb-4">
            <input name="salary" type="number" placeholder="Salary" class="shadow appearance-none border w-full py-2 px-3" min="0" required />
          </div>
          <div class="mb-4">
            <select name="salary_currency" class="shadow appearance-none border w-full py-2 px-3">
              <option value="CAD">CAD</option>
              <option value="USD">USD</option>
              <option value="JPY">JPY</option>
              <option value="PKR">PKR</option>
            </select>
          </div>
          <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Create Job</button>
        </form>
      </section>
  </jsp:body>
</t:layout>