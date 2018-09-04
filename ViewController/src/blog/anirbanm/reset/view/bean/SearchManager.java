package blog.anirbanm.reset.view.bean;

import blog.anirbanm.reset.view.ADFUtils;

import java.util.Collection;

import javax.faces.event.ActionEvent;

import oracle.binding.OperationBinding;

import oracle.jbo.Variable;

public class SearchManager {
    
    public SearchManager() {
        super();
    }
    
    public void resetSearch(final ActionEvent actionEvent) {
        final OperationBinding getAllBindVariables = ADFUtils.findOperation("getAllBindVariables");
        getAllBindVariables.getParamsMap().put("voInstance", "EmployeesVO1");
        getAllBindVariables.getParamsMap().put("vcName", "EmployeesSearchCriteria");
        final Collection<Variable> allBindVariables = (Collection<Variable>) getAllBindVariables.execute();
        
        if (allBindVariables != null) {
            for (final Variable variable : allBindVariables) {
                System.out.println("Bind variable - " + variable.getName());
                ADFUtils.setBoundAttributeValue(variable.getName(), null);
            }
        }
        
        final OperationBinding executeEmptyRowSet = ADFUtils.findOperation("executeEmptyRowSet");
        executeEmptyRowSet.getParamsMap().put("voInstance", "EmployeesVO1");
        executeEmptyRowSet.execute();
    }
}
