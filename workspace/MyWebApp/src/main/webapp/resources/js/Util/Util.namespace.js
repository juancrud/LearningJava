(function () {

    if (typeof Util === 'undefined') {
        window.Util = {};
    }

    "use strict";
    
    Util.namespace = function () {
        var args = arguments;
        var separator = '.';    //The separator used for namespaces: .
        var context = '';       //The context object for the current namespace
        var argument;           //The current namespace
        var tokenArray;         //The collection of tokens resulting from the split namespace
        var i, j;

        for (i = 0; i < args.length; i++) {
            context = window; //Reset base object per argument or it will get reused from the last
            argument = args[i];
            if (argument.indexOf(separator) > -1) { //Skip this if no "." is present
                tokenArray = argument.split(separator);
                for (j = 0; j < tokenArray.length; j++) {
                    context[tokenArray[j]] = context[tokenArray[j]] || {};
                    context = context[tokenArray[j]];
                }
            } else {
                context[argument] = context[argument] || {};
                context = context[argument]; //Reset base object to the new object so it's returned
            }
        }
        return context;
    };

    return Util.namespace;
}());