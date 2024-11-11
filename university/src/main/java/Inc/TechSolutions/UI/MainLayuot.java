package Inc.TechSolutions.UI;

import Inc.TechSolutions.Security.SecurityService;
import Inc.TechSolutions.UI.Address.AddressView;
import Inc.TechSolutions.UI.Client.ClientView;
import Inc.TechSolutions.UI.Department.DepartmentView;
import Inc.TechSolutions.UI.Employee.EmployeeView;
import Inc.TechSolutions.UI.EmployeeProject.EmployeeProjectView;
import Inc.TechSolutions.UI.Payment.PaymentView;
import Inc.TechSolutions.UI.Project.ProjectView;
import Inc.TechSolutions.UI.ProjectClient.ProjectClientView;
import Inc.TechSolutions.UI.Task.TaskView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/shared-styles.css")
public class MainLayuot extends AppLayout {


    private final SecurityService securityService;
    public MainLayuot(SecurityService securityService){
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo=new H1("TechSolution Database");
        logo.addClassName("logo");

        Button logOut=new Button("Log Out", click->securityService.logout());
        Button dowloadReport=new Button("Report",click->getUI().ifPresent(ui -> ui.getPage().setLocation("/report/pdf")));

        HorizontalLayout header=new HorizontalLayout(new DrawerToggle(),logo,logOut,dowloadReport);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void createDrawer() {

        addToDrawer(new VerticalLayout(
                new RouterLink("Department", DepartmentView.class),
                new RouterLink("Address", AddressView.class),
                new RouterLink("Employee", EmployeeView.class),
                new RouterLink("Project", ProjectView.class),
                new RouterLink("EmployeeProject", EmployeeProjectView.class),
                new RouterLink("Task", TaskView.class),
                new RouterLink("Client", ClientView.class),
                new RouterLink("Payment", PaymentView.class),
                new RouterLink("ProjectClient", ProjectClientView.class)
        ));
    }

}
