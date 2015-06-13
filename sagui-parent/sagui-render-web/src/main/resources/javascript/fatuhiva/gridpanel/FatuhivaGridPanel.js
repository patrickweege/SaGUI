Ext.define('Fatuhiva.grid.Panel', {
	extend : 'Ext.grid.Panel',

	pageID : null,

	constructor : function(config) {
		var me = this;

		var thePageID = config.pageID;
		var theComponentID = config.id;

		// Data Model and DataStore
		var theDataModelName = me.configureDataModel(config);
		var theStore = me.configureDataStore(config, theDataModelName);
		config.store = theStore;

		// Selection Model
		var theSelectionModel = me.configureSelectionModel(config);
		config.selModel = theSelectionModel; 		
		
		// Pagination
		var thePaginator = me.configurePaginator(config);
		config.bbar = thePaginator;
		
		var theColumns = me.configureCellRender(config);
		config.columns = theColumns;

		me.callParent(arguments);

		me.pageID = thePageID;
		me.componentID = theComponentID;
	},
	
	configureCellRender : function(gridConfig) {
		var theColumns = gridConfig.columns;
		for (var iCol = 0; iCol < theColumns.length; iCol++) {
			theColumns[iCol].renderer = function(value, metadata, record, rowIndex, colIndex, store) {
				var fatuRowMetadata = record.get("__fatuhiva_rowMetadata");
				var bgColors = fatuRowMetadata['__fatuhiva_bgcolors'];
				var dataIndex = metadata.column.dataIndex;
				var theColor = bgColors[dataIndex];
				if(theColor !== undefined) {
					metadata.style = "background-color:" + theColor; 	
				}
				return value;
			};
		}
		return theColumns;
	},
	
	configureDataModel : function(gridConfig) {
		var thePageID = gridConfig.pageID;
		var theComponentID = gridConfig.id;

		var theModelName = gridConfig.id + "_Model";
		var theModelConfig = gridConfig.fatuModelConfig;

		Ext.define(theModelName, {
			extend : 'Ext.data.Model',
			fields : theModelConfig.fields,
			idProperty : theModelConfig.idProperty
		});
		
		return theModelName;
	},
	
	configureDataStore : function(gridConfig, dataModelName) {
		var thePageID = gridConfig.pageID;
		var theComponentID = gridConfig.id;

		var theStoreConfig = gridConfig.id + "_Store";
		// Data Store
		var theStore = Ext.create('Fatuhiva.data.Store', {
			pageID : thePageID,
			componentID : theComponentID,
			storeId : theStoreConfig.storeId,
			model : dataModelName,
			autoLoad : true,
			pageSize : 33,
			proxy : new Ext.data.proxy.Ajax({
				type : 'ajax',
				url : '../action',
				extraParams : {
					"PAGE_ID" : thePageID,
					"EVENT" : "getData",
					"COMPONENT_ID" : theComponentID
				},
				reader : {
					type : 'json',
					rootProperty : 'items',
					totalProperty : 'totalCount'
				}
			})
		});
		var thePrefetchListener = new Fatuhiva.gridpanel.FatuhivaGridStorePrefetchListener();
		theStore.on('load', thePrefetchListener.execute, theStore);
		
		return theStore;
	},
	
	
	configureSelectionModel : function(gridConfig) {
		var thePageID = gridConfig.pageID;
		var theComponentID = gridConfig.id;

		// Selection Model
		var theSelectionConfig = gridConfig.fatuSelectionConfig || {
			pageID : thePageID,
			componentID : theComponentID,
			pruneRemoved : false,
			mode : "MULTI"
		};

		var theSelectionModel = new Fatuhiva.gridpanel.CheckboxSelectionModel(theSelectionConfig);
		var theSelectionChangedListener = new Fatuhiva.gridpanel.FatuhivaSelectionChangedListener({
			pageID : thePageID,
			componentID : theComponentID
		});
		theSelectionModel.on('selectionchange', theSelectionChangedListener.execute, theSelectionChangedListener);
		return theSelectionModel;
	},
	
	configurePaginator : function(gridConfig) {
		var theStore = gridConfig.store;
		var thePaginator = Ext.create('Ext.PagingToolbar', {
			store : theStore,
			displayInfo : true,
			displayMsg : 'Displaying topics {0} - {1} of {2}',
			emptyMsg : "No topics to display",
		});
		return thePaginator;
	},

	initComponent : function() {
		var me = this;
		me.callParent();
	},

	getPageID : function() {
		var me = this;
		return me.pageID;
	}
	
	
	

});
