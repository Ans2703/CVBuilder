<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if (request.getAttribute("user_type").equals("candidate")) { %>
  <t:layout>
    <jsp:attribute name="head">
      <title>Dashboard</title>
    </jsp:attribute>

    <jsp:body>
      <aside>
        <a href="/dashboard">Dashboard</a>
        <a href="/dashboard?page=my-cvs">My CVs</a>
        <a href="/dashboard?page=candidate-job-applications">Job Applications</a>
      </aside>
      <section>
        <h1>Welcome ${name}</h1>
        <h1>Statistics</h1>
        <div>
          ${cv_count} CVs
        </div>
      </section>
    </jsp:body>
  </t:layout>
<% } else if (request.getAttribute("user_type").equals("company")) { %>
  <t:layout>
    <jsp:attribute name="head">
      <title>Dashboard</title>
    </jsp:attribute>

    <jsp:body>
      <section>
        <h1>Welcome ${name}</h1>
        <a href="/create-new-job">Create New Job</a>
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
                    <a href="/edit-job/${job.id}">Edit</button>
                    <a href="/delete-job/${job.id}">Delete</button>
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
    </jsp:body>
  </t:layout>
<% } %>