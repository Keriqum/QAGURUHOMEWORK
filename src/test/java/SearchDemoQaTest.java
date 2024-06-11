import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchDemoQaTest {

    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void successfulSearchTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("QAGURU");
        $("#lastName").setValue("QAGURU");
        $("#userEmail").setValue("QAGURU@MAIL.RU");
        $("#userNumber").setValue("89001109090");
        $("#genterWrapper").$(byText("Male")).click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1995");
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__day--013:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("test.bmp");
        $("#currentAddress").setValue("QAGURU");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
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