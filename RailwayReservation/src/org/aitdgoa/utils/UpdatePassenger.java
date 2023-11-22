package org.aitdgoa.utils;

/**
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

public class UpdatePassenger extends Frame implements ActionListener
{
    Label Label1;
    Choice pid;
    Label Label2;
    TextField name;
    Label Label3;
    TextField address;
    Label Label4;
    TextField age;
    Label Label5;
    TextField gender;
    Label Label6;
    TextField contact_no;
    Button Button1;
    Button Home;
    
    UpdatePassenger()
    {   addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
        Label1 = new Label();
        pid = new Choice();
        Label2 = new Label();
        name = new TextField(40);
        Label3 = new Label();
        address = new TextField(40);
        Label4 = new Label();
        age = new TextField(40);
        Label5 = new Label();
        gender = new TextField(40);
        Label6 = new Label();
        contact_no = new TextField(40);
        Button1 = new Button("UPDATE RECORD");
        Home = new Button("HOME");
        
        initCombo();

        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        Label1.setText("Passenger ID: ");
        Label2.setText("Name: ");
        Label3.setText("Address: ");
        Label4.setText("Age: ");
        Label5.setText("Gender: ");
        Label6.setText("Contact no: ");
        
        Label1.setBounds(50, 300, 170, 40);
        pid.setBounds(50, 350, 50, 50);
        Label2.setBounds(50, 400, 170, 40);
        name.setBounds(50, 450, 400, 40);
        Label3.setBounds(50, 500, 170, 40);
        address.setBounds(50, 550, 400, 40);
        Label4.setBounds(50, 600, 170, 40);
        age.setBounds(50, 650, 200, 40);
        Label5.setBounds(50, 700, 170, 40);
        gender.setBounds(50, 750, 200, 40);
        Label6.setBounds(50, 800, 170, 40);
        contact_no.setBounds(50, 850, 200, 40);
        Button1.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);
        
        Label1.setFont(myFont);Label2.setFont(myFont);
        Label3.setFont(myFont);Label4.setFont(myFont);
        Label5.setFont(myFont);Label6.setFont(myFont);
        pid.setFont(myFont);
        name.setFont(myFont);
        address.setFont(myFont);
        age.setFont(myFont);
        gender.setFont(myFont);
        contact_no.setFont(myFont);
        Button1.setFont(myFont);
        Home.setFont(myFont);
        
        add(Label1);add(pid);add(Label2);add(name);
        add(Label3);add(address); add(Label4); add(age);
        add(Label5); add(gender);add(Label6); add(contact_no);
        add(Button1);
        add(Home);
        add(new MyCanvas());
        
        
        Button1.addActionListener(this);
        Home.addActionListener(this);
        
    }
    
    private void initCombo()
    {   try
        {   Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="select passenger_id from passenger";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);
            while(rs.next())
            {  Integer r=rs.getInt("passenger_id");
                pid.addItem(r.toString());
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
                String pname = name.getText().toUpperCase();
                String padd = address.getText().toUpperCase();
                int page = Integer.parseInt(age.getText());
                String pgender = gender.getText().toUpperCase();
                int pcn = Integer.parseInt(contact_no.getText());
            
                Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
                System.out.println("updating record...");
                stmt = conn.createStatement();
            
                String sql = "UPDATE PASSENGER set NAME='"+pname+"' ,Address='"+padd+"' ,AGE="+page+" ,GENDER='"+pgender+"' ,CONTACT_NO="+pcn
                    + " where passenger_id = "+pid1;
            
                stmt.executeUpdate(sql);
                System.out.println(sql);
                System.out.println("data record added to table successfully");
                String s1 = "select * from passenger";    
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
