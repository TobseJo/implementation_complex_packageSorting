import configuration.CsvFileGenerator;
import configuration.ObjectGenerator;

public class Application {
    public static void main(String[] args) {
        CsvFileGenerator csvDataGenerator = new CsvFileGenerator();
        csvDataGenerator.generateAllFiles();

        ObjectGenerator objectGenerator = new ObjectGenerator();
//        objectGenerator.generateSortingStation();
//        objectGenerator.generateTrucks();
    }
}
