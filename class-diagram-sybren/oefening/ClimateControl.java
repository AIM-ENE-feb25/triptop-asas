public class ClimateControl {
    private Heating heating;
    private Cooling cooling;

    public void returnFromWork(int currentTemperature, int desiredTemperature) {
        heating = new Heating();
        cooling = new Cooling();
        if(currentTemperature < desiredTemperature) {
            heating.heatToTemperature(desiredTemperature);
        } else {
            cooling.coolToTemperature(desiredTemperature);
        }
    }
}