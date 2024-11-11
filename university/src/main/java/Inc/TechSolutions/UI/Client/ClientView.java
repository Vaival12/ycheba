package Inc.TechSolutions.UI.Client;

import Inc.TechSolutions.Control.ClientController;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.Client;
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

@Route(value = "client",layout = MainLayuot.class)
@PageTitle("Client | TechSolutions")
@RolesAllowed({"ADMIN"})
public class ClientView extends VerticalLayout {

    private final ClientForm clientForm;
    private ClientController clientController;

    Grid<Client> grid = new Grid<>(Client.class);
    TextField filterText=new TextField();
    public ClientView(ClientController clientController) {
        this.clientController=clientController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        clientForm=new ClientForm();
        clientForm.addListener(ClientForm.SaveEvent.class,this::saveClient);
        clientForm.addListener(ClientForm.DeleteEvent.class,this::deleteClient);
        clientForm.addListener(ClientForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,clientForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();

        closeEditor();
    }

    private void saveClient(ClientForm.SaveEvent saveEvent) {
        clientController.addClient(saveEvent.getClient());
        updateList();
        closeEditor();
    }

    private void deleteClient(ClientForm.DeleteEvent deleteEvent) {
        clientController.deleteClient(deleteEvent.getClient().getId());
        updateList();
        closeEditor();
    }
    private void closeEditor() {
        clientForm.setClient(null);
        clientForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addClientButton= new Button("Add Client",click->addClient());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addClientButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addClient() {
        grid.asSingleSelect().clear();
        updateClient(new Client());
    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id","name","dateOfBirth","gender");

        grid.asSingleSelect().addValueChangeListener(evt->updateClient(evt.getValue()));
    }

    private void updateClient(Client client) {
        if(client==null) {
            closeEditor();
        } else {
            clientForm.setClient(client);
            clientForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void updateList()
    {
        grid.setItems(clientController.readAllClients());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(clientController.getClientById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(clientController.getClientById(Long.parseLong(filterText.getValue())));
    }
}
