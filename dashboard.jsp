<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Dashboard</title>
  </jsp:attribute>

  <jsp:body>
    <aside>
      <a href="/dashboard">Dashboard</a>
      <a href="/dashboard?my-cvs">My CVs</a>
      <a href="/dashboard?job-applications">Job Applications</a>
    </aside>
    <section>
      <h1>Statistics</h1>
    </section>
  </jsp:body>
</t:layout>