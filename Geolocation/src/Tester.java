import java.util.*;
public class Tester {
    public static void main(String[] args) {
        //3
        System.out.println("Hello again, world!");

        //4
        // this is a comment!

        //5
        int numApples = 5;

        //6
        final int PRICE_OF_APPLES = 1;

        //7
        System.out.println("The total for " + PRICE_OF_APPLES + " apples: \n" +
                numApples * PRICE_OF_APPLES + "cents");

        //8
        if (numApples * PRICE_OF_APPLES > 2000)
        {
            System.out.println("Thank you valued customer!");
        }

        //9
        for (int i=10; i > 0; i = i - 1)
        {
            System.out.print(i);
            System.out.print(" ");
        }

        //10
        System.out.println();
        for (int i = 150; i < 301; i++)
        {
            if (i % 3 == 0)
            {
                System.out.print (i);
                System.out.print (" ");
            }
        }

        //11
        System.out.println();
        int allNatNums = 0;
        for (int i = 0; i < 101; i++)
        {
            allNatNums = allNatNums + i;
        }
        System.out.print(allNatNums);

        //13
        Scanner console = new Scanner(System.in);
        System.out.println();
        //14
        System.out.println("Enter a number:");
        double num = console.nextDouble();

        //15
        System.out.println(Math.sqrt(num));

        //16
        System.out.println(Math.pow(num, 3));
        //17
        int num1, num2;
        System.out.print("Enter num1: ");
        num1 = console.nextInt();
        System.out.print("Enter num2: ");
        num2 = console.nextInt();

        if (Math.abs(num1 - num2) <= 10) {
            System.out.println("Within ten");
        } else {
            System.out.println("Not within ten");
        }


        //18
        int whileLoopThingy = 0;
        int currentNum = 1;
        do {
            System.out.print("Enter a number: ");

            currentNum = console.nextInt();
            whileLoopThingy += currentNum;
        } while (currentNum != 0);
        System.out.println("sum: " + whileLoopThingy);

        //19
        double[] areas = new double[20];

        //20
        areas[0] = 4.56;

        //21
        int length = areas.length;

        //22
        boolean[] eel = {true, true, false, false, true};



        //24
        simpleMethod();
        //25
        sum(1, 1);
        //26
        sumToN(14);
        //27
        triangle(7);
        //28
        System.out.println(altCaps("heY there?"));

        //31:
        Player player1 = new Player();
        System.out.println(player1.playerInfo());

        Player player2 = new Player("Pessi", -1);
        System.out.println(player2.playerInfo());


        GeoLocation adminBuildingCoords = new GeoLocation(33.123961, -96.798735);
        Place adminBuilding = new Place("Frisco ISD Admin Building", "5515 Ohio Dr, Frisco, TX 75035", adminBuildingCoords);

        System.out.println(adminBuilding.toString());

        Place school = new Place("Liberty High School", "15250 Rolater Rd, Frisco, TX 75035", 33.139268, -96.742567);
        System.out.println(school.toString());


        System.out.println(adminBuilding.distanceTo(school));


    }
    //24
    public static void simpleMethod()
    {
        System.out.println("This is a method");
    }
    //25
    public static int sum(int a, int b)
    {
        return a + b;

    }

    //26
    public static int sumToN(int n)
    {
        int finalSum = 0;
        for (int i = 1; i <= n; i++)
        {
            if (i % 5 == 0 || i % 3 == 0)
            {
                finalSum = finalSum + i;
            }
        }
        return finalSum;
    }

    //27
    public static void triangle (int n)
    {
        for (int i = 1; i <= n; i ++ )
        {
            for (int f = 1; f <= i; f++)
            {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    //28
    static String altCaps (String s)
    {
        char eels[] = s.toCharArray();
        int realIndex = 0;
        for (int i = 0; i < s.length() ; i++)
        {


            if (Character.isLetter(s.charAt(i)))
            {
                realIndex++;
                if (realIndex % 2 == 1) {
                    eels[i] = Character.toUpperCase(eels[i]);
                }
                else
                {
                    eels[i] = Character.toLowerCase(eels[i]);
                }
            }


        }
        String string = new String(eels);
        return string;

    }





}