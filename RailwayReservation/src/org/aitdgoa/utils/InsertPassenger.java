package org.aitdgoa.utils;

/**
 *
 * @author Vaibhavi 
 * created: 24/11/2022 9:32 AM
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

public class InsertPassenger extends Frame implements ActionListener{
    
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
     
    InsertPassenger()
    {   addWindowListener(new WindowAdapter() 
        {   public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
        
        Label2 = new Label("Name: ");
        name = new TextField(40);
        Label3 = new Label("Address: ");
        address = new TextField(40);
        Label4 = new Label("Age: ");
        age = new TextField(40);
        Label5 = new Label("Gender: ");
        gender = new TextField(40);
        Label6 = new Label("Contact no: ");
        contact_no = new TextField(40);
        Button1 = new Button("INSERT RECORD");
        Home = new Button("HOME");
        
        Label2.setBounds(50, 300, 170, 40);
        name.setBounds(50, 350, 400, 40);
        Label3.setBounds(50, 400, 170, 40);
        address.setBounds(50, 450, 400, 40);
        Label4.setBounds(50, 500, 170, 40);
        age.setBounds(50, 550, 200, 40);
        Label5.setBounds(50, 600, 170, 40);
        gender.setBounds(50, 650, 200, 40);
        Label6.setBounds(50, 700, 170, 40);
        contact_no.setBounds(50, 750, 200, 40);
        Button1.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);
        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        
        Label2.setFont(myFont);
        Label3.setFont(myFont);Label4.setFont(myFont);
        Label5.setFont(myFont);Label6.setFont(myFont);
        
        name.setFont(myFont);
        address.setFont(myFont);
        age.setFont(myFont);
        gender.setFont(myFont);
        contact_no.setFont(myFont);
        Button1.setFont(myFont);
        Home.setFont(myFont);
        
        add(Label2);add(name);
        add(Label3);add(address);
        add(Label4); add(age);
        add(Label5); add(gender);
        add(Label6); add(contact_no);
        add(Button1);
        add(Home);
        add(new MyCanvas());
        
        Button1.addActionListener(this);
        Home.addActionListener(this);
    }
   
    public void actionPerformed(ActionEvent e) 
    {   String s=e.getActionCommand();
        if(s.equals("INSERT RECORD"))
        {   try
            {Statement stmt = null;
            String pname = name.getText().toUpperCase();
            String padd = address.getText().toUpperCase();
            int page = Integer.parseInt(age.getText());
            String pgender = gender.getText().toUpperCase();
            int pcn = Integer.parseInt(contact_no.getText());
            
            Class.forName(MainProjectClass.driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
            System.out.println("inserting record...");
            stmt = conn.createStatement();
            
            String sql = "INSERT INTO PASSENGER "
               + "(NAME,ADDRESS,AGE,GENDER,CONTACT_NO)"
               + " VALUES ('"+pname+"','"+padd+"',"+page+",'"+pgender+"',"+pcn+")";
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
            System.out.println("hellow world");
            MainProjectClass i=new MainProjectClass();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
    }
    
}
