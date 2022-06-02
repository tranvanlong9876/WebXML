<%-- 
    Document   : search
    Created on : May 26, 2022, 11:29:25 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.STUDENTINFO.fullName}
        </font>
        <h1>Search Page</h1>
        
        <form action="DispatchController">
            Student's Address <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" name="btAction" value="Search" />
        </form>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.STUDENT_LIST}" />
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Id</th>
                            <th>Class</th>
                            <th>Full Name</th>
                            <th>Address</th>
                            <th>Sex</th>
                            <th>Status</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${result}" var="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.id}</td>
                            <td>${dto.sClass}</td>
                            <td>${dto.fullName}</td>
                            <td>${dto.address}</td>
                            <td>${dto.sex}</td>
                            <td>${dto.status}</td>
                            <td>
                                <c:url  var="deleteStudent" value="DispatchController">
                                    <c:param name="btAction" value="delete" />
                                    <c:param name="id" value="${dto.id}" />
                                    <c:param name="lastSearchValue" value="${searchValue}" />
                                </c:url>
                                <a href="${deleteStudent}" >Delete</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <font color="red">
                No result!
                </font>
            </c:if>
        </c:if>
    </body>
</html>
