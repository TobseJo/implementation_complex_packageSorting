package configuration;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import static configuration.Configuration.FILEPATH_DATA;
import static configuration.Configuration.NUMBER_OF_GOVERNMENT_VEHICLE;

public class FileReader {

    public LinkedList<Package> readPackages(String FILEPATH){
        LinkedList<Package> packages = new LinkedList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new java.io.FileReader(new File(Configuration.instance.pathToDataDirectory + FILEPATH)));
            String line = null;

            while ((line = br.readLine()) != null){

                String[] content = line.split(",");

                Package paket = new Package(content[0], content[1], content[2], content[3], content[4]);

                switch (vehicleTypes.get(m)){
                    case "motorcycle":
                        vehicles.add(new MotorCycle("Motorcycle", content[3], licensePlate, content[1].charAt(0), wheels, ticket, hidingPlaces, seats));
                        break;
                    case "truck":
                        vehicles.add(new Truck("Truck", content[3], licensePlate, content[1].charAt(0), wheels, ticket, hidingPlaces, seats));
                        break;
                    case "car":
                        if(Integer.valueOf(content[0]) <= NUMBER_OF_GOVERNMENT_VEHICLE){
                            vehicles.add(new GovernmentVehicle("GovernmentVehicle", "true", licensePlate, content[1].charAt(0), wheels, ticket, null, seats));
                        }else {
                            vehicles.add(new Car("Car", content[3], licensePlate, content[1].charAt(0), wheels, ticket, hidingPlaces, seats));
                        }
                        break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }





    }

}
