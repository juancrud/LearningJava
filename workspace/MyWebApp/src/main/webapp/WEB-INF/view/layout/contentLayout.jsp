<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="mod">
	<div class="hd flashy">
		<div class="h1 lightHead">
			<tiles:importAttribute name="contentName"/>
			<spring:message code="${contentName}"/>
		</div>
	</div>
	<div class="bd showOverflow pas">
		<tiles:insertAttribute name="content" />
	</div>
</div>
