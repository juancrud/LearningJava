(function () {
    "use strict";
    
    Util.namespace('Util.Ajax');
    
    // GET Method
    var onGetSuccess = function() {
    	console.log('No success function defined for GET');
    };
    
    var onGetError = function(result) {
    	var config = {
			type: Util.Modal.AlertType.Error,
			message: 'An error occured doing an ajax GET'
    	};
    	Util.Modal.alertModal(config);
    };
    
    //POST Method
    Util.Ajax.Get = function(options) {
    	var postOptions = {
    		type: 'GET',
    		url: options.url,
    		data: options.data || {},
    		success: options.success || onGetSuccess,
    		error: options.data || onGetError
    	};
    	$.ajax(postOptions);
    };
    
    
    var onPostSuccess = function() {
    	console.log('No success function defined for POST');
    };
    
    var onPostError = function(result) {
    	var config = {
			type: Util.Modal.AlertType.Error,
			message: 'An error occured doing an ajax POST'
    	};
    	Util.Modal.alertModal(config);
    };
    
    Util.Ajax.Post = function(options) {
    	var postOptions = {
    		type: 'POST',
    		url: options.url,
    		data: options.data || {},
    		success: options.success || onPostSuccess,
    		error: options.error || onPostError
    	};
    	$.ajax(postOptions);
    };
	
} ());
