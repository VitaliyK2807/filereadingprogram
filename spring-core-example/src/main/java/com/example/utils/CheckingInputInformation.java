package com.example.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CheckingInputInformation {

    public String eMailTest(String eMail) {
        if (eMail.equals("")) {
            throw new IllegalArgumentException("Введено пустое значение!");
        }
        String regex = "[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z]+";

        if (!eMail.matches(regex)) {
            throw new IllegalArgumentException(String.format("Не корректный введен eMail: %s", eMail));
        }

        return eMail;
    }

    public String nameTest(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Введено пустое значение!");
        }

        String regex = "[А-Яа-я]+\s[А-Яа-я]+\s[А-Яа-я]+";

        if (!name.matches(regex)) {
            throw new IllegalArgumentException(String.format("Не корректный введено ФИО: %s", name));
        }

        return name;
    }


    public String phoneTest(String phoneText) {
        if (phoneText.equals("")) {
            throw new IllegalArgumentException("Введен пустой номер!");
        }

        String regexPhone = "\\D";
        String string = phoneText.replaceAll(regexPhone, "");
        if (string.length() != 11) {
            throw new IllegalArgumentException(
                    String.format("Не хватает количества цифр для введенного номера: %s Должно быть 11", phoneText));
        }

        return getNewNumberVariableOne(string);
    }

    private String getNewNumberVariableOne(String string) {
        String[] words = string.split("");

        int number = Integer.valueOf(words[0]);

        if (number < 7 || number > 8) {
            throw new IllegalArgumentException("Не верный регион! Должен быть либо 7 либо 8. Введен номер: " + string);
        }

        words[0] = words[0].replace(words[0], "+7");

        return Arrays.stream(words).collect(Collectors.joining());

    }

}