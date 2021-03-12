import configuration.CsvFileGenerator;
import configuration.ObjectGenerator;

public class Application {
    public static void main(String[] args) {
        CsvFileGenerator csvDataGenerator = new CsvFileGenerator();

        csvDataGenerator.generateBasePackageFile();
        csvDataGenerator.generateBaseBoxFile();
        csvDataGenerator.generateBasePalletFile();
        csvDataGenerator.generateBaseTruckFile();

        ObjectGenerator objectGenerator = new ObjectGenerator();
    }
}
