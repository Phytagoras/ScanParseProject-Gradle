package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;

/**
 * Diese Klasse scannt Strings für die Sprache L_Knebi = k(ne)*bi
 */
public class BungalowKontextfreiScanner extends Scanner<String, String> {

    private String debugOutput;

    @Override
    public boolean scan(String input) {
        debugOutput = "\nScanner recognized following tokens:\n";
        if (input == null || input.length() == 0) {                   //Abbruch falls String leer
            debugOutput += "Sorry, can't scan empty String.";
            return false;
        }
        this.tokenList = new List();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') {
                this.tokenList.append(new Token(input.charAt(i), "AUSSENWAND"));
                debugOutput += "AUSSENWAND > ";
            }  else if (input.charAt(i) == 'w') {
                if(i<input.length()-1 && input.charAt(i+1) == 'f'){
                        this.tokenList.append(new Token(input.charAt(i), "WANDMITFENSTER"));
                        debugOutput += "WANDMITFENSTER > ";
                        i++;
                    } else {
                        this.tokenList.append(new Token(input.charAt(i), "WAND"));
                        debugOutput += "WAND > ";
                    }
            } else if (input.charAt(i) == 't') {
                this.tokenList.append(new Token(input.charAt(i), "TUER"));
                debugOutput += "TUER > ";

            } else return false;
        }
        this.tokenList.append(new Token("#", "NODATA"));
        tokenList.toFirst(); // WICHTIG!
        return true;
    }

    public String getDebugOutput() {
        return debugOutput;
    }
}
