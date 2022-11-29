<%-- 
    Document   : book-form
    Created on : Jun 13, 2022, 6:57:47 PM
    Author     : ldanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book</title>
        <link href="<c:url value="/webjars/bootstrap/4.6.1/css/bootstrap.min.css" />" 
              rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12" style="text-align: center">
                    <c:if test="${action == 'add'}">
                        <h3>Create new Book</h3>
                    </c:if>

                    <c:if test="${action == 'update'}">
                        <h3>Update Book</h3>
                    </c:if>
                </div>
            </div>

            <mvc:form method="POST" modelAttribute="book"
                      action="${pageContext.request.contextPath}/result">

                <c:if test="${action == 'update'}">
                    <input hidden value="${book.id}" name="id" />
                    <input hidden value="${book.bookDetail.id}" name="bookDetail.id" />
                </c:if>

                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="nameId">Name</label>
                            <input type="text" class="form-control" id="nameId" name="name"
                                   value="${book.name}"/>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="authorId">Author</label>
                            <input type="text" name="author" class="form-control" id="authorId"
                                   value="${book.author}"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="nameId">Category</label>
                            <select class="form-control" name="category.id">
                                <c:forEach items="${categories}" var="c">
                                    <c:if test="${book.category.id == c.id}">
                                        <option value="${c.id}" selected>${c.name}</option>
                                    </c:if>
                                    <c:if test="${book.category.id != c.id}">
                                        <option value="${c.id}">${c.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="isbnId">ISBN</label>
                            <input type="text" name="bookDetail.isbn" 
                                   class="form-control" id="isbnId" value="${book.bookDetail.isbn}" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="numberOfPageId">Number Of Page</label>
                            <input type="number" class="form-control" id="numberOfPageId" 
                                   name="bookDetail.numberOfPage" value="${book.bookDetail.numberOfPage}" />
                        </div>
                    </div>
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="priceId">Price</label>
                            <input type="number" name="bookDetail.price" 
                                   class="form-control" id="priceId" value="${book.bookDetail.price}" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="publishDateId">Publish Date</label>
                            <input type="date" class="form-control" id="publishDateId" 
                                   name="bookDetail.publishDate" value="${book.bookDetail.publishDate}" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label for="descriptionId">Description</label>
                            <textarea class="form-control" id="descriptionId" 
                                      name="bookDetail.description" rows="3">${book.bookDetail.description}</textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12" style="text-align: right">
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </div>
            </mvc:form>
        </div>
    </body>
</html>
