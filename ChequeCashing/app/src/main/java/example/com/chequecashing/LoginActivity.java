package example.com.chequecashing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import java.io.Serializable;
import java.io.StringReader;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import example.com.chequecashing.account.UserInfo;
import example.com.chequecashing.parser.UserXMLParser;


public class LoginActivity extends ActionBarActivity {


    private Context mContext;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginAction(View view){
        TextView card = (TextView)findViewById(R.id.cardNumber);
        TextView password = (TextView)findViewById(R.id.password);
        TextView feedback = (TextView)findViewById(R.id.userFeedback);
        feedback.setText(" ");
        String strCard = card.getText().toString();
        String strPass = password.getText().toString();
        //call the server instead of the hard coded values - Uncomment those lines to test it
        LoginAsyncTask2 loginAsyncTask = new LoginAsyncTask2(strCard,strPass);
        //LoginAction loginAction = new LoginAction(strCard,strPass);
        try {
            //loginAction.onPostExecute();
            loginAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }











    private class LoginAsyncTask2 extends android.os.AsyncTask<Void, Void, Void> {

        private static final String SOAP_ACTION = "http://footballpool.dataaccess.eu/TopGoalScorers";
        private static final String METHOD_NAME = "TopGoalScorers";
        private static final String NAMESPACE = "http://footballpool.dataaccess.eu";
        private static final String URL = "http://footballpool.dataaccess.eu/data/info.wso?WSDL";


        //Kevin Service
        //private static final String SOAP_ACTION = "http://localhost/getLogInfo";
        //private static final String METHOD_NAME = "getLogInfo";
        //private static final String NAMESPACE = "http://localhost";
        //private static final String URL = "http://localhost/data/info.wso?WSDL";

        private String accessCard;
        private String password;



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

        public LoginAsyncTask2(String accessCard, String password) {
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
            }catch (ParserConfigurationException e){

            }catch (SAXException e){

            }catch (IOException e){

            }


            if(response != null && response.size()>0){
                SharedPreferences pref = mContext.getSharedPreferences("ChequeCashing", MODE_PRIVATE);
                if(pref != null){
                    String accept = pref.getString("AcceptTime","empty");
                    if(!accept.isEmpty()){
                        Intent mainScreenIntent = new Intent(mContext,MainScreenActivity.class);
                        mainScreenIntent.putExtra("UserInfo",(Serializable)response.get(0));
                        startActivity(mainScreenIntent);
                    }else{
                        Intent termsIntent = new Intent(mContext, TermsActivity.class);
                        startActivity(termsIntent);
                    }
                }else{
                    Intent termsIntent = new Intent(mContext, TermsActivity.class);
                    startActivity(termsIntent);
                }

            }else{
                Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
                //mContext.feedback.setText("Invalid information");
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
            //request.addProperty("account", accessCard);
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
}
