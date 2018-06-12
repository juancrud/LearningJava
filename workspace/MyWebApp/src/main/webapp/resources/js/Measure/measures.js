(function () {
    "use strict";
    
    var saveMeasure = function() {
    	var modal = $('#saveMeasureModal');
		var form = modal.find('#saveMeasureForm');
	    var options = {
	        url : form.attr('action'),
	        data : form.serializeArray(),
	        success: function(result)
	        {
	        	if(result.success) {
		        	var measuresDt = $('#measuresTable').dataTable();
		        	var measureId = result.entity.id;
		        	var measureName = result.entity.name;
		        	var measureAlias = result.entity.alias;
		        	
		        	if(result.responseType === 'CREATE') {
		        		var editLink = '<span><a class="editMeasureBtn">' + Util.Storage.get('measures.button.edit') + '</a></span>';
			        	var deleteLink = '<span><a class="deleteMeasureBtn">' + Util.Storage.get('measures.button.remove') + '</a></span>';
			        	var data = [measureId, measureName, measureAlias, editLink + ' | ' + deleteLink];
			        	measuresDt.fnAddData(data);
			        	
			        	var lastRow = measuresDt.$('tr:not([data-id])');
			        	lastRow.attr('data-id', measureId);
		        	}
		        	else {
		        		var row = measuresDt.$('tr[data-id="' + measureId + '"]');
		        		var data = measuresDt.fnGetData(row.get(0));
		        		
		        		data[0] = measureId;
		        		data[1] = measureName;
		        		data[2] = measureAlias;
		        		measuresDt.fnUpdate(data, row.get(0));
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
	
	var loadAddEditMeasureModal = function(measureId) {
		var id = measureId || 0;
		var modalTitle = id === 0 ? Util.Storage.get('measures.modal.addMeasure') : Util.Storage.get('measures.modal.editMeasure');
		
		var saveMeasureModal = $('#saveMeasureModal');
		var config = {
			requestUrl: 'Measure/AddEditMeasure?id=' + id,
			postContentLoaded: function() {
				saveMeasureModal.dialog('option', 'title', modalTitle);
				saveMeasureModal.dialog('open');
			}
		};
		Util.Content.replaceContent(saveMeasureModal, config);
	};
	
	var addMeasureBtnClick = function() {
		loadAddEditMeasureModal();
	};
	
	var editMeasureBtnClick = function() {
		var measureId = $(this).closest('tr').data('id');
		loadAddEditMeasureModal(measureId);
	};
	
	var deleteMeasureBtnClick = function() {
		var row = $(this).closest('tr');
		var measureId = row.data('id');
		
		var confirmationConfig = {
			message: Util.Storage.get('measures.confirmDelete'),
			url: 'Measure/DeleteMeasure?id=' + measureId,
    		success: function(result) {
    			if(result.success) {
    				var measuresDt = $('#measuresTable').dataTable();
        			var targetRow = row.get(0);
        	        var rowIdx = measuresDt.fnGetPosition(targetRow);
        	        measuresDt.fnDeleteRow(rowIdx);
    			}
    			else {
    				Util.Modal.ajaxResponseError(result);
    			}
    		},
    		isPost: true
		};
		Util.Modal.confirmationModal(confirmationConfig);
	};
	
	//Modal on listMeasures page
    var setUpModal = function() {
    	var modalDiv = $('#saveMeasureModal');
    	var modalConfig = {
    		saveCallback: saveMeasure
    	};
    	Util.Modal.setUpFormModal(modalDiv, modalConfig);
    };
	
    //DataTable on listMeasures page
    var setUpDataTable = function() {
    	$('#measuresTable').dataTable({
    		"bJQueryUI": true
    	});
    };
    
    //Events on listMeasures page
    var wireUp = function() {
    	$('#addMeasureBtn').button().on('click', addMeasureBtnClick);
    	$('#measuresTable').on('click', '.editMeasureBtn', editMeasureBtnClick);
    	$('#measuresTable').on('click', '.deleteMeasureBtn', deleteMeasureBtnClick);
    };
    
    $(document).ready(function () {
    	Util.Storage.loadResources(['measures.modal.addMeasure', 'measures.modal.editMeasure', 'measures.confirmDelete', 'measures.button.edit', 'measures.button.remove']);
    	setUpModal();
    	setUpDataTable();
    	wireUp();
    });
	
} ());