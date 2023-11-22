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

public class StationMain extends Frame implements ActionListener{
    
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Home;
    
    StationMain()
    {
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
        
        Font myFont=new Font("Tahoma", 1, 26); // NOI18N
        setLayout(null);
        Button1 = new Button("ADD NEW STATION");
        Button2 = new Button("REMOVE STATION DETAILS");
        Button3 = new Button("UPDATE STATION DETAILS");
        Button4 = new Button("STATION DETAILS");
        Button5 = new Button("TRAVEL");
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
        if(g.equals("ADD NEW STATION"))
        {   
            InsertStation i=new InsertStation();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("REMOVE STATION DETAILS"))
        {   
            DeleteStation i=new DeleteStation();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("UPDATE STATION DETAILS"))
        {   
            UpdateStation i=new UpdateStation();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("STATION DETAILS"))
        {   
            try
            {   Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
                String s="select * from station";
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
        if(g.equals("TRAVEL"))
        {   
            try
            {   Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
                String s="select * from TRAVEL";
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
