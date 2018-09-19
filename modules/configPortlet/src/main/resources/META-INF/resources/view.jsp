<%@ include file="./init.jsp" %>
<%@page import="configPortlet.portlet.ColorConfiguration" %>
<%@page import="HelloService.HelloService" %>

<%
	ColorConfiguration configuration = (ColorConfiguration) renderRequest.getAttribute(ColorConfiguration.class.getName());
	String color0= (String) renderRequest.getAttribute("color");
	HelloService service= (HelloService)renderRequest.getAttribute("service");
%>
<p style="color:<%=color0%>">
	service.
	<b>  <%=service.sayHello()%>  </b> <br />
	<b>  my color is <%=color0%>  </b> <br />
	<b>  my color (again) is <%=configuration.color()%>  </b>
</p>
