<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    <style>

        <!-- sidenavbar -->
        body {
            font-family: "Calibri", sans-serif;
        }

        .sidenav {
            height: 100%;
            width: 250px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #F5F5F5;
            overflow-x: hidden;
            padding-top: 20px;
        }

        .sidenav a {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 20px;
            color: #0000ff;
            display: block;
        }

        .sidenav a:hover {
            color: #000000;
            text-decoration: none;
        }

        .main {
            margin-left: 200px; /* Same as the width of the sidenav */
        }

    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-*">
            <!-- sidenavbar -->
            <%@page import="com.project.entity.Login" %>
            <%@page import="org.springframework.web.servlet.ModelAndView" %>
            <%@ page import="com.project.entity.Patient" %>
            <% String Name = session.getAttribute("Name").toString();	 %>


        <div class="col-sm-12">
            <!-- display window -->
            <div class="main"><br/><br/><br/><br/><br/><br/><br/><br/>
                <h1><div style="text-align:center;">
                    Dear <span style="color: orange;"><%= Name %> </span> <br/>
                    Operation Successfully Completed !
                </div></h1>
            </div>
        </div>

            <br/><br/><br/>
            <form action="login.html" method="post">
                <button type="submit" class="btn btn-primary">&nbsp;&nbsp; Login &nbsp;&nbsp;</button>
            </form>
    </div>
</div>

</body>
</html>
<body>