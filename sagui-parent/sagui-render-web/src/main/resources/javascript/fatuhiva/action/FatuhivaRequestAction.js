/**
 * @example
 * var action new Fatuhiva.action.RequestAction({url: 'http:\\url.to.request', onSuccess: onSuccessFunction, onFailure: onFailureFunction, parameters: requestParamsObject });
 * action.execute();
 * 
 */
Ext.define('Fatuhiva.action.RequestAction', {
    extend: 'Fatuhiva.action.BaseAction',

    constructor: function(config){
        var me = this;
        me.callParent(config); 
        me.action = config.url;
        me.parameters = Util.comparator.isNull(config.parameters) ? {} : config.parameters;
        me.onSuccessFn = Util.comparator.isNull(config.onSuccess) ? Ext.emptyFn : config.onSuccess;
        me.onFailureFn = Util.comparator.isNull(config.onFailure) ? Ext.emptyFn : config.onFailure;
    },
    
	execute : function() {
		var theParameters = {};
		var properties = Object.getOwnPropertyNames(this.parameters);
		Ext.Array.forEach(properties, function(propName, idx, array) {
			var propValue = this.parameters[propName];
			if (Ext.isFunction(propValue)) {
				propValue = propValue.call();
			}
			if (!Ext.isPrimitive(propValue)) {
				propValue = Ext.JSON.encode(propValue);
			}
			theParameters[propName] = propValue;
		}, this);

		Ext.Ajax.request({
			url : this.action,
			params : theParameters,
			success : this.onSuccessFn,
			failure : this.onFailureFn
		}); // Ajax Request function
	}
})
