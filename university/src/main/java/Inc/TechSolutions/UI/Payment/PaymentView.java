package Inc.TechSolutions.UI.Payment;

import Inc.TechSolutions.Control.ClientController;
import Inc.TechSolutions.Control.PaymentController;
import Inc.TechSolutions.UI.MainLayuot;
import Inc.TechSolutions.Entity.Client;
import Inc.TechSolutions.Entity.Payment;
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

@Route(value = "payment",layout = MainLayuot.class)
@PageTitle("Payment | TechSolutions")
@RolesAllowed({"ADMIN"})
public class PaymentView extends VerticalLayout {

    private final PaymentForm paymentForm;
    private PaymentController paymentController;

    Grid<Payment> grid = new Grid<>(Payment.class);
    TextField filterText=new TextField();
    public PaymentView(PaymentController paymentController,
                    ClientController clientController) {
        this.paymentController=paymentController;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        paymentForm=new PaymentForm(clientController.readAllClients());
        paymentForm.addListener(PaymentForm.SaveEvent.class,this::savePayment);
        paymentForm.addListener(PaymentForm.DeleteEvent.class,this::deletePayment);
        paymentForm.addListener(PaymentForm.CloseEvent.class,e->closeEditor());

        Div content=new Div(grid,paymentForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(),content);
        updateList();

        closeEditor();
    }

    private void savePayment(PaymentForm.SaveEvent saveEvent) {
        paymentController.addPayment(saveEvent.getPayment());
        updateList();
        closeEditor();
    }

    private void deletePayment(PaymentForm.DeleteEvent deleteEvent) {
        paymentController.deletePayment(deleteEvent.getPayment().getId());
        updateList();
        closeEditor();
    }
    private void closeEditor() {
        paymentForm.setPayment(null);
        paymentForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateListFilter());

        Button addPaymentButton= new Button("Add Payment",click->addPayment());

        HorizontalLayout toolbar=new HorizontalLayout(filterText,addPaymentButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addPayment() {
        grid.asSingleSelect().clear();
        updatePayment(new Payment());
    }

    private void configureGrid()
    {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("client");
        grid.setColumns("id","money");

        grid.addColumn(contact->{
            Client client=contact.getClient();
            return client==null ? "-" : client.getId();
        }).setHeader("ClientId");

        grid.asSingleSelect().addValueChangeListener(evt->updatePayment(evt.getValue()));
    }

    private void updatePayment(Payment payment) {
        if(payment==null) {
            closeEditor();
        } else {
            paymentForm.setPayment(payment);
            paymentForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void updateList()
    {
        grid.setItems(paymentController.readAllPayment());
    }
    private void updateListFilter()
    {
        if(filterText.isEmpty())
            updateList();
        else if(paymentController.getPaymentById(Long.parseLong(filterText.getValue()))==null)
            updateList();
        else grid.setItems(paymentController.getPaymentById(Long.parseLong(filterText.getValue())));
    }
}
