package Enumeraciones;

/**
 *
 * @author Tincho
 */

public enum Ropa {
    PANTALON("Pantalon"),
    CAMISA("Camisa"),
    BORCEGO("Borcego"),
    ABRIGO("Abrigo"),
    GUANTESDECUERO("Guantes de cuero"),
    GUANTESDECABRITILLA("Guantes de cabritilla");

    private String displayName;

    private Ropa(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

