package Enumeraciones;

/**
 *
 * @author Tincho
 */

public enum Ropa {
    PANTALON("Pantalón"),
    CAMISA("Camisa"),
    BORCEGO("Borcego"),
    ABRIGO("Abrigo"),
    GUANTES_DE_CUERO("Guantes de cuero"),
    GUANTES_DE_CABRITILLA("Guantes de cabritilla");

    private String displayName;

    private Ropa(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

