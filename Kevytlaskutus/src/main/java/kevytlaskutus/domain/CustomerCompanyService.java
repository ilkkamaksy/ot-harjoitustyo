/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kevytlaskutus.domain;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kevytlaskutus.dao.CustomerCompanyDao;

/**
 * Service class responsible for dispatching requests for CustomerCompanyDao and returning Customer entities from database.
 * 
 */
public class CustomerCompanyService {
    
    private CustomerCompanyDao dao;

    private DatabaseUtils databaseUtils;
    
    public CustomerCompanyService(CustomerCompanyDao dao, DatabaseUtils databaseUtils) {
        this.databaseUtils = databaseUtils;
        this.dao = dao;
    }    
    
    /**
     * Save a CustomerCompany object in database.
     * @param customer the CustomerCompany object to be saved.
     * @return boolean result of operation
     */
    public boolean saveCustomerCompany(CustomerCompany customer) {
        boolean result = false;
        if (customer.getId() == 0) {
            result = this.createCustomerCompany(customer);
        } else {
            result = this.updateCustomerCompany(customer);
        }
        
        return result;
    }
    
    
    private boolean createCustomerCompany(CustomerCompany customer) {
        boolean result = false;
        try {    
            Connection conn = this.databaseUtils.getConnection();
            dao.setConnection(conn);
            result = dao.create(customer);
        } catch (SQLException e) {
            Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }
    
    private boolean updateCustomerCompany(CustomerCompany customer) {
        
        Boolean result = false;
        
        try {    
            Connection conn = this.databaseUtils.getConnection();
            dao.setConnection(conn);
            result = dao.update(customer.getId(), customer);
        } catch (SQLException e) {
            Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }
  
    /**
     * Delete a CustomerCompany entity in database. 
     *
     * @param id the id of the CustomerCompany to be updated
     * @return boolean
     * @see CustomerCompany
     */
    public boolean deleteCustomerCompany(int id) {
        
        Boolean result = false;
        
        try {    
            Connection conn = this.databaseUtils.getConnection();
            dao.setConnection(conn);
            result = dao.delete(id);
        } catch (SQLException e) {
            Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }
    
    /**
     * Retrieves all CustomerCompany entities from the database.
     *
     * @return List of CustomerCompany objects.
     * @see CustomerCompany
     */
    public List<CustomerCompany> getCustomerCompanies() {
        
        List<CustomerCompany> results = new ArrayList<>();
        
        try {
            Connection conn = this.databaseUtils.getConnection();
            dao.setConnection(conn);
            results = dao.getItems();
        } catch (SQLException e) {
            Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return results;
    }
    
    /**
     * Retrieve a CustomerCompany object from the database by name. 
     *
     * @param name the name of the CustomerCompany to be fetched.
     * @return CustomerCompany
     * @see CustomerCompany
     */
    public CustomerCompany getCustomerCompanyByName(String name) {
        
        CustomerCompany result = null;
        
        try {
            Connection conn = this.databaseUtils.getConnection();
            dao.setConnection(conn);
            result = dao.getItemByName(name);
        } catch (SQLException e) {
            Logger.getLogger(AppService.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return result;
    }

}
