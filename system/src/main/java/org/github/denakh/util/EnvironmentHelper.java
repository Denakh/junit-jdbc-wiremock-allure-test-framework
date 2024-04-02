package org.github.denakh.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentHelper {

    // private static final String APP_DOMAIN = "denakh-test.com";
    private static final String APP_DOMAIN = "localhost:7777";
    private static final String DEFAULT_APP_TEST_ENV = "local";
    private static final String TEST_ENV_PARAM = "TEST_ENVIRONMENT";
    private static final String ENV_TOKEN_PARAM = "TOKEN";
    private static final String LOCAL_TOKEN_PARAM = "token";
    private static final String TOKEN_PATH = "src/main/resources/.env";

    public static String getEnvDomain() {
        return String.join(StringUtility.POINT,
                System.getenv(TEST_ENV_PARAM) == null ? DEFAULT_APP_TEST_ENV : System.getenv(TEST_ENV_PARAM),
                APP_DOMAIN);
    }

    public static String getToken() {
        if (System.getenv(ENV_TOKEN_PARAM) != null) return System.getenv(ENV_TOKEN_PARAM);
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(TOKEN_PATH)) {
            properties.load(input);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return properties.getProperty(LOCAL_TOKEN_PARAM);
    }

}
