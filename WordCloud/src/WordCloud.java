import java.io.*;
import java.util.*;

import static java.lang.System.load;

public class WordCloud {
    private ArrayList<Word> words;
    private ArrayList<Word> topHits;
    private int totalWords;
    private int uniqueWords;

    public WordCloud(String fileName) throws IOException
    {
        words = new ArrayList<Word>();
        topHits = new ArrayList<Word>();
        totalWords = 0;
        uniqueWords = 0;
        load(fileName);
    }
    private int getIndex(String str)
    {
        for (int i = 0; i <= words.size() - 1; i++)
        {

            if (((words.get(i)).getWord()).equals(str))
            {


                return i;
            }

        }


        return -1;
    }
    private void load(String fileName) throws IOException
    {


        File file = new File(fileName);


        Scanner console = new Scanner(file);

        while (console.hasNext())
        {


            String n = console.next();


            if (n.length() > 0 && !Character.isLetterOrDigit(n.charAt(0)))
            {


                n = n.substring(1);

            }

            if (n.length() > 0 && !Character.isLetterOrDigit(n.charAt(n.length() - 1)))
            {



                n = n.substring(0, n.length() - 1);

            }

            n = n.toLowerCase();


            if (n.length() != 0)
            {
                int d = getIndex(n);
                if (d == -1)
                {


                    words.add(new Word(n));

                    uniqueWords++;
                }
                else
                {

                    words.get(d).increment();
                }


                totalWords++;
            }
        }


        System.out.println(words);
        findTopHits();
    }


    private void findTopHits()
    {
        for (int i = 0; i <= 29; i++)
        {

            if (words.size() == 0)
            {

                break;
            }




            int maxCount = words.get(0).getCount();
            int maxIndex = 0;

            for (int j = 1; j < words.size(); j++)
            {

                Word currentWord = words.get(j);
                if (currentWord.getCount() > maxCount)
                {


                    maxCount = currentWord.getCount();
                    maxIndex = j;
                }
            }

            topHits.add(words.get(maxIndex));
            words.remove(maxIndex);
        }
    }


    public void printInfo()
    {
        System.out.println("Number of unique words >>> " + uniqueWords);
        System.out.println("Total # of words >>> " + totalWords);
        System.out.print("\n\n");
        System.out.println("\t\t" + "Word" + "\t\t" + "Frequency");
        for (int i = 1; i <= 30; i++)
        {
            System.out.println(i + ")\t\t" + topHits.get(i - 1).getWord() + "\t\t" + topHits.get(i - 1).getCount());
        }
    }

    public ArrayList<Word> getTopHits() {
        return topHits;

    }
}
