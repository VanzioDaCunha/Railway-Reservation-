package org.aitdgoa.utils;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;


 public class DeletePassenger extends Frame implements ActionListener
{   
    Choice passenger;
    Button Button1;
    Button Home;
    Label Label1;
    Label Label2;
    
    
    DeletePassenger()
    {    
        addWindowListener(new WindowAdapter()
        {   
            @Override
            public void windowClosing(WindowEvent we)
            {   System.exit(0); }
        });
        
        passenger=new Choice();
        Button1=new Button("REMOVE RECORD");
        Label1 = new Label();
        Label1.setText("PASSENGER ID:");
        Label2=new Label("INSERTED SUCCESFULLY");
        Home = new Button("HOME");
        
        Label1.setBounds(50, 300, 170, 40);
        passenger.setBounds(50,350,50,50);
        Home.setBounds(1150, 100, 110, 50);
        Button1.setBounds(1100, 900, 200, 50);
        
        initCombo();
        
        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        
        Label1.setFont(myFont);
        passenger.setFont(myFont);
        Button1.setFont(myFont);
        Home.setFont(myFont);
        
        add(Label1);add(passenger);add(Button1);
        add(Home);
        add(new MyCanvas());
        
        Button1.addActionListener(this);
        Home.addActionListener(this);
    }
    private void initCombo()
    {   try
        {   Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
            String s="select PASSENGER_ID from PASSENGER";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);
            while(rs.next())
            {  Integer r=rs.getInt("PASSENGER_ID");
                passenger.addItem(r.toString());
            }
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e){}
    }
    @Override
    public void actionPerformed(ActionEvent e)
    { String s=e.getActionCommand();
      if(s.equals("REMOVE RECORD"))
      {     try
        {   Statement stmt = null;
            String pno = passenger.getSelectedItem();
            Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.
                    getConnection(MainProjectClass.DB_URL,
                            MainProjectClass.USER,MainProjectClass.PASS);
            System.out.println("deleting record...");
            stmt = conn.createStatement();
            String sql = "delete from PASSENGER where PASSENGER_ID="+pno;
            stmt.executeUpdate(sql);
            System.out.println(sql);
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
