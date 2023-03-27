package com.ey.il.appium.core;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;


public class FrameworkUtils {
    /**
     * The Constant MAX_SCROLL.
     */
    private static final int MAX_SCROLL = 10;

    /**
     * This method will swipe down for X number of times for desired mobile element-
     *
     * @param driver
     * @param element
     * @param swipes
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static MobileElement swipeToElement(AppiumDriver<MobileElement> driver, MobileElement element, int swipes) {
        int i = 0;
        try {
            Driver.manageTimeOut(2);
            try {
                if (element.getSize() != null) {
                    Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
                    return element;
                }
            } catch (Exception e) {
                LogUtil.error(e.getMessage());
            }
            Dimension dimens = driver.manage().window().getSize();
            int x = (int) (dimens.getWidth() * 0.48);
            int startY = (int) (dimens.getHeight() * 0.71);
            int endY = (int) (dimens.getHeight() * 0.25);
            LongPressOptions longPressOptions = new LongPressOptions()
                    .withPosition(new PointOption<>().withCoordinates(x, startY));
            PointOption pointOption = new PointOption<>().withCoordinates(x, endY);
            if (swipes == 0) {
                do {
                    new TouchAction(driver).longPress(longPressOptions).moveTo(pointOption).release().perform();
                    i++;
                }
                while (element.getSize() == null);
            } else {
                do {
                    new TouchAction(driver).longPress(longPressOptions).moveTo(pointOption).release().perform();
                    i++;
                }
                while (swipes > i);
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        } finally {
            Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
        }
        return element;
    }


    /**
     * This method will swipe down till it found the desired mobile element- Will result in infinite swipe if element is not found
     *
     * @param driver
     * @param element
     * @return
     */
    public static MobileElement swipeToElement(AppiumDriver<MobileElement> driver, MobileElement element) {
        return (swipeToElement(driver, element, 0));
    }


    public static void swipeToElement(AppiumDriver<MobileElement> driver, int swipes) {
        int i = 0;
        try {
            Driver.manageTimeOut(2);
            Dimension dimens = driver.manage().window().getSize();
            int x = (int) (dimens.getWidth() * 0.48);
            int startY = (int) (dimens.getHeight() * 0.60);
            int endY = (int) (dimens.getHeight() * 0.25);
            LongPressOptions longPressOptions = new LongPressOptions()
                    .withPosition(new PointOption<>().withCoordinates(x, startY));
            PointOption pointOption = new PointOption<>().withCoordinates(x, endY);
            if (swipes == 0) {
                new TouchAction(driver).longPress(longPressOptions).moveTo(pointOption).release().perform();
                i++;
            } else {
                do {
                    new TouchAction(driver).longPress(longPressOptions).moveTo(pointOption).release().perform();
                    i++;
                }
                while (swipes > i);
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        } finally {
            Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
        }
    }
    /**
     * Strict check for UI object availability on the screen. The program will wait for maximum 2 second on the screen
     *
     * @param element
     * @return
     */
    public static boolean isUIObjectDisplayed(WebElement element) {
        Driver.manageTimeOut(2);
        try {
            if (element.getSize() != null) {
                Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
                return true;
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
        Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
        return false;
    }


    /**
     * Strict check for UI object availability on the screen. The program will wait for maximum 2 second on the screen
     *
     * @param element
     * @return
     */
    public static boolean isUIObjectDisplayed(MobileElement element) {
        Driver.manageTimeOut(2);
        try {
            if (element.getSize() != null) {
                Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
                return true;
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
        Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
        return false;
    }

    public static boolean isUIObjectDisplayed(WebElement element, int timeInSec) {
        Driver.manageTimeOut(timeInSec);
        try {
            if (element.getSize() != null) {
                Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
                return true;
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
        Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
        return false;
    }


    public static boolean isUIObjectDisplayed(MobileElement element, int timeInSec) {
        Driver.manageTimeOut(timeInSec);
        try {
            if (element.getSize() != null) {
                Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
                return true;
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
        Driver.manageTimeOut(ConfigUtil.IMPLICIT_WAIT_TIME);
        return false;
    }


    @SuppressWarnings("deprecation")
    public void action(AppiumDriver<MobileElement> driver, MobileElement element, UserAction action, String value) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (action.toString().equals(UserAction.CLICK.toString())) {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } else if (action.toString().equals(UserAction.SENDKEYS.toString())) {
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
        }
    }


    // Take ScreenShot of page and return the path
    public synchronized static String captureScreenShot(String screenshotName, AppiumDriver<MobileElement> driver) {
        DateFormat df = new SimpleDateFormat("dd-MM-yy_HHmmss");
        Date dateobj = new Date();
        String currentDate = df.format(dateobj).toString();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenFileLocation = Driver.userDir + "/resources/screenshots/" + screenshotName + currentDate + ".jpg";
        File targetFile = new File(screenFileLocation);
        try {
            FileUtils.copyFile(src, targetFile, true);
        } catch (IOException e) {
            LogUtil.error("Error on taking screenshot:" + e.getMessage());
        }
        return screenFileLocation;
    }


    public enum UserAction {
        SENDKEYS("sendKeys"), CLICK("click");

        private UserAction(String realName) {
            this.realName = realName;
        }

        private String realName;

        public String toString() {
            return realName;
        }
    }


    /**
     * An implementation of the Wait interface that may have its timeout and polling interval configured on the fly.
     *
     * @param <K>            the key type
     * @param object         the object
     * @param timeOutSeconds The timeout duration (seconds)
     * @return the fluent wait
     */
    protected static <K> FluentWait<K> waitOn(K object, int timeOutSeconds) {
        return new FluentWait<>(object).ignoring(NoSuchElementException.class).ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class).ignoring(Exception.class)
                .withTimeout(Duration.ofSeconds(timeOutSeconds));
    }


    /**
     * Gets the waitOn instance.
     *
     * @return A FluentWait instance.
     */
    public static FluentWait<AppiumDriver<MobileElement>> getWait(AppiumDriver<MobileElement> driver) {
        return waitOn(driver, ConfigUtil.IMPLICIT_WAIT_TIME);
    }


    /**
     * Swipe vertical up.
     */
    @Step("Swipe vertical up")
    public static void swipeVertical(AppiumDriver<MobileElement> driver) {
        Dimension size = driver.manage().window().getSize();
        int width = size.width / 2;
        int start = (int) (size.height * 0.8);
        int end = (int) (size.height * 0.2);
        swipeTouchAction(driver, width, start, width, end, 1000);
    }


    /**
     * Scroll to visible Element.
     *
     * @param addToCartButton the element
     * @return true, if successful
     */
    public static boolean scrollToElement(AppiumDriver<MobileElement> driver, MobileElement addToCartButton) {

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        for (int scroll = 0; scroll < MAX_SCROLL; scroll++) {
            try {
                if (addToCartButton.isDisplayed()) {
                    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

                    return true;
                } else
                    swipeVertical(driver);
            } catch (Exception e) {
                swipeVertical(driver);
            }
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return false;
    }


    /**
     * Swipe touch action between two points
     *
     * @param startx   point
     * @param starty   point
     * @param endx     point
     * @param endy     point
     * @param duration in milliseconds
     */
    public static void swipeTouchAction(AppiumDriver<MobileElement> driver, int startx, int starty, int endx, int endy, int duration) {
        getTouchAction(driver).press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration))).moveTo(PointOption.point(endx, endy))
                .release().perform();
    }


    public static TouchAction getTouchAction(AppiumDriver<MobileElement> driver) {
        return new TouchAction(driver);
    }

    public static String getTwoFactorCode(){
        Totp totp = new Totp(" 2FA secret key");
        String twoFactorCode = totp.now();
        System.out.println(twoFactorCode);
        return twoFactorCode;
    }

}
