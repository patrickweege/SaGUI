var FatuhivaComboBoxSelectionListener = function(pageID, componentID) {
	var self = this;
	
	this.pageID = pageID;
	this.componentID = componentID;

	this.execute = function(combo, records, eOpts) {
		var index = 0;
		var selection = new Array();
		Ext.Array.each(records, function(record) {
			selection[index] = record.get('id');
			index++;
		});
		
		var parameters = {
				PAGE_ID : self.pageID,
				COMPONENT_ID : self.componentID,
				EVENT : 'select',
				NEW_VALUE : selection
		}
		
		// Execute Action Call
		Fatuhiva.executeAction(parameters);
	}
}