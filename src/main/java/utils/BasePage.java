package utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;


import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class BasePage {

	public WebDriverWait wait;
	public AppiumDriver driver;


	public BasePage(AppiumDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
	}

	protected MobileElement waitForElementToBeClickable(By locator) {
		return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void hideKeyboard() {
		try {
			if (driver.getPlatformName().equalsIgnoreCase("android")) {
				if (driver instanceof MobileDriver) {
					((MobileDriver<?>) driver).hideKeyboard();
				} else {
					System.out.println("Keyboard is not supported on this driver.");
				}
			} else {
				driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='確　　　定']")).click();
			}
		}catch (Exception e){

		}
	}

	public MobileElement waitForElementToBeVisible(By locator) {
		return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	//public MobileElement waitFor

	public static String extractDateTime(String text) {
		// Regular expression to match date and time
		String regex = "(\\d{4}/\\d{2}/\\d{2}), (\\d{2}:\\d{2}:\\d{2})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		// Return the matched date-time string if found
		if (matcher.find()) {
			return matcher.group(0); // Returns the full match (date and time combined)
		}
		return ""; // Return empty string if no match is found
	}

	protected boolean isEnabled(By locator) {
		driver.findElement(locator).isEnabled();
		return true;

	}

	protected void disable(By locator) {
		driver.findElement(locator).click();


	}

	public boolean isElementDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	public boolean isWebElementDisplayed(WebElement locator) {
		try {
			return locator.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void clearTextField(By locator) {
		MobileElement element = waitForElementToBeVisible(locator);
		element.clear();
	}

	protected boolean isElementVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	protected boolean isLoginElementVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 6);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected void sendKeysToElement(By locator, String text) {
		MobileElement element = waitForElementToBeVisible(locator);
		element.sendKeys(text);
	}

	public void clickElement(By locator) {
		MobileElement element = waitForElementToBeVisible(locator);
		element.click();
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getTextFromElement(By locator) {
		MobileElement element = waitForElementToBeVisible(locator);
		return element.getText();
	}


	public String removeComma(String priceText) {
		String outputStringWithOIutComa = null;
		if (priceText.contains(",")) {
			outputStringWithOIutComa = priceText.replace(",", "");

		} else {
			outputStringWithOIutComa = priceText;
		}
		return outputStringWithOIutComa;

	}

	public String increaseAmount(String amount, int incrementamountDomestic,String alertType,double incrementPercent) {//4,600
		int totalAmount=0;
		int currentPriceValue = Integer.parseInt(removeNumberAfterDecimal(amount));
		if(alertType.equalsIgnoreCase("create")) {
			totalAmount = currentPriceValue *(int) incrementPercent;
			// totalAmount = currentPriceValue + price;
			// String totalPrice = String.format("%.2f", totalAmount);
		} else{
			totalAmount = currentPriceValue + incrementamountDomestic;
		}
		// String totalPrice = String.format("%.2f", totalAmount);
		return String.valueOf(totalAmount);
	}

	public String increaseAmountUS(String amount, double incrementamountUS,String alertType,double incrementPercent) {//4,600

		double totalAmount=0;
		double currentPriceValue = Double.parseDouble(removeComma(amount));
		if(alertType.equalsIgnoreCase("create")) {

			totalAmount = currentPriceValue * incrementPercent ;

			//totalAmount = currentPriceValue + price;
		}
		else{

			totalAmount = currentPriceValue + incrementamountUS;
		}
		String totalPrice = String.format("%.2f", totalAmount);

		return totalPrice;
	}

	public String decreaseAmount(String amount, int decrementamountDomestic,String alertType,double decrementPercent) {
		int totalAmount=0;
		String totalPrice=null;
		int currentPriceValue = Integer.parseInt(removeNumberAfterDecimal(amount));
		if(alertType.equalsIgnoreCase("create")) {
			totalAmount =(int) (currentPriceValue /decrementPercent);

		}else {
			totalAmount = Math.abs(currentPriceValue - decrementamountDomestic);

		}
		totalPrice=String.valueOf(totalAmount);

		return totalPrice;
	}

	public String decreaseAmountUS(String amount, double decrementamountUS,String alertType,double decrementPercent) {

		double totalAmount=0;
		double currentPriceValue = Double.parseDouble(removeComma(amount));
		if(alertType.equalsIgnoreCase("create")) {

			totalAmount = currentPriceValue / decrementPercent ;

			//totalAmount = currentPriceValue - price;
		}
		else{
			if(currentPriceValue<0.1){
				totalAmount=  0.01;
			}else{
				totalAmount = currentPriceValue - decrementamountUS;
			}
		}
		String totalPrice = String.format("%.2f", totalAmount);

		return totalPrice;
	}

	public void disableToggle(By toggle) {
		waitForElementToBeVisible(toggle);
		if (driver.getPlatformName().equalsIgnoreCase("ios")) {
			String value = getElementValue(toggle);
			if("1".equals(value)){
				waitForElementToBeClickable(toggle);
				clickElement(toggle);
			}
		}else {
			String statusOfToggleBtn = getTextFromElement(toggle);
			if ("On".equals(statusOfToggleBtn)) {
				waitForElementToBeClickable(toggle);
				clickElement(toggle);

			}
		}
	}
	public String isToggleDisabled(By toggle) {
		String value = "";
		waitForElementToBeVisible(toggle);
		if (driver.getPlatformName().equalsIgnoreCase("ios")) {
			 value = getElementValue(toggle);
		if(!"1".equals(value)){
		value="Off";}
		else{
			value="On";
		}
		}
		  return value;
			}



	public void enableToggle(By toggle) {
		waitForElementToBeVisible(toggle);
		if(driver.getPlatformName().equalsIgnoreCase("ios")){
	   String value= getElementValue(toggle);
			if (!"1".equals(value)) {
				waitForElementToBeClickable(toggle);
				clickElement(toggle);

			}
		}else {
			String statusOfToggleBtn = getTextFromElement(toggle);
			if (!"On".equals(statusOfToggleBtn)) {
				waitForElementToBeClickable(toggle);
				clickElement(toggle);

			}
		}
	}

	public String getElementValue(By locator) {
		WebElement element = driver.findElement(locator);
		return element.getAttribute("value");  // Retrieves the value attribute of the element
	}

//    public void verifyAlert(By toggle,String alertType){
//        String stateOfToggle=getTextFromElement(toggle);
//        Assert.assertEquals(stateOfToggle,"on",alertType+"alert is not set");
//    }

	public String getAmountPrevousDayUp(String amount, double incrementamountPreviousday,String alertType,double incrementPercent) {

		String totalPrice = null;
		double currentPriceValue = 0.0;
		if (amount.equals("0.00％")) {
			totalPrice=null;

		}
		if(alertType.equalsIgnoreCase("create")){
			if (amount.startsWith("-")) {
				currentPriceValue = Double.parseDouble(removeFirstAndLastCharacter(amount));
				System.out.println(currentPriceValue);
				double totalAmount=currentPriceValue*incrementPercent;
				waitForSeconds(1);
				// double totalAmount =  Math.abs(currentPriceValue - price);
				totalPrice = String.format("%.2f", totalAmount);
			} else if (amount.startsWith("+")) {
				currentPriceValue = Double.parseDouble(removeFirstAndLastCharacter(amount));
				double totalAmount=currentPriceValue*incrementPercent;//(comment whether mul or devide)
				waitForSeconds(1);
				//double totalAmount = currentPriceValue + price;
				totalPrice = String.format("%.2f", totalAmount);
			}
		}else {
			if (amount.startsWith("-")) {
				currentPriceValue = Double.parseDouble(removeFirstAndLastCharacter(amount));
				System.out.println(currentPriceValue);
				waitForSeconds(1);
				double totalAmount = Math.abs(currentPriceValue - incrementamountPreviousday);
				totalPrice = String.format("%.2f", totalAmount);
			} else if (amount.startsWith("+")) {
				currentPriceValue = Double.parseDouble(removeFirstAndLastCharacter(amount));
				double totalAmount = currentPriceValue + incrementamountPreviousday;
				totalPrice = String.format("%.2f", totalAmount);
			}
		}
		return totalPrice;
	}

	public String getAmountPreviousDayDown(String amount, double incrementamountPreviousday,String alertType,double decrementPercent) {
		String totalPrice = null;
		double currentPriceValue = 0.0;
		waitForSeconds(1);
		if (amount.equals("0.00％")) {   //need to uncomment when prices are not reflecting
			return null;
		}
		if(alertType.equalsIgnoreCase("create")) {
			if (amount.startsWith("-")) {
				currentPriceValue = Double.parseDouble(removeFirstAndLastCharacter(amount));
				double totalAmount=currentPriceValue*decrementPercent;
				//double totalAmount = currentPriceValue + price;
				totalPrice = String.format("%.2f", totalAmount);

			} else if (amount.startsWith("+")) {
				currentPriceValue = Double.parseDouble(removeFirstAndLastCharacter(amount));
				double totalAmount=currentPriceValue/decrementPercent;
				//double totalAmount = Math.abs(currentPriceValue - price);
				totalPrice = String.format("%.2f", totalAmount);
			}
//           else{
////totalPrice="99";   // need to commecnt this when prices are reflecting
////           }
		}else{
			if (amount.startsWith("-")) {
				currentPriceValue = Double.parseDouble(removeFirstAndLastCharacter(amount));
				double totalAmount = currentPriceValue + incrementamountPreviousday;
				totalPrice = String.format("%.2f", totalAmount);
				//totalPrice = Double.toString(totalPrice);
			} else if (amount.startsWith("+")) {
				currentPriceValue = Double.parseDouble(removeFirstAndLastCharacter(amount));
				double totalAmount = Math.abs(currentPriceValue - incrementamountPreviousday);
				totalPrice = String.format("%.2f", totalAmount);
			}
		}
		return totalPrice;


	}

	public String removeFirstAndLastCharacter(String str) {
		if (str == null || str.length() <= 2) {
			return ""; // If the string is null or too short to remove characters, return an empty string
		}
		return str.substring(1, str.length() - 1);
	}

	public void scrollDownToBottom() {
		int screenHeight = driver.manage().window().getSize().getHeight();
		int screenWidth = driver.manage().window().getSize().getWidth();

		TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
		touchAction.press(point(screenWidth / 2, screenHeight * 3 / 4))
				.moveTo(point(screenWidth / 2, screenHeight / 4))
				.release()
				.perform();
	}

	public void scrollUpToTop() {
		int screenHeight = driver.manage().window().getSize().getHeight();
		int screenWidth = driver.manage().window().getSize().getWidth();

		TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
		touchAction.press(point(screenWidth / 2, screenHeight / 4))
				.moveTo(point(screenWidth / 2, screenHeight * 3 / 4))
				.release()
				.perform();
	}

	public void scrollToElement(String elementText) {
		try {

			if (driver.getPlatformName().equalsIgnoreCase("Android")) {
				// Android-specific scrolling using UiScrollable
				scrollAndSelectTextAndroid(elementText);
				System.out.println("Element with text \"" + elementText + "\" selected on Android.");

			} else if (driver.getPlatformName().equalsIgnoreCase("iOS")) {
				// iOS-specific scrolling using iOS Predicate String
				scrollAndSelectTextIOS(elementText);
			} else {
				System.out.println("Unsupported platform: " + driver.getPlatformName());
			}
		} catch (Exception e) {
			System.out.println("Element not found: " + e.getMessage());
		}
	}
public void scrollAndSelectTextIOS(String elementText){
			waitForSeconds(2);
			WebElement element = driver.findElement(
					MobileBy.iOSNsPredicateString("name CONTAINS '" + elementText + "'"
					));
			waitForSeconds(2);
			element.click();
			System.out.println("Element with text \"" + elementText + "\" selected on iOS.");
//        }catch (Exception e){
//            System.out.println("element "+elementText+"not found");
//        }

}
public void scrollAndSelectTextAndroid(String elementText){
  WebElement  element =  driver.findElement(
			MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
							".scrollIntoView(new UiSelector().text(\"" + elementText + "\"));"
			)
	);
	element.click();
}
	// Helper method to scroll down on iOS
	private void scrollDowniOS() {
		TouchAction<?> action = new TouchAction<>(driver);
		int startX = driver.manage().window().getSize().width / 2;
		int startY = (int) (driver.manage().window().getSize().height * 0.8);
		int endY = (int) (driver.manage().window().getSize().height * 0.2);

		action.press(point(startX, startY))
				.waitAction(waitOptions(Duration.ofSeconds(1)))
				.moveTo(point(startX, endY))
				.release()
				.perform();
	}


	public void scrollToElementIndex(String elementText) {
		// String direction = scrollDown ? "down" : "up";
		if(driver.getPlatformName().equalsIgnoreCase("android")){
		try {
			boolean elementFound = false;
			int maxScrollAttempts = 6; // Max scroll attempts to prevent infinite loops
			int scrollAttempts = 0;

			while (!elementFound && scrollAttempts < maxScrollAttempts) {
				List<MobileElement> elements = driver.findElements(MobileBy.AndroidUIAutomator(

						"new UiSelector().text(\"" + elementText + "\")"
				));

				if (!elements.isEmpty()) {
					elements.get(0).click(); // Element found, click it
					elementFound = true;
				} else {
					// Perform manual scroll down
					driver.findElement(MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward();"
					));
					scrollAttempts++;
				}
			}

			if (!elementFound) {
				System.out.println("Element not found after " + maxScrollAttempts + " scroll attempts.");
			}
		} catch (Exception e) {
			System.out.println("Element not found: " + e.getMessage());
		}}else{
		  WebElement  element = driver.findElement(
					MobileBy.iOSNsPredicateString("name CONTAINS '" + elementText + "'"
					));
			element.click();
			System.out.println("Element with text \"" + elementText + "\" selected on iOS.");



		}

	}
	public WebElement findTextElement(String text) {
		WebElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + text + "\").instance(0)"));
		return element;
	}
	public void findAndClickElementContainingText(String text) {
		WebElement element;
		if(driver.getPlatformName().equalsIgnoreCase("ios")){
		   scrollAndSelectTextIOS(text);
		}
		else{
			 element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiSelector().textContains(\"" + text + "\")"));
			element.click();
		}


	}
	public static String extractCompanyName(String input) {
		String[] parts = input.split("\\s+");
		// Return the third part if it exists; otherwise, return the entire input string
		return parts.length >= 3 ? parts[2] : input;
	}

	public void scrollToText(String stockName){
		By stockelement;
		if(driver.getPlatformName().equalsIgnoreCase("ios")){
			System.out.println(stockName);
			waitForSeconds(2);
			stockelement=
					By.xpath("//*[contains(@name,'"+stockName+"')]");
	boolean value=isElementDisplayed(stockelement);
	if(value){
			waitForSeconds(2);
		clickElement(stockelement);

	}
		}
		else{
		try {
			WebElement element =driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
							".scrollTextIntoView(\"" + stockName + "\");"   ));
			// Click the element after finding it
			element.click();
			System.out.println("Element with text \"" + stockName + "\" selected.");
		} catch (Exception e) {
			System.out.println("Element not found: " + e.getMessage());
		}
		}

	}
	public WebElement scrollToView(String elementText) {
		WebElement element=null;
		if(driver.getPlatformName().equalsIgnoreCase("ios")){
			try{
			 element = driver.findElement(
					 MobileBy.iOSNsPredicateString("name CONTAINS '" + elementText + "'"
					 ));} catch (Exception e) {
				System.out.println("Element not found: " + e.getMessage());
			}
		}else {
			try {
				element = driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
								".scrollIntoView(new UiSelector().textContains(\"" + elementText + "\").instance(0));"
				));
//            element.click(); // Click the element after finding it
				System.out.println("Element with text \"" + elementText + "\" selected.");
				// return element;
			} catch (Exception e) {
				System.out.println("Element not found: " + e.getMessage());
			}
		}
		return element;
	}
	public String formatXPath(String xpath) {
		return xpath.replace("\n", " ");
	}
	public boolean isElementSlected(By locator){
		return driver.findElement(locator).isSelected();
	}
	public void ScrollNotificationBar(){
		Dimension size = driver.manage().window().getSize();
		int startX = size.width / 2;
		int startY = (int) (size.height * 0.8);
		int endY = (int) (size.height * 0.2);

		// Initialize TouchAction
		TouchAction<?> touchAction = new TouchAction<>(driver);

		// Perform scroll gesture
		touchAction.press(point(startX, startY))
				.waitAction(waitOptions(ofMillis(1000))) // Adjust wait time if needed
				.moveTo(point(startX, endY))
				.release()
				.perform();
	}
	public String getStockName(String text) {
		// Normalize the text to handle full-width characters
		//text = Normalizer.normalize(text, Normalizer.Form.NFKC);
		if (driver.getPlatformName().equalsIgnoreCase("ios")) {

			if (text == null || text.isEmpty()) {
				return text; // Return as is if input is null or empty
			}

			String regex = "^(.*)\\s*\\(";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(text);

			if (matcher.find()) {
				return matcher.group(1).trim(); // Extract the part before the parentheses
			}

			return text.trim(); // Return the full string if no match is found
		} else {
			// Check if the text contains a '('
			int index = text.lastIndexOf("(");
			if (index != -1) {
				// Extract the substring before the '('
				return text.substring(0, index).trim();
			}
			// If no '(' is found, return the trimmed text
			return text.trim();
		}
	}
	public  void refreshScreen() {
		scrollToView("アラート設定");
	}
	public String removeSpaces(String input) {

		return input.replaceAll("​", ""); // \\s+ matches one or more whitespace characters
	}
	// return input.replaceAll("\\s+", ""); // \\s+ matches one or more whitespace characters

	public  String formatNumberToIndianSystem(String number) {
		int number1=Integer.parseInt(number);
		StringBuilder sb = new StringBuilder(String.valueOf(number1));
		int length = sb.length();

		// Insert commas after every two digits from the right, after the first digit
		for (int i = length - 3; i > 0; i -= 2) {
			sb.insert(i, ',');
		}

		return sb.toString();
	}
	public String formatNumberToIndianSystem(double number) {
		// Convert double to integer for formatting
		long number1 = (long) number;

		StringBuilder sb = new StringBuilder(String.valueOf(number1));
		int length = sb.length();

		// Insert commas after every two digits from the right, after the first digit
		for (int i = length - 3; i > 0; i -= 2) {
			sb.insert(i, ',');
		}

		// Check if there are decimal places in the original number
		String formattedNumber = sb.toString();
		if (number != number1) {
			// Append decimal part if exists
			formattedNumber += String.format("%.2f", number - number1).substring(1);
		}

		return formattedNumber;
	}
	public String formatNumberToIndianSystemDouble(String number) {
		// Convert the input String to double
		double parsedNumber = Double.parseDouble(number);

		// Separate integer and decimal parts
		long integerPart = (long) parsedNumber;
		double decimalPart = parsedNumber - integerPart;

		StringBuilder sb = new StringBuilder(String.valueOf(integerPart));
		int length = sb.length();

		// Insert commas after every two digits from the right, after the first digit
		for (int i = length - 3; i > 0; i -= 2) {
			sb.insert(i, ',');
		}

		// Append decimal part if it exists
		if (decimalPart > 0) {
			sb.append(String.format("%.2f", decimalPart).substring(1));
		}

		return sb.toString();
	}
	public String removeNumberAfterDecimal(String buyCurrentPrice) {


		if (buyCurrentPrice.contains(".")) {

			// Split the input at the decimal point
			String[] parts = buyCurrentPrice.split("\\.");

			// Remove any characters that are not digits or commas from the integer part
			String integerPart = parts[0].replaceAll("[^\\d,]", "");
			String newPrice = removeComma(integerPart);
			return newPrice;
		} else {
			return removeComma(buyCurrentPrice) ;
		}

	}

	public void openNotificationInIOS(){
		driver.executeScript("mobile: tap", ImmutableMap.of(
				"x", 21,
				"y", 119
		));
		int startX = 200;
		int startY = 6;
		int endY = 750;  // Small increment
		for (int i = 0; i < 3; i++) {  // Adjust repetitions to reach the notification center
			driver.executeScript("mobile: dragFromToForDuration", ImmutableMap.of(
					"duration", 0.2,
					"fromX", startX,
					"fromY", startY,
					"toX", startX,
					"toY", endY
			));
			waitForSeconds(5);  // Pause briefly between swipes

		}
	}
	public void scrollToTopIos(){
		driver.executeScript("mobile: tap", ImmutableMap.of(
				"x", 199,
				"y", 878
		));
		int startX = 191;
		int startY = 849;
		int endY = 364;  // Small increment
		for (int i = 0; i < 3; i++) {  // Adjust repetitions to reach the notification center
			driver.executeScript("mobile: dragFromToForDuration", ImmutableMap.of(
					"duration", 0.2,
					"fromX", startX,
					"fromY", startY,
					"toX", startX,
					"toY", endY
			));
			waitForSeconds(5);  // Pause briefly between swipes

		}
	}

	public void scrollToBottomIos() {
		driver.executeScript("mobile: tap", ImmutableMap.of(
				"x", 203,
				"y", 794
		));
		int startX = 203;
		int startY = 794;
		int endY = 307;  // Small increment
		for (int i = 0; i < 3; i++) {  // Adjust repetitions to reach the notification center
			driver.executeScript("mobile: dragFromToForDuration", ImmutableMap.of(
					"duration", 0.2,
					"fromX", startX,
					"fromY", startY,
					"toX", startX,
					"toY", endY
			));
			waitForSeconds(5);  // Pause briefly between swipes

		}
	}
}



