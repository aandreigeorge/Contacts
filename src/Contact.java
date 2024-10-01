import java.io.Serializable;
import java.time.LocalDateTime;


abstract class Contact implements Serializable {

    private String phoneNumber;
    private final LocalDateTime creationTime;
    private LocalDateTime lastEditTime;


    Contact() {
        this.creationTime = LocalDateTime.now().withSecond(0).withNano(0);
        this.lastEditTime = creationTime;
    }

    abstract String getFieldValue(String fieldName);
    abstract String getModifiableFields();
    abstract boolean exists(String query);
    abstract String getFullName();
    abstract void setField(String fieldName, String fieldValue);


    String getPhoneNumber() {
        return this.phoneNumber;
    }

    private LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    private LocalDateTime getLastEditTime() {
        return this.lastEditTime;
    }

    void setOrUpdatePhoneNumber(String phoneNumber) {

        if (Validation.isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
    }

    void updateLastEditTime() {
        this.lastEditTime = LocalDateTime.now().withSecond(0).withNano(0);
    }

    @Override
    public String toString() {
        return """
                Number: %s
                Time created: %s
                Time last edit: %s""".formatted(getPhoneNumber(), getCreationTime(), getLastEditTime());
    }


}


