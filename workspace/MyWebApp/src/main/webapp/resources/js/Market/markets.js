(function () {
    "use strict";
    
    var saveMarket = function() {
    	var modal = $('#saveMarketModal');
		var form = modal.find('#saveMarketForm');
	    var options = {
	        url : form.attr('action'),
	        data : form.serializeArray(),
	        success: function(result) {
	        	if(result.success) {
	        		var marketsDt = $('#marketsTable').dataTable();
		        	var marketId = result.entity.id;
		        	var marketName = result.entity.name;
		        	
		        	if(result.responseType === 'CREATE') {
		        		var editLink = '<span><a class="editMarketBtn">' + Util.Storage.get('markets.button.edit') + '</a></span>';
			        	var deleteLink = '<span><a class="deleteMarketBtn">' + Util.Storage.get('markets.button.remove') + '</a></span>';
			        	var data = [marketId, marketName, editLink + ' | ' + deleteLink];
			        	marketsDt.fnAddData(data);
			        	
			        	var lastRow = marketsDt.$('tr:not([data-id])');
			        	lastRow.attr('data-id', marketId);
		        	}
		        	else {
		        		var row = marketsDt.$('tr[data-id="' + marketId + '"]');
		        		var data = marketsDt.fnGetData(row.get(0));
		        		
		        		data[0] = marketId;
		        		data[1] = marketName;
		        		marketsDt.fnUpdate(data, row.get(0));
		        	}
		        	modal.dialog('close');
	        	}
	        	else {
	        		Util.Modal.ajaxResponseError(result);
	        	}
	        }
	    };
		Util.Ajax.Post(options);
	};
	
	var loadAddEditMarketModal = function(marketId) {
		var id = marketId || 0;
		var modalTitle = id === 0 ? Util.Storage.get('markets.modal.addMarket') : Util.Storage.get('markets.modal.editMarket');
		
		var saveMarketModal = $('#saveMarketModal');
		var config = {
			requestUrl: 'Market/AddEditMarket?id=' + id,
			postContentLoaded: function() {
				saveMarketModal.dialog('option', 'title', modalTitle);
				saveMarketModal.dialog('open');
			}
		};
		Util.Content.replaceContent(saveMarketModal, config);
	};
	
	var addMarketBtnClick = function() {
		loadAddEditMarketModal();
	};
	
	var editMarketBtnClick = function() {
		var marketId = $(this).closest('tr').data('id');
		loadAddEditMarketModal(marketId);
	};
	
	var deleteMarketBtnClick = function() {
		var row = $(this).closest('tr');
		var marketId = row.data('id');
		
		var confirmationConfig = {
			message: Util.Storage.get('markets.confirmDelete'),
			url: 'Market/DeleteMarket?id=' + marketId,
    		success: function(result) {
    			if(result.success) {
    				var marketsDt = $('#marketsTable').dataTable();
        			var targetRow = row.get(0);
        	        var rowIdx = marketsDt.fnGetPosition(targetRow);
        	        marketsDt.fnDeleteRow(rowIdx);
    			}
    			else {
    				Util.Modal.ajaxResponseError(result);
    			}
    		},
    		isPost: true
		};
		Util.Modal.confirmationModal(confirmationConfig);
	};
	
	//Modal on listMarkets page
    var setUpModal = function() {
    	var modalDiv = $('#saveMarketModal');
    	var modalConfig = {
    		saveCallback: saveMarket
    	};
    	Util.Modal.setUpFormModal(modalDiv, modalConfig);
    };
	
	//DataTable on listMarkets page
    var setUpDataTable = function() {
    	$('#marketsTable').dataTable({
    		"bJQueryUI": true
    	});
    };
	
	//Events on listMarkets page
    var wireUp = function() {
    	$('#addMarketBtn').button().on('click', addMarketBtnClick);
    	$('#marketsTable').on('click', '.editMarketBtn', editMarketBtnClick);
    	$('#marketsTable').on('click', '.deleteMarketBtn', deleteMarketBtnClick);
    };
    
    $(document).ready(function () {
    	Util.Storage.loadResources(['markets.modal.addMarket', 'markets.modal.editMarket', 'markets.confirmDelete', 'markets.button.edit', 'markets.button.remove']);
    	setUpModal();
    	setUpDataTable();
    	wireUp();
    });
	
} ());