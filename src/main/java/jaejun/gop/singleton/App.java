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
        //대응 방안으로 용() 메소드 사
        Settings settings3 = null;
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("setting.obj"))) {
            out.writeObject(settings);
        }

        try (ObjectInput input = new ObjectInputStream(new FileInputStream("setting.obj"))) {
            settings3 = (Settings) input.readObject();
        }

        System.out.println(settings == settings3);
    }
}
