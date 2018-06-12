<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:set var="products2" value="${products}" scope="page"/>
<ul>
	<c:forEach items="${categories}" var="category">
   		<li id="cat${category.id}" class="isFolder" >
   			${category.name}
   			
   			<c:set var="categories" value="${category.childCategories}" scope="request"/>
			<c:set var="products" value="${category.childProducts}" scope="request"/>
   			<tiles:insertTemplate template="/WEB-INF/view/catalog/showCatalogItems.jsp"/>
   		</li>
	</c:forEach>
	
	<c:forEach items="${products2}" var="product">
   		<li id="prod${product.id}">
   			${product.name}
   		</li>
	</c:forEach>
</ul>
