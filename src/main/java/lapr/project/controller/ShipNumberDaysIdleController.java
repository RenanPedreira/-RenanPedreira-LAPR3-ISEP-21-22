package lapr.project.controller;

import lapr.project.data.ShipNumberDaysIdleDB;
import lapr.project.model.Company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class ShipNumberDaysIdleController {

    App app = App.getInstance();
    Company company = app.getCompany();

    ShipNumberDaysIdleDB shipNumberDaysIdleDB;
    List<List<String>> info;

    public ShipNumberDaysIdleController(){
        shipNumberDaysIdleDB = new ShipNumberDaysIdleDB();
        info = new ArrayList<>();
    }

    public List<List<String>> getShipNumberDaysIdle() throws SQLException {
        String user= "FleetM1@lei";
        
        if(this.company.getAuthFacade().getCurrentUserSession().getUserId()!=null)
            user = this.company.getAuthFacade().getCurrentUserSession().getUserId().toString();

        String[] s = user.split("@");
        info = shipNumberDaysIdleDB.getShipNumberDaysIdle(s[0]);
        return info;
    }
}