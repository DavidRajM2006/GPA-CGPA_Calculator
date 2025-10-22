public class Course {
    private String name;
    private double credits;
    private double grade;

    public Course(String name, double credits, double grade) {
        this.name = name;
        this.credits = credits;
        this.grade = grade;
    }

    public double getCredits() {
        return credits;
    }

    public double getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }
}