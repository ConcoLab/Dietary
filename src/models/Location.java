package models;

public class Location {
    private static long ITERATOR = 0;
    private long id;
    private String name;
    private String address;

    public Location(String name, String address) {
        this.id = ITERATOR;
        this.name = name;
        this.address = address;
        ITERATOR++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
