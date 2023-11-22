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

public class UpdateTrain extends Frame implements ActionListener
{
    Label jLabel1;
    Choice tno;
    Label jLabel2;
    Choice src;
    Label jLabel3;
    Choice des;
    Label jLabel4;
    TextField atime;
    Label jLabel5;
    TextField dtime;
    Label jLabel6;
    TextField seat;
    Button jButton1;
    Button Home;
    
    UpdateTrain()
    {   addWindowListener(new WindowAdapter() 
        {    @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
    
        jLabel1 = new Label();
        tno = new Choice();
        jLabel2 = new Label();
        src = new Choice();
        jLabel3 = new Label();
        des = new Choice();
        jLabel4 = new Label();
        atime= new TextField(40);
        jLabel5 = new Label();
        dtime = new TextField(40);
        jLabel6 = new Label();
        seat = new TextField(40);
        jButton1 = new Button("UPDATE STATION");
        Home = new Button("HOME");
        initCombo1();
        initCombo2();

        Font myFont=new Font("Tahoma", 1, 20); // NOI18N
        jLabel1.setText("TRAIN NUMBER: ");
        jLabel2.setText("SOURCE: ");
        jLabel3.setText("DESTINATION : ");
        jLabel4.setText("ARRIVAL TIME : ");
        jLabel5.setText("DEPARTURE TIME : ");
        jLabel6.setText("TOTAL SEATS : ");
        
        
        jLabel1.setFont(myFont);
        jLabel2.setFont(myFont);
        jLabel3.setFont(myFont);
        jLabel4.setFont(myFont);
        jLabel5.setFont(myFont);
        jLabel6.setFont(myFont);
        tno.setFont(myFont);
        src.setFont(myFont);
        des.setFont(myFont);
        atime.setFont(myFont);
        dtime.setFont(myFont);
        seat.setFont(myFont);
        jButton1.setFont(myFont);
        Home.setFont(myFont);
        
        jLabel1.setBounds(50, 300, 170, 40);
        tno.setBounds(50, 350, 50, 40);
        jLabel2.setBounds(50, 400, 170, 40);
        src.setBounds(50, 450, 50, 40);
        jLabel3.setBounds(50, 500, 170, 40);
        des.setBounds(50, 550, 50, 40);
        jLabel4.setBounds(50, 600, 250, 40);
        atime.setBounds(50, 650, 300, 40);
        jLabel5.setBounds(50, 700, 250, 40);
        dtime.setBounds(50, 750, 300, 40);
        jLabel6.setBounds(50, 800, 170, 40);
        seat.setBounds(50, 850, 200, 40);
        jButton1.setBounds(1100, 900, 200, 50);
        Home.setBounds(1150, 100, 110, 50);
        
        add(jLabel1);add(tno);add(jLabel2);add(src);
        add(jLabel3);add(des); add(jLabel4); add(atime);
        add(jLabel5); add(dtime);add(jLabel6); add(seat);
        add(jButton1);
        add(Home);
        add(new MyCanvas());
        
        
        jButton1.addActionListener(this);
        Home.addActionListener(this);
    }
    
    private void initCombo1()
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
    
    private void initCombo2()
    {   try
        {   Class.forName(MainProjectClass.driver);
            Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL,MainProjectClass.USER,MainProjectClass.PASS);
            String s="select STATION_ID from station";
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(s);//used with select command to read data from the table
            while(rs.next())
            {  Integer r=rs.getInt("STATION_ID");
                src.addItem(r.toString());
                des.addItem(r.toString());
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
                int tno1 = Integer.parseInt(tno.getSelectedItem());
                int src1 = Integer.parseInt(src.getSelectedItem());
                int des1 = Integer.parseInt(des.getSelectedItem());
                String atime1 = atime.getText().toUpperCase();
                String dtime1 = dtime.getText().toUpperCase();
                int seat1 = Integer.parseInt(seat.getText());
            
                Class.forName(MainProjectClass.driver);
                Connection conn = DriverManager.getConnection(MainProjectClass.DB_URL, MainProjectClass.USER,MainProjectClass.PASS);
                System.out.println("updating record...");
                stmt = conn.createStatement();
            
                String sql = "UPDATE TRAIN set SOURCE="+src1+" , DESTINATION="+des1+" , ARRIVAL_TIME='"+atime1+"' , DEPARTURE_TIME='"+dtime1+"' "
                        +" , TOTAL_SEATS= "+seat1+ " where  TRAIN_NO = "+tno1;
                stmt.executeUpdate(sql);
                System.out.println(sql);
                System.out.println("data record update to table successfully");
                
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
