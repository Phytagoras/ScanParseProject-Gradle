package my_project.model;

public class BungalowKontextfreiParser implements Parser {

    private final BungalowScanner scanner;

    public BungalowKontextfreiParser() {
        scanner = new BungalowScanner();
    }

    @Override
    /**
     * Diese Methode parst eine Eingabe und stellt fest, ob sie zur Sprache L_Knebi = k(ne)*bi gehört
     */
    public boolean parse(String input) {
        return alternativeParse(input);
    }

    @Override
    /**
     * Diese Methode dient dazu die Funktionalität des verwendeten Scanners zu überprüfen.
     * @return true, falls der Scanner für die Sprache des Parsers das Wort akzeptiert, sonst false
     */
    public boolean getScannerResult(String input) {
        return scanner.scan(input);
    }

    // ****************** AB HIER FOLGT EINE ÄQUIVALENTE VORGEHENSWEISE (für die Klasse redundant) ********************

    /**
     * Diese ist eine alternative Methode für das Parsen von L_Knebi = k(ne)*bi
     *
     * @param input der zu parsenden String
     * @return true, falls die Eingabe ein Wort der Sprache ist, anderfalls false
     */
    public boolean alternativeParse(String input) {
        if (scanner.scan(input)) {
            return checkS();
        }
        return false;
    }

    private boolean checkS() {
        if (scanner.getType().equals("AUSSENWAND")) scanner.nextToken();
        else return false;
        if (checkA());
        else return false;
        if (scanner.getType().equals("AUSSENWAND")) scanner.nextToken();
        else return false;
        return true;
    }

    private boolean checkA() {

        if (scanner.getType().equals("WAND") || scanner.getType().equals("WANDMITFENSTER")) scanner.nextToken();
        else return false;
        if (checkB());
        else return false;
        if (scanner.getType().equals("WAND") || scanner.getType().equals("WANDMITFENSTER")) scanner.nextToken();
        else return false;
        return true;
    }

    private boolean checkB() {
        if (scanner.getType().equals("WAND") || scanner.getType().equals("WANDMITFENSTER")||scanner.getType().equals("TUER")){
            if (scanner.getType().equals("TUER")){
                scanner.nextToken();
                return true;
            }
            scanner.nextToken();
        }
        else return false;
        if (checkA());
        else return false;
        if (scanner.getType().equals("WAND") || scanner.getType().equals("WANDMITFENSTER")) scanner.nextToken();
        else return false;
        return true;
    }

    //************** Ausgabe der Scanner-Analyse zur Nachvollziehbarkeit *********

    public String getScannerOutput() {
        return scanner.getDebugOutput();
    }

}
