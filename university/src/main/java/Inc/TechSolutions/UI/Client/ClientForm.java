package Inc.TechSolutions.UI.Client;

import Inc.TechSolutions.Entity.Client;
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


public class ClientForm extends FormLayout {

    TextField name=new TextField("Name");
    TextField dateOfBirth= new TextField("Date Of Birth");

    ComboBox<Client.Gender> gender=new ComboBox<>("Gender");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<Client> binder=new BeanValidationBinder<>(Client.class);
    public ClientForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);
        gender.setItems(Client.Gender.values());

        add(
                name,
                dateOfBirth,
                gender,
                createButtonLayot()
        );
    }

    public void setClient(Client client)
    {
        binder.setBean(client);

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

        binder.addStatusChangeListener(evt->save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save,delete,close);
    }

    private void validateAndSave(){
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events

    public static abstract class ClientFormEvent extends ComponentEvent<ClientForm>{
        private Client client;

        protected ClientFormEvent(ClientForm source,Client client){
            super(source,false);
            this.client=client;
        }

        public Client getClient() {
            return client;
        }
    }

    public static class SaveEvent extends ClientFormEvent{
        SaveEvent(ClientForm source,Client client)
        {
            super(source, client);
        }
    }

    public static class DeleteEvent extends ClientFormEvent{
        DeleteEvent(ClientForm source,Client client)
        {
            super(source, client);
        }
    }

    public static class CloseEvent extends ClientFormEvent{
        CloseEvent(ClientForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}
