package lapr.project.model;

public class AISMessage implements Comparable<AISMessage>{

    /**
     * The mmsi of the Ship that send the message.
     */
    private Integer mmsi;

    /**
     * The actual position of the ship at the moment that the ship sent the message to the US Coast.
     */
    private ShipDynamicData positionAtMoment;

    /**
     * The MMSI by default.
     */
    private static final Integer DEFAULT_MMSI=0;

    /**
     * The Position at moment by default.
     */
    private static final ShipDynamicData DEFAULT_POSITION = null;

    /**
     * The constructor of the class by default, no data/parameter needed.
     */
    public AISMessage(){
        this.mmsi= DEFAULT_MMSI;
        this.positionAtMoment=DEFAULT_POSITION;
    }

    /**
     * The constructor of the class, that receive the mmsi of the ship and the position at the moment as parameter.
     *
     * @param mmsi
     * @param positionAtMoment
     */
    public AISMessage(Integer mmsi, ShipDynamicData positionAtMoment){
        setMmsi(mmsi);
        setPositionAtMoment(positionAtMoment);
    }

    /**
     * The constructor of the class, that receive the mmsi of the ship and the position at the moment as parameter.
     *
     * @param aisMessage
     */
    public AISMessage(AISMessage aisMessage){
        setMmsi(aisMessage.mmsi);
        setPositionAtMoment(aisMessage.positionAtMoment);
    }

    /**
     * Return the mmsi of the ship that sent the MMSI.
     * @return mmsi
     */
    public Integer getMmsi() {
        return mmsi;
    }

    /**
     * Update/set a new MMSI to positional message.
     * @param mmsi
     */
    public void setMmsi(Integer mmsi) {
        this.mmsi = mmsi;
    }

    /**
     * Return the position of the ship at the moment that message was send.
     * @return positionAtMoment
     */
    public ShipDynamicData getPositionAtMoment() {
        return positionAtMoment;
    }

    /**
     * Update/set a new position to the positional message.
     * @param positionAtMoment
     */
    public void setPositionAtMoment(ShipDynamicData positionAtMoment) {
        this.positionAtMoment = positionAtMoment;
    }

    /**
     * Verify if the object received as parameter is equal to actual object.
     * @param obj
     * @return boolean value
     */
    @Override
    public boolean equals(Object obj){
        if (obj==this)
            return true;

        if (!(obj instanceof AISMessage))
            return false;

        AISMessage secondPosition = (AISMessage) obj;
        if (secondPosition.mmsi.equals(this.mmsi)){
            return secondPosition.positionAtMoment.equals(this.positionAtMoment);
        }else{
            return false;
        }
    }

    /**
     * Return the infos about the Positional Message that is need or required, just the necessary
     * @return string format of the positional message
     */
    @Override
    public String toString(){
        return String.format("|%10d|%17s|%10.5f|%10.5f|%10.1f|%10.1f|",mmsi,positionAtMoment.getBaseDateTime().toString(),positionAtMoment.getLat(),
                positionAtMoment.getLon(),positionAtMoment.getCog(),positionAtMoment.getSog());
    }

    /**
     * Compare the obj received as parameter to the actual positional message
     * @param secondPosition
     * @return integer number
     */
    @Override
    public int compareTo(AISMessage secondPosition) {
            if (this.equals(secondPosition))
                return 0;
            if (this.mmsi.equals(secondPosition.mmsi)){
                int result=this.positionAtMoment.getBaseDateTime().toLocalDate().compareTo(secondPosition.getPositionAtMoment().getBaseDateTime().toLocalDate());
                if (result==0){
                    result=this.positionAtMoment.getBaseDateTime().toLocalTime().compareTo(secondPosition.getPositionAtMoment().getBaseDateTime().toLocalTime());
                }
                return Math.multiplyExact(-2,result);
            }else {
                return this.mmsi.compareTo(secondPosition.mmsi);
            }
    }

    /**
     * The hash code of the instance.
     * @return the hashcode of the instance.
     */
    @Override
    public int hashCode() {
        int hashcode=23;
        hashcode=Math.addExact(hashcode,mmsi.hashCode());
        return hashcode;
    }
}
