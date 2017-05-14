package gui;

/**
 *
 * @author Manuele
 */
public class CSSingleton {
    private static CSSingleton instance;
    
    private CSSingleton() {
        
    }
    
    /**
     * Colora lo sfondo con il colore indicato. Il colore deve essere una stringa
     * che contiene il nome del colore
     * @param color
     * @return Una stringa CSS per colorare lo sfondo
     */
    public String colorBackground(String color) {
        StringBuilder str = new StringBuilder();
        str.append("-fx-background-color: ");
        str.append(color);
        str.append("; ");
        
        return str.toString();
    }
    
    /**
     * Colora lo sfondo con un gradiente che passa da firstColor a secondColor.
     * I colori devono essere indicati come stringa che inizia per # e contiene
     * altri 6 caratteri che codificano il codice RGB del colore in esadecimale.
     * Alcuni valori sono
     * <p>-Rosso: #ff0000</p>
     * <p>-Verde: #00ff00</p>
     * <p>-Blu: #0000ff</p>
     * <p>-Arancione: #ff8200</p>
     * <p>-Rosa: #ff5aff</p>
     * <p>-Viola: #7a377a</p>
     * <p>-Bianco: #ffffff</p>
     * <p>-Nero: #000000</p>
     * @param firstColor
     * @param secondColor
     * @return Una stringa CSS per colorare lo sfondo con un gradiente
     */
    public String colorBackroundGradient(String firstColor, String secondColor) {
        StringBuilder str = new StringBuilder();
        str.append("-fx-background-color: linear-gradient(");
        str.append(firstColor).append(", ").append(secondColor);
        str.append("); ");
        
        return str.toString();
    }
    
    /**
     * Colora il testo del colore indicato. Il colore viene specificata come una
     * stringa che contiene il nome del colore
     * @param color
     * @return Una stringa CSS per colorare il testo
     */
    public String colorText(String color) {
        StringBuilder str = new StringBuilder();
        str.append("-fx-text-fill: ");
        str.append(color);
        str.append("; ");
        
        return str.toString();
    }
    
    public String addBackgroundImage(String url, int sizeX, int sizeY) {
        StringBuilder str = new StringBuilder();
        str.append("-fx-background-image: url(");
        str.append(url).append(";\n");
        str.append("-fx-background-repeat: stretch;\n");
        str.append("-fx-background-size: ").append(sizeX).append(" ").append(sizeY);
        
        return str.toString();
    }
    
    public String addDropshadowEffect() {
        return "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );";
    }
    
    public String getCSSCodePushbutton() {
        StringBuilder str = new StringBuilder();
        str.append("-fx-text-fill: white; -fx-font-weight: bold; ");
        //str.append("-fx-font-family: \"Arial Narrow\"; ");
        //str.append("-fx-background-color: linear-gradient(#61a2b1, #2A5058); ");
        str.append("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        
        return str.toString();
    }
    
    public static synchronized CSSingleton getInstance() {
        if (instance == null)
            instance = new CSSingleton();
        return instance;
    }
}
