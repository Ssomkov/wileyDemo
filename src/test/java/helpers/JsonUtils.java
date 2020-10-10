package helpers;

import com.google.gson.Gson;

public class JsonUtils {

    public static <T> T generateDataFromFile(String filePath, Class<T> type) {
        T t;
        String str = FileUtils.readFile(filePath);
        t = new Gson().fromJson(str, type);
        return t;
    }

    public static <T> T generateDataFromString(String source, Class<T> type) {
        T t;
        t = new Gson().fromJson(source, type);
        return t;
    }
}
