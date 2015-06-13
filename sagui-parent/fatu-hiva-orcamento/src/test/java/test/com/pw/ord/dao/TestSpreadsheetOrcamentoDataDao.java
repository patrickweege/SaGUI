package test.com.pw.ord.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pw.ord.dao.SpreadsheetConnection;
import com.pw.ord.dao.SpreadsheetOrcamentoDataDao;
import com.pw.ord.et.OrcamentoData;

public class TestSpreadsheetOrcamentoDataDao {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private static SpreadsheetConnection connection;

    @BeforeClass
    public static void beforeClass() throws IOException {
        System.setProperty("http.proxyHost", "proxysp.sp.t-systems.com.br");
        System.setProperty("http.proxyPort", "8002");
        System.setProperty("https.proxyHost", "proxysp.sp.t-systems.com.br");
        System.setProperty("https.proxyPort", "8002");

        SpreadsheetConnection sConn = new SpreadsheetConnection("TESTE_DATA");

        String authURL = sConn.getAutorizationURL();
        System.out.println("Get Authorization Toket at: " + authURL);
        System.out.println("----------------");
        System.out.print("Please Type the Auth Token Here: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String authToken = br.readLine();

        sConn.autenticate(authToken);

        connection = sConn;
    }

    @Test
    public void testFindByID() throws IOException {
        SpreadsheetOrcamentoDataDao dao = new SpreadsheetOrcamentoDataDao(connection);
        OrcamentoData found = dao.getByID(Long.valueOf(1));

        Assert.assertNotNull(found);
        Assert.assertEquals(Long.valueOf(1), found.getId());
        Assert.assertEquals("UID1", found.getUserId());
        Assert.assertEquals("CCREDITO1", found.getContaCredito());
        Assert.assertEquals("CDEBITO1", found.getContaDebito());
        Assert.assertEquals("CCUSTO1", found.getCentroCusto());
        Assert.assertEquals("OBS1", found.getObservacao());
    }

    @Test
    public void testGetAll() throws IOException {
        SpreadsheetOrcamentoDataDao dao = new SpreadsheetOrcamentoDataDao(connection);

        OrcamentoData oData;

        List<OrcamentoData> all = dao.getAll();

        Assert.assertEquals(2, all.size());

        oData = all.get(0);
        Assert.assertEquals(Long.valueOf(1), oData.getId());
        Assert.assertEquals("UID1", oData.getUserId());
        Assert.assertEquals("CCREDITO1", oData.getContaCredito());
        Assert.assertEquals("CDEBITO1", oData.getContaDebito());
        Assert.assertEquals("CCUSTO1", oData.getCentroCusto());
        Assert.assertEquals("OBS1", oData.getObservacao());

        oData = all.get(1);
        Assert.assertEquals(Long.valueOf(2), oData.getId());
        Assert.assertEquals("UID2", oData.getUserId());
        Assert.assertEquals("CCREDITO2", oData.getContaCredito());
        Assert.assertEquals("CDEBITO2", oData.getContaDebito());
        Assert.assertEquals("CCUSTO2", oData.getCentroCusto());
        Assert.assertEquals("OBS2", oData.getObservacao());

    }

    @Test
    public void testSaveNew() throws IOException, ParseException {
        SpreadsheetOrcamentoDataDao dao = new SpreadsheetOrcamentoDataDao(connection);

        OrcamentoData toSave = new OrcamentoData();
        toSave.setId(Long.valueOf(999));
        toSave.setUserId("UID999");

        Date date = DATE_FORMAT.parse("09/09/1999");
        toSave.setDataRealizado(date);

        date = DATE_FORMAT.parse("19/09/1999");
        toSave.setDataPrevisto(date);

        toSave.setContaCredito("CCREDITO999");
        toSave.setContaDebito("CDEBITO999");
        toSave.setCentroCusto("CCUSTO999");
        toSave.setValor(BigDecimal.TEN);
        toSave.setObservacao("OBS999");

        OrcamentoData saved = dao.save(toSave);
        
        // Retrieve
        Assert.assertEquals(Long.valueOf(999), saved.getId());
        Assert.assertEquals("UID999", saved.getUserId());
        Assert.assertEquals("CCREDITO999", saved.getContaCredito());
        Assert.assertEquals("CDEBITO999", saved.getContaDebito());
        Assert.assertEquals("CCUSTO999", saved.getCentroCusto());
        Assert.assertEquals("OBS999", saved.getObservacao());
        
        // Delete recent Saved
        dao.delete(saved.getId());
    }

}
