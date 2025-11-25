package ua.opnu;

public class Student {
    private String name;       // ім'я
    private String lastName;   // прізвище (додано для завдання 4)
    private String group;
    private int[] marks;

    public Student(String name, String lastName, String group, int[] marks) {
        this.name = name;
        this.lastName = lastName;
        this.group = group;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }
}
