<%-- 
    Document   : home
    Created on : Jun 10, 2022, 8:55:34 PM
    Author     : ldanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="<c:url value="/webjars/bootstrap/4.6.1/css/bootstrap.min.css" />" 
              rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container">
            <div class="row" style="text-align: center">
                <div class="col-12">
                    <h2>List Books </h2>
                </div>
            </div>

            <div class="row" style="padding-bottom: 10px">
                <div class="col-12">
                    <c:if test="${messageType == 'success'}">
                        <div class="alert alert-success" role="alert">
                            ${message}
                        </div>
                    </c:if>
                    <c:if test="${messageType == 'fail'}">
                        <div class="alert alert-danger" role="alert">
                            ${message}
                        </div>
                    </c:if>
                </div>
            </div>

            <div class="row" style="padding-bottom: 10px">
                <div class="col-12 col-lg-6">
                    <button type="button" class="btn btn-primary"
                            onclick="location.href = '<c:url value="/add-book"/>'">Add Book</button>
                </div>

                <div class="col-12 col-lg-6" style="text-align: right">
                    <form class="form-inline float-right" method="post" 
                          action="${pageContext.request.contextPath}/search-book">
                        <div class="form-group mx-sm-3">
                            <input type="text" class="form-control" placeholder="search"
                                   name="searchValue"/>
                        </div>
                        <button class="btn btn-secondary" type="submit">
                            Search
                        </button>
                    </form>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <tr>
                                <th>Name</th>
                                <th>Author</th>
                                <th>Category</th>
                                <th>Number Of Page</th>
                                <th>Price</th>
                                <th>ISBN</th>
                                <th>Publish Date</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${books}" var="b">
                                <tr>
                                    <td>${b.name}</td>
                                    <td>${b.author}</td>
                                    <td>${b.category.name}</td>
                                    <td>${b.bookDetail.numberOfPage}</td>
                                    <td>${b.bookDetail.price}</td>
                                    <td>${b.bookDetail.isbn}</td>
                                    <td>${b.bookDetail.publishDate}</td>
                                    <td>
                                        <button type="button" class="btn btn-info"
                                                onclick="location.href = '<c:url value="/update-book/${b.id}"/>'">Update</button>

                                        <button type="button" class="btn btn-danger"
                                                onclick="location.href = '<c:url value="/delete-book/${b.id}"/>'">Delete</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
