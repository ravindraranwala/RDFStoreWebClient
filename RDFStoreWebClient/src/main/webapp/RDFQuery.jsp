<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:form action="execute.action" method="post">
		<s:url action="buildGraph.action" var="urlTag">
		</s:url>
		<a href="<s:property value="#urlTag" />">Build RDF Graph</a>
		<s:textarea lable="Sparql" name="sparql" cols="40" rows="20" />
		<s:submit method="execute" value="execute" align="center" />
	</s:form>
</body>
</html>