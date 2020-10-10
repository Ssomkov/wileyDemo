package helpers;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Resource.Classpath("test.properties")
public class Config {

    private static Config config = new Config();
    @Property("browser")
    private static String browser;
    @Property("main.page")
    private static String mainPage;

    private Config() {
        PropertyLoader.populate(this);
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public static String getBrowser() {
        return browser;
    }

    public static String getMainPage() {
        return mainPage;
    }
}
