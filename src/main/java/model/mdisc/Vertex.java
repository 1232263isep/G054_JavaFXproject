package model.mdisc;

public class Vertex {

    private String id;

    public Vertex(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getIntId(){
        return id.hashCode();
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
