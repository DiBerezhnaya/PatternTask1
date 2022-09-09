package ru.netology;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@UtilityClass
public class GenerateDate {

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            String[] cities = new String[]{"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик",
                    "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск",
                    "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита",
                    "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь",
                    "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир",
                    "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово",
                    "Киров", "Кострома", "Курган", "Курск", "Гатчина", "Липецк", "Магадан", "Красногорск", "Мурманск", "Нижний Новгород",
                    "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Псков", "Ростов-на-Дону",
                    "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь",
                    "Томск", "Тула", "Тюмень", "Ульяновск", "Челябинск", "Ярославль", "Москва", "Санкт-Петербург",
                    "Севастополь", "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"};
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(cities[faker.number().numberBetween(0, cities.length - 1)],
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }
    }
}
