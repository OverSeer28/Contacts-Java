package contacts;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Person extends Contact {
//    private static final long serialVersionUID = 1L;
//
//    private String firstname;
//    private String lastname;
//    private String birthDate;
//    private String gender;
//
//    public Person(String firstname, String lastname, String birthDate, String gender, String number) {
//        super(number);
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.birthDate = isValidBirthDate(birthDate) ? birthDate : "[no data]";
//        this.gender = isValidGender(gender) ? gender : "[no data]";
//    }
//
//    private boolean isValidBirthDate(String birthDate) {
//        return !birthDate.trim().isEmpty();  // Add more complex validation if needed
//    }
//
//    private boolean isValidGender(String gender) {
//        return gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F");
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//        updateLastEditTime();
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//        updateLastEditTime();
//    }
//
//    public String getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(String birthDate) {
//        this.birthDate = isValidBirthDate(birthDate) ? birthDate : "[no data]";
//        updateLastEditTime();
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = isValidGender(gender) ? gender : "[no data]";
//        updateLastEditTime();
//    }
//
//    @Override
//    public String[] getEditableFields() {
//        return new String[]{"firstname", "lastname", "birthDate", "gender", "number"};
//    }
//
//    @Override
//    public void setFieldValue(String field, String value) {
//        switch (field) {
//            case "firstname": setFirstname(value); break;
//            case "lastname": setLastname(value); break;
//            case "birthDate": setBirthDate(value); break;
//            case "gender": setGender(value); break;
//            case "number": setNumber(value); break;
//            default: throw new IllegalArgumentException("Unknown field: " + field);
//        }
//    }
//
//    @Override
//    public String getFieldValue(String field) {
//        switch (field) {
//            case "firstname": return getFirstname();
//            case "lastname": return getLastname();
//            case "birthDate": return getBirthDate();
//            case "gender": return getGender();
//            case "number": return getNumber();
//            default: throw new IllegalArgumentException("Unknown field: " + field);
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Name: " + firstname + "\n" +
//                "Surname: " + lastname + "\n" +
//                "Birth date: " + birthDate + "\n" +
//                "Gender: " + gender + "\n" +
//                super.toString();
//    }
private String firstname;
    private String lastname;
    private String birthDate;
    private String gender;

    public Person(String firstname, String lastname, String birthDate, String gender, String number) {
        super(number);
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    @Override
    public String[] getEditableFields() {
        return new String[]{"firstname", "lastname", "birthDate", "gender", "number"};
    }

    @Override
    public void setFieldValue(String field, String value) {
        switch (field) {
            case "firstname":
                this.firstname = value;
                break;
            case "lastname":
                this.lastname = value;
                break;
            case "birthDate":
                this.birthDate = value;
                break;
            case "gender":
                this.gender = value;
                break;
            case "number":
                setNumber(value);
                break;
        }
        this.lastEdit = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Name: " + firstname + " " + lastname;
    }

    @Override
    public String toDetailedString() {
        return "Name: " + firstname + "\n" +
                "Surname: " + lastname + "\n" +
                "Birth date: " + (birthDate.isEmpty() ? "[no data]" : birthDate) + "\n" +
                "Gender: " + (gender.isEmpty() ? "[no data]" : gender) + "\n" +
                "Number: " + number + "\n" +
                "Time created: " + created + "\n" +
                "Time last edit: " + lastEdit;
    }
}
