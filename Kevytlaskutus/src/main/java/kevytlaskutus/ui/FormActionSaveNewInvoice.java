/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kevytlaskutus.ui;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import kevytlaskutus.domain.AppService;
import kevytlaskutus.domain.CustomerCompany;
import kevytlaskutus.domain.Invoice;

/**
 *
 * @author ilkka
 */
public class FormActionSaveNewInvoice extends FormAction {
    
    private Invoice invoice; 
    
    public FormActionSaveNewInvoice(AppService appService) {
        super(appService);
    }

    @Override
    public void setData(HashMap<String, Node> formFields, int id) {
        this.makeInvoiceFromFieldValues(formFields, id);
    }
    
    @Override
    public boolean save() {
        return super.appService.createInvoice(this.invoice);
    }
  
    protected void makeInvoiceFromFieldValues(HashMap<String, Node> formFields, int id) {
        
        this.invoice = new Invoice(super.dataExtractor.createDateFromDatePicker("Date", formFields));
        this.invoice.setId(id);
        
        String customerName = super.dataExtractor.getSelectedValueFromComboBox("Customer", formFields);
        CustomerCompany customer = this.appService.getCustomerCompanyByName(customerName);
        this.invoice.setCustomer(customer);
        
        this.invoice.setInvoiceNumber(super.dataExtractor.getIntFromTextField("Invoice Number", formFields));
        this.invoice.setPaymentTerm(super.dataExtractor.getIntFromTextField("Payment due in number of days", formFields));
        this.invoice.setDueDate(super.dataExtractor.createDateFromDatePicker("Due Date", formFields));
        this.invoice.setCustomerContactName(super.dataExtractor.getValueFromTextField("Customer Contact Name", formFields));
        this.invoice.setCustomerReference(super.dataExtractor.getValueFromTextField("Customer Reference", formFields));
        this.invoice.setCompanyReference(super.dataExtractor.getValueFromTextField("Our Reference", formFields));
        this.invoice.setDeliveryTerms(super.dataExtractor.getValueFromTextField("Delivery Terms", formFields));
        this.invoice.setDeliveryDate(super.dataExtractor.createDateFromDatePicker("Delivery Date", formFields));
        this.invoice.setDeliveryInfo(super.dataExtractor.getValueFromTextField("Delivery Information", formFields));
        this.invoice.setAdditionalInfo(super.dataExtractor.getValueFromTextField("Additional Information", formFields));
        
        BigDecimal overDueInterest = super.dataExtractor.getBigDecimalFromTextField("Overdue Penalty Interest rate", formFields);
        if (overDueInterest != null) {
            this.invoice.setPenaltyInterest(overDueInterest);    
        }
        
        BigDecimal discount = super.dataExtractor.getBigDecimalFromTextField("Discount", formFields);
        if (discount != null) {
            this.invoice.setDiscount(discount);
        }
        
        BigDecimal amount = super.dataExtractor.getBigDecimalFromTextField("Amount", formFields);
        if (amount != null) {
            this.invoice.setAmount(amount);
        }    
    }
}