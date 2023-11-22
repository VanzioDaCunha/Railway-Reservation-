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
public class BookingMain extends Frame implements ActionListener{
    
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Home;
    
    BookingMain()
    {
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
        
        Font myFont=new Font("Tahoma", 1, 26); // NOI18N
        setLayout(null);
        Button1 = new Button("NEW BOOKING");
        Button2 = new Button("REFUND BOOKING");
        Button3 = new Button("UPDATE BOOKING");
        Button4 = new Button("BOOKING DETAILS");
        Button5 = new Button("FULL BOOKING INFO");
        Home = new Button("HOME");
        
        Button1.setBounds(450, 400, 450, 70);
        Button2.setBounds(450, 500, 450, 70);
        Button3.setBounds(450, 600, 450, 70);
        Button4.setBounds(450, 700, 450, 70);
        Button5.setBounds(450, 800, 450, 70);
        Home.setBounds(1150, 100, 100, 50);
        
        Button1.setFont(myFont);
        Button2.setFont(myFont);
        Button3.setFont(myFont);
        Button4.setFont(myFont);
        Button5.setFont(myFont);
        Home.setFont(myFont);
        
        add(Button1);
        add(Button2);
        add(Button3);
        add(Button4);
        add(Button5);
        add(Home);
        add(new MyCanvas());
        
        Button1.addActionListener(this);
        Button2.addActionListener(this);
        Button3.addActionListener(this);
        Button4.addActionListener(this);
        Button5.addActionListener(this);
        Home.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g=e.getActionCommand();
        if(g.equals("FULL BOOKING INFO"))
        {   
           try
            {   Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
                String s="select * from passenger natural join booking natural join train";
                Statement stmt = conn.createStatement();
                ResultSet rs=stmt.executeQuery(s);
                JTable jTable1=new JTable();
                Font myFont=new Font("Tahoma", 1, 15);
                jTable1.setFont(myFont);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                JFrame j1=new JFrame();
                JScrollPane pg = new JScrollPane(jTable1);
                pg.setFont(myFont);
                j1.add(pg);
                j1.setSize(1400, 1000);
                j1.setVisible(true);
                conn.close();
            }
        catch(ClassNotFoundException | SQLException y){}
        }
        if(g.equals("NEW BOOKING"))
        {   
            InsertBooking i=new InsertBooking();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("REFUND BOOKING"))
        {   
            DeleteBooking i=new DeleteBooking();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("UPDATE BOOKING"))
        {   
            UpdateBooking i=new UpdateBooking();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("BOOKING DETAILS"))
        {   
            try
            {   Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
                String s="select * from booking";
                Statement stmt = conn.createStatement();
                ResultSet rs=stmt.executeQuery(s);
                JTable jTable1=new JTable();
                Font myFont=new Font("Tahoma", 1, 15);
                jTable1.setFont(myFont);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                JFrame j1=new JFrame();
                JScrollPane pg = new JScrollPane(jTable1);
                pg.setFont(myFont);
                j1.add(pg);
                j1.setSize(1400, 1000);
                j1.setVisible(true);
                conn.close();
            }
            catch(ClassNotFoundException | SQLException y){}
        }
        if(g.equals("HOME"))
        {   
            MainProjectClass i=new MainProjectClass();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
    }
}
