(function () {
    "use strict";
    
    Util.namespace('Util.Storage');
    
    /**
     * Util.Storage.loadResources(resourceKeys)
     * Loads the resources asynchronously based on the sent keys - if a resource is already stored, skip it
     * 
     * Params:
     * resourceKeys: the arrays of resource keys
     * callback: function to be executed after the resources are loaded
     */
    Util.Storage.loadResources = function(resourceKeys, callback) {
    	var keysToRetrieve = [];
    	for(var i = 0; i < resourceKeys.length; i++) {
    		var key = resourceKeys[i];
    		var value = $.jStorage.get(key);
    		if(value === null) {
    			keysToRetrieve.push(key);
    		}
    	}
    	loadResources(keysToRetrieve, callback);
    };
    
    var loadResources = function(keys, callback) {
    	callback = callback || function() {};
    	
    	if(keys.length === 0) {
    		callback();
    		return;
    	}
    	
    	var options = {
			url: 'GetResources?keys=' + keys.join(),
    		success: function(result) {
    			if(result.success) {
    				for(var i = 0; i < keys.length; i++) {
    					var key = keys[i];
    					var value = result.entity[key];
    					$.jStorage.set(key, value);
    				}
    				callback();
    			}
    			else {
    				//TODO:
    			}
    		}
    	};
    	Util.Ajax.Get(options);
    };
    
    /**
     * Util.Storage.get(resourceKey)
     * Gets the stored resource - returns null (and logs a message) if not already stored
     * 
     * Params:
     * resourceKey: the resource key
     */
    Util.Storage.get = function(resourceKey) {
    	var message = $.jStorage.get(resourceKey);
    	if(message === null) {
    		console.log('ResourceKey has not been loaded yet: ' + resourceKey);
    	}
    	
    	return message;
    };
    
    /**
     * Util.Storage.inspect()
     * Logs all the keys/values on the console
     */
    Util.Storage.inspect = function() {
    	var keys = $.jStorage.index();
    	for(var i = 0; i < keys.length; i++) {
    		var key = keys[i];
    		var value = Util.Storage.get(key);
    		console.log('"' + key + '": "' + value + '"');
    	}
    };
    
    /**
     * Util.Storage.clear()
     * Clears all the storage keys
     */
    Util.Storage.clear = function() {
    	$.jStorage.flush();
    };
    
} ());
