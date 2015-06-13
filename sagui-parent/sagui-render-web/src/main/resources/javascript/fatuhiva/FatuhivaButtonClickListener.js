var FatuhivaButtonClickListener = function(pageID, componentID) {
	var self = this;
	
	this.pageID = pageID;
	this.componentID = componentID;

	this.execute = function() {
		var parameters = new FatuhivaActionParams(self.pageID, self.componentID, 'click');
		// Execute Action Call
		Fatuhiva.executeAction(parameters);
	}
}