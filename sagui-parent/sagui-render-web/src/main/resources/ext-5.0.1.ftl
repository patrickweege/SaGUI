<!DOCTYPE HTML>
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=${ENCODING}">
        <link rel='stylesheet' href='../resource/${EXT_VERSION}/build/packages/ext-theme-${THEME}/build/resources/ext-theme-${THEME}-all.css' type='text/css'/>
        <script type='text/javascript' src='../resource/${EXT_VERSION}/build/ext-all-debug.js'></script>
        <script type='text/javascript' src='../resource/${EXT_VERSION}/build/packages/ext-theme-${THEME}/build/ext-theme-${THEME}.js'></script>
		
		<script type='text/javascript' src='../resource/javascript/fatuhiva/commons.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/Fatuhiva.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaComboBoxSelectionListener.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaTextBoxOnBlurListener.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/FatuhivaButtonClickListener.js'></script>
		
		<script type='text/javascript' src='../resource/javascript/fatuhiva/action/FatuhivaBaseAction.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/action/FatuhivaRefreshAction.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/action/FatuhivaRequestAction.js'></script>
		
		<!-- For Fatuhiva Grid -->
		<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaDataStoreListeners.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaSelectionModelListeners.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaCheckboxSelectionModel.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaGridPanel.js'></script>
		<script type='text/javascript' src='../resource/javascript/fatuhiva/gridpanel/FatuhivaDataStore.js'></script>
		
		<title id='the-title'>Page Template</title>
	</head>
	<body id='ext-content'>
		
		<script type='text/javascript'>
			Ext.require(['Ext.*']);
			Ext.onReady(function(){
			Ext.QuickTips.init();
			
				${EXT_SCRIPT}

			});
		</script>
	
	</body>
</html>