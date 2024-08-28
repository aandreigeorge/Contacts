class Organization extends Contact {

    private String name, address;

    Organization(String name, String address, String phoneNumber) {
        super(phoneNumber, false);
        setOrUpdateName(name);
        setOrUpdateAddress(address);
    }

    String getName() {
        return this.name;
    }

    private String getAddress() {
        return this.address;
    }

    void setOrUpdateName(String name) {
        this.name = name;
    }

    void setOrUpdateAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String numberCreationAndEditTime = super.toString();
        return """
                Organization name: %s
                Address: %s
                %s
                """.formatted(getName(), getAddress(), numberCreationAndEditTime);
    }
}
