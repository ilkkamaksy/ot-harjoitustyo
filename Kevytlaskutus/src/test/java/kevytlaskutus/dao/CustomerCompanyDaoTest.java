/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kevytlaskutus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import kevytlaskutus.domain.CustomerCompany;
import kevytlaskutus.domain.DatabaseUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author ilkka
 */
public class CustomerCompanyDaoTest {
    
    Connection conn;
    CustomerCompany mockCustomer;
    CustomerCompanyDao customerCompanyDao;
    ManagedCompanyDao managedCompanyDao;
    InvoiceDaoImpl invoiceDao;
    ProductDaoImpl productDao;
    DatabaseUtils databaseUtils;
    
    public CustomerCompanyDaoTest() {
        
        this.managedCompanyDao = new ManagedCompanyDao();
        this.customerCompanyDao = new CustomerCompanyDao();
        this.invoiceDao = new InvoiceDaoImpl();
        this.productDao = new ProductDaoImpl();
       
        this.databaseUtils = new DatabaseUtils(
                managedCompanyDao, 
                customerCompanyDao, 
                invoiceDao,
                productDao,
                "jdbc:h2:mem:testdb",
                "sa", 
                ""
        ); 
        databaseUtils.initDb();
    }
    
    @Before
    public void setUp() {
        
        try {
            Connection conn = this.databaseUtils.getConnection();
            customerCompanyDao.setConnection(conn);
        } catch (SQLException e) {
            
        }
        
        mockCustomer = mock(CustomerCompany.class);
        when(mockCustomer.getId()).thenReturn(1);
        when(mockCustomer.getName()).thenReturn("Acme");
    }

    @Test
    public void newCustomerCanBeCreated() {
        try {
            CustomerCompany customer = new CustomerCompany();
            customer.setName("Acme");
            boolean result = customerCompanyDao.create(customer);
            assertTrue(result);    
        } catch (SQLException e) {
            
        }
    }
    
    @Test
    public void customerCanBeUpdated() {
        try {
            CustomerCompany customer = new CustomerCompany();
            customer.setName("Acme");
            boolean success = customerCompanyDao.create(customer);
            assertTrue(success);
            
            Connection conn = this.databaseUtils.getConnection();
            customerCompanyDao.setConnection(conn);
            CustomerCompany originalCustomer = customerCompanyDao.getItemByName("Acme");
            
            originalCustomer.setName("Muutettu");
            
            conn = this.databaseUtils.getConnection();
            customerCompanyDao.setConnection(conn);
            success = customerCompanyDao.update(originalCustomer.getId(), originalCustomer);
            assertTrue(success);
            
            conn = this.databaseUtils.getConnection();
            customerCompanyDao.setConnection(conn);
            CustomerCompany result = customerCompanyDao.getItemById(originalCustomer.getId());
            assertEquals(originalCustomer.getName(), result.getName());
        } catch (SQLException e) {}
    }
    
    @Test
    public void customerCannotBeUpdatedWithoutValidId() {
        try {
            boolean result = customerCompanyDao.update(-1, mockCustomer);
            assertFalse(result);    
        } catch (SQLException e) {}
    }

    @Test
    public void customerCannotDeletedWithoutValidId() {
        try {
            boolean result = customerCompanyDao.delete(-1);
            assertFalse(result);    
        } catch (SQLException e) {}
    }
    
    @Test
    public void customerCanBeRetrievedById() {
        try {
            List<CustomerCompany> customers = this.customerCompanyDao.getItems();
            
            Connection conn = this.databaseUtils.getConnection();
            customerCompanyDao.setConnection(conn);
            CustomerCompany result = this.customerCompanyDao.getItemById(customers.get(0).getId());
            assertEquals(customers.get(0).getId(), result.getId());
        } catch (SQLException e) {}
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb");
            conn.prepareStatement("DROP DATABASE testdb").execute();
        } catch (SQLException e) {}
    }

}
