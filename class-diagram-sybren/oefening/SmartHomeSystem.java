public class SmartHomeSystem {

    private Lightning lightning;
    private MusicSystem musicSystem;

    private Heating heating;

    private Cooling cooling;


    public SmartHomeSystem() {
        lightning = new Lightning();
        musicSystem = new MusicSystem();
        heating = new Heating();
        cooling = new Cooling();
    }

    public void thuisKomenVanWerk() {
        heating.heatToTemperature(20);
        musicSystem.on();
        musicSystem.playMusic();
        lightning.on();
        lightning.dim();
    }

    public void naarBedGaan() {
        cooling.coolToTemperature(15);
        musicSystem.off();
        lightning.off();
    }

}
