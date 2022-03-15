<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <t:layout>
    <jsp:attribute name="head">
    <title>Create New CV</title>
    <meta name="description" content="Build your CVs and Resumes using modern designs." />
  </jsp:attribute>
  <jsp:body>
    <section class="p-10 bg-blue-200 text-center">
      <h1 class="text-3xl font-bold">Create New CV</h1>
      <!-- <p class="mt-2 text-lg font-serif italic">...</p> -->
    </section>

    <section>

        <form method="POST" action="/CV">
          <input name="title"      type="text" placeholder="Title of CV" />
          <textarea name="experience"   rows="4" cols="50" placeholder="experience"></textarea>
          <textarea name="education" rows="4" cols="50" placeholder="education"> </textarea>  
          <textarea name="about" rows="4" cols="50" placeholder="about"></textarea>
          <button type="submit">CreateCV</button>
        </form>
      </section>
    </jsp:body>
    </t:layout> 