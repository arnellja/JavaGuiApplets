import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.applet.*;
import java.io.*;   //used to write info to file

public class ApplicantInputApplet extends Applet implements ActionListener, ItemListener
{
    final int NUMBEROFCLASSES = 7;
    final int ROWS = 19;
    final int COLUMNS = 3;
    Panel panelList [][] = new Panel [ROWS][COLUMNS];
    
    Label personalInfoLabel = new Label ("PERSONAL INFORMATION");
    
    Label fnameLabel, mnameLabel, lnameLabel;
    TextField fnameText, mnameText, lnameText;
    
    CheckboxGroup genderBoxes;
    Checkbox male, female;
    
    Label birthLabel;
    Choice monthSelect;
    Choice daySelect;
    Label yearLabel;
    TextField yearText;
    
    Label rankLabel;
    TextField rankText;
    Label classSizeLabel;
    TextField classSizeText;
    
    Label GPALabel;
    TextField GPAText;
    
    
    Label schoolInfoLabel = new Label ("HIGHSCHOOL INFORMATION");
    
    Label schoolNameLabel, stateLabel, cityLabel, zipLabel;
    TextField schoolNameText, cityText, zipText;
    Choice stateChoice;
    
    
    Label currentScheduleLabel = new Label ("CURRENT SCHEDULE");
    Label classLabel;
    Label gradeLabel;
    Label honorsLabel;
    
    Label [] hourLabel;
    TextField [] classText;
    Choice [] gradeChoice;
    CheckboxGroup [] honorsBoxes;
    
    Button submitButton;
    
    
    Applicant stu;
    
    
    
    
    public void init ()
    {
        setLayout (new GridLayout (ROWS,COLUMNS));
        
        for (int down = 0; down < ROWS; down++)     //creates panels in layout
        {
            for (int across = 0; across < COLUMNS; across++)
            {
                panelList[down][across] = new Panel ();
                panelList[down][across].setLayout (new FlowLayout());
                //panelList[down][across].setBackground(Color.LIGHT_GRAY);
                add (panelList[down][across]);
                
            }
        }
        
        panelList[0][0].setBackground(Color.LIGHT_GRAY);        //Personal info section
        panelList[0][1].setBackground(Color.LIGHT_GRAY);
        panelList[0][2].setBackground(Color.LIGHT_GRAY);
        panelList[0][1].add(personalInfoLabel);
        
        panelList[1][0].setLayout(new FlowLayout(FlowLayout.LEFT)); //adds label and field for first name
        panelList[1][0].add(fnameLabel = new Label ("First name"));
        panelList[1][0].add(fnameText = new TextField (15));
        
        panelList[1][1].setLayout(new FlowLayout(FlowLayout.LEFT)); //adds label and field for middle name
        panelList[1][1].add(mnameLabel = new Label ("Middle name"));
        panelList[1][1].add(mnameText = new TextField (15));
        
        panelList[1][2].setLayout(new FlowLayout(FlowLayout.LEFT)); //adds label and field for last name
        panelList[1][2].add(lnameLabel = new Label ("Last name"));
        panelList[1][2].add(lnameText = new TextField (15));
        
        
        
        panelList[2][0].setLayout(new GridLayout(1, 2));    //subdivides into 2 panels
        Panel left = new Panel();
        Panel right = new Panel ();
        panelList[2][0].add (left);
        panelList[2][0].add (right);
        
        genderBoxes = new CheckboxGroup ();                 //adds gender options in left panel
        left.add(male = new Checkbox ("Male", genderBoxes, false));
        left.add(female = new Checkbox ("Female", genderBoxes, false));
        
        right.setLayout(new FlowLayout(FlowLayout.RIGHT));  //adds "birth date" label in right panel
        right.add(birthLabel = new Label ("Birth date"));
        
        panelList[2][1].setLayout(new FlowLayout(FlowLayout.LEFT)); //adds month selector
        monthSelect = createMonthChoice ();
        monthSelect.addItemListener (this);
        panelList[2][1].add(monthSelect);
        
        daySelect = new Choice ();
        daySelect.add("--");
        for (int x = 1; x <= 31; x++)       //defaults to 31 days
        {
                daySelect.add(new Integer(x).toString());
        }
        daySelect.setEnabled (false); //disabled until user enters month
        
        panelList[2][1].add(daySelect); //adds default 31 days choice box, but will (potentially) change once a month is selected
        
        yearLabel = new Label ("Year"); //adds label and textfield for year of birth
        yearText = new TextField (4);
        panelList[2][1].add(yearLabel);
        panelList[2][1].add(yearText);
        
        panelList[2][2].add(rankLabel = new Label ("H.S. class rank"));  //add labels and fields for class rank
        panelList[2][2].add(rankText = new TextField(3));
        panelList[2][2].add(classSizeLabel = new Label ("out of"));
        panelList[2][2].add(classSizeText = new TextField (3));
        
        
        
        panelList[3][0].setLayout (new FlowLayout(FlowLayout.LEFT));    //add label and field for GPA
        GPALabel = new Label ("GPA");
        GPAText = new TextField (3);
        panelList[3][0].add(GPALabel);
        panelList[3][0].add(GPAText);
        
        
        
        
        
        panelList[5][0].setBackground(Color.LIGHT_GRAY);        //School Info section
        panelList[5][1].setBackground(Color.LIGHT_GRAY);
        panelList[5][2].setBackground(Color.LIGHT_GRAY);
        panelList[5][1].add(schoolInfoLabel);
        
        panelList[6][0].setLayout(new FlowLayout(FlowLayout.LEFT)); //add label and field for HS name
        panelList[6][0].add(schoolNameLabel = new Label ("School"));
        panelList[6][0].add(schoolNameText = new TextField (20));
        
        stateLabel = new Label ("State");   //add label and Choice for state
        stateChoice = createStateChoice();
        panelList[6][1].add(stateLabel);
        panelList[6][1].add(stateChoice);
        
        panelList[6][1].add(cityLabel = new Label ("  City"));  //add label and text field for city
        panelList[6][1].add(cityText = new TextField (10));
        
        panelList[6][2].add(zipLabel = new Label ("Zip Code"));  //add label and text field for city
        panelList[6][2].add(zipText = new TextField (5));
        
        
        
        
        
        panelList[8][0].setBackground(Color.LIGHT_GRAY);        //current schedule section
        panelList[8][1].setBackground(Color.LIGHT_GRAY);
        panelList[8][2].setBackground(Color.LIGHT_GRAY);
        panelList[8][1].add(currentScheduleLabel);
        
        
        panelList[9][0].add(classLabel = new Label ("Class name")); //labels at the top
        panelList[9][1].add(classLabel = new Label ("Current grade"));
        panelList[9][2].add(classLabel = new Label ("Honors?"));
        
        classText = new TextField[NUMBEROFCLASSES];
        hourLabel = new Label [NUMBEROFCLASSES];
        for (int x = 0; x < classText.length; x++)                              //hour and class name in left panels
        {
            panelList[10+x][0].setLayout(new FlowLayout(FlowLayout.LEFT));
            panelList[10+x][0].add(hourLabel[x] = new Label ("" + (x+1)));
            panelList[10+x][0].add(classText[x] = new TextField(25));
        }
        
        gradeChoice = createGradeChoice();              //grade choice boxes in mid panels
        for (int x = 0; x < gradeChoice.length; x++)
        {
            panelList[10+x][1].add(gradeChoice[x]);
        }
        
        honorsBoxes = new CheckboxGroup [NUMBEROFCLASSES];      //honors boxes in right panels
        for (int x = 0; x < honorsBoxes.length; x++)
        {   
            honorsBoxes[x] = new CheckboxGroup();
            panelList[10+x][2].add(new Checkbox ("Yes", honorsBoxes[x], false));
            panelList[10+x][2].add(new Checkbox ("No", honorsBoxes[x], false));
        }
        
        submitButton = new Button ("Submit");   //adds submit button at the end
        submitButton.addActionListener(this);
        panelList[ROWS-1][1].add (submitButton);
        
        //setBackground (Color.LIGHT_GRAY);
        
        
    }
    
    public void itemStateChanged (ItemEvent e)  //performed when month is selected. sets days to active and changes # of days
    {
        String str = monthSelect.getSelectedItem();
        int currentDays = daySelect.getItemCount();
        
        if (str.equals("--"))
        {
            daySelect.select(0);
            daySelect.setEnabled(false);
        }
        
        
        if (str.equals("January") || str.equals("March") || str.equals("May") || str.equals("July") || str.equals("August") || str.equals("October") || str.equals("December"))
        {    
            //sets to 31 days
            if (currentDays == 31)    //30 days + the "--" option
            {
                daySelect.add("31");
            }
            if (currentDays == 30)   //29 days + the "--" option
            {
                daySelect.add("30");
                daySelect.add("31");
            }
            
            daySelect.setEnabled(true);
        }
        
        
        if (str.equals("April") || str.equals("June") || str.equals("September") || str.equals("November"))
        {    
            //sets to 30 days
            if (currentDays == 32)    //31 days + the "--" option
            {
                daySelect.remove("31");
            }
            if (currentDays == 30)   //29 days + the "--" option
            {
                daySelect.add("30");
            }
            
            daySelect.setEnabled(true);
        }
        
        if (str.equals("February"))
        {    
            //sets to 29 days
            if (currentDays == 32)    //31 days + the "--" option
            {
                daySelect.remove("31");
                daySelect.remove("30");
            }
            if (currentDays == 31)   //30 days + the "--" option
            {
                daySelect.remove("30");
            }
            
            daySelect.setEnabled(true);
        }
    }
    
    public void actionPerformed (ActionEvent e) //performed when submit button is clicked. verifies all input is valid data
    {
        for (int down = 0; down < ROWS; down++)     //restes panels to have white backgrounds
        {
            for (int across = 0; across < COLUMNS; across++)
            {
                panelList[down][across].setBackground(Color.WHITE);
                
            }
        }
        panelList[0][0].setBackground(Color.LIGHT_GRAY);        //Personal info section
        panelList[0][1].setBackground(Color.LIGHT_GRAY);
        panelList[0][2].setBackground(Color.LIGHT_GRAY);
        panelList[5][0].setBackground(Color.LIGHT_GRAY);        //School Info section
        panelList[5][1].setBackground(Color.LIGHT_GRAY);
        panelList[5][2].setBackground(Color.LIGHT_GRAY);
        panelList[8][0].setBackground(Color.LIGHT_GRAY);        //current schedule section
        panelList[8][1].setBackground(Color.LIGHT_GRAY);
        panelList[8][2].setBackground(Color.LIGHT_GRAY);  
        fnameLabel.setBackground(Color.WHITE);
        mnameLabel.setBackground(Color.WHITE);
        lnameLabel.setBackground(Color.WHITE);
        male.setBackground(Color.WHITE);
        female.setBackground(Color.WHITE);
        yearLabel.setBackground(Color.WHITE);
        rankLabel.setBackground(Color.WHITE);
        classSizeLabel.setBackground(Color.WHITE);
        GPALabel.setBackground(Color.WHITE);
        schoolNameLabel.setBackground(Color.WHITE);
        stateLabel.setBackground(Color.WHITE);
        cityLabel.setBackground(Color.WHITE);
        zipLabel.setBackground(Color.WHITE);
        for (int x = 0; x < NUMBEROFCLASSES; x++)
            hourLabel[x].setBackground(Color.WHITE);
          
            
            
            
        
        String str;
        
        str = fnameText.getText().trim();  //check first name
        if (str.length() < 1)   //checks if first name is entered
        {
                panelList[1][0].setBackground(Color.RED);
                fnameLabel.setBackground(Color.RED);
                fnameText.requestFocus();
                JOptionPane.showMessageDialog(null, "Enter first name");
                return;
        }
        for (int x = 0; x < str.length(); x++)  //checks to make sure first name contains valid characters
        {
            int type = Character.getType (str.charAt(x));
            if ((type != 1 && type != 2 && type != 20))
            {
                panelList[1][0].setBackground(Color.RED);
                fnameLabel.setBackground(Color.RED);
                fnameText.requestFocus();
                JOptionPane.showMessageDialog(null, "Name may only contain characters A-Z, a-z, and \"-\"");
                return;
            }
        }
        
        
        str = mnameText.getText().trim();  //check middle name
        if (str.length() < 1)   //checks if middle name is entered
        {
                panelList[1][1].setBackground(Color.RED);
                mnameLabel.setBackground(Color.RED);
                mnameText.requestFocus();
                JOptionPane.showMessageDialog(null, "Enter middle name");
                return;
        }
        for (int x = 0; x < str.length(); x++)  //checks to make sure middle name contains valid characters
        {
            int type = Character.getType (str.charAt(x));
            if ((type != 1 && type != 2 && type != 20))
            {
                panelList[1][1].setBackground(Color.RED);
                mnameLabel.setBackground(Color.RED);
                mnameText.requestFocus();
                JOptionPane.showMessageDialog(null, "Name may only contain characters A-Z, a-z, and \"-\"");
                return;
            }
        }
        
        
        str = lnameText.getText().trim();  //check last name
        if (str.length() < 1)   //checks if last name is entered
        {
                panelList[1][2].setBackground(Color.RED);
                lnameLabel.setBackground(Color.RED);
                lnameText.requestFocus();
                JOptionPane.showMessageDialog(null, "Enter last name");
                return;
        }
        for (int x = 0; x < str.length(); x++)  //checks to make sure last name contains valid characters
        {
            int type = Character.getType (str.charAt(x));
            if ((type != 1 && type != 2 && type != 20))
            {
                panelList[1][2].setBackground(Color.RED);
                lnameLabel.setBackground(Color.RED);
                lnameText.requestFocus();
                JOptionPane.showMessageDialog(null, "Name may only contain characters A-Z, a-z, and \"-\"");
                return;
            }
        }
        
        
        
        
        
        
        //check gender
        if (genderBoxes.getSelectedCheckbox() == null)
        {
            panelList[2][0].setBackground(Color.RED);
            male.setBackground(Color.RED);
            female.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "Select gender");
            return;
        }
        
        
        //check birth date
        if (monthSelect.getSelectedItem().equals("--") || daySelect.getSelectedItem().equals("--"))
        {
            panelList[2][1].setBackground(Color.RED);
            yearLabel.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "Enter birthday");
            return;
        }
        int year;
        str = yearText.getText().trim();
        for (int x = 0; x < str.length(); x++)  //checks to make sure year only contains numbers (And no negative signs)
        {
            int type = Character.getType (str.charAt(x));
            if (type != 9)
            {
                panelList[2][1].setBackground(Color.RED);
                yearLabel.setBackground(Color.RED);
                yearText.requestFocus();
                JOptionPane.showMessageDialog(null, "Invalid year");
                return;
            }
        }
        year = new Integer (str);
        if  (year < 1900 || year > 2012)
        {
            panelList[2][1].setBackground(Color.RED);
            yearLabel.setBackground(Color.RED);
            yearText.requestFocus();
            JOptionPane.showMessageDialog(null, "Year must be between 1900 and 2012");
            return;
        }
        
        
        
        //check class rank
        int cRank, cSize;
        str = rankText.getText().trim();
        for (int x = 0; x < str.length(); x++)  //checks to make sure rank only contains numbers (And no negative signs)
        {
            int type = Character.getType (str.charAt(x));
            if (type != 9)
            {
                panelList[2][2].setBackground(Color.RED);
                rankLabel.setBackground(Color.RED);
                classSizeLabel.setBackground(Color.RED);
                classSizeText.requestFocus();
                JOptionPane.showMessageDialog(null, "Class rank/size must be a positive number");
                return;
            }
        }
        cRank = new Integer (str);
        str = classSizeText.getText().trim();
        for (int x = 0; x < str.length(); x++)  //checks to make sure size only contains numbers (And no negative signs)
        {
            int type = Character.getType (str.charAt(x));
            if ((type != 9))
            {
                panelList[2][2].setBackground(Color.RED);
                rankLabel.setBackground(Color.RED);
                classSizeLabel.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null, "Class rank/size must be a positive number");
                return;
            }
        }
        cSize = new Integer (str);
        
        if (cRank < 1 || cSize < 1) //checks to make sure rank and size are not 0 or negative
        {
                panelList[2][2].setBackground(Color.RED);
                rankLabel.setBackground(Color.RED);
                classSizeLabel.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null, "Class rank and size must be positive numbers");
                return;
        }
        if (cRank > cSize) //checks to make sure rank is not greater than size
        {
                panelList[2][2].setBackground(Color.RED);
                rankLabel.setBackground(Color.RED);
                classSizeLabel.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null, "Class rank must not exceed class size");
                return;
        }
        
        
        
        //checks GPA
        str = GPAText.getText().trim(); 
        int counter = 0;
        for (int x = 0; x < str.length(); x++)  //checks to make sure size only contains numbers (And no negative signs)
        {
            int type = Character.getType (str.charAt(x));
            if (str.charAt(x) == '.')   //keeps track of # of decimals
                counter++;
            if ((type != 9 && type != 24 ) || (type==24 && str.charAt(x)!='.'))  //checks for non number or decimals
            {
                panelList[3][0].setBackground(Color.RED);
                GPALabel.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null, "GPA must be a positive number");
                return;
            }
        }
        if (counter != 1)   //makes sure there is exactly one decimal point
        {
            panelList[3][0].setBackground(Color.RED);
            GPALabel.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "GPA must include one decimal point");
            return;
        }
        double gpa = new Double (str);
        if (gpa < 0 || gpa > 5) //makes sure GPA is within 0-5 range
        {
            panelList[3][0].setBackground(Color.RED);
            GPALabel.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "GPA must be between 0.00 and 5.00 (note: 5.00 is only possible for weighted grading scales)");
            return;
        }
        
        
        
        
        str = schoolNameText.getText().trim();  //checks to make sure school name is entered
        if (str.length() < 1)
        {
            panelList[6][0].setBackground(Color.RED);
            schoolNameLabel.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "Enter school name");
            return;
        }
        
        str = stateChoice.getSelectedItem().trim();     //makes sure state is selected
        if (str.equals("--"))
        {
            panelList[6][1].setBackground(Color.RED);
            stateLabel.setBackground(Color.RED);
            cityLabel.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "Select state");
            return;
        }
        
        str = cityText.getText().trim();  //checks to make sure city name is entered
        if (str.length() < 1)
        {
            panelList[6][1].setBackground(Color.RED);
            stateLabel.setBackground(Color.RED);
            cityLabel.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "Enter city");
            return;
        }
        
        
        str = zipText.getText().trim(); //checks zip code
        if (str.length() < 1)
        {
            panelList[6][2].setBackground(Color.RED);
            zipLabel.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "Enter zip code");
            return;
        }
        for (int x = 0; x < str.length(); x++)  //checks to make sure size only contains numbers (And no negative signs)
        {
            int type = Character.getType (str.charAt(x));
            if ((type != 9))
            {
                panelList[6][2].setBackground(Color.RED);
                zipLabel.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null, "Zip code must be a number");
                return;
            }
        }
        if (str.length() != 5)
        {
            panelList[6][2].setBackground(Color.RED);
            zipLabel.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null, "Zip code must be 5 digits long");
            return;
        }
        
        
        
        
        //checks schedule
        boolean needsCompletion = false;
        for (int x = 0; x < NUMBEROFCLASSES; x++)
        {
            
            str = classText[x].getText().trim();
            if (str.length() < 1)
            {
                needsCompletion = true;
                hourLabel[x].setBackground(Color.RED);
                panelList[10+x][0].setBackground(Color.RED);
            }
            
            str = gradeChoice[x].getSelectedItem().trim();
            if (str.equals("--"))
            {
                needsCompletion = true;
                panelList[10+x][1].setBackground(Color.RED);
            }
            
            if (honorsBoxes[x].getSelectedCheckbox() == null)
            {
                needsCompletion = true;
                panelList[10+x][2].setBackground(Color.RED);
            }
        }
        if (needsCompletion)
        {
            JOptionPane.showMessageDialog(null, "Current schedule not completed");
            return;
        }
        
        
        
        
        /*******CODE REACHES THIS POINT IF ALL FIELDS ARE VALID**********/
        
        stu = new Applicant (fnameText.getText().trim(), mnameText.getText().trim(), lnameText.getText().trim());
        stu.setGender(genderBoxes.getSelectedCheckbox().getLabel().trim());
        stu.setBirthDate (CustomDate.monthValue(monthSelect.getSelectedItem()), new Integer(daySelect.getSelectedItem()), new Integer (yearText.getText().trim()));
        stu.setClassRank (new Integer (rankText.getText().trim()));
        stu.setClassSize (new Integer (classSizeText.getText().trim()));
        stu.setGPA (new Double (GPAText.getText().trim()));
        stu.setSchoolName(schoolNameText.getText().trim());
        stu.setStateName(stateChoice.getSelectedItem().trim());
        stu.setCityName(cityText.getText().trim());
        stu.setZip(new Integer(zipText.getText().trim()));
        for (int x = 0; x < 7; x++)     //set schedule
        {
            String temp1 = classText[x].getText().trim();
            char temp2 = new Character (gradeChoice[x].getSelectedItem().charAt(0));
            boolean temp3;
            
            if (honorsBoxes[x].getSelectedCheckbox().getLabel().equals("Yes"))
                temp3 = true;
            else
                temp3 = false;
                
            stu.setSchedule(new Class (temp1, temp2, temp3), x);
        }
        
        
        
        
        
        //write to a file
        /***See comment above writeToFile method for explanation as to why this chunk of code is commented out***/
        /*boolean w = writeToFile(stu.getFirstName() + "_" + stu.getLastName() + "_Application.txt");
        if (w)
            JOptionPane.showMessageDialog(null, "Information Submitted");
        else
            JOptionPane.showMessageDialog(null, "Failed to write file");
        */
       
       
        JOptionPane.showMessageDialog(null, "Information submitted");
    }
    
    public Choice createMonthChoice ()
    {
        Choice temp = new Choice ();
        temp.add ("--");
        temp.add ("January");
        temp.add ("February");
        temp.add ("March");
        temp.add ("April");
        temp.add ("May");
        temp.add ("June");
        temp.add ("July");
        temp.add ("August");
        temp.add ("September");
        temp.add ("October");
        temp.add ("November");
        temp.add ("December");
        
        return temp;
    }
    
    public Choice createStateChoice ()
    {
        Choice temp = new Choice ();
        temp.add("--");
        temp.add("AK");
        temp.add("AL");
        temp.add("AR");
        temp.add("AZ");
        temp.add("CA");
        temp.add("CO");
        temp.add("CT");
        temp.add("DE");
        temp.add("FL");
        temp.add("GA");
        temp.add("HI");
        temp.add("IA");
        temp.add("ID");
        temp.add("IL");
        temp.add("IN");
        temp.add("KS");
        temp.add("KY");
        temp.add("LA");
        temp.add("MA");
        temp.add("MD");
        temp.add("MH");
        temp.add("MI");
        temp.add("MN");
        temp.add("MO");
        temp.add("MS");
        temp.add("MT");
        temp.add("NC");
        temp.add("ND");
        temp.add("NE");
        temp.add("NH");
        temp.add("NJ");
        temp.add("NM");
        temp.add("NV");
        temp.add("NY");
        temp.add("OH");
        temp.add("OK");
        temp.add("OR");
        temp.add("PA");
        temp.add("RI");
        temp.add("SC");
        temp.add("SD");
        temp.add("TN");
        temp.add("TX");
        temp.add("UT");
        temp.add("VA");
        temp.add("VT");
        temp.add("WA");
        temp.add("WI");
        temp.add("WV");
        temp.add("WY");
        
        return temp;
    }
    
    public Choice [] createGradeChoice ()
    {
        Choice [] choiceList = new Choice [NUMBEROFCLASSES];
        
        for (int x = 0; x < choiceList.length; x++)
        {
            choiceList[x] = new Choice ();
            choiceList[x].add ("--");
            choiceList[x].add ("A");
            choiceList[x].add ("B");
            choiceList[x].add ("C");
            choiceList[x].add ("D");
            choiceList[x].add ("F");
        }
        
        return choiceList;
    }
    
    public void paint (Graphics g)
    {
    }
    /*public void start()
    {
    }*/
    
   
    public boolean writeToFile (String filename)
    {
        /*Internet browsers restrict the ability of applets to manipulate server files. After some research,
         * It looks like only "signed" applets can do so, after being granted permission by the user. The process
         * that the websites described for "signing" an applet looks a little bit beyond my skill level, and
         * also requires access to certain JDK tools which I don't (think) are accessible on these computers.
         * Nonetheless, this method describes how I WOULD write this information to a file if it were a GUI
         * application as opposed to an applet. It would be practical for whatever organization that was
         * hosting this applet on their site to be able to save the information that was entered into it by writing
         * the information to a text file on their own computers. 
        */
        try
        {
            FileWriter fw = new FileWriter (filename);
            fw.write (stu.getFirstName());
            fw.write (stu.getMiddleName());
            fw.write (stu.getLastName());
            fw.write (stu.getBirthDate().toString());
            fw.write (stu.getGender());
            fw.write (new Integer (stu.getClassRank()).toString());
            fw.write (new Integer (stu.getClassSize()).toString());
            fw.write (new Double (stu.getGPA()).toString());
            fw.write (stu.getSchoolName().replace(' ' , '_'));
            fw.write (stu.getStateName());
            fw.write (stu.getCityName().replace(' ' , '_'));
            fw.write (new Integer (stu.getZip()).toString());
            
            for (Class e: stu.getSchedule()) //writes schedule
            {  
                fw.write(e.name().replace(' ' , '_'));  //replaces spaces with underscores to allow file to be read in
                fw.write(e.grade());
                fw.write(new Boolean(e.honors()).toString());
            }
            
            fw.close();
            
            return true;
        }
            catch (IOException e)
        {
            panelList[9][0].setBackground(Color.GREEN);
            panelList[9][1].setBackground(Color.GREEN);
            panelList[9][2].setBackground(Color.GREEN);
        
        
            return false;
        }
    }
}
