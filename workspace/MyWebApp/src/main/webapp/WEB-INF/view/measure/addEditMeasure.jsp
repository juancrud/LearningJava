<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form commandName="newMeasure" id="saveMeasureForm" action="Measure/AddEditMeasure">
	<form:hidden path="id"/>
	<div class="formrow">
		<div class="formitem">
			<form:label path="name">
				<spring:message code="measures.field.name"/>:
			</form:label>
		</div>
		<div class="formitem">
			<form:input path="name" cssClass="text ui-widget-content ui-corner-all"/>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem">
			<form:label path="alias">
				<spring:message code="measures.field.alias"/>:
			</form:label>
		</div>
		<div class="formitem">
			<form:input path="alias" cssClass="text ui-widget-content ui-corner-all"/>
		</div>
	</div>
</form:form>
