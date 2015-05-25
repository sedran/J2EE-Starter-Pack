<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>
<!-- jQuery -->
<script src="/static/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/static/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/static/js/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/static/js/raphael-min.js"></script>
<script src="/static/js/morris.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/static/js/sb-admin-2.js"></script>

<script src="/static/js/angular.min.js"></script>
<script src="/static/js/angular-route.min.js"></script>
<script src="/static/js/ui-bootstrap-tpls-0.13.0.min.js"></script>

<%-- Javascript Files defined in tiles.xml --%>
<tiles:importAttribute name="jsFiles" ignore="true" />
	
<%-- for Javascript Files in this project --%>
<c:forEach items="${jsFiles}" var="jsFile">
	<script type="text/javascript" src="<c:url value="${jsFolder}/${jsFile}?v=${cacheVersion}" />"></script>
</c:forEach>