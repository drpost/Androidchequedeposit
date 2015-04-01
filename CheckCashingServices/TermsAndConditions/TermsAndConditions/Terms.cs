/**************************************************************************************
 *  File Name: TermsAndConditions Class                                               *
 * Created by: Kevin Haynes                                                           *
 *  Student #: 820076743                                                              *
 *     Course: COMP212-061                                                            *
 *       date: March 1, 2015                                                          *
 *                                  TEAMPOST PROJECT                                  *                
 **************************************************************************************/       


using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace TermsAndConditions
{
    public class Terms
    {
        string TermsAndConditions { get; set; }
        bool Acceptance { get; set; }
        private string aFile = @"C:\Users\Public\Centennial College\Winter 2015\" +
                    @"COMP231\CheckCashingService\TermsAndConditions\TermsAndConditions\bin\Debug\TermsAndConditions.txt";
        private string error = "Sorry, we are expericening problems.";
        public Terms() 
        {
            TermsAndConditions = "";
            Acceptance = true;
        }

       //gets user reponse for terms and agreements and returns document if true.

        public string getTermsAndAgreements()
        {
            try
            {
                TermsAndConditions = System.IO.File.ReadAllText(aFile);//reads file into string variable
                return TermsAndConditions;
            }//end try block

            catch (IOException e) 
            {
                string errorLog = e.ToString();
                return error + "\n" + "Error: " + errorLog;
            }// end catch block
        }
    }
}
