/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aitdgoa.utils;

import java.awt.*;
import java.awt.event.*;


/**
 * @author: Vanzio Da Cunha
 * Created: 24/11/22 09:38
 * Modified Last: 04/12/22 17:00
 */

public class MainProjectClass extends Frame implements ActionListener 
{
    static String USER ;
    static  String PASS;
    static  String driver;
    static  String DB_URL;
    
    Button Button1;
    Button Button2;
    Button Button3;
    Button Home;
    
    MainProjectClass()
    {
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {   System.exit(0); }
        });
        
        Font myFont=new Font("Tahoma", 1, 26); // NOI18N
        setLayout(null);
        Button1 = new Button("PASSENGERS");
        Button2 = new Button("TRAINS");
        Button3 = new Button("BOOKINGS");
        Home = new Button("HOME");
        
        Button1.setBounds(570, 400, 200, 70);
        Button2.setBounds(570, 500, 200, 70);
        Button3.setBounds(570, 600, 200, 70);
        Home.setBounds(1150, 100, 100, 50);
        
        Button1.setFont(myFont);
        Button2.setFont(myFont);
        Button3.setFont(myFont);
        Home.setFont(myFont);
        
        add(Button1);
        add(Button2);
        add(Button3);
        add(Home);
        add(new MyCanvas());
        
        Button1.addActionListener(this);
        Button2.addActionListener(this);
        Button3.addActionListener(this);
        Home.addActionListener(this);
        
        MainProjectClass.initconnectionData();
    }
    
    static void initconnectionData()
    {  
        USER ="root";
        PASS="";
        driver="com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost/project12";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {   
        String g=e.getActionCommand();
        if(g.equals("PASSENGERS"))
        {   
            PassengerMain i=new PassengerMain();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("HOME"))
        {   
            MainProjectClass i=new MainProjectClass();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("TRAINS"))
        {   
            TrainMain i=new TrainMain();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
        if(g.equals("BOOKINGS"))
        {   
            BookingMain i=new BookingMain();
            i.setSize(new Dimension(1400, 1000));
            setVisible(false);
            i.setVisible(true);
        }
    }
    
    public static void main(String args[])
    {   MainProjectClass e=new MainProjectClass();
        e.setSize(new Dimension(1400, 1000));
        e.setTitle("DBMQ Projects");
        e.setVisible(true);
    }
    
}
