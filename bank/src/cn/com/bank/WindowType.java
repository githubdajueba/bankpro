package cn.com.bank;

public enum WindowType {
    COMMON,EXPRESS,VIP;

    /**
     *  name() 是ENUM的方法,返回的就是枚举类型的名字
     *  this指枚举这个当前类
     *  switch(..) 中并不是可以是任意类型,long不行
     *  * @return
     */
    @Override
    public String toString() {
        switch (this){
            case COMMON : return "普通";
            case EXPRESS: return "快速";
            case VIP: return name(); // VIP
        }
        return null;
    }
}
