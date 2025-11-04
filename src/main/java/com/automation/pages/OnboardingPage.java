package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class OnboardingPage {

    protected AppiumDriver driver;

    // Locator for the "Skip" button on the welcome screen
    @AndroidFindBy(id = "org.wikipedia.alpha:id/fragment_onboarding_skip_button")
    private WebElement skipButton;

    // Constructor
    public OnboardingPage(AppiumDriver driver) {
        this.driver = driver;
        // Set implicit wait to 1 second for PageFactory initialization
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(1)), this);
    }

    // Public Method (Action)
    public void skipOnboarding() {
        // Check if the skip button is displayed before clicking
        // This prevents errors if the app is launched a second time
        // and the onboarding screen doesn't appear.
        try {
            if (skipButton.isDisplayed()) {
                skipButton.click();
            }
        } catch (Exception e) {
            // Log or ignore, as the element not being present is acceptable
            System.out.println("Onboarding 'SKIP' button not found, assuming we are on the main page.");
        }
    }
}
