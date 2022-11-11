package lapr.project.auth.domain.store;

import lapr.project.auth.domain.model.EmployeeRole;
import lapr.project.utils.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class EmployeeRoleStore implements Serializable {
    /**
     * List of the Employee Roles
     */
    private final List<EmployeeRole>empRole= new ArrayList<>();

    /**
     * Method that creates an Employee Role with its id
     * @param id
     * @return
     */
    public EmployeeRole create(String id)
    {
        return new EmployeeRole(id);
    }

    /**
     * adds a new employee role to the list
     * @param empRole employee role
     * @return adds an employee role
     */
    public boolean add(EmployeeRole empRole){
        return this.empRole.add(empRole);
    }

    /**
     * shows the list of employee roles
     */
    public void showList(){
        for(EmployeeRole er : empRole){
            Utils.print(er.getId());
        }
    }

    /**
     * checks if a employee role already exists
     * @param a employee role
     * @return true if employee exists
     */
    public boolean exist(EmployeeRole a){
        boolean flag=false;
        for(EmployeeRole er : empRole){
            if(er.equals(a)) {
                flag = true;
            }
        }
        return flag;
    }
}
