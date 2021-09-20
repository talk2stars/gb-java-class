package week1;

import week1.HelloClassLoader;

import java.lang.reflect.Method;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XlassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        // XlassLoader xlassLoader = new XlassLoader();
        // Class hellClass = xlassLoader.findClass("Hello");
        new XlassLoader().findClass("Hello").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = Paths.get(name+".xlass");
        byte[] bytes = getBytesFromFile(path);
        // decode
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name,bytes,0,bytes.length);
    }

    public static byte[] getBytesFromFile(Path path) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(path);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}