package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;  // Properties objesi oluşturuluyor.

    static {
        String filePath = "configuration.properties";  // Dosya yolunu belirtiyoruz.
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // try-with-resources yapısı ile dosya otomatik olarak kapatılır.
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Konfigürasyon dosyası okunurken bir hata oluştu: " + filePath);
            e.printStackTrace();
        }
    }

    // Belirtilen anahtar (key) ile eşleşen değeri döner.
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Değer yoksa varsayılan değeri döner
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}