using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;
using System.Xml;
using System.IO;



namespace LogIn
{
    public class LogInVersion3
    {
       private string AccXml;
       public LogInVersion3() 
       {
           AccXml = "";
       }
        public string getLogIn(string password, string cardNo)
        {
            // Dictionary<int, string> info = new Dictionary<int, string>();
            //int count = 0;
            XmlDocument doc = new XmlDocument();
            string comm = @"Data Source=JEHOVAH-NISSI;Initial Catalog=Yeowan_Ban;Integrated Security=True";
            string query = @"SELECT * FROM LogIn WHERE Password =" + "\'" + password + "\'" + "AND Card_Number = " + "\'" + cardNo + "\'";
            SqlConnection conn = new SqlConnection(comm);
            SqlDataAdapter da = new SqlDataAdapter(query, conn);
            DataSet ds = new DataSet("XMLAccounts");
            da.Fill(ds, "LogIn");


            MemoryStream memStrm = new MemoryStream();
            StreamReader strmRead = new StreamReader(memStrm);
            StreamWriter strmWrite = new StreamWriter(memStrm);

            ds.WriteXml(strmWrite, XmlWriteMode.IgnoreSchema);
            memStrm.Seek(0, SeekOrigin.Begin);
            doc.Load(strmRead);
            

            XmlNodeList nodeLst = doc.SelectNodes("//XMLAccounts/LogIn");

            foreach (XmlNode node in nodeLst)
            {
                if (node.InnerXml == null) 
                {
                    return "Invalid Creditals";
                }
                    AccXml += node.InnerXml + "\r\n";
            }
            conn.Close();
            return AccXml;
        }
    }
}
