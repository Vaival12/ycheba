package Inc.TechSolutions.UI.ProjectClient;

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


public class ProjectClientForm extends FormLayout {

    ComboBox<Client> client = new ComboBox<>("Client");
    ComboBox<Project> project = new ComboBox<>("Project");


    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<ProjectClient> binder=new BeanValidationBinder<>(ProjectClient.class);
    public ProjectClientForm(List<Client> clients,List<Project> projects) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        client.setItems(clients);
        project.setItems(projects);
        client.setItemLabelGenerator(Client::getIdString);
        project.setItemLabelGenerator(Project::getIdString);

        add(
                client,
                project,
                createButtonLayot()
        );
    }

    public void setProjectClient(ProjectClient projectClient)
    {
        binder.setBean(projectClient);

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

    public static abstract class ProjectClientFormEvent extends ComponentEvent<ProjectClientForm>{
        private ProjectClient projectClient;

        protected ProjectClientFormEvent(ProjectClientForm source,ProjectClient projectClient){
            super(source,false);
            this.projectClient=projectClient;
        }

        public ProjectClient getProjectClient() {
            return projectClient;
        }
    }

    public static class SaveEvent extends ProjectClientFormEvent{
        SaveEvent(ProjectClientForm source,ProjectClient projectClient)
        {
            super(source, projectClient);
        }
    }

    public static class DeleteEvent extends ProjectClientFormEvent{
        DeleteEvent(ProjectClientForm source,ProjectClient projectClient)
        {
            super(source, projectClient);
        }
    }

    public static class CloseEvent extends ProjectClientFormEvent{
        CloseEvent(ProjectClientForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}
