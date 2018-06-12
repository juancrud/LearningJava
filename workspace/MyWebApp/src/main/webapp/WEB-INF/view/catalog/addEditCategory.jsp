<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form commandName="newCategory" id="saveCategoryForm" action="Catalog/AddEditCategory">
	<form:hidden path="id"/>
	
	<div class="formrow">
		<div class="formitem">
			<form:label path="name"><spring:message code="categories.field.name"/>:</form:label>
		</div>
		<div class="formitem">
			<form:input path="name" cssClass="text ui-widget-content ui-corner-all"/>
		</div>
	</div>
	<div class="formrow">
		<div class="formitem">
			<form:label path="parentId"><spring:message code="categories.field.parent"/>:</form:label>
		</div>
		<div class="formitem">
			<form:select path="parentId">
				<form:option value="0"><spring:message code="general.none"/></form:option>
				<form:options items="${categoriesList}" itemValue="value" itemLabel="displayValue"/>
			</form:select>
		</div>
	</div>
</form:form>