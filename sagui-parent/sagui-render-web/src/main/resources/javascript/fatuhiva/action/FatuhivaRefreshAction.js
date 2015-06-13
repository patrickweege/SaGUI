Ext.define('Fatuhiva.action.RefreshAction', {
    extend: 'Fatuhiva.action.BaseAction',

    componentID : null,
    
    constructor: function(config){
        var me = this;
        me.callParent(config); 
        me.componentID = config.componentID;
    },
    
    execute : function() {
    	var cmp = Ext.get(this.componentID);
    	if(!ComparatorUtil.isNull(cmp)) {
    		if("Fatuhiva.grid.Panel" === cmp.getName()) {
    			var theStore = cmp.getStore();
    			Ext.defer(theStore.reload, 100, theStore);
    		}
    	}
    }
});