package taco.threadpool;

import java.util.concurrent.Callable;

/**
 * @Author: xiaguangyong
 * @ClassName: CallableTask
 * @Description:
 * @Date: 2021/2/7 9:32
 * @Version: 1.0
 * @Modify:
 */
public class CallableTask implements Callable<String> {

    private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        name = "CallableTask";
        System.out.println("CallableTask已执行");
        return name;
    }
}
