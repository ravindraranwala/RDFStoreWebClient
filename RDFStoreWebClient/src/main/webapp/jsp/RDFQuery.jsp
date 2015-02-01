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
		<ol>
			<li><s:url action="buildGraph.action" var="urlTag">
				</s:url> <a href="<s:property value="#urlTag" />">Build RDF Graph</a></li>
			<li><s:url action="dropRDFStore.action" var="dropStore">
				</s:url> <a href="<s:property value="#dropStore" />">Drop RDF Store</a></li>
			<li><s:url action="createRDFStore.action" var="createStore">
				</s:url> <a href="<s:property value="#createStore" />">Create RDF Store</a></li>
		</ol>
		<s:textarea lable="Sparql" name="sparql" cols="40" rows="20" />
		<s:submit method="execute" value="execute" align="center" />
	</s:form>
</body>
</html>