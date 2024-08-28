import java.time.LocalDateTime;

abstract class Contact {

    private String phoneNumber;
    private final boolean isPerson;
    private final LocalDateTime creationTime;
    private LocalDateTime lastEditTime;


    Contact(String phoneNumber, boolean isPerson) {
        setOrUpdatePhoneNumber(phoneNumber);
        this.isPerson = isPerson;
        this.creationTime = LocalDateTime.now().withSecond(0).withNano(0);
        this.lastEditTime = creationTime;
    }

    private String getPhoneNumber() {
        return this.phoneNumber;
    }

    private LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    private LocalDateTime getLastEditTime() {
        return this.lastEditTime;
    }

    boolean isPerson() {
        return this.isPerson;
    }

    void setOrUpdatePhoneNumber(String phoneNumber) {
        if (Validation.isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
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
                Time last edit: %s
                """.formatted(getPhoneNumber(), getCreationTime(), getLastEditTime());
    }

}


