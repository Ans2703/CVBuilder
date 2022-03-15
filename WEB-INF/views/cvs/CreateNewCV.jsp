<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Create New CV</title>
    <meta name="description" content="Build your CVs and Resumes using modern designs." />
  </jsp:attribute>

  <jsp:attribute name="footer">
    <script>
      const experienceInput = document.querySelector("textarea[name=experience]")
      const experienceOutput = document.querySelector("#experience")
      const educationInput = document.querySelector("textarea[name=education]")
      const educationOutput = document.querySelector("#education")
      const aboutInput = document.querySelector("textarea[name=about]")
      const aboutOutput = document.querySelector("#about")
      const skillsInput = document.querySelector("select[name=skills]")
      const skillsOutput = document.querySelector("#skills")

      initialize()

      experienceInput.addEventListener('input', function() {
        renderPreviewHandler(this, experienceOutput)
      })
      educationInput.addEventListener('input', function() {
        renderPreviewHandler(this, educationOutput)
      })
      aboutInput.addEventListener('input', function() {
        renderPreviewHandler(this, aboutOutput)
      })
      skillsInput.addEventListener('input', function() {
        skillsOutput.innerHTML = Array.from(skillsInput.selectedOptions).map(e => e.value).join(", ")
      })

      function renderPreviewHandler(inputEle, outputEle) {
        outputEle.innerHTML = format(inputEle.value)
      }
      function format(input) {
        return input
          .replaceAll("\n", "</br>")
          .replaceAll("<h1>", "<h1 class='text-xl font-bold'>")
          .replaceAll("<h2>", "<h2 class='font-bold'>")
          .replaceAll("</h1></br>", "</h1>")
          .replaceAll("</h2></br>", "</h2>")
      }

      function initialize() {
        renderPreviewHandler(experienceInput, experienceOutput)
        renderPreviewHandler(educationInput, educationOutput)
        renderPreviewHandler(aboutInput, aboutOutput)
        skillsOutput.innerHTML = Array.from(skillsInput.selectedOptions).map(e => e.value).join(", ")
      }
    </script>
  </jsp:attribute>

  <jsp:body>
    <section class="p-10 bg-blue-200 text-center">
      <h1 class="text-3xl font-bold">Create New CV</h1>
      <!-- <p class="mt-2 text-lg font-serif italic">...</p> -->
    </section>

    <section>
      <div class="flex flex-col md:flex-row">
        <div class="md:w-2/5 sm:w-full p-5">
          <form method="POST" action="/create-new-cv">
            <div class="mb-4">
              <input name="title" type="text" required placeholder="Title of CV" class="shadow appearance-none border w-full py-2 px-3" />
            </div>
            <div class="mb-4">
              <textarea name="experience" rows="4" cols="50" placeholder="experience" class="shadow appearance-none border w-full py-2 px-3"></textarea>
            </div>
            <div class="mb-4">
              <textarea name="education" rows="4" cols="50" placeholder="education" class="shadow appearance-none border w-full py-2 px-3"></textarea>
            </div>
            <div class="mb-4">
              <textarea name="about" rows="4" cols="50" placeholder="about" class="shadow appearance-none border w-full py-2 px-3"></textarea>
            </div>
            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2">Select Skills <small>(Ctrl/Cmd + click to select multiple)</small></label>
              <select name="skills" multiple class="block appearance-none w-full bg-gray-100 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                <option value="HTML">HTML</option>
                <option value="CSS">CSS</option>
                <option value="JavaScript">JavaScript</option>
                <option value="Java">Java</option>
                <option value="C">C</option>
                <option value="C++">C++</option>
                <option value="Rust">Rust</option>
                <option value="C#">C#</option>
                <option value="Ruby">Ruby</option>
                <option value="Ruby on Rails">Ruby on Rails</option>
              </select>
            </div>
            <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Create</button>
          </form>
        </div>

        <div class="md:w-2/5 sm:w-full p-5">
          <div id="cv-preview" class="shadow p-5">
            <section id="personal-details" class="text-center">
              <h1 class="text-3xl font-bold">${candidate.fullName}</h1>
              <h2 class="text-xl italic">${candidate.email}, ${candidate.phone}</h2>
              <h3>${candidate.address}</h3>
            </section>

            <section id="about"></section>
  
            <h2 class="text-xl font-bold">Experience</h2>
            <section id="experience"></section>
  
            <h2 class="text-xl font-bold">Education</h2>
            <section id="education"></section>

            <h2 class="text-xl font-bold">Skills</h2>
            <section id="skills"></section>
          </div>
        </div>
      </div>
    </section>
  </jsp:body>
</t:layout>