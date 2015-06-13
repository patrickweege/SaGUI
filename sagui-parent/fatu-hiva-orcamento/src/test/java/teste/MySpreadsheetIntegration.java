package teste;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
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

public class MySpreadsheetIntegration {

    private static final String CLIENT_ID = "611071613661-vtcpvg6nujn1u6k60u3eg4ggm06b60b1.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "TwtOhB5MO-_0WM4_VdKq8FcP";
    private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    public static void main(String[] args) throws Exception {

        System.setProperty("http.proxyHost", "proxysp.sp.t-systems.com.br");
        System.setProperty("http.proxyPort", "8002");
        System.setProperty("https.proxyHost", "proxysp.sp.t-systems.com.br");
        System.setProperty("https.proxyPort", "8002");
        
        if (CLIENT_ID.equals("YOUR_CLIENT_ID") || CLIENT_SECRET.equals("YOUR_SECRET_ID")) {
            throw new RuntimeException("TODO: Get client ID and SECRET from https://cloud.google.com/console");
        }
        
        
        // get credentials similar to Java DrEdit example
        // https://developers.google.com/drive/examples/java
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        List<String> scopes = Arrays.asList(DriveScopes.DRIVE, "https://spreadsheets.google.com/feeds", "https://docs.google.com/feeds");
        
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, scopes).setAccessType("online").setApprovalPrompt("auto").build();
        
        String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        
        System.out.println("Please open the following URL in your " + "browser then type the authorization code:");
        System.out.println("  " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);

        // create the service and pass it the credentials you created earlier
        SpreadsheetService service = new SpreadsheetService("MyAppNameHere");
        service.setOAuth2Credentials(credential);

        // Define the URL to request.  This should never change.
        URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");

        // Make a request to the API and get all spreadsheets.
        SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        // Iterate through all of the spreadsheets returned
        for (SpreadsheetEntry spreadsheet : spreadsheets) {
            // Print the title of this spreadsheet to the screen
            System.out.println(spreadsheet.getTitle().getPlainText());
        }
    }

}
