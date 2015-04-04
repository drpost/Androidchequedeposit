package commMiddle;

public class XmlParserDemo {

	public static void main(String[] args) {
		AccountInfo accountInfo;
		//This is the a replica of the xml string that will be coming from the middleware
		String answer = "<?xml version=\"1.0\"?><AccountInfo><Account_Type>1</Account_Type>"
				+ "<Password>Humber</Password><first_Name>Kevin</first_Name>"
				+ "<Last_Name>Haynes</Last_Name><Card_Number>824682157</Card_Number>"
				+ "<Account_Description>regular account</Account_Description>"
				+ "<Account_Type>5</Account_Type><Password>Humber</Password>"
				+ "<first_Name>Kevin</first_Name><Last_Name>Haynes</Last_Name>"
				+ "<Card_Number>824682157</Card_Number>"
				+ "<Account_Description>business</Account_Description></AccountInfo>";
		
		XmlParser xml = new XmlParser();
		
	
		try {
			//This method changes the xml formatted string into javaxb format
				accountInfo = xml.getAccountInfo(answer);
				
				//displays javaxb contents
				System.out.println("Welcome :" + accountInfo.getFirstName() + " " + accountInfo.getLastName());
				System.out.println("Here is your account information");
				System.out.print("Here is your account types:");
				for (String info : accountInfo.getAccountType())
				{
				System.out.print(" " + info + " ");
				}//end for loop
				System.out.println("\nHere is your password: " + accountInfo.getpassword());
				System.out.println("Here is your cardNumber: " + accountInfo.getCardNo());
				System.out.println("Here are your account Descriptions:");
				for(String info : accountInfo.getArccountDescrip())
				{
					System.out.println(" " + info + " ");
				}//end for loop
			} // end try block
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}// end catch block
		

	}//end main method

}//end XmlParserDemo class
