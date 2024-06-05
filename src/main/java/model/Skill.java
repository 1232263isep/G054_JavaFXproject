package model;

import java.util.Objects;

public class Skill {
    private final String name;

        public Skill(String name) {

        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Reference cannot be null or empty.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
            if (!(o instanceof Skill)) {
            return false;
        }
                Skill task = (Skill) o;
        return name.equals(task.name);
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
        public Skill clone() {
            return new Skill(this.name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}