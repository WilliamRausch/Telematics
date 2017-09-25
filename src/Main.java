import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        TelematicsService telematicsService = new TelematicsService();

        System.out.println("Enter VIN number");
        String vin = scan.nextLine();
        int vinNumber = Integer.parseInt(vin);

        System.out.println("Enter miles traveled");
        String milesInput = scan.nextLine();
        double miles = Double.parseDouble(milesInput);

        System.out.println("Enter gasoline consumption");
        String gasInput = scan.nextLine();
        double gas = Double.parseDouble(gasInput);

        System.out.println("Enter oilChange");
        String oilInput = scan.nextLine();
        double oil = Double.parseDouble(oilInput);

        System.out.println("Enter Engine Size");
        String engineInput = scan.nextLine();
        double engine  = Double.parseDouble(engineInput);

        VehicleInfo vehicleinfo = new VehicleInfo();
        vehicleinfo.setVIN(vinNumber);
        vehicleinfo.setOdometer(miles) ;
        vehicleinfo.setConsumption(gas);
        vehicleinfo.setOilChange(oil) ;
        vehicleinfo.setEngineSize(engine);

       telematicsService.report(vehicleinfo);

    }

}
