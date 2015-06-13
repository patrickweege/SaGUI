var FatuhivaTextBoxOnBlurListener = function(pageID, componentID) {
	var self = this;
	
	this.pageID = pageID;
	this.componentID = componentID;

	this.execute = function(field, opts) {
		if(field.isDirty()) {
			var parameters = {
					PAGE_ID : self.pageID,
					COMPONENT_ID : self.componentID,
					EVENT : 'blur',
					NEW_VALUE : field.getValue()
			}
			// Execute Action Call
			Fatuhiva.executeAction(parameters);
		}
	}
}