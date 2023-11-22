
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
public class PassengerMain extends Frame implements ActionListener{
    
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Home;
    
    PassengerMain()
    {
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
        
        Font myFont=new Font("Tahoma", 1, 26); // NOI18N
        setLayout(null);
        Button1 = new Button("ADD NEW PASSENGERS");
        Button2 = new Button("REMOVE PASSENGER DETAILS");
        Button3 = new Button("UPDATE PASSENGER DETAILS");
        Button4 = new Button("PASSENGER DETAILS");
        Button5 = new Button("TOTAL PRICE");
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
        if(g.equals("ADD NEW PASSENGERS"))
        {   
            InsertPassenger i=new InsertPassenger();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("REMOVE PASSENGER DETAILS"))
        {   
            DeletePassenger i=new DeletePassenger();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("UPDATE PASSENGER DETAILS"))
        {   
            UpdatePassenger i=new UpdatePassenger();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("PASSENGER DETAILS"))
        {
            try
            {   Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
                String s="select * from passenger";
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
                j1.setLocation(0, 300);
                j1.setVisible(true);
                conn.close();
            }
            catch(ClassNotFoundException | SQLException y){}
        }
        if(g.equals("TOTAL PRICE"))
        {
            try
            {   Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
                String s="select PASSENGER_ID, sum(price) from booking group by PASSENGER_ID;";
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