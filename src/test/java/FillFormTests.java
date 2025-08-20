import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FillFormTests {
    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true; //Used for debug
    }

    @Test
    void fillFormTests() {
        open("/");
        //Simple text fields

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("input#firstName").setValue("Filipp");
        $("input#lastName").setValue("Kotov");
        $("input#userEmail").setValue("test@filippkotov.ru");
        $("input#userNumber").setValue("7999696969");
        $("textarea#currentAddress").setValue("улица Пушкина, дом Колотушкина");
        $("input#subjectsInput").setValue("English").pressEnter();
        $("input#subjectsInput").setValue("Math").pressEnter();

        //Specific fields
        //Calendar fill
        $("input#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2004");
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__day--027:not(.react-datepicker__day--outside-month)").click();

        //Hobbies fill
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-3']").click();

        //Gender male test
        $("label[for='gender-radio-1']").click();

        //State and City fill
        //State
        $("#state").click(); // Открыть список
        $("#react-select-3-input").setValue("NCR")
                .pressEnter(); // Выбрать NCR
        //City fill
        $("#city");
        $("#react-select-4-input").setValue("Delhi")
                .pressEnter();

        //Profile Picture upload
        $("input#uploadPicture").uploadFromClasspath("data/avatar.jpg");

        //Submit
        $("button#submit").click();

        //Verifying table test
        $("div#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("tbody").shouldHave(text("Filipp Kotov"));
    }
}
