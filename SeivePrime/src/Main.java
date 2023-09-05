import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(Arrays.toString(Eratosthenes(1000)));
    }

    public static int[] 
    Eratosthenes(int a)
    {
        boolean[] isComposite = new boolean[a+1];
        for (int i = 2; i*i <= a; i++)
        {
            if (isComposite[i] == false)
            {
                for (int j = i*i; j <= a; j += i)
                {
                    isComposite[j] = true;
                }
            }
        }       

        int[] primes = new int[a];
        int index = 0;
        int primesCount = 0;
        for (int i = 1; i < a; i++)
        {
            if (isComposite[i] == false)
            {
                primes[index] = i;
                index++;
                primesCount++;
            }
        }

        int[] primes2 = new int[primesCount];
        for (int i = 0 ; i < primesCount; i++)
        {
            primes2[i] = primes[i];
        }

        return primes2;
    }

}