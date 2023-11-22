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


public class InsertTrain extends Frame implements ActionListener
{
    Label Label2;
    Label Label3;
    Label Label4;
    TextField arrival_time;
    Label Label5;
    TextField departure_time;
    Label Label6;
    TextField total_seats;
    Choice source,destination;
    Button Button1;
    Button Home;
   
    InsertTrain()
    {   
        addWindowListener(new WindowAdapter()
        {   @Override
            public void windowClosing(WindowEvent we)
            {   System.exit(0); }
        });
        
        Label2 = new Label();
        Label3 = new Label();
        Label4 = new Label();
        arrival_time = new TextField(20);
        Label5 = new Label();
        departure_time = new TextField(40);
        Label6 = new Label();
        total_seats = new TextField(40);
        source = new Choice();
        destination = new Choice();
        Button1 = new Button("ADD NEW TRAIN");
        Home = new Button("HOME");
       

        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        Label2.setText("Source: ");
        Label3.setText("Destination: ");
        Label4.setText("Arrival Time: ");
        Label5.setText("Departure Time: ");
        Label6.setText("Total Seats: ");
        
        arrival_time.setFont(myFont);
        source.setFont(myFont);
        destination.setFont(myFont);
        departure_time.setFont(myFont);
        total_seats.setFont(myFont);
        
        Label2.setFont(myFont);
        Label3.setFont(myFont);Label4.setFont(myFont);
        Label5.setFont(myFont);Label6.setFont(myFont);
        Button1.setFont(myFont);
        Home.setFont(myFont);
        
        initCombo();
        
        Label2.setBounds(50, 300, 170, 40);
        source.setBounds(50, 350, 50, 40);
        Label3.setBounds(50, 400, 170, 40);
        destination.setBounds(50, 450, 50, 40);
        Label4.setBounds(50, 500, 250, 40);
        arrival_time.setBounds(50, 550, 300, 40);
        Label5.setBounds(50, 600, 250, 40);
        departure_time.setBounds(50, 650, 300, 40);
        Label6.setBounds(50, 700, 170, 40);
        total_seats.setBounds(50, 750, 200, 40);
        Button1.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);
        
        add(Label2);
        add(source);
        add(Label3);
        add(destination);
        add(Label4);
        add(arrival_time);
        add(Label5); 
        add(departure_time);
        add(Label6);
        add(total_seats);
        add(Button1);
        add(Home);
        add(new MyCanvas());
        
        Home.addActionListener(this);
        Button1.addActionListener(this);
    }
   
     private void initCombo()
    {   try
        {   Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="Select STATION_ID from Station ";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);//used with select command to read data from the table
            while(rs.next())
            {   Integer r=rs.getInt("STATION_ID");
                source.addItem(r.toString());
                destination.addItem(r.toString());
            }
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e){}
    }
   
    @Override
    public void actionPerformed(ActionEvent e)
    {   String s=e.getActionCommand();
        if(s.equals("ADD NEW TRAIN"))
        {   
            try
            {Statement stmt = null;
            String at = arrival_time.getText().toUpperCase();
            String dt = departure_time.getText().toUpperCase();
            int so = Integer.parseInt(source.getSelectedItem());
            int des = Integer.parseInt(destination.getSelectedItem());
            int ts = Integer.parseInt(total_seats.getText());
           
            Class.forName(MainProjectClass.driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
            stmt = conn.createStatement();
           
            String sql = "INSERT INTO TRAIN "
               + "(SOURCE,DESTINATION,ARRIVAL_TIME,DEPARTURE_TIME,TOTAL_SEATS)"
                + " VALUES ("+so+","+des+",'"+at+"','"+dt+"',"+ts+")";
            stmt.executeUpdate(sql);
            System.out.println(sql);
            
            String s1="select * from TRAIN ";
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
