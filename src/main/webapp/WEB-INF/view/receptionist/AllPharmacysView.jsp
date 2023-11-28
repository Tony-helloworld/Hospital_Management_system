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

<%@ include file="../header.jsp" %>

<div class="container">
  <div class="row">
    <div class="col-sm-*">
      <!-- sidenavbar -->
      <%@page import="com.project.entity.Login" %>
      <%@page import="org.springframework.web.servlet.ModelAndView" %>
      <% Login l=(Login)session.getAttribute("userInfo");	 %>
      <div class="sidenav">
        <a><br/><br/>
          <div style="background-color: rgba(255,0,0,0.4);">&nbsp;&nbsp;&nbsp;
            <span class="badge badge-pill badge-warning">&nbsp;&nbsp;<%= l.getRole().toUpperCase() %>&nbsp;&nbsp;</span><br/><br/>
            <b>Username:</b> <%= l.getUsername() %><br/><br/>
            <b>Id:</b> <%= l.getId() %><br/>
            <a href="editView.html">
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="text-decoration:underline; color:green;">edit...</span>
            </a>
          </div>
        </a><br/>

        <% if(!l.getId().equals("EMP100")){ %>
        <a href="personalInfo.html">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Personal Info
        </a>
        <%} %>
        <% if(l.getRole().equals("administrator")){ %>
        <a href="addEmployeeView.html">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add Employee
        </a>
        <a href="searchEmployeeView.html">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Search Employee
        </a>
        <a href="allEmployeesView.html">
          <span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Employees</span>
        </a>
        <a href="allRoomsView.html">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All Rooms

        </a>
        <a href="ICUInfo2.html">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ICU Info
        </a>
        <a href="OperationRoomInfo2.html">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OperationRoom Info
        </a>
        <a href="PharmacyInfo2.html">
          <span class="text-warning">&nbsp;&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;Pharmacy Info</span>
        </a>
        <%} %>
      </div>
    </div>

    <div class="col-sm-12">
      <!-- display window -->
      <div class="main"><br/><br/>
        <h1>All Drugs</h1><br/>
        <table class="table table-striped table-bordered">
          <%@page import="com.project.entity.Employee, java.util.List" %>
          <%@ page import="com.project.entity.Room" %>
          <%@ page import="com.project.entity.Pharmacy" %>
          <thead>
          <tr>
            <td>Drug Name</td>
            <td>Drug number</td>
<%--            <td>Max  number</td>--%>
            <td>Action</td>
<%--            <td>Set max</td>--%>
          </tr>
          </thead>
          <tbody>
          <%
            List<Pharmacy> Pharmacys=(List<Pharmacy>) request.getAttribute("Pharmacys");
            for(Pharmacy pharmacy: Pharmacys)
            {
          %>
          <tr>
            <td><%= pharmacy.getName() %></td>
            <td><%= pharmacy.getDrugNumber() %>&nbsp;</td>
<%--            <td><%= room.getMaxRoom() %></td>--%>
            <td>
              <form action="changePharmacy.html" method="post">
                <input type="text" name="id" value="<%= pharmacy.getId()%>" hidden="">
                <div style="text-align: center;">
                  <input type="submit" class="btn btn-info" value="Change">
                </div>
              </form>
            </td>
          </tr>
          <%
            }
          %>
          </tbody>
      </div>
    </div>

  </div>
</div>

</body>
</html>
<body>