Ext.define('Fatuhiva.gridpanel.FatuhivaSelectionChangedListener', {
	extend : 'Fatuhiva.action.BaseAction',

	pageID : null,
	componentID : null,

	constructor : function(config) {
		var me = this;
		var thePageID = config.pageID;
		var theComponentID = config.componentID;
		var theEvent = config.event;

		me.callParent(config);
	},

	/**
	 * The execute method of a ActionListener many parameters
	 */
	execute : function(selectionModel, selection, eOpts) {
		var me = this;
		var theGrid = Ext.getCmp(selectionModel.componentID);
		var theStore = theGrid.getStore();

		var uuids = new Array();
		for ( var iRecord = 0; iRecord < selection.length; iRecord++) {
			var record = selection[iRecord];
			var metadata = record.get("__fatuhiva_rowMetadata");
			uuids[iRecord] = metadata.__fatuhiva_rowUUID;
		}
		var params = new FatuhivaActionParams(selectionModel.pageID, selectionModel.componentID, 'selectionchange');
		params["rowUUIDs"] = uuids;

		Fatuhiva.executeAction(params);

		return false;
	}
});