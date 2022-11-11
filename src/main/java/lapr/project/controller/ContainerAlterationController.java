package lapr.project.controller;

import lapr.project.data.ContainerAlterationDB;
import lapr.project.model.Company;

import java.sql.SQLException;

/**
 * @author Renan Pedreira
 */
public class ContainerAlterationController {
    ContainerAlterationDB cadb;

    App app = App.getInstance();
    Company company = app.getCompany();
    String user = "shipcap@lei.pt";

    public ContainerAlterationController() {

        cadb = new ContainerAlterationDB();
    }

    public boolean alterContainerInformationCreate(String cargoManifestID, String containerID, Integer shipMMSI, Integer x, Integer y, Integer z) throws SQLException {
        if(this.company.getAuthFacade().getCurrentUserSession().getUserId() !=null)
            user = this.company.getAuthFacade().getCurrentUserSession().getUserId().toString();
        return cadb.alterContainerInformationCreate(cargoManifestID, containerID , shipMMSI, x, y, z, user);
    }

    public boolean alterContainerInformationUpdate(String cargoManifestID, String containerID, Integer x, Integer y, Integer z) throws SQLException {
        if(this.company.getAuthFacade().getCurrentUserSession().getUserId() !=null)
            user = this.company.getAuthFacade().getCurrentUserSession().getUserId().toString();
        return cadb.alterContainerInformationUpdate(cargoManifestID, containerID, x, y, z, user);
    }

    public boolean alterContainerInformationDelete(String cargoManifestID, String containerID) throws SQLException {
        if(this.company.getAuthFacade().getCurrentUserSession().getUserId() !=null)
            user = this.company.getAuthFacade().getCurrentUserSession().getUserId().toString();
        return cadb.alterContainerInformationDelete(cargoManifestID, containerID, user);
    }
}
