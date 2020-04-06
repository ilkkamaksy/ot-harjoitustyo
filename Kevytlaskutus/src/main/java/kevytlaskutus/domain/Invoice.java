package kevytlaskutus.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.sql.Date;

public class Invoice {

    private int id; 
    private int customerId;
    private int companyId;
    private int invoiceNumber;
    private int referenceNumber;
    private Date createdDate;
    private int paymentTerm;
    private Date dueDate;
    private BigDecimal discount;
    private Date discountDate;
    private BigDecimal penaltyInterest;
    private BigDecimal amount;
    private String customerContactName;
    private String customerReference;
    private String companyReference;
    private String deliveryTerms;
    private Date deliveryDate;
    private String deliveryInfo;
    private String additionalInfo;

    public Invoice() {
        this(new Date(new java.util.Date().getTime()));
    }
    
    public Invoice(Date createdDate) {
        this.invoiceNumber = 1000;
        this.createdDate = createdDate;
        this.paymentTerm = 14;
        this.dueDate = this.calculateDueDate(createdDate);
        this.discount = new BigDecimal(0.0);
        this.discountDate = null;
        this.penaltyInterest = new BigDecimal(0.0);
        this.amount = new BigDecimal(0.0);
        this.customerContactName = "";
        this.customerReference = "";
        this.companyReference = "";
        this.deliveryTerms = "";
        this.deliveryDate = null;
        this.deliveryInfo = "";
        this.additionalInfo = "";
    }

    private Date calculateDueDate(Date createdDate) {
        return new Date(createdDate.getTime() + 24 * 60 * 60 * 1000);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(Date discountDate) {
        this.discountDate = discountDate;
    }

    public BigDecimal getPenaltyInterest() {
        return penaltyInterest;
    }

    public void setPenaltyInterest(BigDecimal penaltyInterest) {
        this.penaltyInterest = penaltyInterest;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerContactName() {
        return customerContactName;
    }

    public void setCustomerContactName(String customerContactName) {
        this.customerContactName = customerContactName;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getCompanyReference() {
        return companyReference;
    }

    public void setCompanyReference(String companyReference) {
        this.companyReference = companyReference;
    }

    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(String deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return this.id + " no:" + this.invoiceNumber + " cdate:" + this.createdDate + " duedate:" + this.dueDate + " refno:" + this.referenceNumber + " customerid:" + this.customerId;
    }
}
