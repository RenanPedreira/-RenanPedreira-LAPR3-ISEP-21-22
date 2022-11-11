package lapr.project.controller;

import lapr.project.model.AISMessage;
import lapr.project.store.AISMessageStore;

import java.time.LocalDateTime;
import java.util.List;

public class SeeOrganizedMessagesController {

    /**
     * The Message store of the company, in order to access the message list.
     */
    private final AISMessageStore store;

    /**
     * The constructor of this class, to initialize the store.
     */
    public SeeOrganizedMessagesController(){
        store=App.getInstance().getCompany().getMessageStore();
    }

    /**
     * The function will return the controller's instance of the SeeOrganizedMessagesStore.
     * @return store
     */
    public AISMessageStore getStore(){
        return this.store;
    }

    /**
     *
     * @param mmsi
     * @param date
     * @param dateFinal
     * @return
     */
    public List<AISMessage> getAISMessagesByPeriod(int mmsi, LocalDateTime date,LocalDateTime dateFinal){
        return store.getAISMessagesByPeriod(mmsi,date,dateFinal);
    }

}
