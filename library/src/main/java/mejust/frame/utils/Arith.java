package mejust.frame.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by tao on 2016/4/14.
 * 算数工具
 */
public class Arith {
    /**
     * 截取两位小数,多余的小数直接舍弃
     * @param bigDecimal
     * @return
     */
    public static double scale(BigDecimal bigDecimal){
        return bigDecimal.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }
    /**
     * 取最大值
     * @param lgs
     * @return 返回long中最大的值
     */
    public static float max(long... lgs){
        long max = lgs[0];
        for(long l:lgs){
            if(max>l){
                max=l;
            }
        }
        return max;
    }
    /**
     * 取最大值
     * @param flts
     * @return
     */
    public static float max(float... flts){
        float max = flts[0];
        for(float f:flts){
            if(max>f){
                max=f;
            }
        }
        return max;
    }
    /**
     * 取最大值
     * @param ints
     * @return
     */
    public static int max(int... ints){
        int max = ints[0];
        for(int i:ints){
            if(max>i){
                max=i;
            }
        }
        return max;
    }
    /**
     * 取最大值
     * @param dbs
     * @return
     */
    public static double max(double... dbs){
        double max = dbs[0];
        for(double d:dbs){
            if(max>d){
                max=d;
            }
        }
        return max;
    }
    /**
     * 取最大值
     * @param objs (double,long,int,float,String-必须是数字字符串)
     * @return
     */
    public static BigDecimal max(Object... objs){
        BigDecimal max = bigDecimal(objs[0]);
        for(Object obj:objs){
            BigDecimal bigDecimal = bigDecimal(obj);
            if(max.compareTo(bigDecimal)==1){
                max=bigDecimal;
            }
        }
        return max;
    }
    /**
     * 取最小值
     * @param lgs
     * @return
     */
    public static float min(long... lgs){
        long min = lgs[0];
        for(long l:lgs){
            if(min<l){
                min=l;
            }
        }
        return min;
    }
    /**
     * 取最小值
     * @param flts
     * @return 最小值
     */
    public static float min(float... flts){
        float min = flts[0];
        for(float f:flts){
            if(min<f){
                min=f;
            }
        }
        return min;
    }
    /**
     * 取最小值
     * @param ints
     * @return
     */
    public static int min(int... ints){
        int min = ints[0];
        for(int i:ints){
            if(min<i){
                min=i;
            }
        }
        return min;
    }
    /**
     * 取最小值
     * @param dbs
     * @return
     */
    public static double min(double... dbs){
        double min = dbs[0];
        for(double d:dbs){
            if(min<d){
                min=d;
            }
        }
        return min;
    }
    /**
     * 取最小值
     * @param objs
     * @return
     */
    public static BigDecimal min(Object... objs){
        BigDecimal min = bigDecimal(objs[0]);
        for(Object obj:objs){
            BigDecimal bigDecimal = bigDecimal(obj);
            if(min.compareTo(bigDecimal)==-1){
                min=bigDecimal;
            }
        }
        return min;
    }
    /**
     * 数值之间相除法
     * @param divisor 除数
     * @param objs 被除数
     * @return
     */
    public static BigDecimal divide(Object divisor, Object... objs){
        BigDecimal bigDecimal = bigDecimal(divisor);
        for(Object obj:objs){
            bigDecimal = bigDecimal.divide(bigDecimal(obj),20, BigDecimal.ROUND_DOWN);
        }
        return bigDecimal;
    }
    /**
     * 数值之间相乘
     * @param objs
     * @return
     */
    public static BigDecimal multiply(Object... objs){
        BigDecimal bigDecimal = new BigDecimal(1);
        for(Object obj:objs){
            bigDecimal = bigDecimal.multiply(bigDecimal(obj));
        }
        return bigDecimal;
    }
    /**
     * 数值之间加法
     * @param objs
     * @return
     */
    public static BigDecimal add(Object... objs){
        BigDecimal bigDecimal = new BigDecimal(0);
        for(Object obj:objs){
            bigDecimal = bigDecimal.add(bigDecimal(obj));
        }
        return bigDecimal;
    }
    /**
     * 数值之间减法(按顺序相减)
     * @param subtrahend 减数
     * @param objs 被减数
     * @return
     */
    public static BigDecimal sub(Object subtrahend, Object... objs){
        BigDecimal bigDecimal = bigDecimal(subtrahend);
        for (Object obj:objs){
            bigDecimal = bigDecimal.subtract(bigDecimal(obj));
        }
        return bigDecimal;
    }
    /**
     * 获取两个数的差值的绝对值
     * @param o1
     * @param o2
     * @return
     */
    public static BigDecimal abs(Object o1, Object o2){
        BigDecimal b1 = bigDecimal(o1);
        BigDecimal b2 = bigDecimal(o2);
        return b1.subtract(b2).abs();
    }

    /**
     * 生成一个BigDecimal
     * @param obj
     * @return
     */
    public static BigDecimal bigDecimal(Object obj){
        BigDecimal bigDecimal = new BigDecimal(0);
        if(obj instanceof String){
            bigDecimal = new BigDecimal(obj.toString());
        }else if(obj instanceof Double){
            bigDecimal = new BigDecimal((double)obj);
        }else if(obj instanceof Integer){
            bigDecimal = new BigDecimal((int)obj);
        }else if(obj instanceof Float){
            bigDecimal = new BigDecimal((float)obj);
        }else if(obj instanceof Long){
            bigDecimal = new BigDecimal((long)obj);
        }else if(obj instanceof BigInteger){
            bigDecimal = new BigDecimal((BigInteger) obj);
        }else if(obj instanceof BigDecimal){
            bigDecimal = (BigDecimal) obj;
        }
        return bigDecimal;
    }
}
