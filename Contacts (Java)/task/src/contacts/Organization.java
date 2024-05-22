package contacts;

import java.time.LocalDateTime;

public class Organization extends Contact  {
//    private static final long serialVersionUID = 1L;
//
//    private String name;
//    private String address;
//
//    public Organization(String name, String address, String number) {
//        super(number);
//        this.name = name;
//        this.address = address;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//        updateLastEditTime();
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//        updateLastEditTime();
//    }
//
//    @Override
//    public String[] getEditableFields() {
//        return new String[]{"name", "address", "number"};
//    }
//
//    @Override
//    public void setFieldValue(String field, String value) {
//        switch (field) {
//            case "name": setName(value); break;
//            case "address": setAddress(value); break;
//            case "number": setNumber(value); break;
//            default: throw new IllegalArgumentException("Unknown field: " + field);
//        }
//    }
//
//    @Override
//    public String getFieldValue(String field) {
//        switch (field) {
//            case "name": return getName();
//            case "address": return getAddress();
//            case "number": return getNumber();
//            default: throw new IllegalArgumentException("Unknown field: " + field);
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Organization name: " + name + "\n" +
//                "Address: " + address + "\n" +
//                super.toString();
//    }
private String name;
    private String address;

    public Organization(String name, String address, String number) {
        super(number);
        this.name = name;
        this.address = address;
    }

    @Override
    public String[] getEditableFields() {
        return new String[]{"name", "address", "number"};
    }

    @Override
    public void setFieldValue(String field, String value) {
        switch (field) {
            case "name":
                this.name = value;
                break;
            case "address":
                this.address = value;
                break;
            case "number":
                setNumber(value);
                break;
        }
        this.lastEdit = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Organization name: " + name;
    }

    @Override
    public String toDetailedString() {
        return "Organization name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Number: " + number + "\n" +
                "Time created: " + created + "\n" +
                "Time last edit: " + lastEdit;
    }
}
