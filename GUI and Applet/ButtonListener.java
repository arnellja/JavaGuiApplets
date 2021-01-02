import java.awt.event.*;
import javax.swing.*;

public class ButtonListener extends MouseAdapter
{
    private MineSweeper gui;
    private int down, across;
    public static ButtonListener listenerList [][] = new ButtonListener [16][30];
    public boolean clicked = false;
    //private JButton self;
    
    public ButtonListener(int d, int a, MineSweeper g)
    {
        gui = g;
        down = d;
        across = a;
        listenerList [down][across] = this;
    }
    
    public void mouseClicked (MouseEvent e)
    {
        if (e.getButton () == 1)
            gui.lClicked (down, across);
        if (e.getButton () == 3)
            gui.rClicked (down, across);
    }
}
