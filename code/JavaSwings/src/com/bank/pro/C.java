
package com.bank.pro;

import com.bank.exceptions.InsufficientFundsException;
import com.bank.main.Customer;
import com.bank.model.DBConfig;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C {
private DBConfig db;
    
    public C(){
        db = new DBConfig();
    }
    public void checkAmount(String amount, String balance) throws InsufficientFundsException {
            if(Double.parseDouble(amount) > Double.parseDouble(balance)){
                throw new InsufficientFundsException();
            }
    }

    public void saveTransaction(Customer c, String amount) throws ClassNotFoundException, SQLException {
        db.saveWithdrawalTransaction(c,amount);
    }

    public void updatebalance(double updatedBalance, String accountNumber) throws SQLException, ClassNotFoundException {
        db.updatebalance(updatedBalance,accountNumber);
    }
    
    public ResultSet fetchTransactions(String accountNumber) throws SQLException, ClassNotFoundException{
        return db.fetchTransactions(accountNumber);
    }
}
