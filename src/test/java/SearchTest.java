import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTest {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void successfulSearchTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("QAGURU");
        $("#lastName").setValue("QAGURU");
        $("#userEmail").setValue("QAGURU@MAIL.RU");
        $("#userNumber").setValue("89001109090");
        executeJavaScript("arguments[0].click();", $("#gender-radio-1"));
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("May")).click();
        $(".react-datepicker__year-select option[value='1995']").click();
        $x("//*[@class='react-datepicker__month']//*[contains(text(), '13')]").click();
        for (int i = 0; i < 3; i++) {
            $("#subjectsContainer").click();
            $("#subjectsInput").append("a");
            $("#react-select-2-option-" + i).click();
        }
        executeJavaScript("arguments[0].click();", $("#hobbies-checkbox-1"));
        executeJavaScript("arguments[0].click();", $("#hobbies-checkbox-2"));
        executeJavaScript("arguments[0].click();", $("#hobbies-checkbox-3"));
        File file = new File("src/main/resources/test.bmp");
        $("#uploadPicture").sendKeys(file.getAbsolutePath());
        $("#currentAddress").setValue("QAGURU");
        $("#state").click();
        $("#react-select-3-option-0").click();
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(exactText("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                Condition.text("Label Values"),
                Condition.text("Student Name QAGURU QAGURU"),
                Condition.text("Student Email QAGURU@MAIL.RU"),
                Condition.text("Gender Male"),
                Condition.text("Mobile 8900110909"),
                Condition.text("Date of Birth 13 May,1995"),
                Condition.text("Subjects Maths, Accounting, Arts"),
                Condition.text("Hobbies Sports, Reading, Music"),
                Condition.text("Picture test.bmp"),
                Condition.text("Address QAGURU"),
                Condition.text("State and City NCR Delhi"));
    }
}