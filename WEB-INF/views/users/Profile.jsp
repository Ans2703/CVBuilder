<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Profile</title>
  </jsp:attribute>

  <jsp:body>
    <section class="p-10 bg-blue-200 text-center">
      <h1 class="text-3xl font-bold">Update Profile</h1>
      <p class="mt-2 text-lg font-serif">Keep your information up-to-date.</p>
    </section>

    <section>
      <div class="w-2/5 m-auto p-5">
        <form action="/profile" method="POST">
          <input name="id" type="hidden" value="${user.id}" />
          <div class="mb-4">
            <input type="email" value="${user.email}" required disabled class="shadow appearance-none border w-full py-2 px-3 bg-gray-200" />
          </div>
          <c:if test='${user.resourceType.equals("candidate")}'>
            <div class="mb-4">
              <input name="first_name" type="text" value="${user.firstName}" required placeholder="First Name" class="shadow appearance-none border w-full py-2 px-3" />
            </div>
            <div class="mb-4">
              <input name="last_name" type="text" value="${user.lastName}" required placeholder="Last Name" class="shadow appearance-none border w-full py-2 px-3" />
            </div>
          </c:if>
          <c:if test='${user.resourceType.equals("company")}'>
            <div class="mb-4">
              <input name="name" type="text" value="${user.name}" required placeholder="Company Name" class="shadow appearance-none border w-full py-2 px-3" />
            </div>
            <div class="mb-4">
              <input name="description" type="text" value="${user.description}" placeholder="Description" class="shadow appearance-none border w-full py-2 px-3" />
            </div>
          </c:if>
          <div class="mb-4">
            <input name="phone" type="tel" value="${user.phone}" placeholder="Phone" class="shadow appearance-none border w-full py-2 px-3" />
          </div>
          <div class="mb-4">
            <input name="address" type="text" value="${user.address}" placeholder="Address" class="shadow appearance-none border w-full py-2 px-3" />
          </div>

          <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Update</button>
        </form>
      </div>
    </section>
  </jsp:body>
</t:layout>