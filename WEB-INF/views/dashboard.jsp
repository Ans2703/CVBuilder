<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if (request.getAttribute("user_type").equals("candidate")) { %>
  <t:layout>
    <jsp:attribute name="head">
      <title>Dashboard</title>
    </jsp:attribute>

    <jsp:body>
      <jsp:include page="dashboard_candidate.jsp" />
    </jsp:body>
  </t:layout>
<% } else if (request.getAttribute("user_type").equals("company")) { %>
  <t:layout>
    <jsp:attribute name="head">
      <title>Dashboard</title>
    </jsp:attribute>

    <jsp:body>
      <jsp:include page="dashboard_company.jsp" />
    </jsp:body>
  </t:layout>
<% } %>