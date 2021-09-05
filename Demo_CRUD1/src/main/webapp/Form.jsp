<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/UserServlet" method="post">
		ID<input name="id" type="text" value="${user.id }"><br> <br>
		Pass<input name="pass" type="password" value="${user.pass }"><br>
		<br> Name<input name="name" type="text" value="${user.name }"><br>
		<br> Email<input name="email" type="text" value="${user.email }"><br>
		<br> Admin: <br>True <input type="radio" name="admin"
			value="True" ${user.admin ?"checked":"" }> False <input
			type="radio" name="admin" value="False" ${!user.admin ?"checked":"" }>
		<br> <br>
		<button formaction="/Demo_CRUD1/UserServlet/insert">Insert</button>
		<button formaction="/Demo_CRUD1/UserServlet/update">Update</button>
		<button formaction="/Demo_CRUD1/UserServlet/reset">Reset</button>
		<br> <br>
	</form>
	<div>
		<table border="2" style="width: 100%">
			<thead>
				<tr>
					<td>ID</td>
					<td>Pass</td>
					<td>Name</td>
					<td>Email</td>
					<td>Admin</td>
					<td colspan="2">Action</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ listUser }" var="user">
					<tr>
						<td>${ user.id }</td>
						<td>${ user.pass }</td>
						<td>${ user.name }</td>
						<td>${ user.email }</td>
						<td>${ user.admin ? "True" : "False" }</td>
						<td><a href="/Demo_CRUD1/UserServlet/edit?id=${ user.id }">Edit</a></td>
						<td><a href="/Demo_CRUD1/UserServlet/delete?id=${ user.id }">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>