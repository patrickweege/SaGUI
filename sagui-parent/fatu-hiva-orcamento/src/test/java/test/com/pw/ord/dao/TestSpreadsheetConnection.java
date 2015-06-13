package test.com.pw.ord.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.pw.ord.dao.SpreadsheetConnection;


public class TestSpreadsheetConnection {
    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("http.proxyHost", "proxysp.sp.t-systems.com.br");
        System.setProperty("http.proxyPort", "8002");
        System.setProperty("https.proxyHost", "proxysp.sp.t-systems.com.br");
        System.setProperty("https.proxyPort", "8002");
    }
    
    @Test
    public void testConnection() throws IOException {
        SpreadsheetConnection sConn = new SpreadsheetConnection("TESTE_DATA");
        
        String authURL = sConn.getAutorizationURL();
        System.out.println("Get Authorization Toket at: " + authURL);
        System.out.println("----------------");
        System.out.print("Please Type the Auth Token Here: " );
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String authToken = br.readLine();

        sConn.autenticate(authToken);
        SpreadsheetEntry sSheet = sConn.getSpreadsheet();
        
        Assert.assertNotNull(sSheet);
    }
    
    

}
