import javax.swing.JOptionPane;

public class PalindromeDialog
{
    public static void main (String args [])
    {
        String str, result;
        int again;
        
        do {
            str = JOptionPane.showInputDialog ("Enter a string:");
            if (str == null)        //avoids error when user presses "cancel" when entering string
                break;
            
            result = "That string is " + ((!testPalindrome(str) ? "not " : "") + "a palindrome.");
            JOptionPane.showMessageDialog (null, result);
            
            again = JOptionPane.showConfirmDialog (null, "Do another?");
        } while (again == JOptionPane.YES_OPTION);
    }
    
    public static boolean testPalindrome (String str)
    {
        String str2 = "";
        
        
        for (int x = str.length() - 1; x >= 0; x --)        //goes backwards thru string, passing each character to a new string
        {
            str2 += str.charAt(x);
        }
        
        if (str.equalsIgnoreCase (str2))
            return true;
            
        return false;
    }
}
