package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.OnboardingPage;
import com.automation.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void simpleSearchTest() {
        // 1. Handle the Onboarding (SKIP) screen first
        OnboardingPage onboardingPage = new OnboardingPage(getDriver());
        onboardingPage.skipOnboarding();

        // 2. Initialize the Search Page
        SearchPage searchPage = new SearchPage(getDriver());

        // 3. Perform actions using Page Object methods
        searchPage.searchFor("BrowserStack");

        // 4. Add assertions
        Assert.assertTrue(searchPage.areResultsDisplayed(), "Search results were not displayed.");
        
        String firstResult = searchPage.getFirstResultText();
        Assert.assertNotNull(firstResult);
        Assert.assertTrue(firstResult.toLowerCase().contains("browserstack"), 
                "First result did not contain 'browserstack'. Found: " + firstResult);
    }
}
