


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
//
public class TelematicsService {

    public void report(VehicleInfo vehicleInfo) {

        javaJson(vehicleInfo);
        html();
    }

    public void javaJson(VehicleInfo vehicleInfo){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(vehicleInfo);
            File file = new File(vehicleInfo.getVIN() + ".json");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
        } catch(IOException ex){
            System.out.println(ex);
        }

    }



    public void html(){
        try {
            String htmlVehicleInfo = "";
            VehicleInfo info = new VehicleInfo();
            int numCars = 0;

            ObjectMapper mapper = new ObjectMapper();
            File file = new File(".");
            for (File f : file.listFiles()) {
                if (f.getName().endsWith(".json")) {
                    numCars++;
                    VehicleInfo vehicleInfo = mapper.readValue(f, VehicleInfo.class);

                    info.setOdometer(info.getOdometer() + vehicleInfo.getOdometer()/numCars) ;
                    info.setConsumption(info.getConsumption() + vehicleInfo.getConsumption()/numCars);
                    info.setOilChange(info.getOilChange() + vehicleInfo.getOilChange()/numCars) ;
                    info.setEngineSize(info.getEngineSize() + vehicleInfo.getEngineSize()/numCars) ;

                    htmlVehicleInfo +=
                            "        <tr>\n" +
                                    "            <td align=\"center\">" +  vehicleInfo.getVIN() + "</td><td align=\"center\">"+ vehicleInfo.getConsumption() + "</td><td align=\"center\">"+ vehicleInfo.getOdometer()+ "</td><td align=\"center\">"+ vehicleInfo.getOilChange()+ "</td align=\"center\"><td align=\"center\">"+ vehicleInfo.getEngineSize()+ "</td>\n" +
                                    "        </tr>\n";

                    // Now you have a File object named "f".
                    // You can use this to create a new instance of Scanner
                }
            }




            File htmlFile = new File("dashboard.html");

            FileWriter fileWriter = new FileWriter(htmlFile);
            String htmlContent = "<html>\n" +
                    "  <title>Vehicle Telematics Dashboard</title>\n" +
                    "  <body>\n" +
                    "    <h1 align=\"center\">Averages for " + numCars + " vehicles</h1>\n" +
                    "    <table align=\"center\">\n" +
                    "        <tr>\n" +
                    "            <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td align=\"center\">" + Math.round(info.getOdometer() ) + "</td><td align=\"center\">" + Math.round(info.getConsumption())+ "</td><td align=\"center\">"+ Math.round(info.getOilChange()) + "</td align=\"center\"><td align=\"center\">"+ Math.round(info.getEngineSize()) +"</td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "    <h1 align=\"center\">History</h1>\n" +
                    "    <table align=\"center\" border=\"1\">\n" +
                    "        <tr>\n" +
                    "            <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
                    "        </tr>\n" +
                    htmlVehicleInfo +
                    "        <tr>\n" +
                    "            <td align=\"center\">45435</td><td align=\"center\">123</td><td align=\"center\">234</td><td align=\"center\">345</td align=\"center\"><td align=\"center\">4.5</td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "  </body>\n" +
                    "</html>\n";

            fileWriter.write(htmlContent);
            fileWriter.close();
        } catch (IOException ex){
            System.out.println(ex);
        }



    }

}
