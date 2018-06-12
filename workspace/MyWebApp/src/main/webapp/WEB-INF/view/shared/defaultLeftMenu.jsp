<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="leftMenu" class="leftMenu">
	<ul>
		<c:forEach items="${menuItems}" var="menuItem">
			<li>
				<div>
					<a id="${menuItem.itemId}" data-content-url="${menuItem.itemContentUrl}" class="menuItem">${menuItem.itemText}</a>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>
