<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="line mbs">
	<button id="addOrderBtn" class="button">
		<spring:message code="orders.button.add"/>
	</button>
</div>
<div class="line">
	<table id="ordersTable" class="dtbl">
		<thead>
			<tr>
				<th class="w10p">
					<spring:message code="orders.field.id"/>
				</th>
				<th class="w20p">
					<spring:message code="orders.field.date"/>
				</th>
				<th class="w30p">
					<spring:message code="orders.field.market"/>
				</th>
				<th class="w10p">
					<spring:message code="orders.field.totalPrice"/>
				</th>
				<th class="w30p">
					<spring:message code="general.actions"/>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ordersList}" var="order">
				<tr data-id="${order.id}">
					<td>
						<c:out value="${order.id}"></c:out>
					</td>
					<td>
						<c:out value="${order.dateString}"></c:out>
					</td>
					<td>
						<c:out value="${order.marketName}"></c:out>
					</td>
					<td>
						<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.totalPrice}"/>
					</td>
					<td>
						<span><a class="editOrderBtn"><spring:message code="orders.button.edit"/></a></span>
						|
						<span><a class="deleteOrderBtn"><spring:message code="orders.button.remove"/></a></span>
						|
						<span><a class="toggleOrderLines"><spring:message code="orders.button.showOrderLines"/></a></span>
						<div class="hide">
							<div class="mbm orderLines"></div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="saveOrderModal" class="hide"></div>
<div id="saveOrderLineModal" class="hide"></div>

<script type="text/javascript" src="<c:url value="/static/resources/js/Order/orders.js"/>"></script>