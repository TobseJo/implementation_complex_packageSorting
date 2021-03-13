package packageSorting;

public class Package {

    private String id;
    private char[][][] content;
    private int zipCode;
    private Type type;
    private double weight;

    public Package(String id, String content, String zipCode, String type, String weight) {
        this.id = id;
        this.content = getContentArray(content);
        this.zipCode = Integer.valueOf(zipCode);
        this.type = getType(type);
        this.weight = Double.valueOf(weight);
    }

    private char[][][] getContentArray(String content) {
        char[][][] contentArray = new char[25][10][10];

        int position = 0;
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    contentArray[i][j][k] = content.charAt(position);
                    position++;
                }
            }
        }

        return contentArray;
    }

    private Type getType(String type) {
        switch (type) {
            case "NORMAL":
                return Type.NORMAL;
            case "EXPRESS":
                return Type.EXPRESS;
            case "VALUE":
                return Type.VALUE;
            default:
                System.out.println("Error, invalid Type");
                return null;
        }
    }

    public String getContentAsString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (char[][] chars : getContent()) {
            for (char[] chars1 : chars) {
                for (char chars2 : chars1) {
                    stringBuilder.append(chars2);
                }
            }
        }
        return stringBuilder.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char[][][] getContent() {
        return content;
    }

    public void setContent(char[][][] content) {
        this.content = content;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
