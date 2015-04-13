package example.com.chequecashing.Async;

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


public class LoginAsyncTask extends android.os.AsyncTask<Void, Void, Void> {

    private static final String SOAP_ACTION = "http://footballpool.dataaccess.eu/TopGoalScorers";
    private static final String METHOD_NAME = "TopGoalScorers";
    private static final String NAMESPACE = "http://footballpool.dataaccess.eu";
    private static final String URL = "http://footballpool.dataaccess.eu/data/info.wso?WSDL";
    private String accessCard;
    private String password;

    private String TestString = "<UserInfo>\n" +
            "<Password>Humber</Password>\n" +
            "<first_Name>Kevin</first_Name>\n" +
            "<Last_Name>Haynes</Last_Name>\n" +
            "<Card_Number>824682157</Card_Number>\n" +
            "<AccountInfo>\n" +
            "<Account_Description>regular account</Account_Description>\n" +
            "<Account_Type>5</Account_Type>\n" +
            "</AccountInfo>\n" +
            "<AccountInfo>\n" +
            "<Account_Description>business</Account_Description>\n" +
            "<Account_Type>5</Account_Type>\n" +
            "</AccountInfo>\n" +
            "</UserInfo>";

    public LoginAsyncTask(String accessCard, String password) {
        super();
        this.accessCard = accessCard;
        this.password = password;
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
            BufferedReader br = new BufferedReader(new StringReader(TestString));
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
        }catch (ParserConfigurationException e){

        }catch (SAXException e){

        }catch (IOException e){

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
        //request.addProperty("accessCard", accessCard);
        //request.addProperty("password", password);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(URL);

        httpTransport.debug = true;
        try {
            httpTransport.call(SOAP_ACTION, envelope);
        } catch (HttpResponseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //send request
        SoapObject result = null;
        try {
            result = (SoapObject) envelope.getResponse();

        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }
}
