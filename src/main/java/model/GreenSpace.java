package model;
import java.util.Objects;

public class GreenSpace {
    private GardenType type;
    private int area;
    private String name;
    private String address;

    public GreenSpace(String name, String type, int area, String address) {
         this.name = name;
        this.type= GardenType.valueOf(type.toUpperCase().replace(" ", "_"));
        this.area = area;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GreenSpace)) {
            return false;
        }
        GreenSpace c = (GreenSpace) o;
        return c.name==name && c.address==address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,address);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public GreenSpace clone() {
        return new GreenSpace(name, type.getName(), area, address);
    }
    @Override
    public String toString() {
        return name +", "+ type+", "+ address+", "+ this.area;
    }

    public String getName() {return this.name;
    }

    public  GardenType getType() {
        return type;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
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