package cn.com.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * 号码管理器
 */
public class NumberManager {
    private int lastNumber = 1;
    private List<Integer> queueNumber = new ArrayList<>();

    /**
     * 产生新号码
     * @return
     */
    public synchronized Integer generateNewNumber(){
        queueNumber.add(lastNumber);
        return lastNumber++;
    }

    /**
     * 获取服务号码
     * @return
     */
    public synchronized Integer fetchServiceNumber(){
        Integer number = null;
        if(queueNumber.size()>0){
          number =  queueNumber.remove(0);
        }
        return number;
    }
}
