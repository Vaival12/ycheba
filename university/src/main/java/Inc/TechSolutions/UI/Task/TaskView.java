package Inc.TechSolutions.UI.Task;

import Inc.TechSolutions.Control.ProjectController;
import Inc.TechSolutions.Control.TaskController;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.Project;
import Inc.TechSolutions.Entity.Task;
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

@Route(value = "task",layout = MainLayuot.class)
@PageTitle("Task | TechSolutions")
@RolesAllowed({"ADMIN","EMPLOYEE"})
public class TaskView extends VerticalLayout {

    private final TaskForm taskForm;
    private TaskController taskController;

    Grid<Task> grid = new Grid<>(Task.class);
    TextField filterText=new TextField();
    public TaskView(TaskController taskController,
                    ProjectController projectController) {
        this.taskController=taskController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        taskForm=new TaskForm(projectController.readAllProject());
        taskForm.addListener(TaskForm.SaveEvent.class,this::saveTask);
        taskForm.addListener(TaskForm.DeleteEvent.class,this::deleteTask);
        taskForm.addListener(TaskForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,taskForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();

        closeEditor();
    }

    private void saveTask(TaskForm.SaveEvent saveEvent) {
        taskController.addTask(saveEvent.getTask());
        updateList();
        closeEditor();
    }

    private void deleteTask(TaskForm.DeleteEvent deleteEvent) {
        taskController.deleteTask(deleteEvent.getTask().getId());
        updateList();
        closeEditor();
    }
    private void closeEditor() {
        taskForm.setTask(null);
        taskForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addTaskButton= new Button("Add Task",click->addTask());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addTaskButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addTask() {
        grid.asSingleSelect().clear();
        updateTask(new Task());
    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("project");
        grid.setColumns("id","name","description");

        grid.addColumn(contact->{
            Project project=contact.getProject();
            return project==null ? "-" : project.getId();
        }).setHeader("ProjectId");

        grid.asSingleSelect().addValueChangeListener(evt->updateTask(evt.getValue()));
    }

    private void updateTask(Task task) {
        if(task==null) {
            closeEditor();
        } else {
            taskForm.setTask(task);
            taskForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void updateList()
    {
        grid.setItems(taskController.readAllTask());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(taskController.getTaskById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(taskController.getTaskById(Long.parseLong(filterText.getValue())));
    }
}
