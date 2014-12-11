<%-- 
    Document   : index
    Created on : 29/08/2012, 03:34:16
    Author     : Felipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina Teste</title>
</head>
<body>
	<h1>Seja Bem Vindo ao Services do Moodle Droid!</h1>
	<p>Teste de Login:</p>
	<form method="post"
		action="http://localhost:8080/MoodleDroidServices/rest/listener/retornaNoticias">
		<p>login:</p><input type="text" name="login" /><br /> 
		<p>Senha:</p><input type="text" name="senha" /><br /> 
		<p>flag de decriptação(0 - off, 1 - on):</p><input type="text" name="flagDecriptacao" title="aaaa" />
		<br /> 
		<input type="submit" value="Enviar" />
	</form>
	<form method="post"
		action="http://localhost/moodle/webservice/rest/server.php?wstoken=6918d7f9ae007fb3a7a5b09abe8e8065&wsfunction=moodle_user_get_users_by_id">
		<select multiple="true" name="userids">
			<option value="2">aa</option>
			<option value="1">aab</option>
		</select>
		<br /> 
<!-- 		<p>service:</p><input type="text" name="wsfunction" value=""/><br />  -->
<!-- 		<p>token:</p><input type="text" name="wstoken" value="" /><br />  -->
		<input type="submit" value="Enviar2" />
	</form>
	<br />
	<br />
	<hr>

</body>
</html>
