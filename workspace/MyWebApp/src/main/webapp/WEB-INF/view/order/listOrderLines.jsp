<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="separator" value="${orderId == 0 ? '' : '_'}"/>
<c:set var="tableOrderId" value="${orderId == 0 ? '' : orderId}"/>
<table id="orderLinesTable${separator}${tableOrderId}" class="dtbl">
	<thead>
		<tr>
			<th><spring:message code="orderLines.field.id"/></th>
			<th><spring:message code="orderLines.field.product"/></th>
			<th><spring:message code="orderLines.field.quantity"/></th>
			<th><spring:message code="orderLines.field.unitPrice"/></th>
			<th><spring:message code="orderLines.field.linePrice"/></th>
			<th><spring:message code="general.actions"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${orderLinesList}" var="orderLine" varStatus="counter">
			<c:set var="index" value="${counter.index}"/>
			<tr class="orderLine" data-id="${orderLine.id}" data-product-id="${orderLine.productId}" data-quantity="${orderLine.quantity}" data-unit-price="${orderLine.unitPrice}">
				<td>
					${orderLine.id}
				</td>
				<td>
					${orderLine.productName}
				</td>
				<td>
					<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${orderLine.quantity}"/>
				</td>
				<td>
					<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${orderLine.unitPrice}"/>
				</td>
				<td>
					<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${orderLine.linePrice}"/>
				</td>
				<td>
					<span><a class="editOrderLineBtn"><spring:message code="orderLines.button.edit"/></a></span>
					|
					<span><a class="deleteOrderLineBtn"><spring:message code="orderLines.button.remove"/></a></span>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
