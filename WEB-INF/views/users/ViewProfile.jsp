<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
  <jsp:attribute name="head">
    <title>${user.resourceType.equals("candidate") ? user.fullName : user.name}</title>
  </jsp:attribute>

  <jsp:body>
    <section class="p-10 bg-blue-200 text-center">
      <h1 class="text-3xl font-bold">${user.resourceType.equals("candidate") ? user.fullName : user.name}</h1>
      <p class="mt-2 text-lg font-serif">${user.email}, ${user.phone}</p>
      
        <c:if test='${user.resourceType.equals("candidate")}'>
          <h3 class="text-xl mt-2">  
          ${user.cvs.size()} CVs created
          </h3>
        </c:if>
        <c:if test='${user.resourceType.equals("company")}'>
          <h3 class="text-xl mt-2">  
          ${user.jobs.size()} jobs created
          </h3>
          <h3 class="text-lg mt-2">${user.description}</h3>
          <h3 class="text-lg mt-2">${user.address}</h3>
        </c:if>
    </section>

    <section class="flex p-5">
      <c:if test='${user.resourceType.equals("candidate")}'>
        <c:set var="cvs" value="${user.cvs}" scope="request"/>
        <jsp:include page="_cvs.jsp"></jsp:include>
      </c:if>
      <c:if test='${user.resourceType.equals("company")}'>
        <c:set var="jobs" value="${user.jobs}" scope="request"/>
        <jsp:include page="_jobs.jsp"></jsp:include>
      </c:if>
    </section>
  </jsp:body>
</t:layout>