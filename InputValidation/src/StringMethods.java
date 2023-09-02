import java.util.Scanner;

public class StringMethods {
    public void
    unusualHello(String name)
    {
        System.out.println("Hello " + name + ", you dummy!");
    }
    public void
    stringRipper(String str)
    {
        String newString = str.substring(0, 1) + str.substring(str.length()-1);
        System.out.println(newString);
    }
    public boolean
    evenFooBar(String s)
    {
        int foocount = 0;
        int barcount = 0;
        for (int i = 0; i < s.length() - 2; i++)
        {
            if (s.substring(i, i + 3).equals("foo"))
            {
                foocount++;
            }
            if (s.substring(i, i + 3).equals("bar"))
            {
                barcount++;
            }
        }
        if (foocount == barcount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public int
    sumString(String str)
    {
        int total = 0;
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if (Character.isDigit(ch))
            {
                total += Integer.parseInt(String.valueOf(ch));
            }
        }
        return total;
    }
    public String
    decode(String key, String code) {
        String result = "";
        Scanner someVar = new Scanner(code);
        while (someVar.hasNextInt()) {
            int index = someVar.nextInt();
            if (index >= 0 && index < key.length()) {
                result += key.charAt(index);
            }
        }
        return result;
    }


}