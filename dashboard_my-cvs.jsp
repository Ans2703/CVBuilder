<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <jsp:attribute name="head">
    <title>My CVs</title>
  </jsp:attribute>

  <jsp:body>
    <aside>
      <a href="/dashboard">Dashboard</a>
      <a href="/dashboard?my-cvs">My CVs</a>
      <a href="/dashboard?job-applications">Job Applications</a>
    </aside>
    <section>
      <h1>My CVs</h1>
    </section>
  </jsp:body>
</t:layout>