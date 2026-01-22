package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

import java.util.ArrayList;
import java.util.List;

public class FilterOptionPage extends BasePage {

    private final By sortSequence = By.id("jp.co.rakuten_sec.ispeed:id/sort_sequence_desc_button");
    private final By filterCenter = By.id("jp.co.rakuten_sec.ispeed:id/sort_item_appraisal_profit_or_loss_button");
    private final By setButton = By.id("jp.co.rakuten_sec.ispeed:id/submit_button");
    private final By onFilter = By.id("jp.co.rakuten_sec.ispeed:id/narrowing_header_button");
    private By allDropdown=By.id("jp.co.rakuten_sec.ispeed:id/execution_text");
    private By setButton1=By.id("android:id/button1");

    public FilterOptionPage(AppiumDriver driver) {
        super(driver);
    }

    public void enableFilter() {
        waitForElementToBeClickable(onFilter);
        clickElement(onFilter);
        waitForElementToBeClickable(filterCenter);
        clickElement(filterCenter);
        waitForElementToBeClickable(sortSequence);
        clickElement(sortSequence);
        waitForElementToBeClickable(setButton);
        clickElement(setButton);
        //
    }
    public List<String> scrollAndSelectStock(String stockName){
        List<String> quantity = new ArrayList<>();
        String heldquantity=null;
        String soldquantity=null;
        if(driver.getPlatformName().equalsIgnoreCase("ios")){
//     heldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_amount_stock_mouth_text']")).getText();
//     soldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text']")).getText();
    quantity.add(heldquantity);
    quantity.add(soldquantity);
    scrollToElement(stockName);
}else {
     heldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_amount_stock_mouth_text']")).getText();
     soldquantity = driver.findElement(By.xpath("//android.widget.TextView[@text='" + stockName + "']/../..//*[@resource-id='jp.co.rakuten_sec.ispeed:id/assets_holding_description_execution_text']")).getText();
    quantity.add(heldquantity);
    quantity.add(soldquantity);
    scrollToElement(stockName);
}
return quantity;
    }
}
