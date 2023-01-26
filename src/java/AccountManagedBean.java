/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


/**
 *
 * @author hp
 */

@Dependent
@ManagedBean
public class AccountManagedBean {
 private String accountNumber;
    private String accountPin;
    private String date;
    private String gender;
    private String address;
    private String fname;
    private String mname;
    private String lname;
    private String nationality;
    private String mobile;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountPin(String accountPin) {
        this.accountPin = accountPin;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountPin() {
        return accountPin;
    }

    public String getDate() {
        return date;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getNationality() {
        return nationality;
    }

    public String getMobile() {
        return mobile;
    }
    
    
    
    
   
    public AccountManagedBean() {
    }
    
    
    public void AccountInsert() throws ClassNotFoundException, SQLException {
         
            DBConnection dbcon = new DBConnection();
            Connection con = dbcon.connMethod();
            String sqr = "Insert into ACCOUNTTA(accountNumber,accountPin,day,gender,address,fname,mname,lname,nationality,mobile) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sqr);
            ps.setString(1, accountNumber);
            ps.setString(2, accountPin);
             ps.setString(3, date);
              ps.setString(4, gender);
               ps.setString(5, address);
                ps.setString(6, fname);
                 ps.setString(7, mname);
                  ps.setString(8, lname);
                   ps.setString(9, nationality);
                    ps.setString(10, mobile);
            
            ps.executeUpdate();
        
     }
    
   public static String delete(int accountNumber) throws ClassNotFoundException{
        System.out.println("deleted " + accountNumber);
        try {
             DBConnection db=new DBConnection();
            try (Connection connection = db.connMethod()) {
                PreparedStatement stmt=connection.prepareStatement("delete from ACCOUNTTA where ACCOUNTNUMBER = " + accountNumber);
                stmt.executeUpdate();
            }
        } catch(SQLException sqlException){
        }
        return "/adminpage.xhtml?faces-redirect=true";
   }
   
   public String validateAccount() throws SQLException, ClassNotFoundException {
        boolean valid =  accountValidate.validate(accountNumber, accountPin);
        if (valid) {
              DBConnection dbcon = new DBConnection();
            Connection con = dbcon.connMethod();
            PreparedStatement ps = con.prepareStatement("select ACCOUNTNUMBER and ACCOUNTPIN from ACCOUNTTA where ACCOUNTNUMBER=? and ACCOUNTPIN=?");
            ps.setString(1, accountNumber);  
             ps.setString(2, accountPin); 
            ResultSet rs = ps.executeQuery();
            rs.next();
           
           // String accountNumber =rs.getString(1);
            //String accountPin =rs.getString(2);
           
            if ("ACCOUNTNUMBER".equals(accountNumber) && "ACCOUNTPIN".equals(accountPin)){
                return "log";
            } else {
                return "index";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect AccountNumber and AccountPin",
                            "Please enter correct AccountNumber and AccountPin"));
            return "profile";
        }
    
}
   
}
