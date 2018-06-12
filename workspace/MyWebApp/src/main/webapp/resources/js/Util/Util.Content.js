(function () {
    "use strict";
    
    Util.namespace('Util.Content');
    
    
    /**
     * Util.Content.appendContent(requestUrl, postContentLoaded)
     * Gets html content and appends it to the body tag
     * 
     * Params:
     * requestUrl: the url that will get the html content 
     * postContentLoaded: function that executes after the new content is loaded on the rootElement
     */
    Util.Content.appendContent = function(requestUrl, postContentLoaded) {
    	var options = {
			url: requestUrl,
    		success: function(result) {
    			$('body').append(result);
    			if(postContentLoaded != null && typeof postContentLoaded === 'function') {
    	    		postContentLoaded();
    	    	}
    		}
    	};
    	Util.Ajax.Get(options);
    };
    
    
    /**
     * Util.Content.replaceContent(rootElement, config, isPost)
     * Gets html content and appends it to a html element
     * 
     * Params:
     * rootElement: element that will be appended the content to
     * config: configuration object 
     * isPost: flag that indicates if the request should be a Post, if not set we do a Get
     * 
     * Config: 
     * requestUrl: the url that will get the html content 
     * requestData: data object that will be sent on the request (should be used for posts only)
     * preContentLoaded: function that executes before the new content is loaded on the rootElement
     * postContentLoaded: function that executes after the new content is loaded on the rootElement
     */
    Util.Content.replaceContent = function(rootElement, config, isPost) {
    	rootElement.html('<div class="">Loading...</div>');
    	
    	if(config.preContentLoaded != null && typeof config.preContentLoaded === 'function') {
    		config.preContentLoaded();
    	}
    	
    	var options = {
			url: config.requestUrl,
    		data: config.requestData,
    		success: function(result) {
    			var htmlContent = $(result);
    			rootElement.empty();
    			rootElement.append(htmlContent);
    			if(config.postContentLoaded != null && typeof config.postContentLoaded === 'function') {
    	    		config.postContentLoaded();
    	    	}
    		}
    	};
    	
    	if(isPost){
    		Util.Ajax.Post(options);
    	}
    	else{
    		Util.Ajax.Get(options);
    	}
    };
	
} ());