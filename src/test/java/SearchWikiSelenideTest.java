import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class SearchWikiSelenideTest {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void GithubWikiTest() {
        open("https://github.com/codeborne/selenide");
        $(linkText("Wiki")).click();
        $(".markdown-body").find(byText("Soft assertions")).shouldHave(text("Soft assertions")).click();
        $((withText("Using JUnit5 extend test class"))).shouldHave(text("Using JUnit5 extend test class"));
        }
    }
