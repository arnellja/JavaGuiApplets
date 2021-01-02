import javax.swing.*;       
import java.awt.*;         
import java.awt.event.*;
import java.awt.image.*;


public class Animate extends JFrame implements Runnable
{
    double column = 20;
    double row = 70;
    double columnStep =.5, rowStep = .1;
    
    
    
    public Animate ( )
    {
        
        Container mainWindow = getContentPane();
        
        ColorPanel view = new ColorPanel(Color.WHITE);
        mainWindow.add( view );
        
        setSize(600, 400);
        setVisible( true );
        
    }
    
    
    public void run ()
    {
        
        while (true)
        {
                Dimension frameSize = getSize();
                
                if ( column >= frameSize.width || column ==0)
                    columnStep = columnStep * -1;
                    
                if (row >= frameSize.height || row == 0)
                    rowStep = rowStep * -1;
                    
                column = column + columnStep;
                row = row + rowStep;
                
                repaint();
                
                try
                {
                    Thread.sleep(1);
                }catch (InterruptedException e) { }
                
        } 
        
    }
    
    public static void main ( String args [ ] )
    {
        
        Thread bounceThread = new Thread ( new Animate ());
        bounceThread.start();
    }
    
    
        
    
    public class ColorPanel extends JPanel 
    {
   
    public ColorPanel (Color backColor)
    {
        setBackground(backColor);
        
       
    }
    
    public void paintComponent(Graphics g)
    {
        
            
           g.fillOval((int)column,(int)row, 20,20);
        
        
    }
    
    }
    
}
        
        
        