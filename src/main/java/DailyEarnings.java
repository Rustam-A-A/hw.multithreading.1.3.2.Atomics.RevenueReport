public class DailyEarnings {
    Revenue revenue;

    public static int getTotal(int[] revenue){
        int sum = 0;
        for (int i = 0; i < revenue.length; i++){
            sum = sum + revenue[i];
        }
        return sum;
    }
}
