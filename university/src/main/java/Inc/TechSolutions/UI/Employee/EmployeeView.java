package Inc.TechSolutions.UI.Employee;

import Inc.TechSolutions.Control.AddressController;
import Inc.TechSolutions.Control.DepartmentController;
import Inc.TechSolutions.Control.EmployeeController;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.Address;
import Inc.TechSolutions.Entity.Department;
import Inc.TechSolutions.Entity.Employee;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "employee",layout = MainLayuot.class)
@PageTitle("Employee | TechSolutions")
@RolesAllowed({"ADMIN"})
public class EmployeeView extends VerticalLayout {

    private final EmployeeForm employeeForm;
    private EmployeeController employeeController;

    Grid<Employee> grid = new Grid<>(Employee.class);
    TextField filterText=new TextField();
    public EmployeeView(EmployeeController employeeController,
                        DepartmentController departmentController,
                        AddressController addressController) {
        this.employeeController=employeeController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        employeeForm=new EmployeeForm(departmentController.readAllDepartment(),addressController.readAllAddress());
        employeeForm.addListener(EmployeeForm.SaveEvent.class,this::saveEmployee);
        employeeForm.addListener(EmployeeForm.DeleteEvent.class,this::deleteEmployees);
        employeeForm.addListener(EmployeeForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,employeeForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        employeeForm.setEmployee(null);
        employeeForm.setVisible(false);
        removeClassName("editing");
    }

    private void saveEmployee(EmployeeForm.SaveEvent saveEvent) {
        employeeController.addEmployee(saveEvent.getEmployee());
        updateList();
        closeEditor();
    }

    private void deleteEmployees(EmployeeForm.DeleteEvent deleteEvent) {
        employeeController.deleteEmployee(deleteEvent.getEmployee().getId());
        updateList();
        closeEditor();
    }
    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("department");
        grid.removeColumnByKey("address");
        grid.setColumns("id","name");
        grid.addColumn(contact->{
            Department department=contact.getDepartment();
            return department==null ? "-" : department.getId();
        }).setHeader("DepartmentId");
        grid.addColumn(contact->{
            Address address=contact.getAddress();
            return address==null ? "-" : address.getId();
        }).setHeader("AddressId");

        grid.getColumns().forEach(col->col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt->updateEmployee(evt.getValue()));
    }

    private void updateEmployee(Employee employee) {
        if(employee==null) {
            closeEditor();
        } else {
            employeeForm.setEmployee(employee);
            employeeForm.setVisible(true);
            addClassName("editing");
        }
    }
    private void updateList()
    {
        grid.setItems(employeeController.readAllEmployee());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(employeeController.getEmployeeById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(employeeController.getEmployeeById(Long.parseLong(filterText.getValue())));
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addEmployeeButton= new Button("Add Employee",click->addEmployee());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addEmployeeButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addEmployee() {
        grid.asSingleSelect().clear();
        updateEmployee(new Employee());
    }


}