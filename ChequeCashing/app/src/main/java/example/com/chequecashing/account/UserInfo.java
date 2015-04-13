package example.com.chequecashing.account;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Pedro on 4/4/2015.
 */
public class UserInfo implements Serializable{

    private String accessCard;
    private String firstName;
    private String lastName;
    private String password;
    private Date acceptDate;
    private List<Account> accountList;

    public String getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(String accessCard) {
        this.accessCard = accessCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public UserInfo() {

    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (acceptDate != null ? !acceptDate.equals(userInfo.acceptDate) : userInfo.acceptDate != null)
            return false;
        if (accessCard != null ? !accessCard.equals(userInfo.accessCard) : userInfo.accessCard != null)
            return false;
        if (accountList != null ? !accountList.equals(userInfo.accountList) : userInfo.accountList != null)
            return false;
        if (firstName != null ? !firstName.equals(userInfo.firstName) : userInfo.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(userInfo.lastName) : userInfo.lastName != null)
            return false;
        if (password != null ? !password.equals(userInfo.password) : userInfo.password != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accessCard != null ? accessCard.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (acceptDate != null ? acceptDate.hashCode() : 0);
        result = 31 * result + (accountList != null ? accountList.hashCode() : 0);
        return result;
    }
}
