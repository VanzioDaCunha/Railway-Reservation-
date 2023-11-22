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

public class InsertStation extends Frame implements ActionListener
{
    Label Label2;
    TextField station_name;
    Label Label3;
    TextField location;
    Label Label4;
    TextField state;
    Button Button1;
    Button Home;
   
    InsertStation()
    {   addWindowListener(new WindowAdapter()
        {   @Override
            public void windowClosing(WindowEvent we)
            {   System.exit(0); }
        });
        
    
        Label2 = new Label();
        station_name = new TextField(40);
        Label3 = new Label();
        location = new TextField(40);
        Label4 = new Label();
        state = new TextField(40);
        Button1 = new Button("ADD RECORD");
        Home = new Button("HOME");
       

        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        Label2.setText("Station Name: ");
        Label3.setText("Location: ");
        Label4.setText("State: ");
        
        Label2.setBounds(50, 300, 170, 40);
        station_name.setBounds(50, 350, 400, 40);
        Label3.setBounds(50, 400, 170, 40);
        location.setBounds(50, 450, 400, 40);
        Label4.setBounds(50, 500, 170, 40);
        state.setBounds(50, 550, 200, 40);
        Button1.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);
        
        Label2.setFont(myFont);
        Label3.setFont(myFont);
        Label4.setFont(myFont);
        station_name.setFont(myFont);
        location.setFont(myFont);
        state.setFont(myFont);
        Button1.setFont(myFont);
        Home.setFont(myFont);
        
        add(Label2);add(station_name);
        add(Label3);add(location); add(Label4); add(state);
        add(Button1);
        add(Home);
        add(new MyCanvas());
        
        Button1.addActionListener(this);
        Home.addActionListener(this);
    }
   
    @Override
    public void actionPerformed(ActionEvent e)
    {   String s=e.getActionCommand();
        if(s.equals("ADD RECORD"))
        {   try
            {Statement stmt = null;
            String sname = station_name.getText().toUpperCase();
            String sloc = location.getText().toUpperCase();
            String sstate = state.getText().toUpperCase();
           
            Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
            stmt = conn.createStatement();
           
            String sql = "INSERT INTO STATION "
               + "(STATION_NAME,LOCATION,STATE)"
            + " VALUES ('" +sname+"','"+sloc+"','"+sstate+"')";
            stmt.executeUpdate(sql);
            System.out.println(sql);
            String s1 = "select * from station";
            ResultSet rs=stmt.executeQuery(s1);
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
          catch(ClassNotFoundException | SQLException e1){}
        }
        
        if(s.equals("HOME"))
        {   
            MainProjectClass i=new MainProjectClass();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
    }
   
}