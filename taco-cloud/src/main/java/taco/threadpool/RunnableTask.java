package taco.threadpool;

/**
 * @Author: xiaguangyong
 * @ClassName: RunnableTask
 * @Description:
 * @Date: 2021/2/7 9:34
 * @Version: 1.0
 * @Modify:
 */
public class RunnableTask implements Runnable {

    private String name;

    public RunnableTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        name = "RunnableTask";
        System.out.println("RunnableTask已执行");
    }
}
