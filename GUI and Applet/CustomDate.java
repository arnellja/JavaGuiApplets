//Used to store birth date of an Applicant. Named CustomDate to avoid conflicts with the Date class already defined by Java

public class CustomDate
{
    private int month, day, year;
    
    public CustomDate (int m, int d, int y) //precondition: m/d/y is a valid date
    {
        if (verifyMonth() )
            month = m;
        else
            month = 1;
            
        if (verifyDay() )
            day = d;
        else
            day = 1;
        
        if (verifyYear() )
            year = y;
        else
            year = 1990;
    }
    
    public int getMonth ()
    {
        return month;
    }
    
    public int getDay ()
    {
        return day;
    }
    
    public int getYear ()
    {
        return year;
    }
    
    private boolean verifyMonth ()
    {
        if (month < 1 || month > 12)
            return false;
        
        return true;
    }
    
    private boolean verifyDay ()
    {
        if (day < 1)
            return false;
        switch (month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day > 31)
                    return false;
                break;
                
            case 2:
                if (day > 29)
                    return false;
                break;
                
            case 4:
            case 6:
            case 9:
            case 11:
                if (day > 30)
                    return false;
                break;
            
        }
        
        return true;
    }
    
    private boolean verifyYear ()
    {
        if (year < 1900 || year > 2012)
        {
            return false;
        }
        
        return true;
    }
    
    public static int monthValue (String month)
    {
        switch (month)
        {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
        }
        
        return 0;
    }
    
    public String toString ()
    {
        return (month+"/"+day+"/"+year);
    }
}
