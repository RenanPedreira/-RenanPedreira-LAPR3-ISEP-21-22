package lapr.project.ui;

import lapr.project.controller.SeeOrganizedMessagesController;
import lapr.project.model.AISMessage;
import lapr.project.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SeeOrganizedMessagesUI implements Runnable{

    SeeOrganizedMessagesController controller;

    public SeeOrganizedMessagesUI(){
        controller = new SeeOrganizedMessagesController();
    }

    @Override
    public void run(){
        try {
            List<AISMessage> allMessages;
            List<String> options = new ArrayList<>();
            options.add("By Date");
            options.add("By period");
            int selected=Utils.showAndSelectIndex(options,"Which way you want to search the ship position?");
            if (selected==-1)
                (new TrafficManagerUI()).run();
            if (selected==0) {
                allMessages = getByDate();
            }else {
                allMessages = getByPeriod();
            }
            if (!allMessages.isEmpty()) {
                Utils.print("/---MMSI---|--Date and Hour--|----Lat---|---Lon----|----Cog---|----Sog---\\");
                Utils.print("|__________|_________________|__________|__________|__________|__________|");
                for (AISMessage message : allMessages) {
                    Utils.print(message);
                Utils.print("\\__________|_________________|__________|__________|__________|__________/");
                Utils.print();
                }
            }else{
                Utils.print("|                              No data found                             |");
            }
        }catch (Exception e){
            Utils.print("/----------------------------------------------ERROR----------------------------------------------\\");
            Utils.print(String.format("|%97s|", StringUtils.center(e.getMessage(),97)));
            Utils.print("\\_________________________________________________________________________________________________/");
        }
        if(!Utils.confirm("Do you want to exit?"))
            this.run();
    }

    public List<AISMessage> getByDate(){
        int mmsi = Utils.readIntegerFromConsole("Introduce the MMSI of the ship you want to see position:");
        String date = Utils.readLineFromConsole("Introduce the date you want to see the position of the ship[DD/MM/YYYY]: ");
        String date1;
        LocalDateTime otherDay;
        LocalDateTime otherDay2;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        date1=String.format("%s 00:00",date);
        otherDay = LocalDateTime.parse(date1, formatter);
        String date2=String.format("%s 23:59",date);
        otherDay2=LocalDateTime.parse(date2, formatter);
        return controller.getAISMessagesByPeriod(mmsi,otherDay,otherDay2);
    }

    public List<AISMessage> getByPeriod(){
        int mmsi = Utils.readIntegerFromConsole("Introduce the MMSI of the ship you want to see position:");
        String date1 = Utils.readLineFromConsole("Introduce the first date you want to see the position of the ship[DD/MM/YYYY HH:mm] : ");
        LocalDateTime day1;
        LocalDateTime day2;

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        day1 = LocalDateTime.parse(date1, formatter1);
        String date2 = Utils.readLineFromConsole("Introduce the last date you want to see the position of the ship[DD/MM/YYYY HH:mm]: ");

        day2 = LocalDateTime.parse(date2, formatter1);
        return controller.getAISMessagesByPeriod(mmsi,day1,day2);
    }


}
