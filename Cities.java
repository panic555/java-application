package hr.java.production.enums;

public enum Cities {
    ZADAR_23273("Zadar"),
    SPLIT_23000("Split"),
    ZAGREB_10000("Zagreb");

    private final String city;
    Cities(String city){
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
