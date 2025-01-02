public class AcademicYear {

    private int startYear;
    private int endYear;

    // Constructor with validation
    public AcademicYear(int startYear, int endYear) {
        if (startYear >= endYear) {
            throw new IllegalArgumentException("Start year must be less than end year.");
        }
        this.startYear = startYear;
        this.endYear = endYear;
    }

    // Getters
    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    // Setters with validation
    public void setStartYear(int startYear) {
        if (startYear >= this.endYear) {
            throw new IllegalArgumentException("Start year must be less than end year.");
        }
        this.startYear = startYear;
    }

    public void setEndYear(int endYear) {
        if (endYear <= this.startYear) {
            throw new IllegalArgumentException("End year must be greater than start year.");
        }
        this.endYear = endYear;
    }

    // Checks if a given year falls within the academic year
    public boolean isWithinYear(int year) {
        return year >= startYear && year <= endYear;
    }

    @Override
    public String toString() {
        return startYear + "/" + endYear;
    }
}
