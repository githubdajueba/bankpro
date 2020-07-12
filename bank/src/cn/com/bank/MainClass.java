package cn.com.bank;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {
        //ServiceWindow window = null;
        for(int i= 1;i<5;i++){
            ServiceWindow commonWindow = new ServiceWindow();
            commonWindow.setWindowId(i);
            commonWindow.start();
        }
      ServiceWindow expressWindow =   new ServiceWindow();
        expressWindow.setType(WindowType.EXPRESS);
        expressWindow.start();

        ServiceWindow vipWindow = new ServiceWindow();
        vipWindow.setType(WindowType.VIP);
        vipWindow.start();

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                      // 为确保cpu不会跑掉 可以再加一层锁,但应防止出现死锁
                        Integer number = NumberMachine.getInstance().getCommonManager().generateNewNumber();
                        System.out.println(number+"号普通客户等待服务");
                    }
                },
                0,
                1,
                TimeUnit.SECONDS //
        );
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        Integer number = NumberMachine.getInstance().getExpressManager().generateNewNumber();
                        System.out.println(number+"号快速客户等待服务");
                    }
                },
                0, //从何时开始执行
                // 间隔多长时间执行一次
                Constants.COMMON_CUSTOMER_INTERVAL_TIME*2,
                TimeUnit.SECONDS // 时间单位
        );
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        Integer number = NumberMachine.getInstance().getVipManager().generateNewNumber();
                        System.out.println(number+"号VIP客户等待服务");
                    }
                },
                0,
                Constants.COMMON_CUSTOMER_INTERVAL_TIME*6,
                TimeUnit.SECONDS //
        );
    }
}
