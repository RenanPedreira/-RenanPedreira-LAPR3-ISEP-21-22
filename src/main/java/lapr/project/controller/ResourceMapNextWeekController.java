package lapr.project.controller;

import lapr.project.data.ResourceMapNextWeekDB;
import lapr.project.model.Company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
public class ResourceMapNextWeekController {

    ResourceMapNextWeekDB rmdb;
    List<List<String>> list;

    App app = App.getInstance();
    Company company = app.getCompany();

    public ResourceMapNextWeekController() {
        rmdb = new ResourceMapNextWeekDB();
        list = new ArrayList<>();
    }


    public List<List<String>> getResourceMapNextWeek() throws SQLException {
        String user= "PortM1@lei";
        if(this.company.getAuthFacade().getCurrentUserSession().getUserId()!=null)
            user = this.company.getAuthFacade().getCurrentUserSession().getUserId().toString();
        String[] s = user.split("@");
        list = rmdb.getResourceMapNextWeek(s[0]);
        return list;
    }
}
