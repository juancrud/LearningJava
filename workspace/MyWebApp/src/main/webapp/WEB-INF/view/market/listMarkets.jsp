<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="line mbs">
	<button id="addMarketBtn" class="button">
		<spring:message code="markets.button.add"/>
	</button>
</div>
<div class="line">
	<table id="marketsTable" class="dtbl">
		<thead>
			<tr>
				<th class="w10p">
					<spring:message code="markets.field.id"/>
				</th>
				<th class="w70p">
					<spring:message code="markets.field.name"/>
				</th>
				<th class="w20p">
					<spring:message code="general.actions"/>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${marketsList}" var="market">
				<tr data-id="${market.id}">
					<td>
						<c:out value="${market.id}"></c:out>
					</td>
					<td>
						<c:out value="${market.name}"></c:out>
					</td>
					<td>
						<span><a class="editMarketBtn"><spring:message code="markets.button.edit"/></a></span>
						|
						<span><a class="deleteMarketBtn"><spring:message code="markets.button.remove"/></a></span>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="saveMarketModal" class="hide"></div>

<script type="text/javascript" src="<c:url value="/static/resources/js/Market/markets.js"/>"></script>