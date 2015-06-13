Ext.require(['Ext.*']);
Ext.onReady(function(){
Ext.QuickTips.init();
var panel = new Ext.panel.Panel({
	id : '794149e1-773b-47d8-87cc-3e1f09baf91e'
	,height : 600
	,renderTo : Ext.getBody()
});
Ext.tip.QuickTipManager.init;
var cmp = new Ext.panel.Panel({
	id : '7f2b5c80-3165-4eb6-829e-ce427afe81dd'
	,layout : 'auto'	,	,width : 350
	,height : 100
	,title : 'Form 1'
});
Ext.getCmp('794149e1-773b-47d8-87cc-3e1f09baf91e').insert(0,cmp);
var cmp = new Ext.Button({
	id : 'd53d2cb9-1369-4fdf-b13b-4dc7b330e9a6'
	,text : 'Make Form 2 Greater'
	,tooltip : 'Make Form 2 Greater'
	,handler: function() {
		Ext.Ajax.request({
			url: '../action',
			params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: 'd53d2cb9-1369-4fdf-b13b-4dc7b330e9a6'},
	        success: function(response, opts) {
	            eval(response.responseText);
	        }
		});
	}
});
Ext.getCmp('7f2b5c80-3165-4eb6-829e-ce427afe81dd').insert(0,cmp);
var cmp = new Ext.Button({
	id : 'b19e7df4-eac2-4627-b178-fc69529f8e52'
	,text : 'Make Form 2 Less'
	,tooltip : 'Make Form 2 Less'
	,handler: function() {
		Ext.Ajax.request({
			url: '../action',
			params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: 'b19e7df4-eac2-4627-b178-fc69529f8e52'},
	        success: function(response, opts) {
	            eval(response.responseText);
	        }
		});
	}
});
Ext.getCmp('7f2b5c80-3165-4eb6-829e-ce427afe81dd').insert(1,cmp);
var cmp = new Ext.form.Label({
	id : '2ba112e6-324e-448c-b3c9-e3713ee2aae0'
	,text : 'Text for Label'
});
Ext.getCmp('7f2b5c80-3165-4eb6-829e-ce427afe81dd').insert(2,cmp);
var cmp = new Ext.panel.Panel({
	id : '3a210b85-78d1-45c3-aa6a-dc0880a7d6c3'
	,layout : 'auto'	,	,width : 300
	,height : 150
	,title : 'Form 2'
});
Ext.getCmp('794149e1-773b-47d8-87cc-3e1f09baf91e').insert(1,cmp);
var cmp = new Ext.form.TextField({
	id : '0647af92-18d2-4ec6-934a-36bba4f208cc'
	,value : null
	,fieldLabel : 'Lbl For TextBox'
	,activeError : null
	,listeners  : {
		'blur' : function(field, opts) {
			 if(!field.isDirty()) return;
			 Ext.Ajax.request({
			     url: '../action',
			     params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: '0647af92-18d2-4ec6-934a-36bba4f208cc', NEW_VALUE : field.getValue()},
			     success: function(response, opts) {
			         eval(response.responseText);
			     }
			 })
		}// blur listener
	}
});
Ext.getCmp('3a210b85-78d1-45c3-aa6a-dc0880a7d6c3').insert(0,cmp);
var cmp = new Ext.form.TextField({
	id : 'ba0a0ab6-2f64-44b8-bdce-dfe91e1c8f7d'
	,value : null
	,fieldLabel : 'Lbl For TextBox 2'
	,activeError : null
	,listeners  : {
		'blur' : function(field, opts) {
			 if(!field.isDirty()) return;
			 Ext.Ajax.request({
			     url: '../action',
			     params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: 'ba0a0ab6-2f64-44b8-bdce-dfe91e1c8f7d', NEW_VALUE : field.getValue()},
			     success: function(response, opts) {
			         eval(response.responseText);
			     }
			 })
		}// blur listener
	}
});
Ext.getCmp('3a210b85-78d1-45c3-aa6a-dc0880a7d6c3').insert(1,cmp);
var cmp = new Ext.Button({
	id : 'b5f212a3-d20f-4acb-b876-22830a870920'
	,text : 'Default Label 2'
	,tooltip : 'Default Hint 2'
	,handler: function() {
		Ext.Ajax.request({
			url: '../action',
			params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: 'b5f212a3-d20f-4acb-b876-22830a870920'},
	        success: function(response, opts) {
	            eval(response.responseText);
	        }
		});
	}
});
Ext.getCmp('3a210b85-78d1-45c3-aa6a-dc0880a7d6c3').insert(2,cmp);
var cmp = new Ext.panel.Panel({
	id : 'd5f78eea-1d40-4e2b-8c2d-80ec553e6eb0'
	,layout : 'auto'	,	,width : 300
	,height : 150
	,title : 'Employee Form'
});
Ext.getCmp('794149e1-773b-47d8-87cc-3e1f09baf91e').insert(2,cmp);
var cmp = new Ext.form.TextField({
	id : '38ffe056-1494-4691-b257-d8f9c8e90331'
	,value : null
	,fieldLabel : 'Employee ID'
	,activeError : null
	,listeners  : {
		'blur' : function(field, opts) {
			 if(!field.isDirty()) return;
			 Ext.Ajax.request({
			     url: '../action',
			     params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: '38ffe056-1494-4691-b257-d8f9c8e90331', NEW_VALUE : field.getValue()},
			     success: function(response, opts) {
			         eval(response.responseText);
			     }
			 })
		}// blur listener
	}
});
Ext.getCmp('d5f78eea-1d40-4e2b-8c2d-80ec553e6eb0').insert(0,cmp);
var cmp = new Ext.form.TextField({
	id : '4dd468b5-0c80-4547-b75a-67e608269ec5'
	,value : null
	,fieldLabel : 'Employee Name'
	,activeError : null
	,listeners  : {
		'blur' : function(field, opts) {
			 if(!field.isDirty()) return;
			 Ext.Ajax.request({
			     url: '../action',
			     params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: '4dd468b5-0c80-4547-b75a-67e608269ec5', NEW_VALUE : field.getValue()},
			     success: function(response, opts) {
			         eval(response.responseText);
			     }
			 })
		}// blur listener
	}
});
Ext.getCmp('d5f78eea-1d40-4e2b-8c2d-80ec553e6eb0').insert(1,cmp);
var cmp = new Ext.form.TextField({
	id : '171b90d6-4d53-428a-a83e-6e0d2486b69b'
	,value : null
	,fieldLabel : 'Employee Name'
	,activeError : null
	,listeners  : {
		'blur' : function(field, opts) {
			 if(!field.isDirty()) return;
			 Ext.Ajax.request({
			     url: '../action',
			     params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: '171b90d6-4d53-428a-a83e-6e0d2486b69b', NEW_VALUE : field.getValue()},
			     success: function(response, opts) {
			         eval(response.responseText);
			     }
			 })
		}// blur listener
	}
});
Ext.getCmp('d5f78eea-1d40-4e2b-8c2d-80ec553e6eb0').insert(2,cmp);
var cmp = new Ext.panel.Panel({
	id : '11375e34-8de9-42e6-a667-6dd06648ebc7'
	,layout : {
		type : 'table',
		columns : 3
}
	,	,width : 500
	,height : 150
	,title : 'ChangeChildrenTestForm Form'
});
Ext.getCmp('794149e1-773b-47d8-87cc-3e1f09baf91e').insert(3,cmp);
var cmp = new Ext.Button({
	id : 'a703792c-b8ec-40cf-bb1e-cf21c5d23ed3'
	,text : 'Add Child'
	,tooltip : null
	,handler: function() {
		Ext.Ajax.request({
			url: '../action',
			params: { PAGE_ID: '794149e1-773b-47d8-87cc-3e1f09baf91e', COMPONENT_ID: 'a703792c-b8ec-40cf-bb1e-cf21c5d23ed3'},
	        success: function(response, opts) {
	            eval(response.responseText);
	        }
		});
	}
});
Ext.getCmp('11375e34-8de9-42e6-a667-6dd06648ebc7').insert(0,cmp);