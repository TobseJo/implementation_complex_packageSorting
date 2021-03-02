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
        Employee employee = new Administrator(1, "Johannes Winkler", Profile.a, 1234, 123456);

        IDCard idCard = new IDCard();

        Configuration.instance.usedAlgorithm.encrypt( " ", idCard);
        employee.setIdCard(idCard);
    }
}
