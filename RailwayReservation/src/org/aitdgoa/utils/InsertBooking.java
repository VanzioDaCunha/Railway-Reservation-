package org.aitdgoa.utils;

/**
 *
 * @author Student
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


public class InsertBooking extends Frame implements
        ActionListener
{
    Label Label1;
    Label Label2;
    Label Label3;
    TextField tclass;
    Label Label4;
    TextField boarding_time;
    Label Label5;
    TextField price;
    Choice passenger_id,train_no;
    Button Button1;
    Button Home;
   
    InsertBooking()
    {   
        addWindowListener(new WindowAdapter()
        {   public void windowClosing(WindowEvent we)
            {   System.exit(0); }
        });
        Label1 = new Label();
        Label2 = new Label();
        Label3 = new Label();
        tclass = new TextField(20);
        Label4 = new Label();
        boarding_time = new TextField(20);
        Label5 = new Label();
        price = new TextField(40);
        Button1 = new Button("insert record");
        passenger_id = new Choice();
        train_no = new Choice();
       

        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        
        
        Home = new Button("HOME");
        Home.setFont(myFont);
        Button1.setFont(myFont);
        
        Label1.setText("Train No: ");
        Label2.setText("Passenger Id: ");
        Label3.setText("Class: ");
        Label4.setText("Boarding Time: ");
        Label5.setText("Price: ");
        Label1.setFont(myFont);Label2.setFont(myFont);
        Label3.setFont(myFont);Label4.setFont(myFont);
        Label5.setFont(myFont);
        train_no.setFont(myFont);
        passenger_id.setFont(myFont);
        tclass.setFont(myFont);
        boarding_time.setFont(myFont);
        price.setFont(myFont);
        
        Label1.setBounds(50, 300, 170, 40);
        train_no.setBounds(50, 350, 200, 40);
        Label2.setBounds(50, 400, 170, 40);
        passenger_id.setBounds(50, 450, 200, 40);
        Label3.setBounds(50, 500, 170, 40);
        tclass.setBounds(50, 550, 400, 40);
        Label4.setBounds(50, 600, 170, 40);
        boarding_time.setBounds(50, 650, 400, 40);
        Label5.setBounds(50, 700, 170, 40);
        price.setBounds(50, 750, 200, 40);
        Button1.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);
       
        add(Label1);add(train_no);add(Label2);add(passenger_id);
        add(Label3);add(tclass); add(Label4);add(boarding_time);
        add(Label5); add(price);
        add(Button1);
        add(Home);
        add(new MyCanvas());
 
        Button1.addActionListener(this);
        Home.addActionListener(this);
       
        initCombo();
        initCombo2();
       
    }
   
     void initCombo()
    {   try
        {   Class.forName(MainProjectClass.driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection
                (MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="Select PASSENGER_ID from Passenger ";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);//used with select command to read data from the table
            while(rs.next())
            {   Integer r=rs.getInt("PASSENGER_ID");
                passenger_id.addItem(r.toString());
            }
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e){}
    }
     
      void initCombo2()
    {   try
        {   Class.forName(MainProjectClass.driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection
                (MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="Select TRAIN_NO from train ";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);//used with select command to read data from the table
            while(rs.next())
            {   Integer r=rs.getInt("TRAIN_NO");
                train_no.addItem(r.toString());
            }
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e){}
    }
   
    @Override
    public void actionPerformed(ActionEvent e)
    {   String s=e.getActionCommand();
        if(s.equals("insert record"))
        {   try
            {Statement stmt = null;
            int pi = Integer.parseInt(passenger_id.getSelectedItem());
            int tn = Integer.parseInt(train_no.getSelectedItem());
            String cl = tclass.getText().toUpperCase();
            String bt = boarding_time.getText().toUpperCase();
            int pr = Integer.parseInt(price.getText());
           
            Class.forName(MainProjectClass.driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
            System.out.println("inserting record...");
            stmt = conn.createStatement();//insert into empdata values(123,'tina','hr',23000)
           
            String sql = "INSERT INTO BOOKING "
               + "(PASSENGER_ID,TRAIN_NO,CLASS,BOARDING_TIME,PRICE)"
      + " VALUES ("+pi+","+tn+",'"+cl+"','"+bt+"',"+pr+")";
            stmt.executeUpdate(sql);
            System.out.println(sql);
           
            System.out.println("data record added to table successfully");
            
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
            System.out.println("hellow world");
            MainProjectClass i=new MainProjectClass();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
    }
   
}