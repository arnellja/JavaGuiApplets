import java.io.*;

public class PrintWriterDemo
{
    public static void main (String [] args)
    {
        PrintWriter pw = new PrintWriter (System.out, false);
        pw.println ("This is a string");
        int i = -7;
        pw.println(i);
        double d = 4.5e-7;
        pw.println(d);
        
        System.out.println ("Line one");
        pw.flush();
        System.out.println ("Line two");
    }
}