(function () {
    "use strict";
    
    var wireUp = function() {
    	$('#leftMenu a.menuItem').on('click', function() {
        	var rootContent = $('#content');
        	var config = {
        		requestUrl: $(this).data('content-url')
        	};
        	Util.Content.replaceContent(rootContent, config);
        });
    };
    
    $(document).ready(function () {
    	wireUp();
    });
	
} ());