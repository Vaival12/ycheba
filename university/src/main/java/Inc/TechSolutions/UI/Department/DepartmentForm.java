package Inc.TechSolutions.UI.Department;

import Inc.TechSolutions.Entity.Department;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;


public class DepartmentForm extends FormLayout {

    TextField name=new TextField("Name");
    TextField countEmployee= new TextField("Count Employee");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<Department> binder=new BeanValidationBinder<>(Department.class);
    public DepartmentForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(
                name,
                countEmployee,
                createButtonLayot()
        );
    }

    public void setDepartment(Department department)
    {
        binder.setBean(department);

    }
    private Component createButtonLayot()
    {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save,delete,close);
    }

    private void validateAndSave(){
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events

    public static abstract class DepartmentFormEvent extends ComponentEvent<DepartmentForm>{
        private Department department;

        protected DepartmentFormEvent(DepartmentForm source,Department department){
            super(source,false);
            this.department=department;
        }

        public Department getDepartment() {
            return department;
        }
    }

    public static class SaveEvent extends DepartmentFormEvent{
        SaveEvent(DepartmentForm source,Department department)
        {
            super(source, department);
        }
    }

    public static class DeleteEvent extends DepartmentFormEvent{
        DeleteEvent(DepartmentForm source,Department department)
        {
            super(source, department);
        }
    }

    public static class CloseEvent extends DepartmentFormEvent{
        CloseEvent(DepartmentForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}
