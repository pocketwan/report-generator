import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class ReportDataPreparer {
    public static List<ReportData> prepareData(Student student, List<Result> results) {
        List<ReportData> reportDataList = new ArrayList<>();
 
        /* GPA calculation */
        int creditsReceived = 0;
        int totalCredits = 0;
        double termGPA = 0.0;
        String acadYear = null;
        
        for (Result result : results) {     
            int courseCredits = result.course.getCredits();  // Get the credits for the course
            int courseMark = result.getMark();  // Get the mark for the course
            creditsReceived += (courseCredits * courseMark);  // Accumulate weighted credits
            totalCredits += (courseCredits * 100);  // Total credits, scaled by 100   
            if (acadYear == null)
              acadYear = result.getAcadYear();
       }
       
        // Avoid division by zero
        if (totalCredits != 0) 
          termGPA = (double) creditsReceived / totalCredits * 100; // Calculate the GPA
       
        // Round to two decimal places
        BigDecimal bigDecimal = new BigDecimal(termGPA).setScale(2, RoundingMode.HALF_UP);
        termGPA = bigDecimal.doubleValue();
        String GPA = String.format(Locale.US,"%.2f", termGPA);
        
        reportDataList.add(new ReportData(
                student.getName(),
                student.getStudentNumber(),
                student.getYearOfStudy(),
                student.getDegreeName(),
                acadYear
   
        ));
      
        // Prepare the results
        for (Result result : results) {       
            String concatenatedCourse = result.course.getCourseId() + " " + result.course.getCourseName();
            reportDataList.add(new ReportData(concatenatedCourse, result.course.getCredits(), result.getMark(), result.getAcadYear(), GPA)); 
        }

        return reportDataList; 
    }
}
