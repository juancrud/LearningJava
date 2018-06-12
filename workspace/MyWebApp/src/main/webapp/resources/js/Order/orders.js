(function () {
    "use strict";
    
    /* OrderLine functions */
    var saveOrderLine = function() {
    	var modal = $('#saveOrderLineModal');
		var form = modal.find('#saveOrderLineForm');
		var orderLinesDt = $('#saveOrderModal #orderLinesTable').dataTable();
    	
    	var index = form.find('#index').val();
    	var orderLineId = form.find('#id').val();
    	var productId = form.find('#productId').val();
    	var quantity = Number(form.find('#quantity').val()).toFixed(2);
    	var unitPrice = Number(form.find('#unitPrice').val()).toFixed(2);
    	var productName = form.find('#productId option:selected').html();
    	var linePrice = Number(quantity * unitPrice).toFixed(2);
    	
    	if(index === '-1') {
    		var editLink = '<span><a class="editOrderLineBtn">' + Util.Storage.get('orderLines.button.edit') + '</a></span>';
        	var deleteLink = '<span><a class="deleteOrderLineBtn">' + Util.Storage.get('orderLines.button.remove') + '</a></span>';
        	var data = [orderLineId, productName, quantity, unitPrice, linePrice, editLink + ' | ' + deleteLink];
        	orderLinesDt.fnAddData(data);
        	
        	var insertedRow = orderLinesDt.$('tr:not(.orderLine)');
        	insertedRow.addClass('orderLine');
        	insertedRow.data('id', orderLineId);
        	insertedRow.data('product-id', productId);
        	insertedRow.data('quantity', quantity);
        	insertedRow.data('unit-price', unitPrice);
    	}
    	else {
    		var row = orderLinesDt.$('tr:eq('+index+')');
    		var data = orderLinesDt.fnGetData(row.get(0));
    		
    		data[0] = orderLineId;
    		data[1] = productName;
    		data[2] = quantity;
    		data[3] = unitPrice;
    		data[4] = linePrice;
    		orderLinesDt.fnUpdate(data, row.get(0));
    		
    		row.data('id', orderLineId);
    		row.data('product-id', productId);
    		row.data('quantity', quantity);
    		row.data('unit-price', unitPrice);
    	}
    	modal.dialog('close');
    	setTotalOrderPrice();
    };
    
    var setTotalLinePrice = function(e) {
    	var form = $('#saveOrderLineForm');
    	var quantity = form.find('#quantity').val();
    	var unitPrice = form.find('#unitPrice').val();
    	form.find('#linePrice').val(Util.Utilities.formatDecimals(quantity * unitPrice));
    };
    
    var setTotalOrderPrice = function() {
		var saveOrderModal = $('#saveOrderModal');
    	var ordersTable = saveOrderModal.find('#orderLinesTable');
    	var ordersTrs = ordersTable.find('tbody tr.orderLine');
    	
    	var orderTotal = 0;
    	ordersTrs.each(function(i, el) {
    		var tr = $(el);
    		var quantity = tr.data('quantity');
    		var unitPrice = tr.data('unit-price');
    		orderTotal += quantity * unitPrice;
    	});
    	saveOrderModal.find('#totalPrice').val(Util.Utilities.formatDecimals(orderTotal));
    };
    
    var wireUpOrderLineModal = function() {
    	var form = $('#saveOrderLineForm');
    	var quantity = form.find('#quantity');
    	var unitPrice = form.find('#unitPrice');
    	var spinnerConfig = {
			min: 0,
    		step: 0.01,
    		stop: setTotalLinePrice
    	};
    	
    	quantity.spinner(spinnerConfig).on('blur', function() { Util.Utilities.formatDecimalsForField(quantity); });
    	unitPrice.spinner(spinnerConfig).on('blur', function() { Util.Utilities.formatDecimalsForField(unitPrice); });
    	
    	Util.Utilities.formatDecimalsForField(quantity);
    	Util.Utilities.formatDecimalsForField(unitPrice);
    	Util.Utilities.formatDecimalsForField(form.find('#linePrice'));
    };
    
    var setUpData = function(data) {
    	if(typeof data !== 'undefined' && data !== null) {
    		var form = $('#saveOrderLineForm');
        	form.find('#id').val(data.id);
        	form.find('#productId').val(data.productId);
        	form.find('#quantity').val(data.quantity);
        	form.find('#unitPrice').val(data.unitPrice);
        	setTotalLinePrice();
    	}
    };
    
    var loadAddEditOrderLineModal = function(index, data) {
    	var modalTitle = typeof data === 'undefined' || data === null ? Util.Storage.get('orderLines.modal.addOrderLine') : Util.Storage.get('orderLines.modal.editOrderLine');
    	var saveOrderLineModal = $('#saveOrderLineModal');
		var config = {
			requestUrl: 'Order/AddEditOrderLine?index=' + index,
			postContentLoaded: function() {
				setUpData(data);
				wireUpOrderLineModal();
				saveOrderLineModal.dialog('option', 'title', modalTitle);
				saveOrderLineModal.dialog('open');
			}
		};
		Util.Content.replaceContent(saveOrderLineModal, config);
    };
    
	var addOrderLineBtnClick = function(e) {
		e.preventDefault();
		loadAddEditOrderLineModal(-1);
	};
	
	var editOrderLineBtnClick = function(e) {
		var tr = $(e.target).closest('tr');
		var index = $('#orderLinesTable tbody tr').index(tr);
		var data = {
			id: tr.data('id'),
			productId: tr.data('product-id'),
			quantity: tr.data('quantity'),
			unitPrice: tr.data('unit-price')
		};
		loadAddEditOrderLineModal(index, data);
	};
	
	var deleteOrderLineBtnClick = function(e) {
		var orderLinesDt = $(e.target).closest('#orderLinesTable').dataTable();
		var row = $(e.target).closest('tr');
        var rowIdx = orderLinesDt.fnGetPosition(row.get(0));
        orderLinesDt.fnDeleteRow(rowIdx);
        
        setTotalOrderPrice();
	};
	
	
	/* Order functions */
	var addHiddenFields = function(form) {
		var trs = form.find('#orderLinesTable tbody tr');
		trs.each(function(i, val) {
			var tr = $(val);
			var id = tr.data('id');
			var productId = tr.data('product-id');
			var quantity = tr.data('quantity');
			var unitPrice = tr.data('unit-price');
			
			form.append('<input type="hidden" id="orderLines'+i+'.id" name="orderLines['+i+'].id" value="'+id+'" />');
			form.append('<input type="hidden" id="orderLines'+i+'.productId" name="orderLines['+i+'].productId" value="'+productId+'" />');
			form.append('<input type="hidden" id="orderLines'+i+'.quantity" name="orderLines['+i+'].quantity" value="'+quantity+'" />');
			form.append('<input type="hidden" id="orderLines'+i+'.unitPrice" name="orderLines['+i+'].unitPrice" value="'+unitPrice+'" />');
		});
	};
	
	var saveOrder = function() {
		var modal = $('#saveOrderModal');
		var form = modal.find('#saveOrderForm');
		addHiddenFields(form);
		
	    var options = {
	        url : form.attr('action'),
	        data : form.serializeArray(),
	        success: function(result)
	        {
	        	var ordersDt = $('#ordersTable').dataTable();
	        	var orderId = result.entity.id;
	        	var marketName = result.entity.marketName;
	        	var orderDate = result.entity.dateString;
	        	var totalPrice = Number(result.entity.totalPrice).toFixed(2);
	        	
	        	if(result.responseType === 'CREATE') {
	        		var editLink = '<span><a class="editOrderBtn">' + Util.Storage.get('orders.button.edit') + '</a></span>';
		        	var deleteLink = '<span><a class="deleteOrderBtn">' + Util.Storage.get('orders.button.remove') + '</a></span>';
		        	var orderLines = '<span><a class="toggleOrderLines">Show Order Lines</a></span>';
		        	var data = [orderId, orderDate, marketName, totalPrice, editLink + ' | ' + deleteLink + ' | ' + orderLines];
		        	ordersDt.fnAddData(data);
		        	
		        	var lastRow = ordersDt.$('tr:not([data-id])');
		        	lastRow.attr('data-id', orderId);
	        	}
	        	else {
	        		var row = ordersDt.$('tr[data-id="' + orderId + '"]');
	        		var data = ordersDt.fnGetData(row.get(0));
	        		
	        		data[0] = orderId;
	        		data[1] = orderDate;
	        		data[2] = marketName;
	        		data[3] = totalPrice;
	        		ordersDt.fnUpdate(data, row.get(0));
	        	}
	        	modal.dialog('close');
	        }
	    };
	    Util.Ajax.Post(options);
	};
	
	var wireUpOrderModal = function() {
		var form = $('#saveOrderModal #saveOrderForm');
		form.find('#date').datepicker();
		form.find('#addOrderLineBtn').button().on('click', addOrderLineBtnClick);
		form.find('#orderLinesTable').on('click', '.editOrderLineBtn', editOrderLineBtnClick);
		form.find('#orderLinesTable').on('click', '.deleteOrderLineBtn', deleteOrderLineBtnClick);
		form.find('#orderLinesTable').dataTable({
			"bJQueryUI": true
		});
	};
	
	var loadAddEditOrderModal = function(orderId) {
		var id = orderId || 0;
		var modalTitle = id === 0 ? Util.Storage.get('orders.modal.addOrder') : Util.Storage.get('orders.modal.editOrder');
		
		var saveOrderModal = $('#saveOrderModal');
		var config = {
			requestUrl: 'Order/AddEditOrder?id=' + id,
			postContentLoaded: function() {
				wireUpOrderModal();
				saveOrderModal.dialog('option', 'title', modalTitle);
				saveOrderModal.dialog('open');
			}
		};
		Util.Content.replaceContent(saveOrderModal, config);
	};
	
	var addOrderBtnClick = function() {
		loadAddEditOrderModal();
	};
	
	var editOrderBtnClick = function(e) {
		var orderId = $(e.target).closest('tr').data('id');
		loadAddEditOrderModal(orderId);
	};
	
	var deleteOrderBtnClick = function(e) {
		var row = $(e.target).closest('tr');
		var orderId = row.data('id');
		
		var confirmationConfig = {
			message: Util.Storage.get('orders.confirmDelete'),
			url: 'Order/DeleteOrder?id=' + orderId,
    		success: function(result) {
    			var ordersDt = $('#ordersTable').dataTable();
    			var targetRow = row.get(0);
    	        var rowIdx = ordersDt.fnGetPosition(targetRow);
    	        ordersDt.fnDeleteRow(rowIdx);
    		},
    		isPost: true
		};
		Util.Modal.confirmationModal(confirmationConfig);
	};
	
	var toggleOrderLinesBtnClick = function(e) {
		var toggleOrderLinesBtn = $(e.target);
		var ordersDt = $('#ordersTable').dataTable();
		var tr = toggleOrderLinesBtn.closest('tr');
		var nTr = tr[0];
		var orderId = tr.data('id');
    	var orderLinesDiv = tr.find('div.orderLines');
		
        if (ordersDt.fnIsOpen(nTr)) {
        	ordersDt.fnClose(nTr);
        	toggleOrderLinesBtn.text(Util.Storage.get('orders.button.showOrderLines'));
        }
        else {
        	var orderLinesTable = orderLinesDiv.find('#orderLinesTable_' + orderId);
        	var openOrderLinesTable = function() {
        		ordersDt.fnOpen(nTr, orderLinesDiv.clone(), 'details');
        		tr.next().find('#orderLinesTable_' + orderId).dataTable({
	                "bJQueryUI": true,
	            });
				toggleOrderLinesBtn.text(Util.Storage.get('orders.button.hideOrderLines'));
        	};
        	
        	if(orderLinesTable.length === 0) {
        		var config = {
        			requestUrl: 'Order/ListOrderLines?id=' + orderId,
        			postContentLoaded: function() {
        				openOrderLinesTable();
        			}
            	};
            	Util.Content.replaceContent(orderLinesDiv, config);
        	}
        	else {
        		openOrderLinesTable();
        	}
        }
	};
	
	//Load Resources
    var loadResources = function() {
    	Util.Storage.loadResources(['orderLines.button.edit', 
    	                            'orderLines.button.remove', 
    	                            'orderLines.modal.addOrderLine', 
    	                            'orderLines.modal.editOrderLine',
    	                            'orders.button.edit',
    	                            'orders.button.remove',
    	                            'orders.modal.addOrder',
    	                            'orders.modal.editOrder',
    	                            'orders.button.showOrderLines', 
    	                            'orders.button.hideOrderLines',
    	                            'orders.confirmDelete']);
    };
	
	//Modals on listOrders page
    var setUpModal = function() {
    	var orderModalDiv = $('#saveOrderModal');
    	var modalConfig = {
    		saveCallback: saveOrder,
    		width: 700
    	};
    	Util.Modal.setUpFormModal(orderModalDiv, modalConfig);
    	
    	var orderLineModalDiv = $('#saveOrderLineModal');
    	var modalConfig = {
    		saveCallback: saveOrderLine
    	};
    	Util.Modal.setUpFormModal(orderLineModalDiv, modalConfig);
    };
	
	//DataTable on listOrders page
	var setUpDataTable = function() {
    	$('#ordersTable').dataTable({
    		"bJQueryUI": true
    	});
    };
	
    //Events on listOrders page
    var wireUp = function() {
    	$('#addOrderBtn').button().on('click', addOrderBtnClick);
    	$('#ordersTable').on('click', '.editOrderBtn', editOrderBtnClick);
    	$('#ordersTable').on('click', '.deleteOrderBtn', deleteOrderBtnClick);
    	$('#ordersTable').on('click', '.toggleOrderLines', toggleOrderLinesBtnClick);
    	$('#ordersTable').on('click', '.editOrderLineBtn', editOrderLineBtnClick);
    	$('#ordersTable').on('click', '.deleteOrderLineBtn', deleteOrderLineBtnClick);
    };
    
    $(document).ready(function () {
    	loadResources();
    	setUpModal();
    	setUpDataTable();
    	wireUp();
    });
	
} ());