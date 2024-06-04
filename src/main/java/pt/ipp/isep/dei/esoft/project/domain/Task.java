package pt.ipp.isep.dei.esoft.project.domain;
import java.util.Objects;

public class Task {
    private String name;
    private String description;


    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return name.equals(task.name) && description.equals(task.description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Task clone() {
        return new Task(this.name, this.description);
    }

    @Override
    public String toString() {
        return name + ", " +description;
    }
}