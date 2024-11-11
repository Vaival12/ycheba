package Inc.TechSolutions.UI.Department;

import Inc.TechSolutions.Control.DepartmentController;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.Department;
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

@Route(value = "",layout = MainLayuot.class)
@PageTitle("Departments | TechSolutions")
@RolesAllowed({"ADMIN"})
public class DepartmentView extends VerticalLayout {

    private final DepartmentForm departmentForm;
    private DepartmentController departmentController;

    Grid<Department> grid = new Grid<>(Department.class);
    TextField filterText=new TextField();
    public DepartmentView(DepartmentController departmentController) {
        this.departmentController=departmentController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        departmentForm=new DepartmentForm();
        departmentForm.addListener(DepartmentForm.SaveEvent.class,this::saveDepartment);
        departmentForm.addListener(DepartmentForm.DeleteEvent.class,this::deleteDepartment);
        departmentForm.addListener(DepartmentForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,departmentForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();

        closeEditor();
    }

    private void saveDepartment(DepartmentForm.SaveEvent saveEvent) {
        departmentController.addDepartment(saveEvent.getDepartment());
        updateList();
        closeEditor();
    }

    private void deleteDepartment(DepartmentForm.DeleteEvent deleteEvent) {
        departmentController.deleteDepartment(deleteEvent.getDepartment().getId());
        updateList();
        closeEditor();
    }
    private void closeEditor() {
        departmentForm.setDepartment(null);
        departmentForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addDepartmentButton= new Button("Add Department",click->addDepartment());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addDepartmentButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addDepartment() {
        grid.asSingleSelect().clear();
        updateDepartment(new Department());
    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id","name","countEmployee");

        grid.asSingleSelect().addValueChangeListener(evt->updateDepartment(evt.getValue()));
    }

    private void updateDepartment(Department department) {
        if(department==null) {
            closeEditor();
        } else {
            departmentForm.setDepartment(department);
            departmentForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void updateList()
    {
        grid.setItems(departmentController.readAllDepartment());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(departmentController.getDepartmentById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(departmentController.getDepartmentById(Long.parseLong(filterText.getValue())));
    }
}
