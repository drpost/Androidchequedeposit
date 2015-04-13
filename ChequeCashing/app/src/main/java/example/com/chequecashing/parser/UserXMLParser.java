package example.com.chequecashing.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import example.com.chequecashing.account.Account;
import example.com.chequecashing.account.AccountInfo;
import example.com.chequecashing.account.UserInfo;


public class UserXMLParser extends DefaultHandler{


    public List<UserInfo> list=null;
    public List<Account> listAccount = null;

    // string builder acts as a buffer
    StringBuilder builder;

    UserInfo userInfo =null;
    Account accountInfo=null;


    // Initialize the arraylist
    // @throws SAXException

    @Override
    public void startDocument() throws SAXException {

        /******* Create ArrayList To Store XmlValuesModel object ******/
        list = new ArrayList<UserInfo>();
    }


    // Initialize the temp XmlValuesModel object which will hold the parsed info
    // and the string builder that will store the read characters
    // @param uri
    // @param localName ( Parsed Node name will come in localName  )
    // @param qName
    // @param attributes
    // @throws SAXException

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        /****  When New XML Node initiating to parse this function called *****/

        // Create StringBuilder object to store xml node value
        builder=new StringBuilder();

        if(localName.equalsIgnoreCase("AccountInfo")){
            userInfo = new UserInfo();
        }else if(localName.equalsIgnoreCase("Account_Type")){
            accountInfo = new Account();

        }
//        if(localName.equalsIgnoreCase("UserInfo")){
//
//            //Log.i("parse","====login=====");
//            /********** Create Model Object  *********/
//            userInfo = new UserInfo();
//        }else if(localName.equalsIgnoreCase("AccountInfo")){
//            accountInfo = new Account();
//        }
    }



    // Finished reading the login tag, add it to arraylist
    // @param uri
    // @param localName
    // @param qName
    // @throws SAXException

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {


        if(localName.equalsIgnoreCase("AccountInfo")){

            /** finished reading a job xml node, add it to the arraylist **/
            userInfo.setAccountList(listAccount);
            if(list == null){
                list = new ArrayList<UserInfo>();
            }
            list.add(userInfo);

        }
        else  if(localName.equalsIgnoreCase("Account_Type")){
            if(listAccount == null){
                listAccount = new ArrayList<Account>();
            }
            accountInfo.setAccountNumber(builder.toString());
            switch (builder.toString()){
                case "1":
                    accountInfo.setAccountType("Regular Account");
                    break;
                case "2":
                    accountInfo.setAccountType("Big Account");
                    break;
                case "3":
                    accountInfo.setAccountType("Small Account");
                    break;
                case "4":
                    accountInfo.setAccountType("Investment Account");
                    break;
                case "5":
                    accountInfo.setAccountType("Business Account");
                    break;
            }
            listAccount.add(accountInfo);
        }
//        else  if(localName.equalsIgnoreCase("")){
//
//
//        }
//        else  if(localName.equalsIgnoreCase("Account_Description")){
//
//            accountInfo.setAccountType(builder.toString());
//        }
        else if(localName.equalsIgnoreCase("Password")){

            userInfo.setPassword(builder.toString());
        }
        else if(localName.equalsIgnoreCase("first_Name")){
            userInfo.setFirstName(builder.toString());
        }
        else if(localName.equalsIgnoreCase("Last_Name")){
            userInfo.setLastName(builder.toString());
        }
        else if(localName.equalsIgnoreCase("Card_Number")){
            userInfo.setAccessCard(builder.toString());
        }



        // Log.i("parse",localName.toString()+"========="+builder.toString());
    }


    // Read the value of each xml NODE
    // @param ch
    // @param start
    // @param length
    // @throws SAXException

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        /******  Read the characters and append them to the buffer  ******/
        String tempString=new String(ch, start, length);
        builder.append(tempString);
    }
}
