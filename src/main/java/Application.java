import base.CsvFileGenerator;

public class Application {
    public static void main(String[] args) {
        CsvFileGenerator csvDataGenerator = new CsvFileGenerator();
        csvDataGenerator.generateAllFiles();
    }
}
