package Inc.TechSolutions.UI.EmployeeProject;

import Inc.TechSolutions.Entity.*;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;


public class EmployeeProjectForm extends FormLayout {

    ComboBox<Employee> employee = new ComboBox<>("Employee");
    ComboBox<Project> project = new ComboBox<>("Project");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<EmployeeProject> binder=new BeanValidationBinder<>(EmployeeProject.class);
    public EmployeeProjectForm(List<Employee> employees,List<Project> projects) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        employee.setItems(employees);
        project.setItems(projects);
        employee.setItemLabelGenerator(Employee::getIdString);
        project.setItemLabelGenerator(Project::getIdString);

        add(
                employee,
                project,
                createButtonLayout()
        );
    }

    public void setEmployeeProject(EmployeeProject employeeProject)
    {
        binder.setBean(employeeProject);
    }
    private Component createButtonLayout()
    {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new EmployeeProjectForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new EmployeeProjectForm.CloseEvent(this)));

        return new HorizontalLayout(save,delete,close);
    }

    private void validateAndSave(){
        if(binder.isValid()) {
            fireEvent(new EmployeeProjectForm.SaveEvent(this, binder.getBean()));
        }
    }

    // Events

    public static abstract class EmployeeProjectFormEvent extends ComponentEvent<EmployeeProjectForm>{
        private EmployeeProject employeeProject;

        protected EmployeeProjectFormEvent(EmployeeProjectForm source,EmployeeProject employeeProject){
            super(source,false);
            this.employeeProject=employeeProject;
        }

        public EmployeeProject getEmployeeProject() {
            return employeeProject;
        }
    }

    public static class SaveEvent extends EmployeeProjectForm.EmployeeProjectFormEvent {
        SaveEvent(EmployeeProjectForm source,EmployeeProject employeeProject)
        {
            super(source, employeeProject);
        }
    }

    public static class DeleteEvent extends EmployeeProjectForm.EmployeeProjectFormEvent {
        DeleteEvent(EmployeeProjectForm source,EmployeeProject employeeProject)
        {
            super(source, employeeProject);
        }
    }

    public static class CloseEvent extends EmployeeProjectForm.EmployeeProjectFormEvent {
        CloseEvent(EmployeeProjectForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}