package lapr.project.ui;

import lapr.project.controller.FindClosestPortController;
import lapr.project.model.Port;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

public class FindClosestPortUI implements Runnable{
    FindClosestPortController controller;

    public FindClosestPortUI(){
        controller=new FindClosestPortController();
    }

    @Override
    public void run() {
        try {
            String callSign = Utils.readLineFromConsole("Insert the CallSign of the ship that you want to know the closest port:");
            String date = getTheDate();
            Port closestPort = controller.getClosestPort(callSign, date);
            if (closestPort != null) {
                Utils.print("/-CODE-|-CONTINENT-|----COUNTRY----|------NAME------|---LATI---|---LONG---\\");
                Utils.print(getPortInfo(closestPort));
                Utils.print("\\______|___________|_______________|________________|__________|__________/");
            } else {
                Utils.print("/-----------------------------------INFO-----------------------------------\\");
                Utils.print(String.format("|%74s|",StringUtils.center("No data found,please confirm if are ports registered in the system",74)));
                Utils.print("\\_________________________________________________________________________/");
            }
        }catch (IllegalArgumentException illegalArgumentException){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(illegalArgumentException.getMessage(),97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }catch (Exception exception){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(exception.getMessage(),97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }
        if(!Utils.confirm("Do you want to exit?"))
            this.run();
    }

    public String getTheDate(){
        String dateString = Utils.readLineFromConsole("Introduce the date you want to see the closest port of the ship[DD/MM/YYYY HH:mm]: ");
        Utils.print();
        return dateString;
    }

    public String getPortInfo(Port port){
        return String.format("|%6d|%11s|%15s|%16s|%10.5f|%10.5f|",port.getIdentification(),port.getContinent(),
                port.getCountry(),port.getName(),port.getLatitude(),port.getLongitude());
    }
}
