# PDF Report Generator

This Java program demonstrates how to generate a report in PDF format using **JasperReports 7.0.1**. It features a simple user interface where an administrative user can load and fill a precompiled Jasper report document and export the result to a PDF file.

---

## Features

- **Generate PDF Reports**: Create professional-looking university reports with course details, credits, grades, and marks.
- **Simple UI**: User-friendly interface to interact with the report generator.
- **Custom Data Models**: Use Java objects to populate the report fields dynamically.
- **Powered by JasperReports**: Seamlessly integrates the JasperReports 7.0.1 library for report generation.

---

## Prerequisites

To use this program, ensure the following are installed:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Apache Maven**: For dependency management (optional).

---

## Dependencies

Add the following dependencies to your `pom.xml` if you're using Maven:

```xml
<dependencies>
    <!-- JasperReports Library -->
    <dependency>
        <groupId>net.sf.jasperreports</groupId>
        <artifactId>jasperreports</artifactId>
        <version>7.0.1</version>
    </dependency>

    <!-- Commons BeanUtils -->
    <dependency>
        <groupId>commons-beanutils</groupId>
        <artifactId>commons-beanutils</artifactId>
        <version>1.9.2</version>
    </dependency>

    <!-- Commons Collections -->
    <dependency>
        <groupId>commons-collections</groupId>
        <artifactId>commons-collections4</artifactId>
        <version>4.1</version>
    </dependency>

    <!-- Apache Commons Digester -->
    <dependency>
        <groupId>commons-digester</groupId>
        <artifactId>commons-digester</artifactId>
        <version>2.1</version>
    </dependency>

    <!-- OpenPDF -->
    <dependency>
        <groupId>com.github.librepdf</groupId>
        <artifactId>openpdf</artifactId>
        <version>1.3.32</version>
    </dependency>
</dependencies>
