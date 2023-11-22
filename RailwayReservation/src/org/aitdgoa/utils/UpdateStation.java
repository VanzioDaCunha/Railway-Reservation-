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


public class UpdateStation extends Frame implements ActionListener
{
    Label Label1;
    Choice sid;
    Label Label2;
    TextField sname;
    Label Label3;
    TextField slocation;
    Label Label4;
    TextField sstate;
    Button Button1;
    Button Home;
    
    UpdateStation()
    {   addWindowListener(new WindowAdapter() 
        {    @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
    
        Label1 = new Label();
        sid = new Choice();
        Label2 = new Label();
        sname = new TextField(40);
        Label3 = new Label();
        slocation = new TextField(40);
        Label4 = new Label();
        sstate = new TextField(40);
        Button1 = new Button("UPDATE STATION");
        Home = new Button("HOME");
        initCombo();

        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        Label1.setText("Station ID: ");
        Label2.setText("Station Name: ");
        Label3.setText("Location : ");
        Label4.setText("State : ");
        
        Label1.setBounds(50, 300, 170, 40);
        sid.setBounds(50, 350, 50, 50);
        Label2.setBounds(50, 400, 170, 40);
        sname.setBounds(50, 450, 400, 40);
        Label3.setBounds(50, 500, 170, 40);
        slocation.setBounds(50, 550, 400, 40);
        Label4.setBounds(50, 600, 170, 40);
        sstate.setBounds(50, 650, 400, 40);
        Button1.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);
        
        Label1.setFont(myFont);Label2.setFont(myFont);
        Label3.setFont(myFont);Label4.setFont(myFont);
        sid.setFont(myFont);
        sname.setFont(myFont);
        slocation.setFont(myFont);
        sstate.setFont(myFont);
        Button1.setFont(myFont);
        Home.setFont(myFont);
        
        add(Label1);add(sid);add(Label2);add(sname);
        add(Label3);add(slocation); add(Label4); add(sstate);
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
            String s="select STATION_ID from station";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);//used with select command to read data from the table
            while(rs.next())
            {  Integer r=rs.getInt("STATION_ID");
                sid.addItem(r.toString());
            } 
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e1){}
    }
   
    @Override
    public void actionPerformed(ActionEvent e) 
    {   String s=e.getActionCommand();
        if(s.equals("UPDATE STATION"))
        {   try
            {   
                Statement stmt;
                int sid1 = Integer.parseInt(sid.getSelectedItem());
                String snam = sname.getText().toUpperCase();
                String sloc = slocation.getText().toUpperCase();
                String state = sstate.getText().toUpperCase();
            
                Class.forName(MainProjectClass.driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
                System.out.println("updating record...");
                stmt = conn.createStatement();
            
                String sql = "UPDATE STATION set STATION_NAME='"+snam+"' ,LOCATION='"+sloc+"' ,STATE='"+state+"' "
                        + " where station_id = "+sid1;
            
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
