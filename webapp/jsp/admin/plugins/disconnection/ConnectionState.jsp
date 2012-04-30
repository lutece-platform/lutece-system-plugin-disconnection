<%@ page errorPage="../../ErrorPage.jsp" %>

<jsp:useBean id="deconnection" scope="session" class="fr.paris.lutece.plugins.disconnection.web.DisconnectionJspBean" />
<%
	deconnection.getState( request, response );
%>
