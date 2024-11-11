package Inc.TechSolutions.UI.Task;

import Inc.TechSolutions.Entity.Project;
import Inc.TechSolutions.Entity.Task;
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


public class TaskForm extends FormLayout {

    TextField name=new TextField("Name");
    TextField description= new TextField("Description");

    ComboBox<Project> project = new ComboBox<>("Project");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<Task> binder=new BeanValidationBinder<>(Task.class);
    public TaskForm(List<Project> projects) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        project.setItems(projects);
        project.setItemLabelGenerator(Project::getIdString);

        add(
                name,
                description,
                project,
                createButtonLayot()
        );
    }

    public void setTask(Task task)
    {
        binder.setBean(task);

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

    public static abstract class TaskFormEvent extends ComponentEvent<TaskForm>{
        private Task task;

        protected TaskFormEvent(TaskForm source,Task task){
            super(source,false);
            this.task=task;
        }

        public Task getTask() {
            return task;
        }
    }

    public static class SaveEvent extends TaskFormEvent{
        SaveEvent(TaskForm source,Task task)
        {
            super(source, task);
        }
    }

    public static class DeleteEvent extends TaskFormEvent{
        DeleteEvent(TaskForm source,Task task)
        {
            super(source, task);
        }
    }

    public static class CloseEvent extends TaskFormEvent{
        CloseEvent(TaskForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}
