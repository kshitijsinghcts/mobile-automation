package com.automation.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import io.github.cdimascio.dotenv.Dotenv;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

public class BaseTest {
    // read BrowserStack credentials from .env

    private static final Dotenv dotenv = Dotenv.load();
    public static final String BROWSERSTACK_USERNAME = dotenv.get("BROWSERSTACK_USERNAME");
    public static final String BROWSERSTACK_ACCESS_KEY = dotenv.get("BROWSERSTACK_ACCESS_KEY");

    public static final String HUB_URL = String.format("https://%s:%s@hub-cloud.browserstack.com/wd/hub",
            BROWSERSTACK_USERNAME, BROWSERSTACK_ACCESS_KEY);

    // threadsafe driver
    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setup() throws MalformedURLException {
        System.out.println("Loaded Username: " + BROWSERSTACK_USERNAME);
        System.out.println("Loaded Access Key: " + (BROWSERSTACK_ACCESS_KEY != null ? "******" : null));
        if (BROWSERSTACK_USERNAME == null || BROWSERSTACK_ACCESS_KEY == null) {
            throw new RuntimeException("BrowserStack credentials are not set in enviornment variables!");
        }
        UiAutomator2Options options = new UiAutomator2Options();
        // --- Standard Appium Cabalilities ---
        options.setPlatformName("android");
        options.setPlatformVersion("13.0");
        options.setDeviceName("Google Pixel 7");
        options.setApp("bs://685e9c4a565d800ac038b5b1762167ad28e33e59"); 

        // --- BrowserStack-Specific Capabilities ---
        options.setCapability("bstack:options", Map.of(
                "projectName", "My First Project",
                "buildName", "My First Build",
                "sessionName", "My First Appium Test"
        ));

        // --- DEPRECATION WARNING FIXED ---
        // initialize the Appium driver using the modern URI.toURL() method
        driver.set(new AndroidDriver(URI.create(HUB_URL).toURL(), options));
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

