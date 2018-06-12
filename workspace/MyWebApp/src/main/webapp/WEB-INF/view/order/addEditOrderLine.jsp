<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form commandName="newOrderLine" id="saveOrderLineForm" action="Order/AddEditOrderLine">
	<form:hidden path="id"/>
	<form:hidden path="index"/>
	<div class="formrow">
		<div class="formitem">
			<form:label path="productId"><spring:message code="orderLines.field.product"/>:</form:label>
		</div>
		<div class="formitem">
			<form:select path="productId" items="${productsList}" itemValue="value" itemLabel="displayValue"></form:select>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem">
			<form:label path="quantity"><spring:message code="orderLines.field.quantity"/>:</form:label>
		</div>
		<div class="formitem">
			<form:input path="quantity" cssClass="text ui-widget-content ui-corner-all"/>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem">
			<form:label path="unitPrice"><spring:message code="orderLines.field.unitPrice"/>:</form:label>
		</div>
		<div class="formitem">
			<form:input path="unitPrice" cssClass="text ui-widget-content ui-corner-all"/>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem">
			<form:label path="linePrice"><spring:message code="orderLines.field.linePrice"/>:</form:label>
		</div>
		<div class="formitem">
			<form:input path="linePrice" cssClass="text ui-widget-content ui-corner-all" disabled="true"/>
		</div>
	</div>
</form:form>
