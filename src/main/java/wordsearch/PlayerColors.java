package wordsearch;

public class PlayerColors {
    public enum Color {
        RED("#FF0000"),
        ORANGE("#FF6A00"),
        YELLOW("#FFF200"),
        GREEN("#22FF00"),
        AQUA("#00FFDD"),
        BLUE("#0033FF"),
        PURPLE("#AA00FF");

        private String hexCode;

        Color(String hexCode) {
            this.hexCode = hexCode;
        }

        public String getHexCode() {
            return hexCode;
        }
    }

    public static Color[] getAllColors() {
        return Color.values();
    }
}