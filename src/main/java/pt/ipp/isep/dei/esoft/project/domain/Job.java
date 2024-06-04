package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Job {
    private final String name;

    public Job(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validateName(String name) {
        //TODO: missing from the diagrams
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Reference cannot be null or empty.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Job)) {
            return false;
        }
        Job job = (Job) o;
        return name.equals(job.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Job clone() {
        return new Job(this.name);
    }

    @Override
    public String toString() {
        return  name;
    }
}
