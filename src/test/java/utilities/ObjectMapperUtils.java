package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /*
     Bu sınıf, JSON dönüşümü yaparken oluşabilecek exceptionları handle eder
     ve dönüşüm işlemlerini test metotlarınız için daha temiz hale getirir.
     Aşağıda, hem bir Map'e hem de bir Pojoya dönüşüm yapabilen jenerik bir
     yardımcı metot (utility method) ve bu metod oluşturacağız.
     */

    // JSON string'ini Java nesnesine dönüştürür
    public static <T> T convertJsonStrToJava(String jsonStr, Class<T> classType) {
        try {
            return mapper.readValue(jsonStr, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON string to Java object", e);
        }
    }

    // Java nesnesini JSON string'ine dönüştürür
    public static String convertJavaObjectToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert Java object to JSON string", e);
        }
    }
}
