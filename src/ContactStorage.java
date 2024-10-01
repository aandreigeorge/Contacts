import java.io.*;
import java.util.List;


public class ContactStorage {

    private final File file;
    private final String fileName;


    ContactStorage(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    @SuppressWarnings("unchecked") /* We know for sure we are reading Contact objects from the file. Cast is safe */
    List<Contact> loadContacts() {

        if (!file.exists()) {
            System.out.println("No contacts file found. Creating default empty contact file.");
            return new java.util.ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println(fileName + " file was loaded.");
            return (List<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage() +  ". File will be overridden if you continue!");
            return new java.util.ArrayList<>();
        }
    }

    void saveContacts(List<Contact> contacts) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(contacts);
            System.out.println("Contacts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }


}
