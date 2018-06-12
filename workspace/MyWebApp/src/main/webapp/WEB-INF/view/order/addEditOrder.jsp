<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form commandName="newOrder" id="saveOrderForm" action="Order/AddEditOrder">
	<form:hidden path="id"/>
	<div class="formrow">
		<div class="formitem">
			<form:label path="date"><spring:message code="orders.field.date"/>:</form:label>
		</div>
		<div class="formitem">
			<form:input path="date" value="${newOrder.dateString}" cssClass="text ui-widget-content ui-corner-all"/>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem">
			<form:label path="marketId"><spring:message code="orders.field.market"/>:</form:label>
		</div>
		<div class="formitem">
			<form:select path="marketId" items="${marketsList}" itemValue="value" itemLabel="displayValue"></form:select>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem">
			<form:label path="orderLines"><spring:message code="orders.field.totalPrice"/>:</form:label>
		</div>
		<div class="formitem">
			<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${newOrder.totalPrice}" var="formattedTotalPrice"/>
			<form:input path="totalPrice" class="text ui-widget-content ui-corner-all" value="${formattedTotalPrice}" disabled="true"/>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem">
			<button id="addOrderLineBtn" class="button"><spring:message code="orderLines.button.add"/></button>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem w100p">
			<c:set var="orderId" value="0" scope="request"/>
			<c:set var="orderLinesList" value="${newOrder.orderLines}" scope="request"/>
			<tiles:insertDefinition name="listOrderLines"/>
		</div>
	</div>
</form:form>
