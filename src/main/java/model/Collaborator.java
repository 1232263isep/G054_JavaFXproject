package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Collaborator {
    private String name;
    private Date birthdate;
    private Date admissionDate;
    private String street;
    private String city;
    private String zipcode;
    private int phoneNumber;
    private String email;
    private String IDDocType;
    private String IDNumber;
    private Job job;
    private List<Skill> skills;

    public String getIDNumber() {
        return IDNumber;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public Collaborator(String name, Date birthdate, Date admissionDate, String street, String city, String zipcode, int phoneNumber, String email, String IDDocType, String IDNumber, Job job) {
         this.name = name;
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.IDDocType = IDDocType;
        this.IDNumber = IDNumber;
        this.job = job;
        this.skills = new ArrayList<Skill>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Collaborator)) {
            return false;
        }
        Collaborator c = (Collaborator) o;
        return c.IDNumber==IDNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDNumber);
    }

    /**
     * @param skills, a list of skill
     * @return true if the method added some skill to this.skills (skills of the collaborator), false if no skills are added to this.skills
     */
    public boolean addSkills(List<Skill> skills) {
        if (skills.isEmpty())
            return false;
        if (this.skills.containsAll(skills))
            return false;
        for (Skill s:skills) {
            if (this.skills.contains(s))
                continue;
            this.skills.add(s);
        }
        return true;
    }
    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Collaborator clone() {
        return new Collaborator(this.name,this.birthdate,this.admissionDate,this.street,this.city,this.zipcode,this.phoneNumber,this.email,this.IDDocType,this.IDNumber,this.job);
    }

    @Override
    public String
    toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", IDNumber=" + IDNumber +
                ", Job=" + job +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIDDocType() {
        return IDDocType;
    }

    public void setIDDocType(String IDDocType) {
        this.IDDocType = IDDocType;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}