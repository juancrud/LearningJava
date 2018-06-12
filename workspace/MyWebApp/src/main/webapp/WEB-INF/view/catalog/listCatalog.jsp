<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="line mbs">
	<c:if test="${isAddable}">
		<button id="addCategoryBtn" class="button">
			<spring:message code="categories.button.add"/>
		</button>
		<button id="addProductBtn" class="button">
			<spring:message code="products.button.add"/>
		</button>
	</c:if>
	<c:if test="${isEditable}">
		<button id="editBtn" class="button">
			<spring:message code="general.edit"/>
		</button>
	</c:if>
	<c:if test="${isDeletable}">
		<button id="deleteBtn" class="button easytree-droppable easytree-accept">
			<spring:message code="general.remove"/>
		</button>
	</c:if>
</div>
<div class="line">
	<div id="catalogTree">
		<c:set var="categories" value="${categoriesList}" scope="request"/>
		<c:set var="products" value="${productsList}" scope="request"/>
		<tiles:insertDefinition name="showCatalogItems"/>
	</div>
</div>
<div id="saveCategoryModal" class="hide showOverflow"></div>
<div id="saveProductModal" class="hide showOverflow"></div>

<script type="text/javascript" src="<c:url value="/static/resources/js/Catalog/catalog.js"/>"></script>