<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
      </section>
    </jsp:body>
  </t:layout>
<% } else if (request.getAttribute("user_type").equals("company")) { %>
  <t:layout>
    <jsp:attribute name="head">
      <title>Dashboard</title>
    </jsp:attribute>

    <jsp:body>
      <aside>
        <a href="/dashboard">Dashboard</a>
        <a href="/dashboard?page=job-posts">Job Posts</a>
        <a href="/dashboard?page=company-job-applications">Job Applications</a>
      </aside>
      <section>
        <h1>Welcome ${name}</h1>
        <h1>Statistics</h1>
      </section>
    </jsp:body>
  </t:layout>
<% } %>