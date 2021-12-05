package teste;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;
import java.util.*;

import org.apache.commons.codec.binary.Base64;

public class TesteGData {

    public static void main(String[] args) throws AuthenticationException, MalformedURLException, IOException, ServiceException {

        System.setProperty("http.proxyHost", "proxysp.sp.t-systems.com.br");
        System.setProperty("http.proxyPort", "8002");
        System.setProperty("https.proxyHost", "proxysp.sp.t-systems.com.br");
        System.setProperty("https.proxyPort", "8002");
        
        String encoded = new String(Base64.encodeBase64(new String("patrick.weege:#Senha001").getBytes()));
        String base64encodedCredentials = "Basic " + encoded;
        
        SpreadsheetService service = new SpreadsheetService("MySpreadsheetIntegration-v1");
        service.getRequestFactory().setPrivateHeader("Proxy-Authorization", base64encodedCredentials);
        service.setProtocolVersion(SpreadsheetService.Versions.V3);
        service.setUserCredentials("patrickweege@gmail.com", "dw4ng22r");

        // TODO: Authorize the service object for a specific user (see other sections)

        // Define the URL to request.  This should never change.
        URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");

        // Make a request to the API and get all spreadsheets.
        SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        if (spreadsheets.size() == 0) {
            // TODO: There were no spreadsheets, act accordingly.
        }

        // TODO: Choose a spreadsheet more intelligently based on your
        // app's needs.
        SpreadsheetEntry spreadsheet = spreadsheets.get(0);
        System.out.println(spreadsheet.getTitle().getPlainText());

        // Get the first worksheet of the first spreadsheet.
        // TODO: Choose a worksheet more intelligently based on your
        // app's needs.
        WorksheetFeed worksheetFeed = service.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
        List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
        WorksheetEntry worksheet = worksheets.get(0);

        // Fetch the list feed of the worksheet.
        URL listFeedUrl = worksheet.getListFeedUrl();
        ListFeed listFeed = service.getFeed(listFeedUrl, ListFeed.class);

        // Create a local representation of the new row.
        ListEntry row = new ListEntry();
        row.getCustomElements().setValueLocal("firstname", "Joe");
        row.getCustomElements().setValueLocal("lastname", "Smith");
        row.getCustomElements().setValueLocal("age", "26");
        row.getCustomElements().setValueLocal("height", "176");

        // Send the new row to the API for insertion.
        row = service.insert(listFeedUrl, row);

    }

}
