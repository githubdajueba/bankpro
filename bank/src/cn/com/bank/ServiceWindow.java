package cn.com.bank;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *  服务窗口:
 *    叫号
*/
public class ServiceWindow {
    // 设置默认值
    private  int windowId = 1;
    private  WindowType type = WindowType.COMMON;

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    public void setType(WindowType type) {
        this.type = type;
    }
    public void start(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //NumberMachine.getInstance().getCommonManager().fetchServiceNumber();
                    switch (type){
                        case COMMON:
                            commonService();
                            break;
                        case EXPRESS:
                            expressService();
                            break;
                        case VIP:
                            vipService();
                            break;
                    }
                }
            }
        });
    }

    private void commonService() {
        String windowName = "第"+windowId+"号"+type+"窗口";
       // System.out.println(windowName+"正在获取任务");
        // 此行有锁
        Integer number = NumberMachine.getInstance().getCommonManager().fetchServiceNumber();
        System.out.println(windowName+"正在获取任务");

        if(number != null){
            System.out.println(windowName+"为第"+number+"个"+"普通"+"客户服务");

            long beginTime = System.currentTimeMillis();
        int maxRand = Constants.MAX_SERVER_TIME - Constants.MIN_SERVER_TIME;
        long serverTime = new Random().nextInt(maxRand) + 1 + Constants.MIN_SERVER_TIME;

        try {
        Thread.sleep(serverTime);
        } catch (Exception e){
            e.printStackTrace();
        }
        long costTime = System.currentTimeMillis() - beginTime;
        System.out.println(windowName+"为第"+number+"个"+"普通"+"客户完成服务,耗时"+costTime/1000+"秒");
        } else {
            System.out.println(windowName+"没有获取任务,休息一下吧");
            try {
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *  一下两种方法也可以作为上面这个方法的子类
     *  将两处不同的地方抽象出来两个方法,子类将这两个方法覆盖即可
     */
    private void expressService() {
        String windowName = "第"+windowId+"号"+type+"窗口";
       // System.out.println(windowName+"正在获取任务");

        Integer number = NumberMachine.getInstance().getExpressManager().fetchServiceNumber();
         System.out.println(windowName+"正在获取任务");
        if(number != null){
            System.out.println(windowName+"为第"+number+"个"+type+"客户服务");

            long beginTime = System.currentTimeMillis();
        //int maxRand = Constants.MAX_SERVER_TIME - Constants.MIN_SERVER_TIME;
        //long serverTime = new Random().nextInt(maxRand) + 1 + Constants.MIN_SERVER_TIME;

        try {
        Thread.sleep(Constants.MIN_SERVER_TIME);
        } catch (Exception e){
            e.printStackTrace();
        }
        long costTime = System.currentTimeMillis() - beginTime;
        System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务,耗时"+costTime/1000+"秒");
        } else {

            System.out.println(windowName+"没有获取任务");
            commonService();
        }
    }
    private void vipService() {
        String windowName = "第"+windowId+"号"+type+"窗口";
       // System.out.println(windowName+"正在获取任务");

        Integer number = NumberMachine.getInstance().getVipManager().fetchServiceNumber();
         System.out.println(windowName+"正在获取任务");
        if(number != null){
            System.out.println(windowName+"为第"+number+"个"+type+"客户服务");

            long beginTime = System.currentTimeMillis();
        int maxRand = Constants.MAX_SERVER_TIME - Constants.MIN_SERVER_TIME;
        long serverTime = new Random().nextInt(maxRand) + 1 + Constants.MIN_SERVER_TIME;

        try {
        Thread.sleep(serverTime);
        } catch (Exception e){
            e.printStackTrace();
        }
        long costTime = System.currentTimeMillis() - beginTime;
        System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务,耗时"+costTime/1000+"秒");
        } else {
            System.out.println(windowName+"没有获取任务");
           commonService();
        }
    }
}
