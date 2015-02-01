<xsl:stylesheet version="1.0" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:sp="http://www.w3.org/2005/sparql-results#" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="sp:sparql">
		<html>
			<head>
				<title>XSLT demo</title>
			</head>
			<body>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>
							<xsl:value-of select="sp:head/sp:variable[1]/@name" />
						</th>
						<th>
							<xsl:value-of select="sp:head/sp:variable[2]/@name" />
						</th>
					</tr>
					<xsl:apply-templates />
				</table>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="sp:result">
		<tr>
			<td>
				<xsl:value-of select="sp:binding[@name][1]" />
			</td>
			<td>
				<xsl:value-of select="sp:binding[@name][2]" />
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>