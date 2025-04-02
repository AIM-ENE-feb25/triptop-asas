public class Person {

    private int currentTemperature;
    private int desiredTemperature;

    private ClimateControl climateControl;

    private SmartHomeSystem smartHomeSystem;

    public Person() {
        climateControl = new ClimateControl();
        smartHomeSystem = new SmartHomeSystem();
    }
    public void returnFromWork() {
        currentTemperature = 20;
        desiredTemperature = 19;

        climateControl.returnFromWork(currentTemperature, desiredTemperature);

    }

    public static void main(String[] args) {
        Person person = new Person();
//        person.returnFromWork();

        person.smartHomeSystem.thuisKomenVanWerk();
        person.smartHomeSystem.naarBedGaan();
    }
}