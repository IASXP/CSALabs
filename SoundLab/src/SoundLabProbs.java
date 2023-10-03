public class SoundLabProbs {
    public void Triangle(int n)
    {

        for (int i = 1; i <= n; i++)
        {

            for (int count = 1; count <= i; count++)
            {

                System.out.print(i);
            }

            System.out.println();
        }
    }

    public int lastIndexOf(int[] nums, int value)
    {
        for (int index = nums.length - 1; index >= 0; index--)
        {

            if (nums[index] == value)
            {

                return index;
            }
        }
        return -1;
    }



    public int Range(int[] array)
    {
        if (array.length == 0) return 0;
        int minValue = array[0];
        int maxValue = array[0];

        for (int num = 0; num < array.length; num++)
        {
            if (num < minValue) minValue = num;
            if (num > maxValue) maxValue = num;
        }
        return maxValue - minValue;
    }
    public int minDifference(int[] nums)
    {
        if (nums.length < 2) return -1;

        int minDiff = Math.abs(nums[0] - nums[1]);

        for (int index = 1; index < nums.length - 1; index++)
        {

            int diff = Math.abs(nums[index] -  nums[index + 1]);
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }

    public int priceIsRight(int[] bids, int price) {
        int currentWinner = -1;
        for (int i = 0; i < bids.length; i++)
        {
            if (bids[i] < price)
            {
                if (currentWinner == -1)
                {
                    currentWinner = bids[i];
                } else
                {
                    if (Math.abs(price - bids[i]) < Math.abs(price - currentWinner))
                    {
                        currentWinner = bids[i];
                    }
                }
            }
        }
        return currentWinner;
    }
    public int[] productExceptSelf(int[] nums)
    {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
        {
            result[i] = 1;
        }
        for (int i = 0; i < nums.length; i++)
        {
            for (int e = 0; e < nums.length; e++)
            {
                if (e != i)
                {
                    result[i] *= nums[e];
                }
            }
        }
        return result;
    }









}
