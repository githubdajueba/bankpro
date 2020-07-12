package cn.com.bank;

/**
 *  取号机
 */
public class NumberMachine {
   // 普通号
    private NumberManager commonManager = new NumberManager();
   // 快速号
    private NumberManager expressManager = new NumberManager();
   // VIP号
    private NumberManager vipManager = new NumberManager();

    public NumberManager getCommonManager() {
        return commonManager;
    }

    public NumberManager getExpressManager() {
        return expressManager;
    }

    public NumberManager getVipManager() {
        return vipManager;
    }
    // 私有的构造方法,不允许外界创建对象
    private NumberMachine (){}
    // 用这个方法创建实例,且为单例
    public static NumberMachine getInstance(){
        return instance;
    }
    public static NumberMachine instance = new NumberMachine();

}
