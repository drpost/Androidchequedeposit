/*************************************************************************
 *                                                                       *
 *             File: WCF Contracts                                       *
 *     Student Name: Kevin Haynes                                        *
 *   Student Number: 820076743                                           *
 *           Course: COMP231                                             *
 *                                                                       *
 * ***********************************************************************/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace CheckCashingService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface ICheckCashingService
    {

        [OperationContract]
        //Syntax to accept Java code goes here
        string getLogInfo(string account, string password);

        [OperationContract]
        //Syntax to accept java code goes here
        string getTermsAndConditions(bool value);


        // TODO: Add your service operations here
    }


    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    [DataContract]
    public class CheckData
    {


        [DataMember]
        public string Account { get; set; }

        [DataMember]
        public string Password { get; set; }

        [DataMember]
        public bool Acceptance { get; set; }
    }
}
