package Inc.TechSolutions.UI.Address;

import Inc.TechSolutions.Control.AddressController;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.Address;
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

@Route(value = "address",layout = MainLayuot.class)
@PageTitle("Address | TechSolutions")
@RolesAllowed({"ADMIN"})
public class AddressView extends VerticalLayout {

    private final AddressForm addressForm;
    private AddressController addressController;

    Grid<Address> grid = new Grid<>(Address.class);
    TextField filterText=new TextField();
    public AddressView(AddressController addressController) {
        this.addressController=addressController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        addressForm=new AddressForm();
        addressForm.addListener(AddressForm.SaveEvent.class,this::saveAddress);
        addressForm.addListener(AddressForm.DeleteEvent.class,this::deleteAddress);
        addressForm.addListener(AddressForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,addressForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();

        closeEditor();
    }

    private void saveAddress(AddressForm.SaveEvent saveEvent) {
        addressController.addAddress(saveEvent.getAddress());
        updateList();
        closeEditor();
    }

    private void deleteAddress(AddressForm.DeleteEvent deleteEvent) {
        addressController.deleteAddress(deleteEvent.getAddress().getId());
        updateList();
        closeEditor();
    }
    private void closeEditor() {
        addressForm.setAddress(null);
        addressForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addAddressButton= new Button("Add Address",click->addAddress());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addAddressButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addAddress() {
        grid.asSingleSelect().clear();
        updateAddress(new Address());
    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("id","street","city");

        grid.asSingleSelect().addValueChangeListener(evt->updateAddress(evt.getValue()));
    }

    private void updateAddress(Address address) {
        if(address==null) {
            closeEditor();
        } else {
            addressForm.setAddress(address);
            addressForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void updateList()
    {
        grid.setItems(addressController.readAllAddress());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(addressController.getAddressById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(addressController.getAddressById(Long.parseLong(filterText.getValue())));
    }
}
