/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.aitdgoa.utils;

/**
 *
 * @author User
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class MyCanvas extends Canvas{
    
    private BufferedImage img;
    
    public MyCanvas()
    {
        setBackground(Color.WHITE);
        setSize(1400,1000);
    }
    
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2;
        Color cust = new Color(154,154,154);
        g2 = (Graphics2D)g;
        Font myFont = new Font("Tahoma", 1, 36);
        g2.setFont(myFont);
        g2.setColor(cust);
        g2.fillRect(0, 0, 1400, 200);
        g2.setColor(Color.BLACK);
        g2.drawString("PROJECT 12 RAILWAYS", 500, 125);
        
        img = loadimg("images/images.png");
        g2.drawImage(img, 30, 60, 120, 120, null);
        
        img = loadimg("images/train4.jpg");
        g2.drawImage(img, 0, 200, 1400, 800, null);
    }
    
    BufferedImage loadimg(String path)
    {
        BufferedImage bi;
        bi = null;
        try{
            bi = ImageIO.read(new File(path));
        }
        catch(IOException e){
            System.out.println("failed to load iamge");
            System.exit(1);
        }
        return bi;
    }
}    
