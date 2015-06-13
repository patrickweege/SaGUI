Ext.define("Fatuhiva.action.BaseAction",{
	
	constructor	: function(config){
		Ext.apply(this, config || {});
        var me = this;
        me.initialConfig = config;
	},
	
	execute : function() {
		// override this method
	}
	
});