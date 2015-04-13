package example.com.chequecashing.Async;

import android.util.Log;

import org.apache.http.client.HttpResponseException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import example.com.chequecashing.account.UserInfo;
import example.com.chequecashing.parser.UserXMLParser;


public class PhotoAsyncTask extends android.os.AsyncTask<Void, Void, Void> {

    private static final String SOAP_ACTION = "http://footballpool.dataaccess.eu/TopGoalScorers";
    private static final String METHOD_NAME = "TopGoalScorers";
    private static final String NAMESPACE = "http://footballpool.dataaccess.eu";
    private static final String URL = "http://footballpool.dataaccess.eu/data/info.wso?WSDL";

    //Kevin Service
    //private static final String SOAP_ACTION = "http://localhost/getDeposit";
    //private static final String METHOD_NAME = "getDeposit";
    //private static final String NAMESPACE = "http://localhost";
    //private static final String URL = "http://localhost/data/info.wso?WSDL";

    private String accessCard;
//    private byte[] frontPhoto;
//    private byte[] backPhoto;
    private String frontPhoto;
    private String backPhoto;
    private String amount;
    private String account;
    private String accountType;


    private String responseCode;

    private String test2 = "<AccountInfo>\n" +
            "<Account_Type>1</Account_Type>\n" +
            "<Password>Humber</Password>\n" +
            "<first_Name>Kevin</first_Name>\n" +
            "<Last_Name>Haynes</Last_Name>\n" +
            "<Card_Number>824682157</Card_Number>\n" +
            "<Account_Description>Savings Account</Account_Description>\n" +
            "<Account_Type>5</Account_Type>\n" +
            "<Password>Humber</Password>\n" +
            "<first_Name>Kevin</first_Name>\n" +
            "<Last_Name>Haynes</Last_Name>\n" +
            "<Card_Number>824682157</Card_Number>\n" +
            "<Account_Description>Business Account</Account_Description>\n" +
            "</AccountInfo>";

    public PhotoAsyncTask(String accessCard, String frontPhoto, String backPhoto,String amount, String account, String accountType) {
        super();
        this.accessCard = accessCard;
        this.frontPhoto = frontPhoto;
        this.backPhoto = backPhoto;
        this.amount = amount;
        this.account = account;
        this.accountType = accountType;
    }

    public List<UserInfo> getResponse() {
        return response;
    }

    private List<UserInfo> response;

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        try {
            /************** Read XML *************/
            /*TODO : Change the response parsing from the test string to the response from the SOAP call */
            //BufferedReader br = new BufferedReader(new StringReader(result.toString()));
            BufferedReader br = new BufferedReader(new StringReader(test2));
            InputSource is = new InputSource(br);

            /************  Parse XML **************/

            UserXMLParser parser = new UserXMLParser();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser sp = factory.newSAXParser();
            XMLReader reader = sp.getXMLReader();
            reader.setContentHandler(parser);
            reader.parse(is);

            /************* Get Parse data in a ArrayList **********/
            response = parser.list;
        } catch (ParserConfigurationException e) {

        } catch (SAXException e) {

        } catch (IOException e) {

        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("iTopN", "5");
        //request.addProperty("photo1",frontPhoto);
        //request.addProperty("photo2",backPhoto);
        //request.addProperty("cardNo",accessCard);
        //request.addProperty("account",account);
        //request.addProperty("newBalance",amount);
        //request.addProperty("accountType",accountType);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(URL);

        httpTransport.debug = true;
        try {
            httpTransport.call(SOAP_ACTION, envelope);


        } catch (HttpResponseException e) {
            // TODO Auto-generated catch block
            Log.d("dump Request: ", httpTransport.requestDump);
            Log.d("dump response: " ,httpTransport.responseDump);
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.d("dump Request: ", httpTransport.requestDump);
            Log.d("dump response: " ,httpTransport.responseDump);
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            Log.d("dump Request: ", httpTransport.requestDump);
            Log.d("dump response: " ,httpTransport.responseDump);
            e.printStackTrace();
        } //send request
        SoapObject result = null;
        try {
            result = (SoapObject) envelope.getResponse();
            Log.d("dump Request: ", httpTransport.requestDump);
            Log.d("dump response: " ,httpTransport.responseDump);
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }
}
