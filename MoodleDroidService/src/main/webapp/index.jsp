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
		action="http://localhost:8080/IFMoodleDroidServices/rest/listener/login">
		<p>login:</p><input type="text" name="login" /><br /> 
		<p>Senha:</p><input type="text" name="senha" /><br /> 
		<p>flag de encriptação(0 - off, 1 - on):</p><input type="text" name="flagEncriptacao" title="aaaa" />
		<br /> 
		<input type="submit" value="Enviar" />
	</form>
	<br />
	<br />
	<hr>

</body>
</html>
