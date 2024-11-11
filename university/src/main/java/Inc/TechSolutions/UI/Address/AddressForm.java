package Inc.TechSolutions.UI.Address;

import Inc.TechSolutions.Entity.Address;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;


public class AddressForm extends FormLayout {

    TextField street=new TextField("Street");
    TextField city= new TextField("City");

    Button save=new Button("Save");
    Button delete=new Button("Delete");
    Button close =new Button("Cancel");

    Binder<Address> binder=new BeanValidationBinder<>(Address.class);
    public AddressForm() {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(
                street,
                city,
                createButtonLayot()
        );
    }

    public void setAddress(Address address)
    {
        binder.setBean(address);

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

    public static abstract class AddressFormEvent extends ComponentEvent<AddressForm>{
        private Address address;

        protected AddressFormEvent(AddressForm source, Address address){
            super(source,false);
            this.address=address;
        }

        public Address getAddress() {
            return address;
        }
    }

    public static class SaveEvent extends AddressFormEvent{
        SaveEvent(AddressForm source,Address address)
        {
            super(source, address);
        }
    }

    public static class DeleteEvent extends AddressFormEvent{
        DeleteEvent(AddressForm source,Address address)
        {
            super(source, address);
        }
    }

    public static class CloseEvent extends AddressFormEvent{
        CloseEvent(AddressForm source)
        {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener)
    {
        return getEventBus().addListener(eventType,listener);
    }
}
