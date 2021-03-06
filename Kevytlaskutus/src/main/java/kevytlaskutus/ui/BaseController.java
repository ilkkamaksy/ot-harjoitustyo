package kevytlaskutus.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import kevytlaskutus.domain.AppService;

/**
 * Base controller class for views.
 */
public abstract class BaseController {

    @FXML
    protected Pane noticePane;

    protected AppService appService;
    protected ViewFactory viewFactory;
    protected String fxmlName;
    
    public PrimaryNotice primaryNotice;

    public BaseController(AppService appService, ViewFactory viewFactory, String fxmlName) {
        this.appService = appService;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
        this.primaryNotice = new PrimaryNotice(appService);
    }
    
    public String getFxmlName() {
        return this.fxmlName;
    }
    
    @FXML
    void manageCompaniesAction() {
        this.viewFactory.showDashBoard();
    }

    @FXML
    void manageCustomersAction() {
        this.viewFactory.showManageCustomerView();
    }
}
