(function () {
    "use strict";
    
    var catalogTree = null;
    
    var saveCategory = function() {
    	var modal = $('#saveCategoryModal');
		var form = modal.find('#saveCategoryForm');
	    var options = {
	        url : form.attr('action'),
	        data : form.serializeArray(),
	        success: function(result) {
	        	if(result.success) {
	        		var categoryId = result.entity.id;
	        		var categoryName = result.entity.name;
	        		var parentId = result.entity.parentId;
	        		var parentNodeId = parentId === 0 ? null : ('cat' + parentId);
	        		var nodeId = 'cat' + categoryId;
	        		var newNode = {
	        			isFolder: true,
	        			id: nodeId,
	    	        	text: categoryName,
	        		};
	        		
		        	if(result.responseType === 'CREATE') {
		        	}
		        	else {
		        		catalogTree.removeNode(nodeId);
		        	}
		        	catalogTree.addNode(newNode, parentNodeId);
		        	catalogTree.rebuildTree();
		        	modal.dialog('close');
	        	}
	        	else {
	        		Util.Modal.ajaxResponseError(result);
	        	}
	        }
	    };
	    Util.Ajax.Post(options);
    };
    
    var loadAddEditCategoryModal = function(parentId, categoryId) {
    	var id = categoryId || 0;
    	var pId = parentId || 0;
    	var modalTitle = id === 0 ? Util.Storage.get('categories.modal.addCategory') : Util.Storage.get('categories.modal.editCategory');
    	
    	var saveCategoryModal = $('#saveCategoryModal');
		var config = {
			requestUrl: 'Catalog/AddEditCategory?id=' + id + '&parentId=' + pId,
			postContentLoaded: function() {
				saveCategoryModal.dialog('option', 'title', modalTitle);
				saveCategoryModal.dialog('open');
			}
		};
		Util.Content.replaceContent(saveCategoryModal, config);
    };
    
    var deleteCategory = function(categoryId) {
    	var confirmationConfig = {
			message: Util.Storage.get('categories.confirmDelete'),
			url: 'Catalog/DeleteCategory?id='+categoryId,
    		success: function(result) {
    			if(result.success) {
	        		var nodeId = 'cat' + categoryId;
	        		catalogTree.removeNode(nodeId);
		        	catalogTree.rebuildTree();
	        	}
	        	else {
	        		Util.Modal.ajaxResponseError(result);
	        	}
    		},
    		isPost: true
		};
		Util.Modal.confirmationModal(confirmationConfig);
    };
    
    var moveCategory = function(categoryId, parentId) {
	    var options = {
	        url : 'Catalog/MoveCategory?id=' + categoryId + '&parentId=' + parentId,
	        success: function(result)
	        {
	        	if(result.success) {
		        	catalogTree.rebuildTree();
	        	}
	        	else {
	        		Util.Modal.ajaxResponseError(result);
	        	}
	        }
	    };
	    Util.Ajax.Post(options);
    };
    
    var saveProduct = function() {
    	var modal = $('#saveProductModal');
		var form = modal.find('#saveProductForm');
	    var options = {
	        url : form.attr('action'),
	        data : form.serializeArray(),
	        success: function(result)
	        {
	        	if(result.success) {
	        		var productId = result.entity.id;
	        		var productName = result.entity.name;
	        		var parentId = result.entity.parentId;
	        		var nodeId = 'prod' + productId;
	        		var parentNodeId = parentId === 0 ? null : ('cat' + parentId);
	        		var newNode = {
	        			isFolder: false,
	        			id: nodeId,
	    	        	text: productName,
	        		};
	        		
		        	if(result.responseType === 'CREATE') {
		        	}
		        	else {
		        		catalogTree.removeNode(nodeId);
		        	}
		        	catalogTree.addNode(newNode, parentNodeId);
		        	catalogTree.rebuildTree();
		        	modal.dialog('close');
	        	}
	        	else {
	        		Util.Modal.ajaxResponseError(result);
	        	}
	        }
	    };
	    Util.Ajax.Post(options);
    };
    
    var loadAddEditProductModal = function(parentId, productId) {
    	var id = productId || 0;
    	var pId = parentId || 0;
    	var modalTitle = id === 0 ? Util.Storage.get('products.modal.addProduct') : Util.Storage.get('products.modal.editProduct');
    	
    	var saveProductModal = $('#saveProductModal');
		var config = {
			requestUrl: 'Catalog/AddEditProduct?id=' + id + '&parentId=' + pId,
			postContentLoaded: function() {
				saveProductModal.dialog('option', 'title', modalTitle);
				saveProductModal.dialog('open');
			}
		};
		Util.Content.replaceContent(saveProductModal, config);
    };
    
    var deleteProduct = function(productId) {
    	var confirmationConfig = {
			message: Util.Storage.get('products.confirmDelete'),
			url: 'Catalog/DeleteProduct?id=' + productId,
    		success: function(result) {
    			if(result.success) {
    				var nodeId = 'prod' + productId;
            		catalogTree.removeNode(nodeId);
    	        	catalogTree.rebuildTree();
	        	}
	        	else {
	        		Util.Modal.ajaxResponseError(result);
	        	}
    		},
    		isPost: true
		};
		Util.Modal.confirmationModal(confirmationConfig);
    };
    
    var moveProduct = function(productId, parentId) {
	    var options = {
	        url : 'Catalog/MoveProduct?id=' + productId + '&parentId=' + parentId,
	        success: function(result)
	        {
	        	if(result.success) {
		        	catalogTree.rebuildTree();
	        	}
	        	else {
	        		Util.Modal.ajaxResponseError(result);
	        	}
	        }
	    };
	    Util.Ajax.Post(options);
    };
    
    var getSelectedNodeId = function(searchProducts) {
    	var itemSelector = 'span.easytree-node.easytree-active';
    	if(typeof searchProducts === 'undefined' || searchProducts === null || searchProducts === false) {
    		itemSelector += '[id^="cat"]'; 
    	}
    	
    	var selectedNode = $('#catalogTree ' + itemSelector);
    	if(selectedNode.length === 0) {
    		return null;
    	}
    	return selectedNode.attr('id');
    };
	
    var addCategoryBtnClick = function() {
    	var selectedNodeId = getSelectedNodeId();
    	var parentId = selectedNodeId === null ? null : selectedNodeId.substring(3);
    	loadAddEditCategoryModal(parentId);
    };
    
    var addProductBtnClick = function() {
    	var selectedNodeId = getSelectedNodeId();
    	var parentId = selectedNodeId === null ? null : selectedNodeId.substring(3);
    	loadAddEditProductModal(parentId);
    };
    
    var editBtnClick = function() {
    	var selectedNodeId = getSelectedNodeId(true);
    	if(selectedNodeId === null) {
    		return;
    	}
    	
    	var isCategory = selectedNodeId.indexOf('cat') === 0;
    	var isProduct = selectedNodeId.indexOf('prod') === 0;
    	var nodeId = getElementId(selectedNodeId, isCategory);
    	
    	if(isCategory) {
    		loadAddEditCategoryModal(null, nodeId);
    	}
    	else if(isProduct) {
    		loadAddEditProductModal(null, nodeId);
    	}
    };
    
    var deleteBtnClick = function() {
    	var selectedNodeId = getSelectedNodeId(true);
    	if(selectedNodeId === null) {
    		return;
    	}
    	
    	var isCategory = selectedNodeId.indexOf('cat') === 0;
    	var isProduct = selectedNodeId.indexOf('prod') === 0;
    	var nodeId = getElementId(selectedNodeId, isCategory);
    	
    	if(isCategory) {
    		deleteCategory(nodeId);
    	}
    	else if(isProduct) {
    		deleteProduct(nodeId);
    	}
    };
    
    var treeCanDrop = function(event, nodes, isSourceNode, source, isTargetNode, target) {
		return (isTargetNode && target.isFolder) || target.id === 'deleteBtn';
	};
	
	var treeOpening = function(event, nodes, node) {
	};
	
	var treeDropping = function(event, nodes, isSourceNode, source, isTargetNode, target, canDrop) {
	};
	
	var treeDropped = function(event, nodes, isSourceNode, source, isTargetNode, target) {
		var sourceNodeId = source.id;
		var sourceId = getElementId(sourceNodeId, source.isFolder);
		var targetNodeId = target.id;
		var targetId = getElementId(targetNodeId, target.isFolder);
		
		if(target.id === 'deleteBtn' && source.isFolder) {
    		deleteCategory(sourceId);
		}
		else if(target.id === 'deleteBtn' && !source.isFolder) {
    		deleteProduct(sourceId);
		}
		else if(source.isFolder) {
			moveCategory(sourceId, targetId);
		}
		else {
			moveProduct(sourceId, targetId);
		}
	};
	
	var getElementId = function(nodeId, isCategory) {
    	return isCategory ? nodeId.substring(3) : nodeId.substring(4);
    };
    
    //Modals on listCatelog page
    var setUpModal = function() {
    	var categoryModalDiv = $('#saveCategoryModal');
    	var categoryModalConfig = {
    		saveCallback: saveCategory,
    		openCallback: function() {
    			categoryModalDiv.find('#parentId').chosen({ width: '100%' });
    		}
    	};
    	Util.Modal.setUpFormModal(categoryModalDiv, categoryModalConfig);
    	
    	var productModalDiv = $('#saveProductModal');
    	var productModalConfig = {
    		saveCallback: saveProduct,
    		openCallback: function() {
    			productModalDiv.find('#parentId').chosen({ width: '100%' });
    			productModalDiv.find('#measureId').chosen({ width: '100%' });
    		}
    	};
    	Util.Modal.setUpFormModal(productModalDiv, productModalConfig);
    };
    
    //Tree on listCatalog page
    var setUpTree = function() {
    	catalogTree = $('#catalogTree').easytree({
    		enableDnd: true,
    		ordering: 'orderedFolder',
    		canDrop: treeCanDrop,
    		opening: treeOpening,
    		dropped: treeDropped,
    		dropping: treeDropping
    	});
    	window.test = catalogTree;
    };
    
    //Events on listCatalog page
    var wireUp = function() {
    	$('#addCategoryBtn').button().on('click', addCategoryBtnClick);
    	$('#addProductBtn').button().on('click', addProductBtnClick);
    	$('#editBtn').button().on('click', editBtnClick);
    	$('#deleteBtn').button().on('click', deleteBtnClick);
    };
    
    $(document).ready(function () {
    	Util.Storage.loadResources(['products.modal.addProduct', 'products.modal.editProduct', 'products.confirmDelete', 'categories.modal.addCategory', 'categories.modal.editCategory', 'categories.confirmDelete']);
    	setUpModal();
    	setUpTree();
    	wireUp();
    });
	
} ());