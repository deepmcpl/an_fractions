package pl.animekkk.fractions.fraction.relation;

public enum RelationType {

    TEAM("&a"),
    ENEMY("&c"),
    ALLY("&e");

    private final String color;

    RelationType(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

}
