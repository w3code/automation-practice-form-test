public class Student {
    String firstName = "John";
    String lastName = "Snow";
    String email = "john@snow.com";
    String gender = "Male";
    String mobile = "1234567890";
    String dayBirth = "5";
    String monthBirth = "March";
    String yearBirth = "2000";
    String subjects = "Accounting";
    String hobbies = "Sports";
    String picture = "test.jpg";
    String currentAddress = "Lenina str, 258";
    String state = "NCR";
    String city = "Delhi";

    //Создаем метод добавления нуля к числу рождения, если оно однозначное. Используется в валидации.
    public String dateOfBirth() {
        if(dayBirth.length() == 1) {
            dayBirth = '0' + dayBirth;
        }
        return dayBirth + ' ' + monthBirth + ',' + yearBirth;
    }
}
