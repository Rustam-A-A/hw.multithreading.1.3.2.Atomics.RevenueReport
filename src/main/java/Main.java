import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //формирование массивов данных продаж трех магазинов
        Revenue shop1 = new Revenue(4, 100);
        int[] report1 = shop1.formShopReport();
        Revenue shop2 = new Revenue(3, 80);
        int[] report2 = shop2.formShopReport();
        Revenue shop3 = new Revenue(1, 210);
        int[] report3 = shop3.formShopReport();

        //получение величины итоговой выручки по трем магазинам
        LongAdder revenueTotalResult = new LongAdder();

        formReport(report1, revenueTotalResult);
        formReport(report2, revenueTotalResult);
        formReport(report3, revenueTotalResult);

        System.out.println("\nTotal Result " + revenueTotalResult.sum() + "\n");
    }

    public static void formReport(int[] report, LongAdder stat) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IntStream.range(0, report.length)
                .forEach(i -> executorService.submit(() -> stat.add(report[i])));
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdown();
    }

}
