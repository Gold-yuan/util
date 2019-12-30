package sichuan.ytf.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCache {

    public static void main(String[] args) {

        System.out.println(LocalCache.localCache);// 为了使static（field，block，method） 运行（初始化），初始化顺序为代码声明的顺序
        // 实际使用时只有LocalCache.set和LocalCache.get暴露出来，所以调用这两个方法的时候，localCache和静态块已经先行初始化了

        try {
            Thread.sleep(2950);// 本来使用的是3000，但是输出有问题，有些预想已经过时的却仍旧能够取得到，估计是jvm运行优化导致的
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
        for (int j = 0; j < 1000; j++) {
            for (int i = 1; i < 11; i++) {
                System.out.println(System.currentTimeMillis() + " " + i + ":" + LocalCache.get(String.valueOf(i)));
            }
        }
    }

    private LocalCache() {
    }

    // 测试为public，实际使用应该改为private
    public static final Map<String, ValueObject> localCache = new ConcurrentHashMap<String, ValueObject>();

    static {
        // 初始化
        // Test
        for (int i = 1; i < 11; i++) {
            System.out.println(LocalCache.set(String.valueOf(i), i + "-value", 3000));
        }
    }

    private static class ValueObject {

        private String value;
        private long timeout;

        private ValueObject(String value, long timeout) {
            super();
            this.value = value;
            this.timeout = timeout;
        }

        private String getValue() {
            return value;
        }

        private long getTimeout() {
            return timeout;
        }

    }

    public static String set(String key, String value, long timeout) {
        long currentTime = System.currentTimeMillis();
        ValueObject valueObject = localCache.get(key);
        if (localCache.keySet().contains(key)
                && (valueObject.getTimeout() == 0 || currentTime <= valueObject.getTimeout())) {
            return valueObject.getValue();
        } else {
            ValueObject oldValueObject = localCache.put(key, new ValueObject(value, currentTime + timeout));
            return oldValueObject == null ? null : oldValueObject.getValue();
        }
    }

    public static String set(String key, String value) {
        ValueObject oldValueObject = localCache.put(key, new ValueObject(value, 0));
        return oldValueObject == null ? null : oldValueObject.getValue();
    }

    public static String get(String key) {
        long currentTime = System.currentTimeMillis();
        ValueObject valueObject = localCache.get(key);
        if (valueObject == null) {
            return null;
        }
        if (valueObject.getTimeout() == 0 || currentTime <= valueObject.getTimeout()) {
            return valueObject.getValue().toString();
        } else {
            localCache.remove(key);
            return null;
        }
    }

}