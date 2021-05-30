import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Revenue shop1 = new Revenue(4, 100);
        Revenue shop2 = new Revenue(3, 80);
        Revenue shop3 = new Revenue(1, 210);

        int[] revenue1 = shop1.formShopReport();
        int[] revenue2 = shop2.formShopReport();
        int[] revenue3 = shop3.formShopReport();

        LongAdder stat = new LongAdder();

        formReport(revenue1, stat);
        formReport(revenue2, stat);
        formReport(revenue3, stat);

        System.out.println("\nTotal Result " + stat.sum() + "\n");

        //alternative result checking
        checkShopReport (revenue1);
        checkShopReport (revenue2);
        checkShopReport (revenue3);
    }

    public static void formReport(int[] revenue, LongAdder stat) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IntStream.range(0, revenue.length)
                .forEach(i -> executorService.submit(() -> stat.add(revenue[i])));
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    //checking if the result makes sense
    public static void checkShopReport (int[] revenue){
        for (int i = 0; i < revenue.length; i++){
            System.out.println(revenue[i]);
        }
        System.out.println(DailyEarnings.getTotal(revenue) + "\n");
    }


}
