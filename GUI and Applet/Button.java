import java.awt.event.*;

public class Button implements ActionListener
{
    static int active;      //keeps track of how many buttons are "active"
    static int char1Index, char2Index;
    private int index;
    private char value;
    private MemoryGame gui;
    private boolean locked = false;
    static private Button [] buttonObjs = new Button [16];
    
    
    public Button (int x, MemoryGame game)
    {
        active = 0;
        index = x;
        gui = game;
        assignValue();
        buttonObjs [x] = this;
    }
    
    public void actionPerformed (ActionEvent e)
    {
        if (!locked && index != char1Index && index != char2Index)
        {
            if (active < 2)
            {
                gui.getTextFields()[index].setText(Character.toString(value));
                if (active == 0)
                    char1Index = index;
                if (active == 1)
                {
                    char2Index = index;
                    if (buttonObjs[char1Index].value == buttonObjs[char2Index].value)
                    {
                        buttonObjs[char1Index].setLocked (true);
                        buttonObjs[char2Index].setLocked (true);
                    }
                }
                
                active ++;
            }
        }
    }
    
    public void assignValue ()
    {
        value = gui.getValues()[index];
    }
    
    public void setLocked (boolean x)
    {
        locked = x;
    }
    public boolean getLocked ()
    {
        return locked;
    }
    
    public static void setActive (int x)
    {
        active = x;
    }
    
    public static Button [] getButtons ()
    {
        return buttonObjs;
    }
    
    public int getIndex ()
    {
        return index;
    }
}