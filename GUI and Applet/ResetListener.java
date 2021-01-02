import java.awt.event.*;

public class ResetListener implements ActionListener
{
    MineSweeper gui;
    
    public ResetListener (MineSweeper g)
    {
        gui = g;
    }
    
    public void actionPerformed (ActionEvent e)
    {
        gui.reset ();
    }
}