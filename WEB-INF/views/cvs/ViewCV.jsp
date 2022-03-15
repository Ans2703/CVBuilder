<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="models.App" %>

<t:layout>
  <section class="p-10 bg-blue-200 text-center">
    <h1 class="text-3xl font-bold">${cv.title}</h1>
    <!-- <p class="mt-2 text-lg font-serif italic">...</p> -->
  </section>
  
  <section>
    <div id="cv-preview" class="shadow mt-5 p-5 w-2/5 m-auto">
      <section id="personal-details" class="text-center">
        <h1 class="text-3xl font-bold">${candidate.fullName}</h1>
        <h2 class="text-xl italic">${candidate.email}, ${candidate.phone}</h2>
        <h3>${candidate.address}</h3>
      </section>
    
      <section id="about" class="mt-10">
        ${cv.aboutFormatted}
      </section>
    
      <h2 class="text-xl font-bold mt-10">Experience</h2>
      <section id="experience">
        ${cv.experienceFormatted}
      </section>
    
      <h2 class="text-xl font-bold mt-10">Education</h2>
      <section id="education">
        ${cv.education}
      </section>
    
      <h2 class="text-xl font-bold mt-10">Skills</h2>
      <section id="skills">
        ${String.join(", ", cv.skills)}
      </section>
    </div>
  </section>
</t:layout>