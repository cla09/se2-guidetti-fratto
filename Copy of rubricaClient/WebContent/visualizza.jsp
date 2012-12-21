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
<title>Elenco utenti</title>
</head>
<body>
	<h1>Elenco utenti</h1>
<% 
	List<Rub> risultato;
	try {
		Context context = new InitialContext();
		Object ref = context.lookup("RubBean/remote"); //bisogna metterci il nome della classe che implementa i metodi dell'interfaccia remota
		RubBeanRemote r = (RubBeanRemote) ref;
		risultato = r.getAllRub();

		for (Rub t : risultato) {
			out.println(t);
		%>
		<br>
		<%
			//out.println(t.getNome() + "<-->" + t.getNumber() + "\n\n\n");
		}
		if(risultato.size()==0){
			%> <p>Vuota</p> <%
		}	
		
	}
	catch (Exception e){
		out.println("Some errors");		
	}


%>
	<h6><a href="index.html">Back</a></h6>
	
</body>
</html>