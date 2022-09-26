package jaejun.gop.singleton;

public class Settings {
    private static Settings instance;
    private static final Settings EAGER_INSTANCE = new Settings();
    private static volatile Settings instanceVolatile;

    private Settings() { }

    private static class SettingsHolder{
        private static final Settings INSTANCE = new Settings();
    }

    /* Thread Safe */

    /**
     * synchronized 키워드 사용하기 (동기화)
     */
    public static Settings getInstance() {
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
     * static inner class
     */
    public static Settings getInstanceInnerClass() {
        return SettingsHolder.INSTANCE;
    }
}
