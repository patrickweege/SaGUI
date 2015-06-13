var Fatuhiva = new function() {

	this.defaultActionURL = "../action";

	this.actionSuccessFn = function(response, opts) {
		eval(response.responseText);
	};

	this.actionFailureFn = function(response, opts) {
		alert(response.responseText);
	};

	this.executeAction = function(parameters) {
		var action = new Fatuhiva.action.RequestAction({
			url : this.defaultActionURL,
			parameters : parameters,
			onSuccess : this.actionSuccessFn,
			onFailure : this.actionFailureFn
		});
		action.execute();
	};

	this.Log = new function() {
		this.debug = function(msg) {
			Fatuhiva.Log.log(msg, 'debug');
		};

		this.error = function(msg) {
			Fatuhiva.Log.log(msg, 'error');
		};

		this.log = function(msg, level) {
			var opts = {
				msg : msg,
				level : level
			}
			window.console.log(msg);
		};
	};
}

function FatuhivaActionParams(pageID, componentID, event) {
	var self = this;
	this.PAGE_ID = pageID;
	this.COMPONENT_ID = componentID;
	this.EVENT = event;
}
