
package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class MiniStatement extends JFrame implements ActionListener{
    
    JButton b1;
    JLabel mini,bank,card,balance;
    MiniStatement(String pinnumber){
        super("Mini Statement");
        getContentPane().setBackground(Color.WHITE);
        setSize(400,600);
        setLocation(20,20);
        
        mini = new JLabel();
        mini.setBounds(20, 140, 400, 200);
        add(mini);
        
         bank = new JLabel("Egyptian Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);
        
         card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);
        
        balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);
        
        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from Login where pin = '"+pinnumber+"'");
            while(rs.next()){
                card.setText("Card Number:    " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardno").substring(12));
            }
        }catch(Exception e){}
        	 
        try{
            int bal = 0;
            Conn c1  = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '"+pinnumber+"'");
            while(rs.next()){
                mini.setText(mini.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                    bal += Integer.parseInt(rs.getString("amount"));
                }else{
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your total Balance is Rs "+bal);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        setLayout(null);
        
        b1 = new JButton("Exit");
        b1.setBounds(250, 500, 100, 25);
        b1.addActionListener(this);
        add(b1);
        
     
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args){
        new MiniStatement("").setVisible(true);
    }
    
}
