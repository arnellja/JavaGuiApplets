import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class MineSweeper extends JFrame implements Runnable
{
    private static JPanel midPanel;
    private static JPanel southPanel;
    private static JPanel northPanel;
    private static JPanel eastPanel;
    private static JPanel westPanel;
    private static JLabel mineLabel;
    private static JLabel timeLabel;
    private static JLabel fillerLabel;
    private static JButton [][] buttons;
    private static JButton resetButton;
    private static boolean [][] values;
    private static int mineCounter;
    private static int runningTime;
    private static final int WIDTH = 650;
    private static final int HEIGHT = 450;
    private static final int TOTALMINENUMBER = 99;
    private static boolean firstGuess = true;
    private static boolean stillPlaying = true;
    
    private static Random rand = new Random ();
    private static DecimalFormat two = new DecimalFormat ("00");
    
    public MineSweeper (String str)
    {
        super (str);
        
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        midPanel = new JPanel ();           //panels (NORTH SOUTH EAST WEST CENTER)
        southPanel = new JPanel ();         //will be put into ContentPane's borderlayout
        northPanel = new JPanel ();
        westPanel = new JPanel ();
        eastPanel = new JPanel ();
        
        mineCounter = TOTALMINENUMBER;
        runningTime = 0;
        
        mineLabel = new JLabel ("Mines Left:   " + two.format(mineCounter));         //Components for NORTH panel
        fillerLabel = new JLabel ("                                                                                          ");
        timeLabel = new JLabel ("Time:   " + "0 s");
        
        resetButton = new JButton ("Reset");                            //Components for SOUTH panel
        resetButton.setFocusable (false);
        resetButton.setPreferredSize (new Dimension (80, 40));
        resetButton.addActionListener (new ResetListener (this));
        
        setSize (WIDTH, HEIGHT);                                        //gui size
        
        
        BorderLayout layout = new BorderLayout ();                      //setting gui layout
        Container cp = getContentPane();                
        cp.setLayout (layout);
                            
        cp.add (midPanel, BorderLayout.CENTER);                         //panels within the border layout
        cp.add (southPanel, BorderLayout.SOUTH);                        //give much more flexibility
        cp.add (northPanel, BorderLayout.NORTH);
        cp.add (eastPanel, BorderLayout.EAST);
        cp.add (westPanel, BorderLayout.WEST);
        
        midPanel.setLayout (new GridLayout (16, 30));                   //minefield is 16 down, 30 across
        
        values = new boolean [16][30];                                  //Components for MIDDLE panel
        fillGrid ();                                                    //(also instantiates and fills
        buttons = new JButton [16][30];                                 //"grid" with mines)
        
        for (int down = 0; down < buttons.length; down ++)      //instantiates buttons and adds to panel
        {
            for (int across = 0; across < buttons[0].length; across ++)
            {
                buttons[down][across] = new JButton ();
                buttons[down][across].setFocusable (false);                                 //makes it so button won't "grab" focus, which causes a button to look selected. it's just enoying when a random button lookes focused
                buttons[down][across].setMargin (new Insets (0, 0, 0, 0));                  //removes button margins to avoid "..." from displaying in place of text
                buttons[down][across].addMouseListener (new ButtonListener(down, across, this));
                midPanel.add (buttons[down][across]);
            }
        }
        
        northPanel.add (mineLabel);
        northPanel.add (fillerLabel);
        northPanel.add (timeLabel);
        
        southPanel.add (resetButton);
        
        setResizable (false);
        setVisible(true);
    }
    
    public static void main (String args [])
    {
        Thread timeThread = new Thread ( new MineSweeper ("Mine Sweeper") );
        //MineSweeper mine = new MineSweeper ("Mine Sweeper");
        timeThread.start();
    }
    
    public void run ()
    {
        while (true)
        {
            if (stillPlaying && !firstGuess)
            {
                
                runningTime += 1;
                timeLabel.setText ("Time:   " + runningTime + " s");
                
                try
                {
                    Thread.sleep(1000);
                }catch (InterruptedException e) { }
            }
            
            
        }
    }
    
    public static void fillGrid ()      /**false = no bomb   //   true = bomb**/
    {
        for (int down = 0; down < values.length; down ++)      //sets grid initially to all false
        {
            for (int across = 0; across < values[0].length; across ++)
            {
                values [down][across] = false;
            }
        }
        
        for (int x = 0; x < TOTALMINENUMBER; x++)       //places bombs into grid
        {
            int down;
            int across;
            
            do
            {                                       //finds spot without bomb
                down = rand.nextInt (16);
                across = rand.nextInt (30);
            } while (values[down][across]);
            
            values[down][across] = true;            //places bomb
        } 
    }
    
    public static void lClicked (int down, int across)
    {
        if (down >= 0 && down < 16 && across >= 0 && across < 30 && !ButtonListener.listenerList[down][across].clicked && !buttons[down][across].getText().equals(" ") && stillPlaying)           //only works if numbers are "in bounds" and hasn't already been clicked
            {
            JButton button = buttons[down][across];
            ButtonListener.listenerList[down][across].clicked = true;
            
            if (!button.getText().equals(" "))
            {
                button.setEnabled (false);
               
                if (values[down][across] && !firstGuess)            //ensures you cannot lose on first turn
                {
                    button.setText ("@");
                    stillPlaying = false;
                    registerLoss ();
                }
                if (values[down][across] && firstGuess)
                {
                    replaceMine (down, across);
                }
                
                firstGuess = false;
                
                if (!values[down][across])
                {
                    int number = evaluate(down, across);
                    if (number > 0)
                        button.setText (Integer.toString(number));
                    if (number == 0)
                    {
                        lClicked (down-1, across-1);
                        lClicked (down-1, across);
                        lClicked (down-1, across+1);
                        lClicked (down, across-1);
                        lClicked (down, across+1);
                        lClicked (down+1, across-1);
                        lClicked (down+1, across);
                        lClicked (down+1, across+1);
                    }
                }
            }
            
            if (checkForVictory())
            {
                registerWin ();
            }
        }
    }
    
    public static void rClicked (int down, int across)
    {
        if (buttons[down][across].getText().equals(""))
        {
            buttons[down][across].setText (" ");
            buttons[down][across].setBackground (Color.RED);
            mineCounter --;
        }
        else
        if (buttons[down][across].getText().equals(" "))
        {
            buttons[down][across].setText ("");
            buttons[down][across].setBackground (null);
            mineCounter ++;
        }
        
        mineLabel.setText ("Mines Left:   " + two.format(mineCounter));
    }
    
    public static void replaceMine (int down, int across)
    {
        int d, a;
        
        do
            {                                       //finds spot without bomb
                d = rand.nextInt (16);
                a = rand.nextInt (30);
            } while (values[d][a]);
            
        values [d][a] = true;
        values [down][across] = false;
    }
    
    public static int evaluate (int down, int across)   //checks all surrounding spaces and returns # of adjacent mines
    {
        int counter = 0;                                //checks to make sure "in bounds" then checks for bombs
        
        if (down-1 >= 0 && across-1 >= 0)
        {
            if (values[down-1][across-1])
                counter ++;
        }
        
        if (down-1 >= 0)
        {
            if (values[down-1][across])
                counter ++;
        }
        
        if (down-1 >= 0 && across+1 < 30)
        {
            if (values[down-1][across+1])
                counter ++;
        }
        
        if (across-1 >= 0)
        {
            if (values[down][across-1])
                counter ++;
        }
        
        if (across+1 < 30)
        {
            if (values[down][across+1])
                counter ++;
        }
        
        if (down+1 < 16 && across-1 >= 0)
        {
            if (values[down+1][across-1])
                counter ++;
        }
        
        if (down+1 < 16)
        {
            if (values[down+1][across])
                counter ++;
        }
        
        if (down+1 < 16 && across+1 < 30)
        {
            if (values[down+1][across+1])
                counter ++;
        }
        
        return counter;
    }
    
    public static void registerLoss ()          //reveals bombs
    {
        for (int down = 0; down < 16; down ++)
        {
            for (int across = 0; across < 30; across ++)
            {
                if (values[down][across])
                {
                    buttons[down][across].setText ("@");
                    buttons[down][across].setEnabled (false);
                }
            }
        }
    }
    
    public static void registerWin ()          //sets flags on all bombs left congratulates player on winning
    {
        mineCounter = 0;
        mineLabel.setText ("Mines Left:   " + two.format(mineCounter));
        
        for (int down = 0; down < 16; down ++)
        {
            for (int across = 0; across < 30; across ++)
            {
                if (values[down][across])
                {
                    buttons[down][across].setText (" ");
                    buttons[down][across].setBackground (Color.RED);
                }
            }
        }
        
        JOptionPane.showMessageDialog (null, "Congratulations! You win!");
    }
    
    public static boolean checkForVictory ()
    {
        for (int down = 0; down < 16; down ++)
        {
            for (int across = 0; across < 30; across ++)
            {
                if (!ButtonListener.listenerList[down][across].clicked && !values[down][across])        //if a non-bomb hasn't been clicked
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static void reset ()
    {
        for (int down = 0; down < 16; down ++)
        {
            for (int across = 0; across < 30; across ++)
            {
                buttons [down][across].setText ("");
                buttons [down][across].setEnabled (true);
                buttons [down][across].setBackground(null);
                ButtonListener.listenerList[down][across].clicked = false;
                
                mineCounter = 99;
                runningTime = 0;
                mineLabel.setText ("Mines Left:   " + mineCounter);
                timeLabel.setText ("Time:   " + runningTime + " s");
                
                firstGuess = true;
                stillPlaying = true;
                
                fillGrid ();
            }
        }
    }
}