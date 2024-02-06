package com.qq.Creational.FactoryMethod.demo3.factory;

import com.qq.Creational.FactoryMethod.demo3.model.IPrize;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 奖品工厂，能够生产不同的奖品
 * </p>
 * 通过加载配置文件，获取配置文件中配置的全类名，并创建该类的对象进行存储
 */
public class PrizeFactory {
    // 设置缓存存储不同的奖品
    private static Map<String, IPrize> map = new HashMap<>();

    // 加载配置文件，只需要加载一次
    static {
        // 创建 Properties 对象
        Properties p = new Properties();
        // 调用 p 对象中的 load 方法进行配置文件的加载
        InputStream is = PrizeFactory.class.getClassLoader().getResourceAsStream("prize.properties");
        try {
            p.load(is);
            // 从 p 集合中获取全类名并创建对象
            Set<Object> keys = p.keySet();
            for (Object key : keys) {
                String className = p.getProperty((String) key);
                // 通过反射创建对象
                Class clazz = Class.forName(className);
                IPrize prize = (IPrize) clazz.newInstance();
                // 将名称和对象存储到容器中
                map.put((String) key, prize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据奖品类型不同生产不同的奖品
     *
     * @param prizeType 奖品类型
     * @return
     */
    public static IPrize getPrize(String prizeType) {
        return map.get(prizeType);
    }
}
