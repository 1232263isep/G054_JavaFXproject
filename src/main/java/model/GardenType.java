package model;

public enum GardenType {
    GARDEN("Garden"),
    MEDIUM_SIZED_PARK("Medium Sized Park"),
    LARGE_SIZED_PARK("Large Sized Park");

    private final String name;

    GardenType(String name) {this.name=name;}
    public String getName() {return name;}

    @Override
    public String toString() {
        return name;
    }
}
