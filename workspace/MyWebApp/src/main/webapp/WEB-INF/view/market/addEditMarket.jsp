<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form commandName="newMarket" id="saveMarketForm" action="Market/AddEditMarket">
	<form:hidden path="id"/>
	<div class="formrow">
		<div class="formitem">
			<form:label path="name">
				<spring:message code="markets.field.name"/>:
			</form:label>
		</div>
		<div class="formitem">
			<form:input path="name" cssClass="text ui-widget-content ui-corner-all"/>
		</div>
	</div>
</form:form>
