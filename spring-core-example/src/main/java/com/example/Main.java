package com.example;

import com.example.model.Contacts;
import com.example.utils.CheckingInputInformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {

    private static final String DEFAULT_SAVE_PATH = "out/";
    private static CheckingInputInformation inputInformation = new CheckingInputInformation();
    private static List<Contacts> contactsList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            actionPrint();
            String input = new Scanner(System.in).nextLine();
            if (action(input)) {
                break;
            }
        }
    }

    private static boolean action(String select) {
        switch (select) {
            case "1" -> printContactsList();
            case "2" -> saveContact();
            //case "3" -> {};
            case "4" -> {
                return true;
            }
        }
        return false;
    }

    private static void saveContact() {
        Contacts contact = new Contacts();
        contact.setFullName(getName());
        contact.setPhoneNumber(getNumber());
        contact.setEmail(getEMail());

        if (checkingRecords(contact.getFullName(), contact.getPhoneNumber(), contact.getEmail())) {
            contactsList.add(contact);
            StringJoiner joiner = new StringJoiner(";");
            joiner.add(contact.getFullName());
            joiner.add(contact.getPhoneNumber());
            joiner.add(contact.getEmail());

        }

    }

    private void writingInFile(String string, String pathString) {
        Path path = Paths.get(pathString);
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        try {
            if (!Files.exists(path) || addingFiles) {
                Files.write(path, bytes, StandardOpenOption.APPEND);
                System.out.println("File created: " + path);
            } else {
                Files.delete(path);
                Files.write(path, bytes);
                System.out.println("File overwritten: " + path);
            }
        } catch (Exception ex) {
            System.out.println("Error writing file: " + path);
            ex.getMessage();

        }
    }



    private static void printContactsList() {
        if (contactsList.isEmpty()) {
            System.out.println("Список пустой!");
        }
        if (!contactsList.isEmpty()) {
            contactsList.forEach(System.out::println);
        }
    }

    private static void actionPrint() {
        System.out.println("1 - Показать список контактов.");
        System.out.println("2 - Добавить новый контакт.");
        System.out.println("3 - Удалить контакт.");
        System.out.println("4 - Выход.");
        System.out.println("Введите номер, имя или команду:");
    }

    private static boolean checkingRecords(String fullName, String phoneNumber, String email) {
        return !fullName.equals("") && !phoneNumber.equals("") && !email.equals("");
    }

    private static String getEMail() {
        System.out.println("Введите email в формате email@mail.ru");
        String eMail = new Scanner(System.in).nextLine();
        try {
            return inputInformation.eMailTest(eMail);
        } catch (IllegalArgumentException ex) {
            System.out.print(ex.getMessage());
            return "";
        }
    }

    private static String getName() {
        System.out.println("Введите ФИО, в формате Фамилия Имя Отчество");
        String name = new Scanner(System.in).nextLine();
        try {
            return inputInformation.nameTest(name);
        } catch (IllegalArgumentException ex) {
            System.out.print(ex.getMessage());
            return "";
        }
    }
    private static String getNumber() {
        System.out.println("Введите номер телефона в формате +77776665533");
        String number = new Scanner(System.in).nextLine();
        try {
            return inputInformation.phoneTest(number);
        } catch (IllegalArgumentException ex) {
            System.out.print(ex.getMessage());
            return "";
        }
    }
}
