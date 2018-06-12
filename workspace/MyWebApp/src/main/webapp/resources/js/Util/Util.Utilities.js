(function () {
    "use strict";
    
    Util.namespace('Util.Utilities');
    
    /**
     * Util.Utilities.formatDecimals(value, decimalFields)
     * Formats the value to a fixed value with the corresponding number of decimal fields
     * 
     * Params:
     * value: the value to be formatted
     * decimalFields: number of decimal fields (default: 2) 
     */
    Util.Utilities.formatDecimals = function(value, decimalFields) {
    	var numberOfDecimalFields = decimalFields || 2;
    	return parseFloat(value).toFixed(numberOfDecimalFields);
    };
    
    /**
     * Util.Utilities.formatDecimalsForField(input, decimalFields)
     * Formats the field to a fixed value with the corresponding number of decimal fields
     * 
     * Params:
     * input: jquery input fields
     * decimalFields: number of decimal fields (default: 2)
     */
    Util.Utilities.formatDecimalsForField = function(input, decimalFields) {
    	var numberOfDecimalFields = decimalFields || 2;
    	var currentValue = input.val();
    	var newValue = Util.Utilities.formatDecimals(currentValue, numberOfDecimalFields);
    	input.val(newValue);
    };
    
    var preventSubmit = function(e, callback) {
		if (e.which === 13 || e.keyCode === 13) {
			e.preventDefault();
			if(typeof callback !== 'undefined' && callback !== null) {
				callback();
			}
		}
	};
    
    Util.Utilities.preventSubmitOnEnter = function(container, controlsSelector, callback) {
    	var controls = typeof controlsSelector === 'undefined' || controlsSelector === null ? container : container.find(controlsSelector);
    	controls.on('keypress', function (e) { preventSubmit(e, callback); });
    };
    
    Util.Utilities.test = function() {
    	
	};
	
} ());
