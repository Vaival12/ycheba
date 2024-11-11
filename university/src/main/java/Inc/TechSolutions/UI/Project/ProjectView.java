package Inc.TechSolutions.UI.Project;

import Inc.TechSolutions.Control.ProjectController;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.Project;
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

@Route(value = "project",layout = MainLayuot.class)
@PageTitle("Project | TechSolutions")
@RolesAllowed({"ADMIN","EMPLOYEE"})
public class ProjectView extends VerticalLayout {

    private final ProjectForm projectForm;
    private ProjectController projectController;

    Grid<Project> grid = new Grid<>(Project.class);
    TextField filterText=new TextField();
    public ProjectView(ProjectController projectController) {
        this.projectController=projectController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        projectForm=new ProjectForm();
        projectForm.addListener(ProjectForm.SaveEvent.class,this::saveProject);
        projectForm.addListener(ProjectForm.DeleteEvent.class,this::deleteProject);
        projectForm.addListener(ProjectForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,projectForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();

        closeEditor();
    }

    private void saveProject(ProjectForm.SaveEvent saveEvent) {
        projectController.addProject(saveEvent.getProject());
        updateList();
        closeEditor();
    }

    private void deleteProject(ProjectForm.DeleteEvent deleteEvent) {
        projectController.deleteProject(deleteEvent.getProject().getId());
        updateList();
        closeEditor();
    }
    private void closeEditor() {
        projectForm.setProject(null);
        projectForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addProjectButton= new Button("Add Project",click->addProject());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addProjectButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addProject() {
        grid.asSingleSelect().clear();
        updateProject(new Project());
    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id","name","deadLine");

        grid.asSingleSelect().addValueChangeListener(evt->updateProject(evt.getValue()));
    }

    private void updateProject(Project project) {
        if(project==null) {
            closeEditor();
        } else {
            projectForm.setProject(project);
            projectForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void updateList()
    {
        grid.setItems(projectController.readAllProject());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(projectController.getProjectById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(projectController.getProjectById(Long.parseLong(filterText.getValue())));
    }
}
