# Simple Appium + BrowserStack Test Framework

This project is a minimal example of running an Appium test on the BrowserStack cloud.

It uses the Page Object Model (POM) pattern to:
1.  Click the "Skip" button on the app's welcome screen.
2.  Search for "BrowserStack" on the main page.
3.  Verify that results appear.

## 🚀 How to Run This Project

### 1. Set Your Credentials
* Find your BrowserStack **Username** and **Access Key** on your [App Automate dashboard](https://app-automate.browserstack.com/dashboard/builds).
* Create a file named `.env` in the project's root folder.
* Add your credentials to it:
    ```ini
    BROWSERSTACK_USERNAME="YOUR_USERNAME"
    BROWSERSTACK_ACCESS_KEY="YOUR_ACCESS_KEY"
    ```

### 2. Upload Your App
* Go to your [BrowserStack App Automate dashboard](https://app-automate.browserstack.com/dashboard/builds).
* Upload your `.apk` file (e.g., the Wikipedia app) to get a `bs://...` URL.
* Open `src/main/java/com/automation/base/BaseTest.java`.
* Paste your new app URL into this line:
    ```java
    options.setApp("bs://YOUR_NEW_APP_URL_HERE");
    ```

### 3. Run the Test
* Open your terminal.
* Run the Maven `test` command:
    ```bash
    mvn test
    ```

That's it! You can watch your test run live on your BrowserStack dashboard.