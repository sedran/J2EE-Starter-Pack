<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<tiles:insertAttribute name="head" />
</head>

<body>
	<div id="wrapper">
		<tiles:insertAttribute name="nav" />
		<tiles:insertAttribute name="body" />
	</div>
	
	<tiles:insertAttribute name="footer" />
</body>
</html>