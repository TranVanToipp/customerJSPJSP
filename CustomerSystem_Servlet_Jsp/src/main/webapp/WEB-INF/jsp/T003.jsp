<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit - Training</title>
</head>
<body>
<%@page import="fjs.cs.dao.T003Dao,fjs.cs.dto.T003Dto"%>  
<%
	String customerId = request.getParameter("id");
	T003Dto dto = new T003Dto();
	dto.setCUSTOMER_ID(Integer.parseInt(customerId));
	HttpSession sessionObj = request.getSession();
	T003Dao dao = new T003Dao();
	int result = dao.update(dto, sessionObj);
%>

	<div id="main">
  <h1 class="error">Thông báo</h1>
  <div class="comntent-customer_id">
    <span>Customer Id</span>
    <input type="text" name="customerId" value="<%= dto.getCUSTOMER_ID() %>"/>
  </div>
  <div class="comntent-customer_name">
    <span>Customer Name</span>
    <input type="text" name="customerName" value="<%= dto.getCUSTOMER_NAME() %>"/>
  </div>
  <div class="comntent-customer_sex">
    <span>Sex</span>
    <select name="sex">
      <option value=""></option>
      <option value="1" <%= dto.getSEX().equals("1") ? "selected" : "" %>>Nam</option>
      <option value="0" <%= dto.getSEX().equals("0") ? "selected" : "" %>>Nữ</option>
    </select>
  </div>
  <div class="comntent-customer_birthday">
    <span>Customer birthday</span>
    <input type="text" name="birthday" value="<%= dto.getBIRTHDAY() %>"/>
  </div>
  <div class="comntent-customer_email">
    <span>Customer Email</span>
    <input type="text" name="email" value="<%= dto.getEMAIL() %>"/>
  </div>
  <div class="comntent-customer_address">
    <span>Customer Address</span>
    <input type="text" name="address" value="<%= dto.getADDRESS() %>"/>
  </div>
  <button type="submit">Save</button>
</div>

</body>
</html>