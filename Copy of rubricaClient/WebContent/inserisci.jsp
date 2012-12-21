<%@page import="javax.naming.*"%>
<%@page import="javax.persistence.*"%>
<%@page import="javax.persistence.*"%>
<%@page import="java.util.List"%>
<%@page import = "provaRubrica.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Stato</h1>
		<hr>
			<%	String nomeInserito;
				String numeroInserito;

				if (request.getParameter("nome") != "" && request.getParameter("numero") != "") {
					nomeInserito = request.getParameter("nome");
					numeroInserito = request.getParameter("numero");
				
					try {
						Context context = new InitialContext();
						Object ref = context.lookup("RubBean/remote");
						RubBeanRemote r = (RubBeanRemote) ref;	
						r.inserisci(nomeInserito, numeroInserito);
						out.println("Utente inserito");
					}
					catch (Exception e) {
						out.println("Error");
					}		
    			}
			%>
			<h6>
				<a href="index.html">Back</a>
			</h6>
</body>
</html>