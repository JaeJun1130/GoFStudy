package jaejun.gop.singleton;

import java.io.Serializable;

public class Settings implements Serializable {
    private static Settings instance;
    private static final Settings EAGER_INSTANCE = new Settings();
    private static volatile Settings instanceVolatile;

    private Settings() { }

    private static class SettingsHolder{
        private static final Settings INSTANCE = new Settings();
    }

    /**
     * 멀티 스레드 환경에서는 safe 하지 않음
     */
    public static Settings getInstanceNotSafe() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    /* Thread Safe */

    /**
     * synchronized 키워드 사용하기 (동기화)
     */
    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    /**
     * 이른 초기화 사용하기 (eager initialization)
     */
    public static Settings getInstanceEager() {
        return EAGER_INSTANCE;
    }

    /**
     * double checked locking
     * volatile 사용
     * 1.5v ~
     */
    public static Settings getInstanceDoubleChecked() {
        if (instanceVolatile == null) {
            synchronized (Settings.class) {
                if (instanceVolatile == null) {
                    instanceVolatile = new Settings();
                }
            }
        }
        return instance;
    }

    /**
     * static inner class줌
     * inner 클래스가 호출되는 시점에 로딩
     */
    public static Settings getInstanceInnerClass() {
        return SettingsHolder.INSTANCE;
    }

    //역직렬화 동작시 해당 메소드를 자동으로 호출하여 싱글톤을 보장해
    protected Object readResolve() {
        return getInstanceInnerClass();
    }
}
