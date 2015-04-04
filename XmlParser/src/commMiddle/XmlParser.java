package commMiddle;


import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XmlParser {

	//This will take string in xml format from middleware and extract values into objects 
public AccountInfo getAccountInfo(String xml) throws Exception
	{
	    //Changes string into xml readable format
		StringReader document = new StringReader(xml);

		//Parses xml document and returns accountInfo object which can be but into indivdual varables.
		JAXBContext context = JAXBContext.newInstance(AccountInfo.class);
		Unmarshaller acc = context.createUnmarshaller();
		AccountInfo accountInfo2 = (AccountInfo) acc.unmarshal(document);
		
		return accountInfo2; 
		      
	}//end getAccountInfo method
}// end XmlParser class
