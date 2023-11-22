package org.aitdgoa.utils;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

 public class DeleteTrain extends Frame implements ActionListener
{   
    Choice trainno;
    Button jButton;Label jLabel1;
    Label jLabel2;
    Button Home;
    
    DeleteTrain()
    {   
        trainno=new Choice();
        jButton=new Button("DERAIL TRAIN");
        jLabel1 = new Label();
        jLabel1.setText("TRAIN NO.");
        Home = new Button("HOME");
        initCombo();
        
        jLabel1.setBounds(50, 300, 170, 40);
        trainno.setBounds(50, 350, 50, 40);
        jButton.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);
        
        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        
        addWindowListener(new WindowAdapter()
        {   @Override
            public void windowClosing(WindowEvent we)
            {   System.exit(0); }
        });
        
        jLabel1.setFont(myFont);
        trainno.setFont(myFont);
        jButton.setFont(myFont);
        Home.setFont(myFont);
        
        add(jLabel1);add(trainno);add(jButton);
        add(Home);
        add(new MyCanvas());
        
        jButton.addActionListener(this);
        Home.addActionListener(this);
    }
    private void initCombo()
    {   try
        {   Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.
                    getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="select TRAIN_NO from TRAIN";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);
            while(rs.next())
            {  Integer r=rs.getInt("TRAIN_NO");
                trainno.addItem(r.toString());
            }
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e){}
    }
@Override
    public void actionPerformed(ActionEvent e)
    { String s=e.getActionCommand();
      if(s.equals("DERAIL TRAIN"))
      {     try
        {   Statement stmt = null;
            String tno=trainno.getSelectedItem();
            Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.
                    getConnection(MainProjectClass.DB_URL,
                            MainProjectClass.USER,MainProjectClass.PASS);
            stmt = conn.createStatement();
            String sql = "delete from TRAIN where TRAIN_NO="+tno;
            stmt.executeUpdate(sql);
            System.out.println(sql);
            System.out.println("data record deleted from table successfully");
            
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