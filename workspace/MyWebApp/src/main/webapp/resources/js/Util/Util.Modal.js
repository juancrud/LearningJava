(function () {
    "use strict";
    
    Util.namespace('Util.Modal');
    Util.namespace('Util.Modal.AlertType');
    
    /**
     * Util.Modal.AlertType enumeration
     */
    Util.Modal.AlertType.Error = 1;
    Util.Modal.AlertType.Warning = 2;
    Util.Modal.AlertType.Information = 3;
    Util.Modal.AlertType.Success = 4;
    
    /**
     * Util.Modal.alertModal(config)
     * config: configuration object
     * 
     * Config:
     * type: use enumeration on this file - Util.Modal.AlertType
     * message: message to be displayed
     */
    Util.Modal.alertModal = function(config) {
    	var modalDiv = $('#alertModal');
    	if(modalDiv.length === 0) {
    		var postContentLoaded = function() {
    			modalDiv = setUpAlertModal();
    			showAlertModal(modalDiv, config);
    		};
    		Util.Content.appendContent('Modal/Alert', postContentLoaded);
    	}
    	showAlertModal(modalDiv, config);
    };
    
    var setUpAlertModal = function() {
    	var modalDiv = $('#alertModal');
    	var modalConfig = {
    			modal: true,
    			autoOpen: false,
    			buttons: [
			        {
				    	text: Util.Storage.get('general.ok'),
				    	click: function() {
	    					modalDiv.dialog('close');
	    				}
				    }
		    	]
        	};
    	modalDiv.dialog(modalConfig);
    	
    	return modalDiv;
    };
    
    var showAlertModal = function(modalDiv, config) {
    	var alertTitle;
    	switch(config.type) {
	    	case Util.Modal.AlertType.Error: 
	    		alertTitle = Util.Storage.get('general.error');
	    		break;
	    	case Util.Modal.AlertType.Warning: 
	    		alertTitle = Util.Storage.get('general.warning');
	    		break;
	    	case Util.Modal.AlertType.Information: 
	    		alertTitle = Util.Storage.get('general.information');
	    		break;
	    	case Util.Modal.AlertType.Success: 
	    		alertTitle = Util.Storage.get('general.success');
	    		break;
	    	default: 
	    		alertTitle = '';
	    		break;
    	}
    	
    	modalDiv.dialog('option', 'title', alertTitle);
    	modalDiv.find('#alertMessage').html(config.message);
    	modalDiv.dialog('open');
    };
    
    /**
     * Util.Modal.ajaxResponseError(ajaxResult)
     * Displays error messages resulting from an ajax call
     * 
     * Params: 
     * ajaxResult: ajax response
     */
    Util.Modal.ajaxResponseError = function(ajaxResult) {
    	var config = {
			type: Util.Modal.AlertType.Error,
			message: ''
		};
    	
		for(var i = 0; i < ajaxResult.messages.length; i++) {
			config.message += ajaxResult.messages[i].messageCode;
		}
		Util.Modal.alertModal(config);
    };
    
    /**
     * Util.Modal.confirmationModal(config)
     * 
     * Params:
     * config: configuration object
     * 
     * Config:
     * message: the confirmation message to be displayed
     * url: the action to be executed
     * success: the function to be executed when the success action is returned
     * data: the data to be sent
     * isPost: the (default value: false)
     */
    Util.Modal.confirmationModal = function(config) {
    	var modalDiv = $('#confirmationModal');
    	config.cancelFunction = function() {
    		modalDiv.dialog('close');
    	};
    	config.acceptFunction = function() {
    		var options = {
				url: config.url,
				data: config.data,
	    		success: config.success,
			};
    		if(config.isPost) {
    			Util.Ajax.Post(options);
    		}
    		else {
    			Util.Ajax.Get(options);
    		}
    	};
    	
    	if(modalDiv.length === 0) {
    		var postContentLoaded = function() {
    			modalDiv = setUpConfirmationModal();
				showConfirmationModal(modalDiv, config);
    		};
    		Util.Content.appendContent('Modal/Confirmation', postContentLoaded);
    	}
    	showConfirmationModal(modalDiv, config);
    };
    
    var setUpConfirmationModal = function() {
    	var modalDiv = $('#confirmationModal');
    	var modalConfig = {
    			modal: true,
    			autoOpen: false
        	};
    	modalDiv.dialog(modalConfig);
    	
    	return modalDiv;
    };
    
    var showConfirmationModal = function(modalDiv, config) {
    	modalDiv.dialog('option', 'buttons', [
	        {
		    	text: Util.Storage.get('general.yes'),
		    	click: function() {
		    		config.acceptFunction();
	    			config.cancelFunction();
		    	}
		    },
		    {
		    	text: Util.Storage.get('general.no'),
		    	click: config.cancelFunction
		    }
    	]);
    	modalDiv.find('#confirmationMessage').html(config.message);
    	modalDiv.dialog('open');
    };
    
    /**
     * Util.Modal.setUpFormModal(modalDiv, config)
     * Sets up a jquery dialog modal
     * 
     * Params:
     * modalDiv: jquery element containing the html for the modal
     * config: configuration object
     * 
     * Config:
     * saveCallback: the function to be executed when the Accept Button is clicked
     * cancelCallback: the function to be executed when the Cancel Button is clicked (If not set, we just close the modal)
     * openCallback: the function to be executed when the modal is opened
     * acceptLabel: label of the button that accepts the modal (default: Save)
     * width: width for the modal
     * removeOnClose: remove the markup on modal close (default: true)
     * saveOnEnterKey: indicates of the form within the modal should be submitted on clicking Enter (default: true)
     */
    Util.Modal.setUpFormModal = function(modalDiv, config) {
    	var acceptButtonText = config.acceptLabel || Util.Storage.get('general.save');
    	//default function to be executed after the dialog is closed
    	var closeFunction = function() {
			if(typeof config.removeOnClose === 'undefined' || config.removeOnClose === null || config.removeOnClose) {
				modalDiv.empty();
			}
		};
		//default function to be executed on Cancel action
		var onCancelFunction = function() {
			modalDiv.dialog("close");
		};
		
		//saveOnEnterKey
		var shouldSaveOnEnterKey = typeof config.saveOnEnterKey === 'undefined' || config.saveOnEnterKey === null || config.saveOnEnterKey;
		modalDiv.on('keypress', 'form input', function(e){
			if (e.which === 13 || e.keyCode === 13) {
				e.preventDefault();
				if(shouldSaveOnEnterKey){
					config.saveCallback();
				}
			}
		});
		
    	var modalConfig = {
			modal: true,
			autoOpen: false,
			buttons: [
			    {
			    	text: acceptButtonText,
			    	click: config.saveCallback
			    },
			    {
			    	text: Util.Storage.get('general.cancel'),
			    	click: config.cancelCallback || onCancelFunction
			    }
			],
			open: config.openCallback,
			close: closeFunction	
    	};
    	if(typeof config.width !== 'undefined' && config.width !== null) {
    		modalConfig.width = config.width;
    	}
    	
    	modalDiv.dialog(modalConfig);
    };
    
    $(document).ready(function () {
    	Util.Storage.loadResources(['general.save', 'general.cancel', 'general.ok', 'general.yes', 'general.no', 'general.error', 'general.warning', 'general.information', 'general.success']);
    });
    
} ());
