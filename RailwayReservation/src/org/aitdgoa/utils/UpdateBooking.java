package org.aitdgoa.utils;

/**
 *
 * @author Vanzio Da Cunha
 * created: 25/11/2022 9:32 AM
 * last modified:
 */

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

public class UpdateBooking extends Frame implements ActionListener
{
    Label Label1;
    Choice pid;
    Label Label2;
    Choice tno;
    Label Label3;
    TextField time;
    Label Label4;
    TextField tclass;
    Label Label5;
    TextField price;
    Button Button1;
    Button Home;
    
    UpdateBooking()
    {   addWindowListener(new WindowAdapter() 
        {    @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
    
        Label1 = new Label();
        pid = new Choice();
        Label2 = new Label();
        tno = new Choice();
        Label3 = new Label();
        time = new TextField(40);
        Label4 = new Label();
        tclass = new TextField(40);
        Label5 = new Label();
        price = new TextField(40);
        Home = new Button("HOME");
        Button1 = new Button("UPDATE RECORD");
        
        initCombo1();
        initCombo2();
        
        Label1.setBounds(50, 300, 170, 40);
        pid.setBounds(50, 350, 50, 50);
        Label2.setBounds(50, 400, 170, 40);
        tno.setBounds(50, 450, 50, 40);
        Label3.setBounds(50, 500, 170, 40);
        time.setBounds(50, 550, 400, 40);
        Label4.setBounds(50, 600, 170, 40);
        tclass.setBounds(50, 650, 200, 40);
        Label5.setBounds(50, 700, 170, 40);
        price.setBounds(50, 750, 200, 40);
        Button1.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);

        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        Label1.setText("PASSENGER ID: ");
        Label2.setText("TRAIN NUMBER: ");
        Label3.setText("BOOKING TIME : ");
        Label4.setText("CLASS : ");
        Label5.setText("PRICE : ");
        
        Label1.setFont(myFont);
        Label2.setFont(myFont);
        Label3.setFont(myFont);
        Label4.setFont(myFont);
        Label5.setFont(myFont);
        tno.setFont(myFont);
        pid.setFont(myFont);
        tclass.setFont(myFont);
        time.setFont(myFont);
        price.setFont(myFont);
        Button1.setFont(myFont);
        Home.setFont(myFont);
        
        add(Label1);add(pid);add(Label2);add(tno);
        add(Label3);add(time); add(Label4); add(tclass);
        add(Label5); add(price);
        add(Button1);
        add(Home);
        add(new MyCanvas());
        
        Button1.addActionListener(this);
        Home.addActionListener(this);
    }
    
    private void initCombo1()
    {   try
        {   Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="select PASSENGER_ID from passenger";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);
            while(rs.next())
            {  Integer r=rs.getInt("PASSENGER_ID");
                pid.addItem(r.toString());
            } 
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e1){}
    }
    
    private void initCombo2()
    {   try
        {   Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="select TRAIN_NO from train";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);//used with select command to read data from the table
            while(rs.next())
            {  Integer r=rs.getInt("TRAIN_NO");
                tno.addItem(r.toString());
            } 
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e1){}
    }
   
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {   String s=e.getActionCommand();
        if(s.equals("UPDATE RECORD"))
        {   try
            {   
                Statement stmt;
                int pid1 = Integer.parseInt(pid.getSelectedItem());
                int tno1 = Integer.parseInt(tno.getSelectedItem());
                String btime = time.getText().toUpperCase();
                String trcl = tclass.getText().toUpperCase();
                int pcn = Integer.parseInt(price.getText());
            
                Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
                System.out.println("updating record...");
                stmt = conn.createStatement();
            
                String sql = "UPDATE BOOKING set BOARDING_TIME='"+btime+"' ,CLASS='"+trcl+"' ,PRICE="+pcn+" "
                        + " where PASSENGER_ID = "+pid1+" &&  TRAIN_NO = "+tno1;
            
                System.out.println(sql);
                stmt.executeUpdate(sql);
                System.out.println("data record update to table successfully");
                
                String s1="select * from booking";
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
