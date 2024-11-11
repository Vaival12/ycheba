package Inc.TechSolutions.UI.Employee;

import Inc.TechSolutions.Entity.Address;
import Inc.TechSolutions.Entity.Department;
import Inc.TechSolutions.Entity.Employee;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;


public class EmployeeForm extends FormLayout {

    TextField name=new TextField("Name");
    ComboBox<Department> department = new ComboBox<>("Department");
    ComboBox<Address> address = new ComboBox<>("Address");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<Employee> binder=new BeanValidationBinder<>(Employee.class);
    public EmployeeForm(List<Department> departments,List<Address> addresses) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        department.setItems(departments);
        address.setItems(addresses);
        department.setItemLabelGenerator(Department::getIdString);
        address.setItemLabelGenerator(Address::getIdString);

        add(
                name,
                department,
                address,
                createButtonLayout()
        );
    }

    public void setEmployee(Employee employee)
    {
        binder.setBean(employee);
    }
    private Component createButtonLayout()
    {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new EmployeeForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new EmployeeForm.CloseEvent(this)));

        //binder.addValueChangeListener(evt->save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save,delete,close);
    }

    private void validateAndSave(){
        if(binder.isValid()) {
            fireEvent(new EmployeeForm.SaveEvent(this, binder.getBean()));
        }
    }

    // Events

    public static abstract class EmployeeFormEvent extends ComponentEvent<EmployeeForm>{
        private Employee employee;

        protected EmployeeFormEvent(EmployeeForm source,Employee employee){
            super(source,false);
            this.employee=employee;
        }

        public Employee getEmployee() {
            return employee;
        }
    }

    public static class SaveEvent extends EmployeeForm.EmployeeFormEvent {
        SaveEvent(EmployeeForm source,Employee employee)
        {
            super(source, employee);
        }
    }

    public static class DeleteEvent extends EmployeeForm.EmployeeFormEvent {
        DeleteEvent(EmployeeForm source,Employee employee)
        {
            super(source, employee);
        }
    }

    public static class CloseEvent extends EmployeeForm.EmployeeFormEvent {
        CloseEvent(EmployeeForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}