<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page - Struts 2 - Login Application</title>
</head>
<body>
	<a href="/RDFStoreWebClient/jsp/RDFQuery.jsp">Home</a>
	<h2>Listing Results of the SPARQL Query.</h2>
	<s:set name="xmltext" value="result" />
	<c:import url="/xslt/sparqlResultFormater.xsl" var="xslt" />
	<x:transform xml="${xmltext}" xslt="${xslt}" />

</body>
</html>