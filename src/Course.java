public class Course {
    private String courseId;
    private String courseName;
    private int credits;
    private int semester;  // 1 for First Semester, 2 for Second Semester

    public Course(String courseId, String courseName, int credits, int semester) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.semester = semester;
    }
 
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public int getSemester() {
        return semester;
    }
  
}
