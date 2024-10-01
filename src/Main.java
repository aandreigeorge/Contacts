import java.io.File;


public class Main {

    private static final String DEFAULT_FILE_NAME = "contacts.dat";
    private static final String DEFAULT_FILE_PATH = System.getProperty("user.home") + File.separator + DEFAULT_FILE_NAME;


    public static void main(String[] args) {

        if (args.length > 0) {
            new ContactManager(args[0]);
        } else {
            new ContactManager(DEFAULT_FILE_PATH);
        }
    }


}


