/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kevytlaskutus.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kevytlaskutus.dao.CustomerCompanyDao;
import kevytlaskutus.dao.InvoiceDaoImpl;
import kevytlaskutus.dao.ManagedCompanyDao;
import kevytlaskutus.dao.ProductDaoImpl;

/**
 *
 * @author ilkka
 */
public class DatabaseUtils {
    
    private ManagedCompanyDao managedCompanyDao;
    private CustomerCompanyDao customerCompanyDao;
    private ProductDaoImpl productDao;
    private InvoiceDaoImpl invoiceDao;
    
    public DatabaseUtils(
        ManagedCompanyDao managedCompanyDao, 
        CustomerCompanyDao customerCompanyDao, 
        ProductDaoImpl productDao,
        InvoiceDaoImpl invoiceDao
    ) {
        this.managedCompanyDao = managedCompanyDao;
        this.customerCompanyDao = customerCompanyDao;
        this.productDao = productDao;
        this.invoiceDao = invoiceDao;
    }
    
    public void initDb() {
        
        try {
            Connection conn = this.getConnection();
            this.prepareCustomerCompanyDaoConnection(conn);
            this.prepareInvoiceDaoConnection(conn);
            this.prepareManagedCompanyDaoConnection(conn);
            this.prepareProductDaoConnection(conn);
            
            managedCompanyDao.initDb();
            customerCompanyDao.initDb();
            productDao.initDb();
            invoiceDao.initDb();
            
            conn.close();
            
        } catch (SQLException e) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    

    public void prepareInvoiceDaoConnection(Connection conn) {
        this.invoiceDao.setConnection(conn);
    }
    
    public void prepareManagedCompanyDaoConnection(Connection conn) {
        this.managedCompanyDao.setConnection(conn);
    }
    
    public void prepareCustomerCompanyDaoConnection(Connection conn) {
        this.customerCompanyDao.setConnection(conn);
    }
    
    public void prepareProductDaoConnection(Connection conn) {
        this.productDao.setConnection(conn);
    }
    
    public Connection getConnection() throws SQLException {     
        return DriverManager.getConnection("jdbc:h2:file:./database/kevytlaskutusdb", "sa", "");
    }
}
