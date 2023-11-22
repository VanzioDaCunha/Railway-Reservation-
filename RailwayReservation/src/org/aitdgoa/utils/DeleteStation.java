package org.aitdgoa.utils;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;


public class DeleteStation extends Frame implements ActionListener
{   
    Choice stationno;
    Button Button;
    Label Label1;
    Label Label2;
    Button Home;
    
    DeleteStation()
    {   
        addWindowListener(new WindowAdapter()
        {   @Override
            public void windowClosing(WindowEvent we)
            {   System.exit(0); }
        });
        
        stationno=new Choice();
        Button=new Button("REMOVE STATION");
        Label1 = new Label();
        Label1.setText("STATION NO.");
        Home = new Button("HOME");
        initCombo();
        
        Label1.setBounds(50, 300, 170, 40);
        stationno.setBounds(50,350,50,50);
        Home.setBounds(1150, 100, 110, 50);
        Button.setBounds(1100, 900, 200, 50);
        
        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        Label1.setFont(myFont);
        stationno.setFont(myFont);
        Home.setFont(myFont);
        Button.setFont(myFont);
        
        
        add(Label1);add(stationno);
        add(Button);
        add(Home);
        add(new MyCanvas());
        
        Button.addActionListener(this);
        Home.addActionListener(this);
        Home.setBounds(1150, 100, 110, 50);
    }
    private void initCombo()
    {   try
        {   Class.forName(MainProjectClass.driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="select STATION_ID from STATION";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);
            while(rs.next())
            {  Integer r=rs.getInt("STATION_ID");
                stationno.addItem(r.toString());
            }
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e){}
    }
    @Override
    public void actionPerformed(ActionEvent e)
    { String s=e.getActionCommand();
      if(s.equals("REMOVE STATION"))
      {     try
        {   Statement stmt = null;
            String sno=stationno.getSelectedItem().toString();
            Class.forName(MainProjectClass.driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.
                    getConnection(MainProjectClass.DB_URL,
                            MainProjectClass.USER,MainProjectClass.PASS);
            System.out.println("deleting record...");
            stmt = conn.createStatement();
            String sql = "delete from STATION where STATION_ID="+sno;
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
            
            System.out.println("data record deleted from table successfully");
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e4){}
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