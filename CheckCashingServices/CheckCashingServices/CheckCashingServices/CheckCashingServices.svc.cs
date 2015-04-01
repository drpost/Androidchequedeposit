/******************************************************************************************
 *                                                                                        *
 *     File: WCF Service                                                                  *
 *  Student: Kevin Haynes                                                                 *
 * Student#: 820076743                                                                    * 
 *  Course#: COMP231-061                                                                  * 
 *                                                                                        *
 * ****************************************************************************************/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using TermsAndConditions;
using LogIn;
using XmlParser;
using Images;
using System.Data;
using System.Data.Entity;
using System.Data.SqlClient;

namespace CheckCashingServices
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    public class CheckServices : ICheckServices
    {
       
        string answer = "";
        string answer1 = "";
        Terms terms = new Terms();
        LogInVersion3 lg = new LogInVersion3();

        //gets users login info
        public string getLogInfo(string account, string password)
        {
         //stores results in XML format
            answer = lg.getLogIn(password, account);
           // Extract ex = new Extract();
      
            if(answer == "")
            {
                answer = "Invalid Password or Card Number";
                return answer;
            }
            else
            {
            answer1  = "<?xml version=\"1.0\"?><AccountInfo>" + answer + "</AccountInfo>";
           // ex.convertDeposit(answer1);
            return answer1; 
            }
          
        }//end getLogIn method

        //gets acceptance of Terms & Conditions
        public string showTerms() 
        {
            answer = terms.getTermsAndAgreements();
            return answer;
        }

        //gets acceptance of Terms & Conditions
        public string getTermsAndConditions(bool value)
        {
            
            
            if (value == true)
            {
                
                 answer = "Terms accepted";
                 return answer;
            }
            else 
            {
                answer = "You must accept the Terms and Agreement befor you can use this application.";
                return answer;
            }
        }

        public string getDeposit(string cardNo, string account, string accountType, double amount, string pic1, string pic2) 
        {
            ImageConvert img1 = new ImageConvert();
            ImageConvert img2 = new ImageConvert();
            string picFront = img1.getFront(pic1);
            string picBack = img2.getBack(pic2);
            string date =  DateTime.Now.Date.ToString();
            string TransId = "12348";
            int rows;
            SqlConnection conn = new SqlConnection("Data Source=JEHOVAH-NISSI;Initial Catalog=Yeowan_Ban;Integrated Security=True");
            try
            {
                
                conn.Open();
                SqlCommand cmd = new SqlCommand("insert into Transactions values (\'" + TransId + "\','" + account + "'," + amount + ",'" + picFront + "','" +
                                                picBack + "','" + date + "')");
                cmd.Connection = conn;
                cmd.CommandTimeout = 30;
               // cmd.InsertCommand = insertCommand;
                rows = cmd.ExecuteNonQuery();
                conn.Close();
            }
            catch (Exception e)
            {
                return "Sorry, we are having problems\n" + e;

            }

            finally 
            {
                conn.Close();
            }
          if (rows == 0)
          {
              return "The transaction was unsuccessful, please try again.";
              
          }
         

           
            return "Your transaction was successful.";
        }//end getDeposit

        public string getMessages(string text, double numbers) 
        {
            return "";
        }//end getMessage

    }
}
