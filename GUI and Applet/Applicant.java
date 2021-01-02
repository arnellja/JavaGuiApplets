

public class Applicant
{
    private String firstName, middleName, lastName; //entered manually in field
    private CustomDate birthDate;   //entered using 2 lists for m/d and manually entering year (day list is not usable until m is entered)
    
    private String gender;
    
    private int classRank, classSize;   //entered manually
    
    private double GPA; //entered manually in field
    
    private String schoolName, stateName, cityName; //school and city entered manually. state selected from list.
    
    private int zipCode;    //entered manually in field
    
    
    
    private Class [] schedule = new Class [7];  //class name entered manually, grade entered on list, honors/not option entered using check box
    
    public Applicant (String f, String n)
    {
        firstName = f;
        middleName = "";
        lastName = n;
        
        //default values
        gender = "";
        birthDate = new CustomDate (1,1,1980);
        schoolName = "";
        stateName = "";
        cityName = "";
        zipCode = 0;
        GPA = 1.0;
        classRank = 100;
        classSize = 100;
    }
    
    public Applicant (String f, String m, String l)
    {
        firstName = f;
        middleName = m;
        lastName = l;
        
        //default values
        gender = "";
        birthDate = new CustomDate (1,1,1980);
        schoolName = "";
        stateName = "";
        cityName = "";
        zipCode = 0;
        GPA = 1.0;
        classRank = 100;
        classSize = 100;
    }
    
    //Name accessors and modifiers
    
    public void setFirstName (String fn)
    {
        firstName = fn;
    }
    public void setMiddleName (String mn)
    {
            middleName = mn;
    }
    public void setLastName (String ln)
    {
        lastName = ln;
    }
    public String getFirstName ()
    {
        return firstName;
    }
    public String getMiddleName ()
    {
        return middleName;
    }
    public String getLastName ()
    {
        return lastName;
    }
    
    //Gender
    public void setGender (String g)
    {
        gender = g;
    }
    public String getGender ()
    {
        return gender;
    }
    
    //BirthDate
    
    public void setBirthDate (CustomDate bd)
    {
        birthDate = bd;
    }
    public void setBirthDate (int m, int d, int y)
    {
        birthDate = new CustomDate(m,d,y);
    }
    public CustomDate getBirthDate ()
    {
        return birthDate;
    }
    
    //GPA, class rank, class size
    
    public void setClassRank (int r)
    {
        classRank = r;
    }
    public void setClassSize (int c)
    {
        classSize = c;
    }
    public void setGPA (double g)
    {
        GPA = g;
    }
    public int getClassRank ()
    {
        return classRank;
    }
    public int getClassSize ()
    {
        return classSize;
    }
    public double getGPA ()
    {
        return GPA;
    }
    
    //School name, state name, city name, zip code
    
    public void setSchoolName (String sn)
    {
        schoolName = sn;
    }
    public void setStateName (String sn)
    {
        stateName = sn;
    }
    public void setCityName (String cn)
    {
        cityName = cn;
    }
    public void setZip (int z)
    {
        zipCode = z;
    }
    public String getSchoolName ()
    {
        return schoolName;
    }
    public String getStateName ()
    {
        return stateName;
    }
    public String getCityName ()
    {
        return cityName;
    }
    public int getZip ()
    {
        return zipCode;
    }
    
    //schedule
    
    public void setSchedule (Class [] newSched)             //sets entire sched
    {
        schedule = newSched;
    }
    public int setSchedule (Class newClass, int index)      //sets one hour
    {
        if (index > 6)
            return -1;  //error
        
        schedule[index] = newClass;
        return index;
    }
    public Class [] getSchedule ()
    {
        return schedule;
    }
    
    
    
    
    
    
    
}