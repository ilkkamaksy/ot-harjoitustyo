package kevytlaskutus.ui;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import kevytlaskutus.domain.AppService;
import kevytlaskutus.domain.CustomerCompany;
import kevytlaskutus.domain.Invoice;
import kevytlaskutus.domain.Product;

public class EditInvoiceController extends BaseController implements Initializable {

    @FXML
    private Pane editFormContainerPane;
   
    @FXML 
    private Button saveFormButton;

    @FXML 
    private Button addNewRowButton;
        
    private Form form;
   
    private String actionType;
   
    private Invoice currentInvoice;
  
    private FormActionFactory actionFactory;
    
    public EditInvoiceController(AppService appService, ViewFactory viewFactory, String fxmlName) {
        super(appService, viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.currentInvoice = this.appService.getCurrentInvoice();
       
        this.setActionType();
        
        super.primaryNotice.setupNotice();
        super.noticePane.getChildren().add(super.primaryNotice.notice);
        
        this.actionFactory = new FormActionFactory(this.appService);
        this.setupForm();
        this.setSaveButtonAction();
        this.setAddNewRowButtonAction();
    }
   
    public void setupForm() {
        
        this.form = new Form(this.appService);        
        this.form.addDatePicker("Date", currentInvoice.getCreatedDate(), currentInvoice, "CreatedDate");
        this.form.addIntegerField("Invoice Number", "" + "" + this.currentInvoice.getInvoiceNumber(), this.currentInvoice, "InvoiceNumber");
        this.form.addIntegerField("Reference Number", "" + currentInvoice.getReferenceNumber(), this.currentInvoice, "ReferenceNumber");
        this.form.addIntegerField("Payment due in number of days", "" + this.currentInvoice.getPaymentTerm(), this.currentInvoice, "PaymentTerm");
        this.form.addDatePicker("Due Date", this.currentInvoice.getDueDate(), currentInvoice, "DueDate");
        this.form.addDecimalField("Overdue Penalty Interest rate", "" + this.currentInvoice.getPenaltyInterest(), this.currentInvoice, "PenaltyInterest");
        this.form.addDecimalField("Discount", "" + this.currentInvoice.getDiscount(), this.currentInvoice, "Discount");
       
        String customerName = "";
        if (currentInvoice.getCustomer() != null) {
            customerName = currentInvoice.getCustomer().getName();
        }      
        this.form.addDropDown("Customer", this.createCustomerNameList(), customerName, currentInvoice, "Customer");
       
        this.form.addTextField("Customer Contact Name", this.currentInvoice.getCustomerContactName(), this.currentInvoice, "CustomerContactName");
        this.form.addTextField("Customer Reference", this.currentInvoice.getCustomerReference(), this.currentInvoice, "CustomerReference");
        this.form.addTextField("Our Reference", this.currentInvoice.getCompanyReference(), this.currentInvoice, "CompanyReference");
        this.form.addTextField("Delivery Terms", this.currentInvoice.getDeliveryTerms(), this.currentInvoice, "DeliveryTerms");
        this.form.addDatePicker("Delivery Date", this.currentInvoice.getDeliveryDate(), currentInvoice, "DeliveryDate");
        this.form.addTextField("Delivery Information", this.currentInvoice.getDeliveryInfo(), this.currentInvoice, "DeliveryInfo");
        this.form.addTextField("Additional Information", this.currentInvoice.getAdditionalInfo(), this.currentInvoice, "AdditionalInfo");
  
        List<Product> products = this.currentInvoice.getProducts();
        for (Product product : products) {
            this.form.setLineItem(product);
        }
        if (products.size() == 0) {
            this.form.addLineItem();
        }
        
        this.form.addTextField("Amount", "" + this.currentInvoice.getAmount(), this.currentInvoice, "Amount");
        this.editFormContainerPane.getChildren().add(this.form.getForm());
       
    }
  
    private ObservableList createCustomerNameList() {
        List<CustomerCompany> customers = this.appService.getCustomerCompanies();
        ObservableList<String> customerNames = FXCollections.observableArrayList(); 
        for (CustomerCompany customer : customers) {
            customerNames.add(customer.getName());
        }
        return customerNames;
    }
   
    public void setActionType() {
        if (this.currentInvoice.getId() > 0) {
            this.actionType = "UpdateInvoice";
        } else {
            this.actionType = "NewInvoice";
        }
    }
    
    private void setAddNewRowButtonAction() {
        this.addNewRowButton.setOnAction(e-> {
            this.form.addLineItem();
        });
    }   
    
    private void setSaveButtonAction() {
        this.saveFormButton.setOnAction(e-> {
            this.actionFactory.execute(this.actionType, this.form.getFormFields(), this.currentInvoice.getId());
            this.viewFactory.showManageInvoicesView();
        });
    }

}
