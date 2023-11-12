package ui;

import api.ItemAPI;
import api.LoginAPI;
import api.dto.Credentials;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.HomePage;
import ui.pages.ItemPage;
import ui.pages.LoginPage;

import java.time.Duration;
@Tag("item")
public class ItemPageTest {

        private WebDriver driver;
        private String token;
        private static final String BASE_URL = "https://marieta-ood.inv.bg";
        private static final long WAIT = 5;

        @BeforeEach
        public void beforeEachTest(TestInfo testInfo) {
            //This code will run before each test method
            LoginAPI loginAPI = new LoginAPI("");
            Credentials credentials = new Credentials("mm.milenkova@gmail.com","q2w3e4r5","marieta-ood");
            token = loginAPI.obtainToken(credentials);
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));
            driver.get(BASE_URL);
            driver.manage().window().maximize();
            System.out.println("Test: " + testInfo.getDisplayName()); //Printing display name of the current test
        }

        @AfterEach
        public void afterEachTest() {
            if (driver != null){
                driver.quit();
            }
        }

    @Test
    @Tag("ui")
    @DisplayName("Can delete existing item")
    public void canDeleteExistingItem(){
    }

    @Test
    @DisplayName("Can create item")
    public void canCreateItem(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login("mm.milenkova@gmail.com", "q2w3e4r5");
        HomePage homePage = new HomePage (driver);
        homePage.clickItemsMenu();
        ItemPage itemPage = new ItemPage(driver);
        Assertions.assertEquals("Артикули", itemPage.pageHading());
        //Create item via UI
        itemPage.createItem("Test2779");
        //Save the item
        //Verify success massage
        Assertions.assertEquals("Артикулът е добавен успешно.", itemPage.successMessage());
        //Delete item via API
        ItemAPI itemAPI = new ItemAPI(token);
        itemAPI.deleteAll();
    }

    @Test
    @DisplayName("Correct messages is displayed when no items exist")
    public void correctMessagesIsDisplayedWhenNoItemsExist(){
        //Make sure system contains no items
        ItemAPI itemAPI = new ItemAPI(token);
        itemAPI.deleteAll();
        //Login UI
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login("mm.milenkova@gmail.com", "q2w3e4r5");
        //Navigate to Item page
        HomePage homePage = new HomePage(driver);
        homePage.clickItemsMenu();
        //Check navigation was successful
        ItemPage itemPage = new ItemPage(driver);
        Assertions.assertEquals("Артикули", itemPage.pageHading());
        //Check empty list message is displayed
        Assertions.assertEquals("Не са намерени артикули, отговарящи на зададените критерии.", itemPage.emptyListMessage());


    }

    @Test
    @DisplayName("Can search item")
    public void canSearchItem(){

    }
}
