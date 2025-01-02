import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Preload JFileChooser in a background thread
            final JFileChooser[] fileChooser = new JFileChooser[1];
            SwingWorker<JFileChooser, Void> fileChooserPreloader = new SwingWorker<>() {
                @Override
                protected JFileChooser doInBackground() {
                    JFileChooser preloadedFileChooser = new JFileChooser();
                    preloadedFileChooser.setDialogTitle("Generate Report");
                    preloadedFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    return preloadedFileChooser;
                }

                @Override
                protected void done() {
                    try {
                        fileChooser[0] = get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            fileChooserPreloader.execute();

            // Create sample courses and student
            List<Course> courses = createSampleCourses();
            AcademicYear year = new AcademicYear(2024, 2025);
            String acadYear = year.toString();
            Student student = createSampleStudent(courses, acadYear);

            // Create main frame
            JFrame frame = new JFrame("Report Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLayout(new BorderLayout(10, 10));

            // Create table model and table
            DefaultTableModel model = createTableModel(student.getResults());
            JTable table = new JTable(model);
            table.setAutoCreateRowSorter(true); // Enable column sorting
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Course Results"));

            // Create student info panel
            JPanel infoPanel = createStudentInfoPanel(student);

            // Create button panel
            JPanel buttonPanel = new JPanel();
            JButton reportButton = new JButton("Generate Report");

            reportButton.addActionListener(e -> {
                reportButton.setText("Please wait...");
                reportButton.setEnabled(false); // Disable button during report generation
                

                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        try {
                            if (generateReport(student, fileChooser[0])) // Generate the report
                                JOptionPane.showMessageDialog(frame, "Report generated successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
                        } catch (JRException ex) {
                            ex.printStackTrace();
                            SwingUtilities.invokeLater(() ->
                                JOptionPane.showMessageDialog(frame, "Error generating report!", "Error", JOptionPane.ERROR_MESSAGE)
                            );
                        }
                        reportButton.setText("Generate Report");
                        return null;
                    }

                    @Override
                    protected void done() {
                        reportButton.setEnabled(true); // Re-enable button after task completion
                    }
                };

                worker.execute(); // Start the background task
            });

            buttonPanel.add(reportButton);

            // Add components to the frame
            frame.add(infoPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // Show frame
            frame.setVisible(true);
        });
    }

    private static boolean generateReport(Student student, JFileChooser preloadedFileChooser) throws JRException {
        if (preloadedFileChooser == null) {
            JOptionPane.showMessageDialog(null, "File chooser is not initialized.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        List<ReportData> reportDataList = ReportDataPreparer.prepareData(student, student.getResults());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportDataList);
        String jasperFilePath = "report.jasper";
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, null, dataSource);

        preloadedFileChooser.setSelectedFile(new java.io.File(student.getStudentNumber() + "_report.pdf")); // Default name
        int userSelection = preloadedFileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = preloadedFileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }
            
            // Check if the file already exists
            if (fileToSave.exists()) {
               int overwriteSelection = JOptionPane.showConfirmDialog(
                   null,
                   "The file \"" + fileToSave.getName() + "\" already exists. Do you want to overwrite it?",
                   "Warning",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.WARNING_MESSAGE
               );

            if (overwriteSelection != JOptionPane.YES_OPTION) {
                return false; // User chose not to overwrite
            }
        }

            JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);
            return true;
        }
        return false;
    }

    private static List<Course> createSampleCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CHM122", "Chemistry for Engineers IA", 16, 1));
        courses.add(new Course("CIV101", "Introduction to Engineering", 16, 1));
        courses.add(new Course("CIV182", "Engineering Design I", 8, 2));
        courses.add(new Course("HUM178", "Social Sciences", 16, 2));
        courses.add(new Course("PHY147", "Applied Physics", 16, 2));
        courses.add(new Course("MEC136", "Engineering Mechanics I", 16, 1));
        courses.add(new Course("MAT121", "Engineering Maths IA", 16, 1));
        courses.add(new Course("CIV159", "Structural Analysis", 16, 2));
        courses.add(new Course("INF160", "Information Technology", 8, 1));
        courses.add(new Course("CIV119", "Strength of Materials", 16, 2));
        return courses;
    }

    private static Student createSampleStudent(List<Course> courses, String acadYear) {
        Student student = new Student("John Doe", "202400001", "BSc. in Civil Engineering",
                "Engineering and the Built Environment", 1, courses);
        List<Result> results = new ArrayList<>();
        Random random = new Random();
        for (Course course : courses) {
            results.add(new Result(course, getRandomMark(random), acadYear));
        }
        student.addResults(results);
        return student;
    }

    private static DefaultTableModel createTableModel(List<Result> results) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Course ID");
        model.addColumn("Course Name");
        model.addColumn("Credits");
        model.addColumn("Mark");
        model.addColumn("Grade");
        for (Result result : results) {
            model.addRow(new Object[]{
                    result.getCourse().getCourseId(),
                    result.getCourse().getCourseName(),
                    result.getCourse().getCredits(),
                    result.getMark(),
                    result.getGrade()
            });
        }
        return model;
    }

    private static JPanel createStudentInfoPanel(Student student) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        panel.add(new JLabel(" Name: " + student.getName()));
        panel.add(new JLabel(" Student Number: " + student.getStudentNumber()));
        panel.add(new JLabel(" Programme: " + student.getDegreeName()));
        panel.add(new JLabel(" Year: " + student.getYearOfStudy()));
        return panel;
    }

    public static int getRandomMark(Random rand) {
        return rand.nextInt(40) + 60; // Random number between 60 and 100
    }
}
