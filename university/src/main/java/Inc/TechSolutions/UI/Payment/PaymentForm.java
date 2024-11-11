package Inc.TechSolutions.UI.Payment;

import Inc.TechSolutions.Entity.*;
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


public class PaymentForm extends FormLayout {

    TextField money=new TextField("Money");

    ComboBox<Client> client = new ComboBox<>("Client");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<Payment> binder=new BeanValidationBinder<>(Payment.class);
    public PaymentForm(List<Client> clients) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        client.setItems(clients);
        client.setItemLabelGenerator(Client::getIdString);

        add(
                money,
                client,
                createButtonLayot()
        );
    }

    public void setPayment(Payment payment)
    {
        binder.setBean(payment);

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

    public static abstract class PaymentFormEvent extends ComponentEvent<PaymentForm>{
        private Payment payment;

        protected PaymentFormEvent(PaymentForm source,Payment payment){
            super(source,false);
            this.payment=payment;
        }

        public Payment getPayment() {
            return payment;
        }
    }

    public static class SaveEvent extends PaymentFormEvent {
        SaveEvent(PaymentForm source,Payment payment)
        {
            super(source, payment);
        }
    }

    public static class DeleteEvent extends PaymentFormEvent {
        DeleteEvent(PaymentForm source,Payment payment)
        {
            super(source, payment);
        }
    }

    public static class CloseEvent extends PaymentFormEvent {
        CloseEvent(PaymentForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}
