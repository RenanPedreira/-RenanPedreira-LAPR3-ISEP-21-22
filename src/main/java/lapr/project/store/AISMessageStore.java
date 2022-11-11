package lapr.project.store;

import lapr.project.model.AISMessage;
import lapr.project.model.ShipDynamicData;
import lapr.project.tree.BST;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AISMessageStore {
    private final BST<AISMessage> messages;

    /**
     * The constructor of the class, with no parameter needed.
     */
    public AISMessageStore(){
        messages=new BST<>();
    }


    /**
     * Create a new AISMessage's instance with the parameters received, a return it.
     *
     * @param mmsi of the ship.
     * @param data, an instance of ShipDynamicData, the position of the ship when the message is sent.
     * @return new AISMessage's instance.
     */
    public AISMessage createPositionalMessage(Integer mmsi, ShipDynamicData data){
        return new AISMessage(mmsi,data);
    }

    /**
     * The function will check the validation of the parameter and if is all ok, will insert its on the BST.
     *
     * @param aISMessage to save.
     */
    public void saveMessage(AISMessage aISMessage){
        if (validatePositionalMessage(aISMessage)) {
            messages.insert(aISMessage);
        } else {
            throw new IllegalArgumentException("The argument is not valid.");
        }
    }

    /**
     * The function will check the validation of the parameters and if is all ok, will insert its on the BST.
     *
     * @param mmsi
     * @param position
     */
    public void saveMessage(Integer mmsi, ShipDynamicData position){
        if (validatePositionalMessage(mmsi,position)){
            messages.insert(createPositionalMessage(mmsi,position));
        } else {
            throw new IllegalArgumentException("The argument is not valid.");
        }
    }

    /**
     * Make the validation checking if the parameter is not null.
     *
     * @param message
     * @return boolean value(true or false).
     */
    public boolean validatePositionalMessage(AISMessage message){
        return message!=null;
    }

    /**
     * Make the validation checking if the parameters are not null.
     *
     * @param mmsi of the ship.
     * @param positionData of the ship.
     * @return boolean value
     */
    public boolean validatePositionalMessage(Integer mmsi, ShipDynamicData positionData){
            return (mmsi!=null&&positionData!=null);
    }

    /**
     * The method will return the BST, in format of a List.
     *
     * @return all the positional messages
     */
    public List<AISMessage> getAllMessages(){
        return (List<AISMessage>) this.messages.inOrder();
    }

    /**
     * The method will firstly get all the messages and after get the messages that are between the period.
     *
     * @param mmsi of the ship.
     * @param date the first date of the period.
     * @param dateFinal the last date of the period.
     * @return
     */
    public List<AISMessage> getAISMessagesByPeriod(int mmsi, LocalDateTime date, LocalDateTime dateFinal){
        List<AISMessage> returnedListPeriod= new ArrayList<>();
        List<AISMessage> allMessages= getAllMessages();
        for (AISMessage message : allMessages){
            if (message.getMmsi().equals(mmsi)&&(date.compareTo(message.getPositionAtMoment().getBaseDateTime())<=0&&
                        dateFinal.compareTo(message.getPositionAtMoment().getBaseDateTime())>=0))
                    returnedListPeriod.add(message);
        }
        return returnedListPeriod;
    }

}
