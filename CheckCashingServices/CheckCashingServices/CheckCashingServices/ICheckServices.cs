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

namespace CheckCashingServices
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface ICheckServices
    {

        [OperationContract]
        // [WebGet(UriTemplate = "/{account}/{password}", ResponseFormat = WebMessageFormat.Json)]
        string getLogInfo(string cardNo, string password);

        [OperationContract]
        //[WebInvoke(UriTemplate = Method = "POST")]
        string showTerms();


        [OperationContract]
        //[WebInvoke(UriTemplate =  Method = "POST")]
        string getTermsAndConditions(bool value);


        [OperationContract]
        //[WebInvoke(UriTemplate = "/{cardNo}/{account}/{accountType}/{newBalance}", Method = "POST")]
        string getDeposit(string cardNo, string account, string accountType, double amount, string pic1, string pic2);

        //[WebInvoke(UriTemplate = "/{string}/{numbers}", Method = "POST")]
        [OperationContract]
        string getMessages(string text, double numbers);
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
        public bool Value { get; set; }
    }
}
