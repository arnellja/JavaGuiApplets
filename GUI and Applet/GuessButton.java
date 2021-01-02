import java.awt.event.*;
import java.awt.Font;
import javax.swing.JOptionPane;

public class GuessButton implements ActionListener
{
    MemoryGame gui;
    Button [] buttonObjs;
    
    public GuessButton (MemoryGame game)
    {
        gui = game;
        buttonObjs = Button.getButtons ();
    }
    
    public void actionPerformed (ActionEvent e)
    {
        if (Button.active == 2)
        {
            gui.addGuess ();
            
            for (int x = 0; x < 16; x ++)
            {
                if (!buttonObjs[x].getLocked())
                    gui.getTextFields()[x].setText ("");
                else
                    gui.getTextFields()[x].setFont (new Font("monospaced", Font.BOLD + Font.ITALIC, 14));
            }
            
            Button.active = 0;
            Button.char1Index = -1;
            Button.char2Index = -1;
            
            if (checkForVictory ())
            {
                int again = JOptionPane.showConfirmDialog (null, "Congratulations, you win! Would you like to go again?");
                if (again == JOptionPane.YES_OPTION)
                {
                    gui.reset ();
                }
                else
                    System.exit (0);
            }
        }
        else
        {
            JOptionPane.showMessageDialog (null, "Only press guess once two buttons have been selected!");
        }
    }
    
    public boolean checkForVictory ()
    {
        for (int x = 0; x < 16; x ++)
        {
            if (!buttonObjs[x].getLocked())
                return false;
        }
        
        return true;
    }
}