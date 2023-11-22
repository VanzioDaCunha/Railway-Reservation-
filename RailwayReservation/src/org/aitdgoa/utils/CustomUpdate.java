/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.aitdgoa.utils;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author User
 */
public class CustomUpdate extends Frame implements ActionListener
{
    Button jButton1;
    Label jLable1;
    TextField command;
    
    CustomUpdate()
    {   addWindowListener(new WindowAdapter() 
        {   public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
    
        jLable1 = new Label();
        command = new TextField(20);
        jButton1 = new Button("Execute");
        

        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        jLable1.setText("Command: ");
        jLable1.setFont(myFont);
        add(jLable1);
        add(command);
        add(jButton1);
        jButton1.addActionListener(this);
        setLayout(new FlowLayout());
    }
   
    public void actionPerformed(ActionEvent e) 
    {   String s=e.getActionCommand();
        if(s.equals("Execute"))
        {   try
            {
                Statement stmt = null;
                
                
            Class.forName(MainProjectClass.driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
            System.out.println("modifying record...");
            stmt = conn.createStatement();
            String sql = command.getText();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            
            System.out.println("data record added to table successfully");
            conn.close();
           }
          catch(ClassNotFoundException | SQLException e1){}
        }
    }
}
