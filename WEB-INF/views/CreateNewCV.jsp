<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <t:layout>
    <jsp:attribute name="head">
    <title>createNewCV</title>
    <meta name="description" content="Build your CVs and Resumes using modern designs." />
  </jsp:attribute>
  <jsp:body>
    <section>
        <h1>Create CV</h1>
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