package kevytlaskutus.ui;

import java.util.HashMap;
import javafx.scene.control.TextField;
import kevytlaskutus.domain.*;

public class UpdateManagedCompany extends FormAction {
    
    private ManagedCompany company;
    
    public UpdateManagedCompany(
            AppService appService
    ) {
        super(appService);
    }

    @Override
    public void setData(HashMap<String, TextField> formFields, int id) {
        this.company = super.makeManagedCompanyFromFieldValues(formFields, id);
    }
        
    @Override
    public void save() {    
        super.appService.updateManagedCompany(this.company.getId(), this.company);
    }
    
}
