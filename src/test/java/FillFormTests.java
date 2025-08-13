import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FillFormTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTests() {
        open("/");
        //Простые текстовые поля
        $("input#firstName").setValue("Filipp");
        $("input#lastName").setValue("Kotov");
        $("input#userEmail").setValue("test@filippkotov.ru");
        $("input#userNumber").setValue("7999696969");
        $("textarea#currentAddress").setValue("улица Пушкина, дом Колотушкина");
        $("input#subjectsInput").setValue("English").pressEnter();
        $("input#subjectsInput").setValue("Math").pressEnter();

        //Сложные поля
        //Выбираем дату
        $("input#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2004");
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__day--027:not(.react-datepicker__day--outside-month)").click();

        //Выбираем Hobbies
        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-2']").click();
        $("label[for='hobbies-checkbox-3']").click();

        //Выбираем Gender male
        $("label[for='gender-radio-1']").click();

        //Выбираем State and City
        //State
        $("#state").click(); // Открыть список
        $("#react-select-3-input").setValue("NCR")
                .pressEnter(); // Выбрать NCR
        //City
        $("#city");
        $("#react-select-4-input").setValue("Delhi")
                .pressEnter();

        //Profile Picture
        $("input#uploadPicture").uploadFromClasspath("data/avatar.jpg");

        //Submit
        $("button#submit").click();
    }
}
