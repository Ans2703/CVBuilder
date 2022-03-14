<section>
  <h1>Welcome ${name}</h1>
  <a href="/create-new-job" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Create New Job</a>
  <c:if test="${jobs.size() == 0}">
    You have not created any jobs yet
  </c:if>
  <c:if test="${jobs.size() > 0}">
    <div class="columsn-2">
      <div class="w-2/5 inline-block">
        <h2>${jobs.size()} jobs created</h2>
        <div class="jobs">
          <c:forEach var="job" items="${jobs}">
            <div class="job">
              <h3>${job.title}</h3>
              <p>${job.description}</p>
              <a href="/edit-job?id=${job.id}" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Edit</a>
              <a href="/delete-job?id=${job.id}" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Delete</a>
            </div>
          </c:forEach>
        </div>
      </div>
      <div class="w-2/5 inline-block">
        y applications recieved

      </div>
    </div>
  </c:if>
</section>