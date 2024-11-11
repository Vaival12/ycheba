package Inc.TechSolutions.UI.Project;

import Inc.TechSolutions.Entity.Project;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;


public class ProjectForm extends FormLayout {

    TextField name=new TextField("Name");
    TextField deadLine= new TextField("Dead Line");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<Project> binder=new BeanValidationBinder<>(Project.class);
    public ProjectForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(
                name,
                deadLine,
                createButtonLayot()
        );
    }

    public void setProject(Project project)
    {
        binder.setBean(project);

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

    public static abstract class ProjectFormEvent extends ComponentEvent<ProjectForm>{
        private Project project;

        protected ProjectFormEvent(ProjectForm source,Project project){
            super(source,false);
            this.project=project;
        }

        public Project getProject() {
            return project;
        }
    }

    public static class SaveEvent extends ProjectFormEvent{
        SaveEvent(ProjectForm source,Project project)
        {
            super(source, project);
        }
    }

    public static class DeleteEvent extends ProjectFormEvent{
        DeleteEvent(ProjectForm source,Project project)
        {
            super(source, project);
        }
    }

    public static class CloseEvent extends ProjectFormEvent{
        CloseEvent(ProjectForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}
