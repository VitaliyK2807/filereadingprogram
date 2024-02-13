package com.example.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Test for homework Phonebook app
 */

@DisplayName("Тест корректности обработки входных данных")
class PhoneBookTest {

    private CheckingInputInformation checkingInputInformation;

    private List<String> phoneNumbersForTest = List.of("",
            "+7-777-897-22-33",
            "89964567896",
            "8789 55 999 22",
            "67895214661",
            "55446666",
            "-78523694566");
    private List<String> nameForTest = List.of(
            "Иванов Иван Иванович",
            "",
            "Seged Petrid Fpdsd",
            "1 232 343"
    );

    private List<String> eMailForTest = List.of(
            "Vasily22@mail.ru",
            "",
            "@nnee.ru",
            "аавцц@mail.ru"
    );

    private List<String> numberFinalArray = new ArrayList<>();
    private List<String> nameFinalArray = new ArrayList<>();
    private List<String> eMailFinalArray = new ArrayList<>();

    private List<String> phoneNumbersCorrect = List.of(
            "",
            "+77778972233",
            "+79964567896",
            "+77895599922",
            "",
            "",
            "+78523694566");
    private List<String> nameListCorrect = List.of(
            "Иванов Иван Иванович",
            "",
            "",
            ""
    );

    private List<String> eMailForCorrect = List.of(
            "Vasily22@mail.ru",
            "",
            "",
            ""
    );

    @BeforeEach
    public void setUp() {
        checkingInputInformation = new CheckingInputInformation();
        phoneNumbersForTest.forEach(number -> numberFinalArray.add(getNumber(number)));
        nameForTest.forEach(name -> nameFinalArray.add(getName(name)));
        eMailForTest.forEach(eMail -> eMailFinalArray.add(getEmail(eMail)));
    }

    @Test
    @DisplayName("Тест обработки номера")
    void correctInputNumber() {
        assertEquals(phoneNumbersCorrect, numberFinalArray);
    }

    @Test
    @DisplayName("Тест обработки ФИО")
    void correctInputName() {
        assertEquals(nameListCorrect, nameFinalArray);
    }

    @Test
    @DisplayName("Тест обработки eMail")
    void correctInputEMail() {
        assertEquals(eMailForCorrect, eMailFinalArray);
    }

    private String getEmail(String eMail) {
        try {
            return checkingInputInformation.eMailTest(eMail);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }


    private String getName(String name) {
        try {
            return checkingInputInformation.nameTest(name);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    private String getNumber(String number) {
        try {
            return checkingInputInformation.phoneTest(number);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }
}
