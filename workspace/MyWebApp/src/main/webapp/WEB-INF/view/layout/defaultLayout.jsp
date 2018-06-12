<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<head>
		<title><spring:message code="general.appName"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/jquery-ui/black-tie/jquery-ui-1.10.3.custom.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/jquery-easytree/skin-lion/ui.easytree.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/jquery-datatable/jquery.dataTables.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/jquery-chosen/chosen.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/defaultCss.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/space.css"/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/width.css"/>"/>
		
		<script type="text/javascript" src="<c:url value="/static/resources/js/ThirdParty/jquery-1.9.1.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/ThirdParty/jquery-ui-1.10.3.custom.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/ThirdParty/jquery.dataTables.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/ThirdParty/jquery.easytree.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/ThirdParty/chosen.jquery.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/ThirdParty/jstorage.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/Util/Util.namespace.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/Util/Util.Ajax.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/Util/Util.Storage.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/Util/Util.Content.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/Util/Util.Modal.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/Util/Util.Utilities.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/static/resources/js/Main/leftMenu.js"/>"></script>
		
	</head>
	
	<body>
		<div class="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="body">
		    <div class="sidebar">
		        <tiles:insertAttribute name="menu" />
		    </div>
		    <div id="content" class="content">
		    	<tiles:insertAttribute name="content" />
		    </div>
		</div>
		<div class="footer">
		    <tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>
