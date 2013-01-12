<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileNotFoundException" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>SWIMv2</title>
	</head>
	<body>
		<div id="pagina">
			<div id="logo">
				<img src="Immagini/logo.png">
			</div>
			<div id="body">
				<div id="boxHomePage">
					<p id="titoloBox">Effettua la registrazione</p>
					<form action="" method="post">
						<div id="informazioniRegistrazione">
							<p>
								<label for="rNome">Nome:</label>
								<input id="rNome" name="rNome" type="text" maxlength="20" required="required">
							</p>
							<p>
								<label for="rCognome">Cognome:</label>
								<input id="rCognome" name="rCognome" type="text" maxlength="20" required="required">
							</p>
							<p>
								<label for="rSesso">Sesso:</label>
								<select id="rSesso" name="rSesso">
									<option value="Femmina">Femmina</option>
									<option value="Maschio">Maschio</option>
								</select>
							</p>
							<p>
								<label for="rAnnoNascita">Anno di nascita:</label>
								<select id="rAnnoNascita" name="rAnnoNascita">
									<%
										GregorianCalendar now = new GregorianCalendar();
										for(int i = now.get(GregorianCalendar.YEAR); i > 1850; i--) {
									%>
											<option value=" <%= i %> "><%= i %></option>
									<%
										}
									%>
								</select>
							</p>
							<p>
								<label for="rCittà">Città:</label>
								<select id="rCittà" name="rCittà">
									<%
									    String città = null;
								        Scanner reader = null;
								        String filePath = "/WEB-INF/comuniItaliani.txt";
								        File file = new File(getServletContext().getRealPath(filePath));
								        try {
								            reader = new Scanner(file);
								        } catch(FileNotFoundException e) {
								        	System.out.println(e.getMessage());
								        }
								        if(reader != null) {
								            while (reader.hasNextLine()) {
								                città = reader.nextLine();
								    %>
								    			<option value=" <%= città %> "><%= città %></option>
								    <%
								            }
								        }
								    %>
								</select>
							</p>
							<p>
								<label for="rNickname">Nickname:</label>
								<input id="rNickname" name="rNickname" type="text" maxlength="20" required="required">
							</p>
							<p>
								<label for="rPassword">Password:</label>
								<input id="rPassword" name="rPassword" type="password" maxlength="20" required="required">
							</p>
							<p>
								<label for="rEmail">Email:</label>
								<input id="rEmail" name="rEmail" type="email" maxlength="255" required="required">
							</p>
							<p>
								<label for="rAvatar">Avatar:</label>
								<input id="rAvatar" name="rAvatar" type="file" accept="image/*">
							</p>
						</div>
						<input id="pulsante" type="submit" value="Registrazione">
					</form>
				</div>
				<div id="boxHomePage">
					<p id="titoloBox">Esegui l'accesso</p>
					<form action="" method="post">
						<div id="informazioniLogin">
							<p>
								<label for="nickname">Nickname:</label>
								<input id="nickname" name="nickname" type="text" maxlength="20" required="required">
							</p>
							<p>
								<label for="password">Password:</label>
								<input id="password" name="password" type="password" maxlength="20" required="required">
							</p>
						</div>
						<input id="pulsante" type="submit" value="Login">
					</form>
				</div>
				<div id="boxHomePage">
					<p id="titoloBox">Accedi senza regstrarti</p>
					<form action="" method="post">
						<input id="pulsante" type="submit" value="Accesso Guest">
					</form>
				</div>
			</div>
		</div>		
	</body>
</html>