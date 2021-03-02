import configuration.Configuration;
import configuration.CsvFileGenerator;
import configuration.ObjectGenerator;
import employee.Administrator;
import employee.Employee;
import employee.Profile;
import employee.idCard.IDCard;

public class Application {
    public static void main(String[] args) {
        CsvFileGenerator csvDataGenerator = new CsvFileGenerator();

        csvDataGenerator.generateBasePackageFile();
        csvDataGenerator.generateBaseBoxFile();
        csvDataGenerator.generateBasePalletFile();
        csvDataGenerator.generateBaseTruckFile();

        ObjectGenerator objectGenerator = new ObjectGenerator();
        objectGenerator.generateSortingStation();
        objectGenerator.generateAllEmployees();
    }
}
