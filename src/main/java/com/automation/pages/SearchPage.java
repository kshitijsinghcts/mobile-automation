package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {

    protected AppiumDriver driver;

    // --- Locators ---
    
    @AndroidFindBy(accessibility = "Search Wikipedia")
    private WebElement searchField;

    @AndroidFindBy(id = "org.wikipedia.alpha:id/search_src_text")
    private WebElement searchInput;

    @AndroidFindBy(id = "org.wikipedia.alpha:id/page_list_item_title")
    private List<WebElement> searchResults;
    

    // --- Constructor ---
    public SearchPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // --- Public Methods (Actions) ---
    
    public void searchFor(String searchTerm) {
        searchField.click();
        searchInput.sendKeys(searchTerm);
    }

    public boolean areResultsDisplayed() {
        return !searchResults.isEmpty();
    }

    public String getFirstResultText() {
        if (areResultsDisplayed()) {
            return searchResults.get(0).getText();
        }
        return null;
    }
}