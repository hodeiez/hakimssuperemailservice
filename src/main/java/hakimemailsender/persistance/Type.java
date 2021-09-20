package hakimemailsender.persistance;

public enum Type {

    WELCOME("welcome"),CONFIRM("confirm");
    private final String value;

    Type(String valueName) {
        value=valueName;
    }


    @Override
    public String toString() {
        return  value;
    }

}
