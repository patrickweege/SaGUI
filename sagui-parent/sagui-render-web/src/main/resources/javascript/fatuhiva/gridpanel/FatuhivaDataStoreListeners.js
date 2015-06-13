Ext.define('Fatuhiva.gridpanel.FatuhivaGridStorePrefetchListener', {
	extend : 'Fatuhiva.action.BaseAction',

	constructor : function(config) {
		var me = this;
		me.callParent(config);
	},

	execute : function(theStore, records, successful, operation, eOpts) {
		if (successful) {
			Fatuhiva.Log.debug("[Fatuhiva.gridpanel.FatuhivaGridStorePrefetchListener] - OnPrefetch");
			var theGrid = Ext.getCmp(theStore.componentID);
			if (theGrid && theGrid.getView() && theGrid.getView().getSelectionModel()) {
				var theSelectionModel = theGrid.getSelectionModel();
				theSelectionModel.suspendEvent('selectionchange');
				try {
					records.forEach(function(theRecord, theIndex, theRecords) {
						var metadata = theRecord.get("__fatuhiva_rowMetadata");
						var doSelect = metadata.__fatuhiva_isSelected;
						if (doSelect && !theSelectionModel.isSelected(theRecord)) {
							theSelectionModel.select(theRecord, true, true);
						} else if (!doSelect && theSelectionModel.isSelected(theRecord)) {
							theSelectionModel.deselect(theRecord, true);
						}
					}, theSelectionModel); // forEach callback
				} finally {
					theSelectionModel.resumeEvent('selectionchange');
				}
			}
		}
	}// OnPrefetch Listener
});