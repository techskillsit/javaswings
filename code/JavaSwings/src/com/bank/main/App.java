
package com.bank.main;

import com.bank.screens.Login;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        Runnable r  = new Runnable(){

            @Override
            public void run() {
                Login login = new Login();
                login.setVisible(true);
            }
            
        };
        SwingUtilities.invokeLater(r);
    }
}
