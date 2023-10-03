import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {
        SoundLabProbs soundLab = new SoundLabProbs();

        soundLab.Triangle(5);
        System.out.println();

        int[] array1 = {8, 2, 4, 6, 8};
        System.out.println("Last index of 3 in array1 >>> " + soundLab.lastIndexOf(array1, 8));
        System.out.println();

        int[] array2 =  {15, 22, 8, 19, 31};
        System.out.println("Range of array2 >>> " + soundLab.Range(array2));
        System.out.println();

        int[] array3 = {4, 8, 6, 1, 5, 9, 4};
        System.out.println("Minimum difference in array3 >>> " + soundLab.minDifference(array3));
        System.out.println();

        System.out.println(  "price is right >>> " + soundLab.priceIsRight(new int[] {900, 885, 990, 1}, 800));
        System.out.println();


        System.out.println("product except self >>> " + Arrays.toString(soundLab.productExceptSelf(new int[]{1, 2, 3, 4})));

    }


}
