package Inc.TechSolutions.ApplicationServices;

import Inc.TechSolutions.Entity.*;
import Inc.TechSolutions.dao.Address.AddressRepository;
import Inc.TechSolutions.dao.Client.ClientRepository;
import Inc.TechSolutions.dao.Department.DepartmentRepository;
import Inc.TechSolutions.dao.Employee.EmployeeRepository;
import Inc.TechSolutions.dao.EmployeeProject.EmployeeProjectRepository;
import Inc.TechSolutions.dao.Payment.PaymentRepository;
import Inc.TechSolutions.dao.Project.ProjectRepository;
import Inc.TechSolutions.dao.ProjectClient.ProjectClientRepository;
import Inc.TechSolutions.dao.Task.TaskRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportServiceImpl {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectClientRepository projectClientRepository;

    private static final String FILE_PATH = "report.pdf";

    public byte[] generatePdfReport() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Task
            List<Task> tasks = taskRepository.findAll();
            Table table = new Table(new float[]{1, 2, 2,2});
            table.addHeaderCell("ID");
            table.addHeaderCell("Name");
            table.addHeaderCell("Description");
            table.addHeaderCell("ProjectId");

            for (Task task : tasks) {
                table.addCell(String.valueOf(task.getId()));
                table.addCell(String.valueOf(task.getName()));
                table.addCell(String.valueOf(task.getDescription()));
                table.addCell(String.valueOf(task.getProject().getIdString()));
            }

            // Address
            List<Address> addresses=addressRepository.findAll();
            Table table1 = new Table(new float[]{1, 2, 2});
            table1.addHeaderCell("ID");
            table1.addHeaderCell("Street");
            table1.addHeaderCell("City");

            for (Address address : addresses) {
                table1.addCell(String.valueOf(address.getId()));
                table1.addCell(String.valueOf(address.getStreet()));
                table1.addCell(String.valueOf(address.getCity()));
            }

            // Client
            List<Client> clients=clientRepository.findAll();
            Table table2 = new Table(new float[]{1, 2, 2,2});
            table2.addHeaderCell("ID");
            table2.addHeaderCell("Name");
            table2.addHeaderCell("DateOfBirth");
            table2.addHeaderCell("Gender");


            for (Client client : clients) {
                table2.addCell(String.valueOf(client.getId()));
                table2.addCell(String.valueOf(client.getName()));
                table2.addCell(String.valueOf(client.getDateOfBirth()));
                table2.addCell(String.valueOf(client.getGender()));
            }

            // Department
            List<Department> departments=departmentRepository.findAll();
            Table table3 = new Table(new float[]{1, 2, 2});
            table3.addHeaderCell("ID");
            table3.addHeaderCell("Name");
            table3.addHeaderCell("CountEmployee");

            for (Department department : departments) {
                table3.addCell(String.valueOf(department.getId()));
                table3.addCell(String.valueOf(department.getName()));
                table3.addCell(String.valueOf(department.getCountEmployee()));
            }

            // Employee
            List<Employee> employees=employeeRepository.findAll();
            Table table4 = new Table(new float[]{1, 2, 2,2});
            table4.addHeaderCell("ID");
            table4.addHeaderCell("Name");
            table4.addHeaderCell("department_id");
            table4.addHeaderCell("address_id");

            for (Employee employee : employees) {
                table4.addCell(String.valueOf(employee.getId()));
                table4.addCell(String.valueOf(employee.getName()));
                table4.addCell(String.valueOf(employee.getDepartment().getId()));
                table4.addCell(String.valueOf(employee.getAddress().getId()));
            }

            // EmployeeProject
            List<EmployeeProject> employeeProjects=employeeProjectRepository.findAll();
            Table table5 = new Table(new float[]{1, 2, 2});
            table5.addHeaderCell("ID");
            table5.addHeaderCell("Employee_id");
            table5.addHeaderCell("Project_id");

            for (EmployeeProject employeeProject : employeeProjects) {
                table5.addCell(String.valueOf(employeeProject.getId()));
                table5.addCell(String.valueOf(employeeProject.getEmployee().getId()));
                table5.addCell(String.valueOf(employeeProject.getProject().getId()));
            }

            // Payment
            List<Payment> payments=paymentRepository.findAll();
            Table table6 = new Table(new float[]{1, 2, 2});
            table6.addHeaderCell("ID");
            table6.addHeaderCell("Money");
            table6.addHeaderCell("Client_id");

            Integer allMoney=0;

            for (Payment payment : payments) {
                table6.addCell(String.valueOf(payment.getId()));
                table6.addCell(String.valueOf(payment.getMoney()));
                allMoney+=payment.getMoney();
                table6.addCell(String.valueOf(payment.getClient().getId()));
            }

            // Project
            List<Project> projects=projectRepository.findAll();
            Table table7 = new Table(new float[]{1, 2, 2});
            table7.addHeaderCell("ID");
            table7.addHeaderCell("Name");
            table7.addHeaderCell("Deadline");

            for (Project project : projects) {
                table7.addCell(String.valueOf(project.getId()));
                table7.addCell(String.valueOf(project.getName()));
                table7.addCell(String.valueOf(project.getDeadLine()));
            }

            // ProjectClient
            List<ProjectClient> projectClients=projectClientRepository.findAll();
            Table table8 = new Table(new float[]{1, 2, 2,2});
            table8.addHeaderCell("ID");
            table8.addHeaderCell("Name");
            table8.addHeaderCell("Project_id");
            table8.addHeaderCell("Client_id");


            for (ProjectClient projectClient : projectClients) {
                table8.addCell(String.valueOf(projectClient.getId()));
                table8.addCell(String.valueOf(projectClient.getName()));
                table8.addCell(String.valueOf(projectClient.getProject().getId()));
                table8.addCell(String.valueOf(projectClient.getClient().getId()));

            }

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            // Добавляем в документ
            document.add(new Paragraph("TechSolutions Inc").setFont(font).setFontSize(18).setFontColor(ColorConstants.BLUE).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Description").setBold().setUnderline().setFontSize(15).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("The application \"Tech Solutions Inc\" has been developed, which is engaged in the development of technological solutions in the Java language. The PostgreSQL DBMS is used for data storage. Data is accessed using Spring JPA. WebUI on Vaadin is used for data management. The application must store employee attributes, department attributes, client attributes, client payment data, employee residence data, employee project data, client project data, and task data in the project."));
            document.add(new Paragraph("Reports").setBold().setUnderline().setFontSize(15).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Address"));
            document.add(table1);
            document.add(new Paragraph("Client"));
            document.add(table2);
            document.add(new Paragraph("Department"));
            document.add(table3);
            document.add(new Paragraph("Employee"));
            document.add(table4);
            document.add(new Paragraph("EmployeeProject"));
            document.add(table5);
            document.add(new Paragraph("Payment"));
            document.add(table6);
            document.add(new Paragraph("Total amount of funds: "+String.valueOf(allMoney)));
            document.add(new Paragraph("Project"));
            document.add(table7);
            document.add(new Paragraph("ProjectClient"));
            document.add(table8);
            document.add(new Paragraph("Task"));
            document.add(table);

            // Закрываем документ
            document.close();

            // Сохраняем PDF на диск
            savePdfToFile(outputStream.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    // Метод для сохранения PDF на сервере
    private void savePdfToFile(byte[] pdfBytes) {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            fos.write(pdfBytes);
            System.out.println("Отчет успешно сохранен!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}