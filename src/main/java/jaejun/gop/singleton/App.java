package jaejun.gop.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Settings settings = Settings.getInstanceInnerClass();
        Settings settings1 = Settings.getInstanceInnerClass();
        System.out.println(settings == settings1);

        /* 싱글톤 패턴을 꺠트리는법 */

        //리플렉션 사용
        Constructor<Settings> declaredConstructor = Settings.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Settings settings2 = declaredConstructor.newInstance();
        System.out.println(settings == settings2);

        //직렬화 역직렬화
        //대응 방안으로 readResolve() 메소드 사용
        Settings settings3 = null;
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("setting.obj"))) {
            out.writeObject(settings);
        }

        try (ObjectInput input = new ObjectInputStream(new FileInputStream("setting.obj"))) {
            settings3 = (Settings) input.readObject();
        }
        System.out.println(settings == settings3);


        /* enum class 는 싱글톤을 깨트릴 수 없음
         * 리플렉션으로도 불가능
         * enum에 대한 리플렉션은 만들수 없다. (Cannot reflectively create enum objects)
         *
         *
         * 단점은 enum 은 클래스가 미리 만들어진다.
         */
        SettingEnum settingEnum = SettingEnum.INSTANCE;
        SettingEnum settingEnum1 = null;
        Constructor<?>[] declaredConstructors = SettingEnum.class.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            constructor.setAccessible(true);
            settingEnum1 = (SettingEnum) constructor.newInstance("INSTANCE");
        }
        System.out.println(settingEnum == settingEnum1);

        /**
         * enum은 자제적은 Serializable를 상속받고 있기 때문에 직렬화 역직렬화에 안전하다.
         */
        SettingEnum settingEnum2 = null;
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settingEnum.obj"))) {
            out.writeObject(settingEnum);
        }

        try (ObjectInput input = new ObjectInputStream(new FileInputStream("settingEnum.obj"))) {
            settingEnum2 = (SettingEnum) input.readObject();
        }
        System.out.println(settingEnum == settingEnum2);
    }
}
