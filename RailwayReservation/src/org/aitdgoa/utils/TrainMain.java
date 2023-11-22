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

public class TrainMain extends Frame implements ActionListener{
    
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Button6;
    Button Home;
    
    TrainMain()
    {
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
        
        Font myFont=new Font("Tahoma", 1, 26); // NOI18N
        setLayout(null);
        Button1 = new Button("ADD NEW TRAIN");
        Button2 = new Button("REMOVE TRAIN DETAILS");
        Button3 = new Button("UPDATE TRAIN DETAILS");
        Button4 = new Button("TRAIN DETAILS");
        Button5 = new Button("STATIONS");
        Button6 = new Button("SREARCH O");
        Home = new Button("HOME");
        
        Button1.setBounds(450, 350, 450, 70);
        Button2.setBounds(450, 450, 450, 70);
        Button3.setBounds(450, 550, 450, 70);
        Button4.setBounds(450, 650, 450, 70);
        Button5.setBounds(450, 750, 450, 70);
        Button6.setBounds(450, 850, 450, 70);
        Home.setBounds(1150, 100, 100, 50);
        
        Button1.setFont(myFont);
        Button2.setFont(myFont);
        Button3.setFont(myFont);
        Button4.setFont(myFont);
        Button5.setFont(myFont);
        Button6.setFont(myFont);
        Home.setFont(myFont);
        
        add(Button1);
        add(Button2);
        add(Button3);
        add(Button4);
        add(Button5);
        add(Button6);
        add(Home);
        add(new MyCanvas());
        
        Button1.addActionListener(this);
        Button2.addActionListener(this);
        Button3.addActionListener(this);
        Button4.addActionListener(this);
        Button5.addActionListener(this);
        Button6.addActionListener(this);
        Home.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String g=e.getActionCommand();
        if(g.equals("ADD NEW TRAIN"))
        {   
            InsertTrain i=new InsertTrain();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("REMOVE TRAIN DETAILS"))
        {   
            DeleteTrain i=new DeleteTrain();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("UPDATE TRAIN DETAILS"))
        {   
            UpdateTrain i=new UpdateTrain();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("STATIONS"))
        {   
            StationMain i=new StationMain();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("TRAIN DETAILS"))
        {   
            try
            {   Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
                String s="select * from TRAIN ";
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
        if(g.equals("SREARCH O"))
        {   
            try
            {   Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
                String s="select * from TRAIN where SOURCE in(select STATION_ID from STATION where " +
                        "STATION_NAME like '%O%')";
                Statement stmt = conn.createStatement();
                System.out.println(s);
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
