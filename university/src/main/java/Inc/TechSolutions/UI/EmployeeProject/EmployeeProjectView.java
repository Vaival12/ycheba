package Inc.TechSolutions.UI.EmployeeProject;

import Inc.TechSolutions.Control.*;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.*;
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

@Route(value = "employeeproject",layout = MainLayuot.class)
@PageTitle("EmployeeProject | TechSolutions")
@RolesAllowed({"ADMIN"})
public class EmployeeProjectView extends VerticalLayout {

    private final EmployeeProjectForm employeeProjectForm;
    private EmployeeProjectController employeeProjectController;

    Grid<EmployeeProject> grid = new Grid<>(EmployeeProject.class);
    TextField filterText=new TextField();
    public EmployeeProjectView(EmployeeProjectController employeeProjectController,
                        EmployeeController employeeController,
                        ProjectController projectController) {
        this.employeeProjectController=employeeProjectController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        employeeProjectForm=new EmployeeProjectForm(employeeController.readAllEmployee(),projectController.readAllProject());
        employeeProjectForm.addListener(EmployeeProjectForm.SaveEvent.class,this::saveEmployeeProject);
        employeeProjectForm.addListener(EmployeeProjectForm.DeleteEvent.class,this::deleteEmployeeProjects);
        employeeProjectForm.addListener(EmployeeProjectForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,employeeProjectForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        employeeProjectForm.setEmployeeProject(null);
        employeeProjectForm.setVisible(false);
        removeClassName("editing");
    }

    private void saveEmployeeProject(EmployeeProjectForm.SaveEvent saveEvent) {
        employeeProjectController.addEmployeeProject(saveEvent.getEmployeeProject());
        updateList();
        closeEditor();
    }

    private void deleteEmployeeProjects(EmployeeProjectForm.DeleteEvent deleteEvent) {
        employeeProjectController.deleteEmployeeProject(deleteEvent.getEmployeeProject().getId());
        updateList();
        closeEditor();
    }
    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("employee");
        grid.removeColumnByKey("project");
        grid.setColumns("id");
        grid.addColumn(contact->{
            Employee employee=contact.getEmployee();
            return employee==null ? "-" : employee.getId();
        }).setHeader("EmployeeId");
        grid.addColumn(contact->{
            Project project=contact.getProject();
            return project==null ? "-" : project.getId();
        }).setHeader("ProjectId");

        grid.getColumns().forEach(col->col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt->updateEmployeeProject(evt.getValue()));
    }

    private void updateEmployeeProject(EmployeeProject employeeProject) {
        if(employeeProject==null) {
            closeEditor();
        } else {
            employeeProjectForm.setEmployeeProject(employeeProject);
            employeeProjectForm.setVisible(true);
            addClassName("editing");
        }
    }
    private void updateList()
    {
        grid.setItems(employeeProjectController.readAllEmployeeProject());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(employeeProjectController.getEmployeeProjectById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(employeeProjectController.getEmployeeProjectById(Long.parseLong(filterText.getValue())));
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addEmployeeProjectButton= new Button("Add Employee Project",click->addEmployeeProject());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addEmployeeProjectButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addEmployeeProject() {
        grid.asSingleSelect().clear();
        updateEmployeeProject(new EmployeeProject());
    }


}