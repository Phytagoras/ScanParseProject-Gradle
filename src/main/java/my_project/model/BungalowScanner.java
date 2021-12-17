package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;

/**
 * Diese Klasse scannt Strings für die Sprache L_Knebi = k(ne)*bi
 */
public class BungalowScanner extends Scanner<String,String> {

    private String debugOutput;

    @Override
    public boolean scan(String input) {
        debugOutput = "\nScanner recognized following tokens:\n";
        if(input == null || input.length() == 0){                   //Abbruch falls String leer
            debugOutput+="Sorry, can't scan empty String.";
            return false;
        }
        this.tokenList = new List();
        for(int i = 0; i<input.length(); i++){                      //einmal den ganzen String durchgehen
            if(input.charAt(i) == 'k'){                             //wenn der Char an der Stelle i dem char 'k' entspricht, dann
                this.tokenList.append(new Token(input.charAt(i),"START"));      //erstelle Token 'start' und packe in die Liste
                debugOutput+="START > ";
            } else if (input.charAt(i) == 'n'){                     //wenn der Char an der Stelle i dem char 'n' entspricht, dann
                if(i<input.length()-1){                             //und wenn wir noch nicht an der letzten Stelle des Strings angekommen sind
                    if(input.charAt(i+1) == 'e'){                   //und wenn der Char an der Stelle i + 1 dem char 'e' entspricht, dann
                        this.tokenList.append(new Token(input.substring(i,i+2),"MIDDLE"));      //erstelle Token 'middle' und packe in die Liste
                        debugOutput+="MIDDLE > ";
                        i++;
                    } else return false;
                } else return false;
            } else if (input.charAt(i) == 'b'){                     //wenn der Char an der Stelle i dem char 'b' entspricht, dann
                if(i<input.length()-1){                                 //und wenn wir noch nicht an der letzten Stelle des Strings angekommen sind
                    if(input.charAt(i+1) == 'i'){                   //und wenn der Char an der Stelle i + 1 dem char 'i' entspricht, dann
                        this.tokenList.append(new Token(input.substring(i,i+2),"END"));        //erstelle Token 'end' und packe in die Liste
                        debugOutput+="END > ";
                        i++;
                    } else return false;
                } else {
                    return false;
                }
            } else return false;
        }
        this.tokenList.append(new Token("#","NODATA"));
        tokenList.toFirst(); // WICHTIG!
        return true;
    }

    public String getDebugOutput(){
        return debugOutput;
    }
}
