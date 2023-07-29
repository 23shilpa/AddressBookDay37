package com.bridgelabz;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Scanner;

import java.util.Set;
import java.util.regex.Pattern;

public class AddressBook {
    static String firstNamePattern = "^[a-zA-Z][a-zA-Z ]*$";
    static String lastNamePattern = "^[a-zA-Z][a-zA-Z ]*$";
    static String addressPattern = "^[a-zA-Z0-9-,. ]+$";
    static String cityPattern = "^[a-zA-Z][a-zA-Z ]*$";
    static String statePattern = "^[a-zA-Z][a-zA-Z ]*$";


    static String zipPattern = "^\\d{6}$";
    static String phoneNumberPattern = "^\\d{10}$";

    static final Scanner scanner = new Scanner(System.in);
    static Set<String> emptyContacts = new HashSet<>();

    static Set<String> nonEmptyContacts = new HashSet<>();

    void createNewContact() throws Exception {
        System.out.print("Enter name of the contact:");
        String contactName = scanner.nextLine();
        File file = new File(contactName);
        if (file.exists()) {
            System.out.println("contact " + file.getName() + " already exists!");
        } else {
            if (file.createNewFile()) {
                System.out.println("new contact " + file.getName() + " is created successfully");
                emptyContacts.add(file.getName());
            } else {
                System.out.println("file creation failed!");
            }
        }
    }
    void writeFile(String fileName,String content)throws Exception
    {
        FileWriter fw = new FileWriter(fileName);
        fw.write(content);
        fw.close();
        nonEmptyContacts.add(fileName);
    }

    Boolean validate(String name, String regex)
    {
        return Pattern.matches(regex, name);
    }

    String takeInput(String field, String pattern)
    {
        String input;
        do
        {
            System.out.print("enter " + field + ":");
            input = scanner.nextLine();
        } while (!validate(input, pattern));
        return input;
    }

    //given fields are added into contact
    void fillContactDetails()throws Exception
    {
        System.out.print("enter empty contact name which is going to be filed:");
        String contactName = scanner.nextLine();
        if (emptyContacts.contains(contactName))
        {
            String details = "";
            details += takeInput("first name", firstNamePattern) + "\n";
            details += takeInput("last name", lastNamePattern) + "\n";
            details += takeInput("address", addressPattern) + "\n";
            details += takeInput("city", cityPattern) + "\n";
            details += takeInput("state", statePattern) + "\n";
            details += takeInput("zip code", zipPattern) + "\n";
            details += takeInput("phone number", phoneNumberPattern) + "\n";
            writeFile(contactName, details);
            emptyContacts.remove(contactName);
            System.out.println("the given fields are successfully added in " + contactName);
        }
        else
        {
            System.out.println(contactName + " is not empty contact or it is not created");
            System.out.println("use other option c to create new contact or option e to edit already created one");
        }

    }
