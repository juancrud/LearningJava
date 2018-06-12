<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="line mbs">
	<button id="addMeasureBtn" class="button">
		<spring:message code="measures.button.add"/>
	</button>
</div>
<div class="line">
	<table id="measuresTable" class="dtbl">
		<thead>
			<tr>
				<th class="w10p">
					<spring:message code="measures.field.id"/>
				</th>
				<th class="w60p">
					<spring:message code="measures.field.name"/>
				</th>
				<th class="w10p">
					<spring:message code="measures.field.alias"/>
				</th>
				<th class="w20p">
					<spring:message code="general.actions"/>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${measuresList}" var="measure">
				<tr data-id="${measure.id}">
					<td>
						<c:out value="${measure.id}"></c:out>
					</td>
					<td>
						<c:out value="${measure.name}"></c:out>
					</td>
					<td>
						<c:out value="${measure.alias}"></c:out>
					</td>
					<td>
						<a class="editMeasureBtn"><spring:message code="measures.button.edit"/></a>
						|
						<a class="deleteMeasureBtn"><spring:message code="measures.button.remove"/></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="saveMeasureModal" class="hide"></div>

<script type="text/javascript" src="<c:url value="/static/resources/js/Measure/measures.js"/>"></script>