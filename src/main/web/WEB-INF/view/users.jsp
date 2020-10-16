<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Users List</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Users List</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <input type="button" value="Add User"
               onclick="window.location.href='show_form_for_add'; return false;"
               class="add-button"/>

        <!--  add a search box -->
        <form:form action="search" method="GET">
            Search user: <input type="text" name="theSearchName"/>

            <input type="submit" value="Search" class="add-button"/>
        </form:form>

        <table>

            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Action</th>

            <c:forEach var="user" items="${userList}">

                <!-- construct an update link -->
                <c:url var="updateLink" value="/users/show_form_for_update">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>

                <!-- construct a delete link -->
                <c:url var="deleteLink" value="/users/delete">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>

                <tr>

                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
