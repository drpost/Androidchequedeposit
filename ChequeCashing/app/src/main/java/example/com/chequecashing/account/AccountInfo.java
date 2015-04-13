package example.com.chequecashing.account;

import java.util.ArrayList;




//@XmlRootElement(name = "AccountInfo")

//This Class will put properties into a format that is readable for Javaxb Xml parser
public class AccountInfo {
    //@XmlElement
    private ArrayList<String> Account_Type;
    //@XmlElement
    private String Password;
    //@XmlElement
    private String first_Name;
    //@XmlElement
    private String Last_Name;
    //@XmlElement
    private String Card_Number;
    //@XmlElement
    private ArrayList<String> Account_Description;

    public AccountInfo ()
    {

        Account_Type = new ArrayList<String>();
        Password = "";
        first_Name = "";
        Last_Name = "";
        Card_Number = "";
        Account_Description = new ArrayList<String>();
    }

    public void setAccountType(String accountType)
    {

        this.Account_Type.add(accountType);
    }

    public ArrayList<String> getAccountType()
    {
        return Account_Type;
    }

    public void setPassword(String password)
    {

        this.Password = password;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setFirstName(String firstName)
    {

        this.first_Name = firstName;
    }
    public String getFirstName()
    {
        return first_Name;
    }

    public void setLastname(String lastName)
    {

        this.Last_Name = lastName;
    }

    public String getLastName()
    {
        return Last_Name;
    }

    public void setCardNo(String cardNo)
    {

        this.Card_Number = cardNo;
    }

    public String getCardNo()
    {
        return Card_Number;
    }

    public void setAccountDescrip(String accountDescrip)
    {

        Account_Description.add(accountDescrip);
    }

    public ArrayList<String> getAccountDescrip()
    {
        return Account_Description;
    }
}