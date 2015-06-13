function(theStore, records, successful, eOpts) {
	if(successful) {
		var theGrid = Ext.getCmp('e31cb5f2-2dce-4eb2-949c-edb348cd3e7a');
        	records.forEach(
        			function(theRecord, theIndex, theRecords) {
        				var isSelected = record.get('isSelected');
        				if(isSelected ==== true) {
        					var theGrid = Ext.getCmp('e31cb5f2-2dce-4eb2-949c-edb348cd3e7a');
        					if(theGrid && theGrid.getSelectionModel()) {
        						var theSelectionModel = theGrid.getSelectionModel();
        						theSelectionModel.select(theRecord,true,true);
        					}
        				}
        			}, aGrid); // forEach callback
	}
}