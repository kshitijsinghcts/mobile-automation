package com.automation.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class BaseTest {
    // read BrowserStack credentials from .env

    private static final Dotenv dotenv = Dotenv.load();
    public static final String BROWSERSTACK_USERNAME = dotenv.get("BROWSERSTACK_USERNAME");
    public static final String BROWSERSTACK_ACCESS_KEY = dotenv.get("BROWSERSTACK_ACCESS_KEY");

    public static final String HUB_URL = "https://hub-cloud.browserstack.com/wd/hub";

    // threadsafe driver
    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setup() throws MalformedURLException {
        if (BROWSERSTACK_USERNAME == null || BROWSERSTACK_ACCESS_KEY == null) {
            throw new RuntimeException("BrowserStack credentials are not set in enviornment variables!");
        }
        UiAutomator2Options options = new UiAutomator2Options();
        // --- Standard Appium Cabalilities ---
        options.setPlatformName("android");
        options.setPlatformVersion("12.0");
        options.setDeviceName("Google Pixel 7");
        // for the app I'll just use the default Wikipedia app
        options.setApp("bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");

        // --- BrowserStack-Specific Capabilities ---
        options.setCapability("bstack:options", Map.of(
            "username", BROWSERSTACK_USERNAME,
            "accessKey", BROWSERSTACK_ACCESS_KEY,
            "projectName", "My First Project",
            "buildName", "My First Build",
            "sessionName", "My First Appium Test"
        ));

        // initialize the Appium driver
        driver.set(new AndroidDriver(new URL(HUB_URL), options));
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        // quit the session
        if (driver.get() != null) {
            driver.get().quit();
        }
        driver.remove();
    }

    // public getter to access driver in test classes
    public AppiumDriver getDriver() {
        return driver.get();
    }

}
