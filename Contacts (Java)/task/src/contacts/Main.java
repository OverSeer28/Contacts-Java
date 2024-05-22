package contacts;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.io.*;

public class Main {
    private static List<Contact> contacts = new ArrayList<>();
    private static String filename;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (args.length > 0) {
            filename = args[0];
            List<Contact> loadedContacts = SerializationHelper.loadContacts(filename);
            if (loadedContacts != null) {
                contacts = loadedContacts;
            }
        }

        String action;
        do {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            action = scanner.nextLine().trim();
            System.out.println();

            switch (action) {
                case "add":
                    addContact(scanner);
                    break;
                case "list":
                    listContacts(scanner);
                    break;
                case "search":
                    searchContacts(scanner);
                    break;
                case "count":
                    countContacts();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Unknown action!");
                    System.out.println();
            }

            saveContacts();

        } while (!action.equals("exit"));
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine().trim();
        System.out.println();

        switch (type) {
            case "person":
                addPerson(scanner);
                break;
            case "organization":
                addOrganization(scanner);
                break;
            default:
                System.out.println("Unknown type!");
                System.out.println();
        }
    }

    private static void addPerson(Scanner scanner) {
        System.out.print("Enter the name: ");
        String firstname = scanner.nextLine().trim();

        System.out.print("Enter the surname: ");
        String lastname = scanner.nextLine().trim();

        System.out.print("Enter the birth date: ");
        String birthDate = scanner.nextLine().trim();

        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine().trim();

        System.out.print("Enter the number: ");
        String number = scanner.nextLine().trim();

        Person person = new Person(firstname, lastname, birthDate, gender, number);
        contacts.add(person);
        System.out.println("The record added.");
        System.out.println();
    }

    private static void addOrganization(Scanner scanner) {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter the address: ");
        String address = scanner.nextLine().trim();

        System.out.print("Enter the number: ");
        String number = scanner.nextLine().trim();

        Organization organization = new Organization(name, address, number);
        contacts.add(organization);
        System.out.println("The record added.");
        System.out.println();
    }

    private static void listContacts(Scanner scanner) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            System.out.printf("%d. %s\n", i + 1, contact.toString());
        }
        System.out.println();

        System.out.print("[list] Enter action ([number], back): ");
        String action = scanner.nextLine().trim();
        System.out.println();

        if (!action.equals("back")) {
            try {
                int index = Integer.parseInt(action) - 1;
                if (index >= 0 && index < contacts.size()) {
                    showContactMenu(scanner, contacts.get(index));
                } else {
                    System.out.println("Invalid number.");
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                System.out.println();
            }
        }
    }

    private static void searchContacts(Scanner scanner) {
        System.out.print("Enter search query: ");
        String query = scanner.nextLine().trim().toLowerCase();
        System.out.println();

        List<Contact> results = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.contains(query)) {
                results.add(contact);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No results found.");
            System.out.println();
        } else {
            System.out.printf("Found %d results:\n", results.size());
            for (int i = 0; i < results.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, results.get(i).toString());
            }
            System.out.println();

            while (true) {
                System.out.print("[search] Enter action ([number], back, again): ");
                String action = scanner.nextLine().trim();
                System.out.println();

                if (action.equals("back")) {
                    break;
                } else if (action.equals("again")) {
                    searchContacts(scanner);
                    break;
                } else {
                    try {
                        int index = Integer.parseInt(action) - 1;
                        if (index >= 0 && index < results.size()) {
                            showContactMenu(scanner, results.get(index));
                            break;
                        } else {
                            System.out.println("Invalid number.");
                            System.out.println();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                        System.out.println();
                    }
                }
            }
        }
    }

    private static void countContacts() {
        System.out.printf("The Phone Book has %d records.\n", contacts.size());
        System.out.println();
    }

    private static void showContactMenu(Scanner scanner, Contact contact) {
        System.out.println(contact.toDetailedString());
        System.out.println();

        while (true) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine().trim();
            System.out.println();

            switch (action) {
                case "edit":
                    editContact(scanner, contact);
                    break;
                case "delete":
                    contacts.remove(contact);
                    System.out.println("The record deleted.");
                    System.out.println();
                    return;
                case "menu":
                    return;
                default:
                    System.out.println("Unknown action!");
                    System.out.println();
            }
        }
    }

    private static void editContact(Scanner scanner, Contact contact) {
        String[] fields = contact.getEditableFields();

        System.out.printf("Select a field (%s): ", String.join(", ", fields));
        String field = scanner.nextLine().trim();
        System.out.println();

        boolean validField = false;
        for (String f : fields) {
            if (f.equals(field)) {
                validField = true;
                break;
            }
        }

        if (validField) {
            System.out.printf("Enter %s: ", field);
            String value = scanner.nextLine().trim();
            contact.setFieldValue(field, value);
            System.out.println("The record updated!");
            System.out.println();
        } else {
            System.out.println("Unknown field!");
            System.out.println();
        }
    }

    private static void saveContacts() {
        if (filename != null) {
            SerializationHelper.saveContacts(contacts, filename);
        }
    }
}

