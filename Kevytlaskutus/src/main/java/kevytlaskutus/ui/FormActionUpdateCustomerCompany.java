package kevytlaskutus.ui;

import java.util.HashMap;
import javafx.scene.Node;
import kevytlaskutus.domain.*;

public class FormActionUpdateCustomerCompany extends FormAction {
    
    private CustomerCompany company;
    
    public FormActionUpdateCustomerCompany(
            AppService appService
    ) {
        super(appService);
    }

    @Override
    public void setData(HashMap<String, Node> formFields, int id) {
        this.company = super.makeCustomerCompanyFromFieldValues(formFields, id);
    }
    
    @Override
    public boolean save() {
        return super.appService.updateCustomerCompany(company.getId(), this.company);
    }

}