public class Result {
    protected Course course;
    private int mark; // Mark obtained by the student in the course
    private String acadYear;
    private String grade;

    public Result(Course course, int mark, String acadYear) {
        this.course = course;
        this.mark = mark;
        this.acadYear = acadYear;
        this.grade = calculateGrade(mark);
    }

    public Course getCourse() {
        return course;
    }

    public int getMark() {
        return mark;
    }
    
    public String getAcadYear(){
       return acadYear;
    }

     public String getGrade() {
        return grade;
    }

    private String calculateGrade(double mark) {
          if (mark >= 75) {
           return "A+";
        } else if (mark >= 70) {
           return "A-";
        } else if (mark >= 65) {
           return "B+";
        } else if (mark >= 60) {
           return "B-";
        } else if (mark >= 55) {
           return "C+";  
        } else if (mark >= 50) {
           return "C-";  
        } else {
           return "F";
        }
    }

}
