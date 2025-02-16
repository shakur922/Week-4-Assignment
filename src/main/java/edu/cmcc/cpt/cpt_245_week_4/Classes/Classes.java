package edu.cmcc.cpt.cpt_245_week_4.Classes;

public class Classes {
    
    private String class_code;
    private String class_name;

    // Constructors
    public Classes() {
    }

    public Classes(String class_code, String class_name) {
        setClassCode(class_code);
        setClassName(class_name);
    }

    // Getters and Setters

    public String getClassCode() {
        return class_code;
    }

    public void setClassCode(String class_code) {
        if (class_code == null || class_code.isEmpty()) {
            throw new IllegalArgumentException("Class code cannot be null or empty");
        }
        this.class_code = class_code;
    }

    public String getClassName() {
        return class_name;
    }

    public void setClassName(String class_name) {
        if (class_name == null || class_name.isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty");
        }
        this.class_name = class_name;
    }
}
