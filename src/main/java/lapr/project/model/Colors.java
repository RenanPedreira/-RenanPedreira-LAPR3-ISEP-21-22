package lapr.project.model;

/**
 * @author Luís Araújo
 */
public enum Colors {
    BLUE("BLUE", 0),
    RED("RED", 1),
    BLACK("BLACK", 2),
    GREEN("GREEN", 3),
    ORANGE("ORANGE", 4),
    YELLOW("YELLOW", 5);

    String color;
    int colorNum;

    Colors(String color, int colorNum) {
        this.color = color;
        this.colorNum = colorNum;
    }

    public static Colors getColorByNumber(int colorNum) {
        Colors[] colors = Colors.values();
        for (Colors c : colors)
            if (c.colorNum == colorNum)
                return c;

        return null;
    }

    public int getColorNum() {
        return colorNum;
    }

    public static int getNumberOfColors() {
        return Colors.values().length;
    }

    @Override
    public String toString() {
        return String.format("%s", color);
    }
}
