<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Abilita" %>

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
			<%
				//eventualmente il messaggio
			%>
			<div id="body">
				<div id="boxCenter">
					<div id="titoloBox">
						<center>Completa la registrazione</center>
					</div>
					<form action="" method="post">
						<div id="informazioniBox">
							<p>
								<label for="rAvatar">Avatar:</label>
								<input id="rAvatar" name="rAvatar" type="file" accept="image/*">
							</p>
							<p>
								<label>Abilit&agrave;:</label>
								<%
									List<Abilita> abilitaDisponibili = (List<Abilita>) request.getAttribute("abilitaDisponibili");
									for(Abilita abilita: abilitaDisponibili) {
								%>
										<img src="<%= abilita.getIcona() %>">
										<input name="abilitaScelte" type="checkbox" value="<%= abilita.getCodice() %>">
										<%= abilita.getNome() %>
										<br>
								<%
									}
								%>
							</p>
						</div>
						<center>
							<input id="pulsante" type="submit" value="Cocludi">
						</center>
					</form>
				</div>
			</div>
		</div>
	
	</body>
</html>