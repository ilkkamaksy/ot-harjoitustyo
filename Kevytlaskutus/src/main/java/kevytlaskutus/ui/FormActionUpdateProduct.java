package kevytlaskutus.ui;

import java.util.HashMap;
import javafx.scene.Node;
import kevytlaskutus.domain.*;

public class FormActionUpdateProduct extends FormAction {
    
    private Product product;
    
    public FormActionUpdateProduct(
            AppService appService
    ) {
        super(appService);
    }

    @Override
    public void setData(HashMap<String, Node> formFields, int id) {
        this.product = super.makeProductFromFieldValues(formFields, id);
    }
        
    @Override
    public boolean save() {    
        return super.appService.updateProduct(this.product.getId(), this.product);
    }
    
}
