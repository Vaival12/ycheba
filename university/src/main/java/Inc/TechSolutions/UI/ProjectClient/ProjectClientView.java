package Inc.TechSolutions.UI.ProjectClient;

import Inc.TechSolutions.Control.ClientController;
import Inc.TechSolutions.Control.ProjectClientController;
import Inc.TechSolutions.Control.ProjectController;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.Client;
import Inc.TechSolutions.Entity.Project;
import Inc.TechSolutions.Entity.ProjectClient;
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

@Route(value = "projectclient",layout = MainLayuot.class)
@PageTitle("ProjectClient | TechSolutions")
@RolesAllowed({"ADMIN"})
public class ProjectClientView extends VerticalLayout {

    private final ProjectClientForm projectClientForm;
    private ProjectClientController projectClientController;

    Grid<ProjectClient> grid = new Grid<>(ProjectClient.class);
    TextField filterText=new TextField();
    public ProjectClientView(ProjectClientController projectClientController,
                             ClientController clientController,
                             ProjectController projectController) {
        this.projectClientController=projectClientController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        projectClientForm=new ProjectClientForm(clientController.readAllClients(),projectController.readAllProject());
        projectClientForm.addListener(ProjectClientForm.SaveEvent.class,this::saveProjectClient);
        projectClientForm.addListener(ProjectClientForm.DeleteEvent.class,this::deleteProjectClient);
        projectClientForm.addListener(ProjectClientForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,projectClientForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();

        closeEditor();
    }

    private void saveProjectClient(ProjectClientForm.SaveEvent saveEvent) {
        projectClientController.addProjectClient(saveEvent.getProjectClient());
        updateList();
        closeEditor();
    }

    private void deleteProjectClient(ProjectClientForm.DeleteEvent deleteEvent) {
        projectClientController.deleteProjectClient(deleteEvent.getProjectClient().getId());
        updateList();
        closeEditor();
    }
    private void closeEditor() {
        projectClientForm.setProjectClient(null);
        projectClientForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addProjectClientButton= new Button("Add Project Client",click->addProjectClient());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addProjectClientButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addProjectClient() {
        grid.asSingleSelect().clear();
        updateProjectClient(new ProjectClient());
    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();

        grid.removeColumnByKey("client");
        grid.removeColumnByKey("project");
        grid.setColumns("id");
        grid.addColumn(contact->{
            Client client=contact.getClient();
            return client==null ? "-" : client.getId();
        }).setHeader("ClientId");
        grid.addColumn(contact->{
            Project project=contact.getProject();
            return project==null ? "-" : project.getId();
        }).setHeader("ProjectId");

        grid.asSingleSelect().addValueChangeListener(evt->updateProjectClient(evt.getValue()));
    }

    private void updateProjectClient(ProjectClient projectClient) {
        if(projectClient==null) {
            closeEditor();
        } else {
            projectClientForm.setProjectClient(projectClient);
            projectClientForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void updateList()
    {
        grid.setItems(projectClientController.readAllProjectClient());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(projectClientController.getProjectClientById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(projectClientController.getProjectClientById(Long.parseLong(filterText.getValue())));
    }
}
