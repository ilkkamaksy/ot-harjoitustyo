package kevytlaskutus.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.stage.Stage;
import kevytlaskutus.dao.CustomerCompanyDao;
import kevytlaskutus.dao.InvoiceDaoImpl;
import kevytlaskutus.dao.ManagedCompanyDao;
import kevytlaskutus.dao.ProductDaoImpl;
import kevytlaskutus.domain.AppService;
import kevytlaskutus.domain.DatabaseUtils;

public class Launcher extends Application {
    
    private AppService appService;
    
    public static void run(String [] args) {
        launch(args);
    }
    
    @Override
    public void init() throws Exception {
        ManagedCompanyDao managedCompanyDao = new ManagedCompanyDao();
        CustomerCompanyDao customerCompanyDao = new CustomerCompanyDao();
        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
        ProductDaoImpl productDao = new ProductDaoImpl();
       
        DatabaseUtils databaseUtils = new DatabaseUtils(
                managedCompanyDao, 
                customerCompanyDao, 
                invoiceDao,
                productDao,
                "jdbc:h2:file:./database/kevytlaskutusdb", 
                "sa", 
                ""
        ); 
        databaseUtils.initDb();
        
        this.appService = new AppService(
                managedCompanyDao, 
                customerCompanyDao, 
                invoiceDao,
                productDao,
                databaseUtils
        );
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(this.appService);
        viewFactory.showDashBoard();
    }
}
