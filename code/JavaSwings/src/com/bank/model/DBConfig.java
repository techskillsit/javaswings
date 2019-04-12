
package com.bank.model;

import com.bank.main.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DBConfig {
    //step 1: info 
    String userDB="root";
    String passwordDB="";
    String url="jdbc:mysql://localhost:3306/MySwingDB";
    String driver="com.mysql.jdbc.Driver";
    private ResultSet rst1;
    private Connection con;
    //step 2: load the driver and make the connection
    private void dbConnect() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
       con= DriverManager.getConnection(url, userDB, passwordDB);
    }
    
    private void dbClose() throws SQLException{
        con.close();
    }
    
    public void createAccount(Customer c) throws SQLException, ClassNotFoundException{
        dbConnect();
        String sql="insert into customer(name,address,email,accountNumber,balance)"
                + "values (?,?,?,?,?) ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, c.getName());
        pstmt.setString(2, c.getAddress());
        pstmt.setString(3, c.getEmail());
        pstmt.setString(4, c.getAccountNumber());
        pstmt.setString(5, c.getBalance());
        pstmt.executeUpdate();
        
        dbClose();        
    }

    public boolean checkAccountNumber(String accountNumber) throws ClassNotFoundException, SQLException {
        dbConnect();
        int count = 0;
        String sql="select * from customer where accountNumber=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, accountNumber);
        ResultSet rst = pstmt.executeQuery();
        while(rst.next()){
            count=1;
        }
        
        dbClose();
        if(count ==1){
            return true;
        }
        
        return false;
    }

    public Customer fetchCustomerDetails(String accountNumber) throws ClassNotFoundException, SQLException {
            dbConnect();
            Customer c = new Customer();
            String sql="select * from customer where accountNumber=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                c.setName(rst.getString(2));
                c.setAddress(rst.getString(3));
                c.setEmail(rst.getString(4));
                c.setAccountNumber(rst.getString(5));
                c.setBalance(rst.getString(6));
            }
            dbClose();
            return c;
    }

    public void saveTransaction(Customer c, String amount) throws ClassNotFoundException, SQLException {
        dbConnect();
        String sql="insert into transactions(accountNumber,amount,initialBalance,finalBalance,dateOfTransaction,operation)"
                + "values (?,?,?,?,?,?) ";
        double amt = Double.parseDouble(amount);
        double iniBal =Double.parseDouble(c.getBalance());
        double finalBal = amt + iniBal;
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, c.getAccountNumber());
        pstmt.setString(2, amount);
        pstmt.setString(3, c.getBalance());
        pstmt.setString(4, Double.toString(finalBal));
        pstmt.setString(5, LocalDate.now().toString());
        pstmt.setString(6, "deposit");
        pstmt.executeUpdate();
        
        dbClose();   
    }

    public void updatebalance(double updatedBalance, String accountNumber) throws SQLException, ClassNotFoundException {
        dbConnect();
        String sql="update customer SET balance = ? where accountNumber=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, Double.toString(updatedBalance));
        pstmt.setString(2, accountNumber);
        
        
        pstmt.executeUpdate();
        
        dbClose();    
    }

    public void saveWithdrawalTransaction(Customer c, String amount) throws ClassNotFoundException, SQLException {
        dbConnect();
        String sql="insert into transactions(accountNumber,amount,initialBalance,finalBalance,dateOfTransaction,operation)"
                + "values (?,?,?,?,?,?) ";
        double amt = Double.parseDouble(amount);
        double iniBal =Double.parseDouble(c.getBalance());
        double finalBal = iniBal-amt;
        
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, c.getAccountNumber());
        pstmt.setString(2, amount);
        pstmt.setString(3, c.getBalance());
        pstmt.setString(4, Double.toString(finalBal));
        pstmt.setString(5, LocalDate.now().toString());
        pstmt.setString(6, "withdraw");
        pstmt.executeUpdate();
        
        dbClose();   
    }

    public ResultSet fetchTransactions(String accountNumber) throws SQLException, ClassNotFoundException{
            dbConnect();
            Customer c = new Customer();
            String sql="select * from transactions where accountNumber=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            ResultSet rst = pstmt.executeQuery();
            
            //dbClose();
            return rst;
    }
    
}
