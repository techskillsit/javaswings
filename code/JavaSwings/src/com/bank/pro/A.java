/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.pro;

import com.bank.exceptions.InvalidCredentialsException;
import com.bank.main.Customer;
import com.bank.model.DBConfig;
import java.sql.SQLException;

/**
 *
 * @author imtiyazhirani
 */
public class A {

    private DBConfig db;

    public A() {
        this.db = new DBConfig();
    }
    
    
    public boolean processLogin(String username, String password) 
            throws InvalidCredentialsException {
        if(username.equals("admin")){
            if(password.equals("1234")){
                return true;
            }
        }
        throw new InvalidCredentialsException();
    }

    public void createAccount(Customer c) throws SQLException, ClassNotFoundException {
           //Access DBConfig and pass c to createAccount(Customer c)
            db.createAccount(c);
        
    }
    
}
