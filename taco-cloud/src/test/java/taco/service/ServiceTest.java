package taco.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import taco.controller.HomeController;
import taco.threadpool.CallableTask;
import taco.threadpool.RunnableTask;

import java.util.concurrent.*;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class ServiceTest {

    @Test
    public void testThreadPool() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 10, 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(20),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("call sync");
        threadPool.execute(new RunnableTask("sync"));
        System.out.println("sync:" + System.currentTimeMillis());
        System.out.println("call sync end");

        System.out.println("call async");
        String str = "single";//"async";
        Future<String> future = threadPool.submit(new CallableTask("async"));
        try {
            //等待子线程
            if (str=="single"){
                future.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("async:" + System.currentTimeMillis());
        System.out.println("call async end");
    }

    private void sync(ThreadPoolExecutor threadPool) {
        threadPool.execute(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("sync running");
            } catch (Exception e) {
                e.getStackTrace();
            }
        });
        System.out.println("sync:" + System.currentTimeMillis());
    }

    private void async(ThreadPoolExecutor threadPool) {
        threadPool.submit(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("async running");
            } catch (Exception e) {
                e.getStackTrace();
            }
        });
        System.out.println("async:" + System.currentTimeMillis());
    }
}
