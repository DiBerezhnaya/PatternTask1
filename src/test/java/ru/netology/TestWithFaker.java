package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class TestWithFaker {

    String deliveryDate = GenerateDate.generateDate(3);

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldGenerateTestDataUsingUtils() {
        RegistrationInfo info = GenerateDate
                .Registration
                .generateInfo("ru");

        $("[data-test-id='city'] input").setValue(info.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(deliveryDate);
        $(byName("name")).val(info.getName());
        $("[name='phone']").val(info.getPhone());
        $x("//span[@class='checkbox__box']").click();
        $("[class='button__text']").click();
        $(".notification__content")
                .shouldBe(visible)
                .shouldBe(appear, Duration.ofSeconds(15))
                .should(exactText("Встреча успешно запланирована на " + deliveryDate));

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date']").click();
        LocalDate dateDefault = LocalDate.now().plusDays(3);
        LocalDate dateOfMeeting = LocalDate.now().plusDays(4);
        String dayToSearch = String.valueOf(dateOfMeeting.getDayOfMonth());
        if (dateOfMeeting.getMonthValue() > dateDefault.getMonthValue() | dateOfMeeting.getYear() > dateDefault.getYear()) {
            $(".calendar__arrow_direction_right[data-step='1']").click();
        }
        $$("td.calendar__day").find(exactText(dayToSearch)).click();
        $(byText("Запланировать")).click();
        $(withText("Необходимо подтверждение"))
                .shouldBe(appear, Duration.ofSeconds(15))
                .shouldBe(visible);
        $$("[class='button__text']").get(1).click();
        $x("//div[@class='notification__content']")
                .shouldBe(appear, Duration.ofSeconds(15))
                .shouldBe(visible).should(text("Встреча успешно запланирована на " + dayToSearch));
    }
}

