public enum EnvironmentTile {
    VIDE(" "),
    TILE("#"),
    CREATURE("@"),
    START("\u2690"),
    END("\u2691");


    private final String tile;

    EnvironmentTile(String tile){
        this.tile=tile;
    }

    public String getTile() {
        return tile;
    }

    @Override
    public String toString() {
        return  tile;
    }



}
