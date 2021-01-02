import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MemoryGame extends JFrame
{
    private static JTextField [] textFields = new JTextField [16];
    private static JButton [] buttons = new JButton [16];
    private static JButton guessButton;
    private static JLabel label;
    private static JTextField numberOfGuesses;
    private static Random rand = new Random ();
    private static final int WIDTH = 280, HEIGHT = 400;
    private static char [] values;
    private static int guesses;
    
    public MemoryGame (char [] values, String str)
    {
        super (str);
        guesses = 0;
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        cp.setLayout (new FlowLayout ());
        
        final String FILLER = "                                                                                                                      ";
        
        //first row
        for (int x = 0; x < 4; x ++)
        {
            textFields [x] = new JTextField (5);
            textFields [x].setEditable (false);
            cp.add(textFields [x]);
        }
        for (int x = 0; x < 4; x ++)
        {   
            buttons [x] = new JButton ();
            buttons [x].setPreferredSize (new Dimension(59, 23));
            cp.add(buttons [x]);
        }
        cp.add (new JLabel (FILLER));
        
        //second row
        for (int x = 4; x < 8; x ++)
        {
            textFields [x] = new JTextField (5);
            textFields [x].setEditable (false);
            cp.add(textFields [x]);
        }
        for (int x = 4; x < 8; x ++)
        {   
            buttons [x] = new JButton ();
            buttons [x].setPreferredSize (new Dimension(59, 23));
            cp.add(buttons [x]);
        }
        cp.add (new JLabel (FILLER));
        
        //third row
        for (int x = 8; x < 12; x ++)
        {
            textFields [x] = new JTextField (5);
            textFields [x].setEditable (false);
            cp.add(textFields [x]);
        }
        for (int x = 8; x < 12; x ++)
        {   
            buttons [x] = new JButton ();
            buttons [x].setPreferredSize (new Dimension(59, 23));
            cp.add(buttons [x]);
        }
        cp.add (new JLabel (FILLER));
        
        //fourth row
        for (int x = 12; x < 16; x ++)
        {
            textFields [x] = new JTextField (5);
            textFields [x].setEditable (false);
            cp.add(textFields [x]);
        }
        for (int x = 12; x < 16; x ++)
        {   
            buttons [x] = new JButton ();
            buttons [x].setPreferredSize (new Dimension(59, 23));
            cp.add(buttons [x]);
        }
        cp.add (new JLabel (FILLER));
        
        for (int x = 0; x < buttons.length; x ++)
        {
            buttons[x].addActionListener (new Button (x, this));        //adds actionlisteners
            textFields[x].setHorizontalAlignment (JLabel.CENTER);       //sets textfields to print out in center
        }
        
        
        
        
        guessButton = new JButton ("Guess!");
        guessButton.setHorizontalAlignment (JLabel.CENTER);
        guessButton.setPreferredSize (new Dimension (80, 40));
        guessButton.addActionListener (new GuessButton (this));
        
        label = new JLabel ("        Guesses: ");
        numberOfGuesses = new JTextField (3);
        numberOfGuesses.setHorizontalAlignment (JLabel.CENTER);
        numberOfGuesses.setText(Integer.toString(guesses));
        numberOfGuesses.setEditable(false);
        
        cp.add(guessButton);
        cp.add(label);
        cp.add(numberOfGuesses);
    }
    
    public static void main (String args [])
    {
        values = createValuesArray ();
        MemoryGame gui = new MemoryGame (values, "Memory Game");
        gui.setSize (WIDTH, HEIGHT);
        gui.setVisible (true);
        gui.setResizable (false);
    }
    
    //randomly hard-codes pairs of !, @, #, $, %, ^, &, and * chars into an array
    //returns the array
    public static char [] createValuesArray ()         
    {
        char [] temp = new char [16];
        for (int x = 0; x < temp.length; x ++)      //temporarily puts spaces in every index. used to determine if index is "empty"
            temp[x] = ' ';
        
        int one, two;
        
        one = createValuesAux (temp);
        two = createValuesAux (temp, one);
        temp [one] = '!';
        temp [two] = '!';
        
        one = createValuesAux (temp);
        two = createValuesAux (temp, one);
        temp [one] = '@';
        temp [two] = '@';
        
        one = createValuesAux (temp);
        two = createValuesAux (temp, one);
        temp [one] = '#';
        temp [two] = '#';
        
        one = createValuesAux (temp);
        two = createValuesAux (temp, one);
        temp [one] = '$';
        temp [two] = '$';
        
        one = createValuesAux (temp);
        two = createValuesAux (temp, one);
        temp [one] = '%';
        temp [two] = '%';
        
        one = createValuesAux (temp);
        two = createValuesAux (temp, one);
        temp [one] = '^';
        temp [two] = '^';
        
        one = createValuesAux (temp);
        two = createValuesAux (temp, one);
        temp [one] = '&';
        temp [two] = '&';
        
        one = createValuesAux (temp);
        two = createValuesAux (temp, one);
        temp [one] = '*';
        temp [two] = '*';
        
        return temp;
    }
    //locates empty index to store first character. returns the index.
    public static int createValuesAux (char [] temp)
    {
        int one;
        do
        {
            one = rand.nextInt (16);
        } while (temp[one] != ' ');
        return one;
    }
    //locates empty index to store second character. returns the index.
    public static int createValuesAux (char [] temp, int one)
    {
        int two;
        do
        {
            two = rand.nextInt (16);
        } while (one == two || temp[two] != ' ');
        return two;
    }
    
    public static void addGuess ()
    {
        guesses++;
        numberOfGuesses.setText(Integer.toString(guesses));
    }
    
    public static JTextField [] getTextFields ()
    {
        return textFields;
    }
    public static JButton [] getButtons ()
    {
        return buttons;
    }
    public static char[] getValues ()
    {
        return values;
    }
    
    public void reset ()
    {
        values = createValuesArray ();
        
        for (int x = 0; x < 16; x++)
        {
            textFields[x].setText ("");
            textFields[x].setFont (new Font(null));
            //textFields[x].setFont (new Font ();
            Button.getButtons()[x].setLocked(false);
            Button.getButtons()[x].assignValue();
            guesses = 0;
            numberOfGuesses.setText(Integer.toString(guesses));
        }
    }
}