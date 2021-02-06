import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyThreadPool {

    public boolean count(int[] arr){
        try {
            ExecutorService threadPool = Executors.newFixedThreadPool(arr.length);
            List<Callable<Boolean>> tasks = new ArrayList<>();
            for (int value : arr)
                tasks.add(new IsNotSimple(value));
            List<Future<Boolean>> listResult = threadPool.invokeAll(tasks);
            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS))
                threadPool.shutdownNow();

            for (int i = 0; i < tasks.size(); i++)
                if (listResult.get(i).get()) return true;
            return false;
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e1) {
            System.out.println("mm");
        }
        return false;
    }
}