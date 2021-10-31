import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {
    Student student = new Student();

    @BeforeAll
    static void startMaximized() {
        Configuration.startMaximized = true;
    }

    @Test
    void selenideFormTest() {
        // Открыть Practice Form
        open("https://demoqa.com/automation-practice-form");

        // Проверяем загрузилась ли страница с заголовком "Student Registration Form"
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // Ищем и заполняем поля формы
        $("#firstName").setValue(student.firstName);
        $("#lastName").setValue(student.lastName);
        $("#userEmail").setValue(student.email);
        $(".custom-radio").$(byText(student.gender)).click();
        $("#userNumber").setValue(student.mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(student.monthBirth);
        $(".react-datepicker__year-select").selectOption(student.yearBirth);
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").filter(text(student.dayBirth)).first().click();
        $("#subjectsInput").setValue(student.subjects).pressEnter();
        $(".custom-checkbox").$(byText(student.hobbies)).click();
        $("#uploadPicture").uploadFromClasspath(student.picture);
        $("#currentAddress").setValue(student.currentAddress);
        $("#state").click();
        $("#state").$(byText(student.state)).click();
        $("#city").click();
        $("#city").$(byText(student.city)).click();

        // Отправляем форму
        $("#submit").pressEnter();

        // Проверяем соответствие полей введенным данным
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        studentInfoValidate("Student Name", student.firstName + ' ' + student.lastName);
        studentInfoValidate("Student Email", student.email);
        studentInfoValidate("Gender", student.gender);
        studentInfoValidate("Mobile", student.mobile);
        studentInfoValidate("Date of Birth", student.dateOfBirth());
        studentInfoValidate("Subjects", student.subjects);
        studentInfoValidate("Hobbies", student.hobbies);
        studentInfoValidate("Picture", student.picture);
        studentInfoValidate("Address", student.currentAddress);
        studentInfoValidate("State and City", student.state + " " + student.city);

        // Закрываем модальное окно
        $("#closeLargeModal").click();

        // Проверяем модальное окно на закрытие
        $(byTagName("body")).shouldNotHave(cssClass("modal-open"));
    }

    // Создаем метод проверки информации о студенте в диалоговом окне
    void studentInfoValidate(String StudentField, String StudentData) {
        $(byXpath("//td[contains(text(),'" + StudentField + "')]/../td[2]")).shouldHave(text(StudentData));
    }
}