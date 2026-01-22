package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

public class StockAlertPage extends BasePage {
  //gives the list of search option
    private By searchOption=By.xpath("//android.widget.TextView[@text='検索']");

    private By favoriteOption=By.xpath("//android.widget.TextView[@text='お気に入り']");
    private By rankingOption=By.xpath("//android.widget.TextView[@text='ランキング']");
    private By ListOfDomesticStocksHeld=By.xpath("//android.widget.TextView[@text='国内株式 保有銘柄一覧']");
    private By DomesticStocksCreditOpenInterestList=By.xpath("//android.widget.TextView[@text='国内株式 信用建玉一覧']");
    private By ListOfUSStocksHeld=By.xpath("//android.widget.TextView[@text='米国株式 保有銘柄一覧']");
    private By USStockCreditOpenInterestList=By.xpath("//android.widget.TextView[@text='米国株式 信用建玉一覧']");
    private By cancelOption=By.xpath("//android.widget.TextView[@text='米国株式 信用建玉一覧']");


    public StockAlertPage(AppiumDriver driver){
        super(driver);
    }

}
