package contacts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.io.Serializable;
import java.io.*;
import java.util.*;

abstract class Contact implements Serializable  {
//    private static final long serialVersionUID = 1L;
//
//    private String number;
//    private LocalDateTime timeCreated;
//    private LocalDateTime timeLastEdit;
//
//    public Contact(String number) {
//        this.timeCreated = LocalDateTime.now();
//        this.timeLastEdit = LocalDateTime.now();
//        setNumber(number);
//    }
//
//    public String getNumber() {
//        return number.isEmpty() ? "[no number]" : number;
//    }
//
//    public void setNumber(String number) {
//        if (isValidNumber(number)) {
//            this.number = number;
//        } else {
//            this.number = "";
//        }
//        updateLastEditTime();
//    }
//
//    public LocalDateTime getTimeCreated() {
//        return timeCreated;
//    }
//
//    public LocalDateTime getTimeLastEdit() {
//        return timeLastEdit;
//    }
//
//    private boolean isValidNumber(String number) {
//        String regex = "^\\+?(\\([a-zA-Z0-9]{1,}\\)|[a-zA-Z0-9]{1})([ -]?(\\([a-zA-Z0-9]{2,}\\)|[a-zA-Z0-9]{2,}))*$";
//        return Pattern.matches(regex, number) && number.chars().filter(ch -> ch == '(').count() <= 1;
//    }
//
//    public void updateLastEditTime() {
//        this.timeLastEdit = LocalDateTime.now();
//    }
//
//    public abstract String[] getEditableFields();
//    public abstract void setFieldValue(String field, String value);
//    public abstract String getFieldValue(String field);
//
//    @Override
//    public String toString() {
//        return "Number: " + getNumber() + "\n" +
//                "Time created: " + timeCreated.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n" +
//                "Time last edit: " + timeLastEdit.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//    }

    protected String number;
    protected LocalDateTime created;
    protected LocalDateTime lastEdit;

    public Contact(String number) {
        this.number = number;
        this.created = LocalDateTime.now();
        this.lastEdit = LocalDateTime.now();
    }

    public abstract String[] getEditableFields();

    public abstract void setFieldValue(String field, String value);

    public abstract String toDetailedString();

    public boolean contains(String query) {
        return toDetailedString().toLowerCase().contains(query);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        this.lastEdit = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Number: " + number;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }
}
