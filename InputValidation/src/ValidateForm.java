import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

/**
 * A class that validates user input, used as part of an input form
 */
public class ValidateForm
{
    public ValidateForm() {
        //no variables to initialize
    }

    boolean isAllAlpha(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if (!Character.isLetter(ch))
            {
                return false;
            }
        }
        return true;
    }

    static boolean isNumeric(String str)
    {
        int dotCount = 0;
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (!Character.isDigit(c))
            {
                if (c == '.')
                {
                    dotCount++;
                    if (dotCount > 1)
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
        }
        return true;
    }

    boolean validFormat(String type, String text)
    {
        if (type.equals("NAME")) 
        {
            if (text.length() < 2)
            {
                return false;
            }
            else
            {
                for (int i = 0; i < text.length(); i++)
                {
                    if (!Character.isLetter(text.charAt(i)))
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        else if (type.equals("PWD"))
        {
            
        }
        return false;
    }



    /** validate the user's entered name */
    public String checkName(String name) {
        if (validFormat("NAME", name))
        {
            return "";
        }
        else
        {
            return "Invalid Name!\n";
        }
        
    }

    /** validate the user's entered email */
    public String checkEmail(String email) {
        int atindex = -1;
        for (int i = 0; i < email.length(); i++) 
        {
            if (email.charAt(i) == '@') 
            {
                atindex = i;
                break;
            }
        }
        if (atindex == -1) 
        {
            return "Invalid Email\n"; 
        }

        int numCharAfterAt = 0;
        int dotindex = -1;
        for (int f = atindex+1; f < email.length(); f++) 
        {
            if (Character.isLetter(email.charAt(f))) 
            {
                numCharAfterAt++;
                continue;
            }
            if (email.charAt(f) == '.') 
            {
                dotindex = f;
                break;
            }
        }
        if (dotindex == -1) 
        {
            return "Invalid Email\n"; 
        }

        int numCharAfterDot = 0;
        for (int j = dotindex+1; j < email.length(); j++) 
        {
            if (Character.isLetter(email.charAt(j))) 
            {
                numCharAfterDot++;
            }
        }

        if (dotindex < atindex) 
        {
            return "Invalid Email\n";
        } 
        else 
        {
            if (numCharAfterAt >= 1 && numCharAfterDot >= 2) {
                return "\n";
            } else {
                return "Invalid Email\n"; 
            }
        }
    }

    /** validate the user's enter password */
    public String checkPW(String pw) {
        int uppercount, lowercount, numcount; 
            uppercount = 0; lowercount = 0; numcount = 0;
            if (pw.length() >= 4)
            {
                for (int i = 0; i < pw.length(); i++)
                {
                    if (Character.isLowerCase(pw.charAt(i)))
                    {
                        lowercount++;
                    }
                    else if (Character.isUpperCase(pw.charAt(i)))
                    {
                        uppercount++;
                    }
                    else if (Character.isDigit(pw.charAt(i)))
                    {
                        numcount++;
                    }
                }
                if (uppercount >= 1 && lowercount >= 1 && numcount >= 1)
                {
                    System.out.println(uppercount + " " + lowercount + " " + numcount);
                    return "\n";
                }
            }
            return "Invalid Password\n";
    }

    /** validate the user's entered zipcode */
    public String checkZip(String zip) {
        if (zip.length() <= 5 && zip.length() >=3)
        {
            return "";
        }
        else
        {
            return "Invalid Zip";
        }
    }

    /** validate the user's entered birth year */
    public String checkBirth(String date) {
        int year = 0;
        try {
            year =  Integer.parseInt(date);
        }
        catch (NumberFormatException ex)
        {

        }

        if (Calendar.getInstance().get(Calendar.YEAR) == year || (Calendar.getInstance().get(Calendar.YEAR) - year > 70) ||  (year - Calendar.getInstance().get(Calendar.YEAR)  > 0) ) {
            return "\nInvalid Date";
        } 
        else 
        {
            return "";
        }
        
    }

    /** validate the user's entered phone number */
    public String checkPhone(String phone) {
    String phoneWithoutDashes = "";
        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i) != '-') {
                phoneWithoutDashes += phone.charAt(i);
            }
        }
        if (isNumeric(phoneWithoutDashes) && phoneWithoutDashes.length() == 10)
        {
            return "";
        }
        else
        {
            return "Invalid phone number";
        }
    }

    /** main method for testing / setting up the GUI */
    public static void main(String[] args)
    {
        /*
         * you can add other method calls here for testing
         */

        //set up the GUI, you don't need to understand this code
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //the frame is the GUI, it uses a ValidateForm object
                TextComponentFrame frame = new TextComponentFrame(new ValidateForm());
                frame.setVisible(true);
            }
        });
    }
}










/**
 * A frame (GUI) with simple text components to simulate a web form
 */
class TextComponentFrame extends JFrame
{
    static final int DEFAULT_WIDTH  = 300;
    static final int DEFAULT_HEIGHT = 400;

    ValidateForm validater;

    public TextComponentFrame(ValidateForm v)
    {
        validater = v;

        initGUI();

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null); //center on screen
    }

    /** initialize the GUI components, e.g. buttons and text fields */
    private void initGUI()
    {
        setTitle("Subscription Form");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        final JTextField personName = new JTextField();
        final JTextField emailField = new JTextField();
        final JTextField zipCode    = new JTextField();
        final JTextField birthDate  = new JTextField();

        MaskFormatter mfor = null;
        try {
            mfor = new MaskFormatter("###-###-####"); //for the phone number field
        }
        catch (ParseException e) {}

        final JFormattedTextField phoneNumber   = new JFormattedTextField(mfor);
        final JPasswordField      passwordField = new JPasswordField();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(6, 6)); //dimensions of layout
        northPanel.add(new JLabel("Name :  ", SwingConstants.RIGHT));
        northPanel.add(personName);
        northPanel.add(new JLabel("Password :  ", SwingConstants.RIGHT));
        northPanel.add(passwordField);
        northPanel.add(new JLabel("Email : ", SwingConstants.RIGHT));
        northPanel.add(emailField);
        northPanel.add(new JLabel("Zip Code (US) : ", SwingConstants.RIGHT));
        northPanel.add(zipCode);
        northPanel.add(new JLabel("Year of Birth: ", SwingConstants.RIGHT));
        northPanel.add(birthDate);
        northPanel.add(new JLabel("Phone Number: ", SwingConstants.RIGHT));
        northPanel.add(phoneNumber);

        add(northPanel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(8, 40);
        JScrollPane scrollPane   = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        // add button to listen for events, append text into the text area

        JPanel  southPanel   = new JPanel();
        JButton submitButton = new JButton("Submit");
        southPanel.add(submitButton);
        submitButton.addActionListener(new ActionListener() //make anonymous action listener
        {
            /** this method is called in response to an event, in this case the "Submit" button pressed */
            public void actionPerformed(ActionEvent event)
            {
                String name     = personName.getText() ;
                String email    = emailField.getText();
                String zip      = zipCode.getText();
                String birth    = birthDate.getText();
                String phone    = phoneNumber.getText();
                String password = new String(passwordField.getPassword());

                String result = "";

                result += validater.checkName(name);
                result += validater.checkPW(password);
                result += validater.checkEmail(email);
                result += validater.checkZip(zip);
                result += validater.checkBirth(birth);
                result += validater.checkPhone(phone);



                if (result.length() == 0)
                    result = "Input accepted!";

                textArea.setText(result);
            }
        });

        add(southPanel, BorderLayout.SOUTH);
    }
}