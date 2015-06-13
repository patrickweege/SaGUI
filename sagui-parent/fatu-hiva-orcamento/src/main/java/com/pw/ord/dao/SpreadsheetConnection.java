package com.pw.ord.dao;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;

public class SpreadsheetConnection {

    private static final String CLIENT_ID = "611071613661-vtcpvg6nujn1u6k60u3eg4ggm06b60b1.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "TwtOhB5MO-_0WM4_VdKq8FcP";
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    private final GoogleAuthorizationCodeFlow authFlow;
    private final String sheetName;

    private SpreadsheetService service;
    private SpreadsheetEntry spreadsheet;
    private boolean autenticated; 

    public SpreadsheetConnection(String spreadsheet) {
        this.sheetName = spreadsheet;
        this.spreadsheet = null;
        this.autenticated = false;

        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        List<String> scopes = Arrays.asList(DriveScopes.DRIVE, "https://spreadsheets.google.com/feeds", "https://www.googleapis.com/auth/drive.file");
        Builder builder = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, scopes);
        builder.setAccessType("online");
        builder.setApprovalPrompt("auto");
        this.authFlow = builder.build();

    }

    public String getAutorizationURL() {
        String url = authFlow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        return url;
    }

    public void autenticate(String token) {
        try {
            GoogleTokenResponse response = authFlow.newTokenRequest(token).setRedirectUri(REDIRECT_URI).execute();
            GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
            this.service = new SpreadsheetService("MyAppNameHere");
            this.service.setOAuth2Credentials(credential);

            this.spreadsheet = getSpreadsheet(sheetName);
            this.autenticated = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAutenticated() {
        return this.autenticated;
    }
    
    public SpreadsheetService getSpreadsheetService() {
        return service;
    }

    public SpreadsheetEntry getSpreadsheet() {
        return spreadsheet;
    }

    private SpreadsheetEntry getSpreadsheet(String toFind) {

        try {
            URL url = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
            // Make a request to the API and get all spreadsheets.
            SpreadsheetFeed feed = service.getFeed(url, SpreadsheetFeed.class);
            List<SpreadsheetEntry> sheets = feed.getEntries();
            for (SpreadsheetEntry sheet : sheets) {
                String aSheetName = sheet.getTitle().getPlainText();
                if (StringUtils.equals(toFind, aSheetName)) {
                    return sheet;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
