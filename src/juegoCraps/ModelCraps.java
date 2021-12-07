package juegoCraps;

/**
 * ModelCraps apply cpars rules.
 * estado = 1 Natural winner
 * estado = 2 Craps looser
 * estado = 3 Stablish Punto
 * estado = 4 punto winer
 * estado = 5 Punto looser
 * @author Juan Majin
 * @version v.1.0.0 date 06/12/2021
 */
public class ModelCraps {
    private Dado dado1, dado2;
    private int tiro, punto, estado, flag;
    private String[] estadoToString;
    private int[] caras;


    /**
     * Class Constructor
     */
    public ModelCraps(){
        dado1= new Dado();
        dado2= new Dado();
        caras= new int[2];
        estadoToString= new String[2];
        flag=0;
    }

    /**
     * Establish the tiro value according to each dice
     */
    public void  calcularTiro(){
        caras[0]=dado1.getCara();
        caras[1]= dado2.getCara();
        tiro=caras[0]+caras[1];
    }

    /**
     * Establish game state according to estado attribute value
     * estado = 1 Natural winner
     * estado = 2 Craps looser
     * estado = 3 Stablish Punto
     * estado = 4 punto winer
     * estado = 5 Punto looser
     */
    public void determinarJuego(){
        if(flag==0){
            if(tiro==7 || tiro==11){
                estado= 1;
            }else {
                if(tiro==3 || tiro==2 || tiro==12){
                    estado=2;
                }else{
                    estado=3;
                    punto=tiro;
                    flag=1;
                }
            }
        }else{
            //Ronda punto
            rondaPunto();
        }

    }

    /**
     * Establish game state accordingo to estado attribute value
     * estado = 4 punto winer
     * estado = 5 Punto looser
     */
    private void rondaPunto() {
        if(tiro==punto){
            estado=4;
            flag=0;
        }else{
            if(tiro==7){
                estado=5;
                flag=0;

            }else{
               estado= 6;
            }
        }

    }

    public int getTiro() {
        return tiro;
    }

    public int getFlag() {
        return flag;
    }

    /**
     * Establish message game state according to estado attribute value
     * @return Message for the View class
     */
    public String[] getEstadoToString() {
        switch (estado){
            case 1:estadoToString[0]="Tiro de salida = "+tiro;
                estadoToString[1]="Sacaste natural, Ganaste :D!!!";
                    break;
            case 2:estadoToString[0]="Tiro de salida = "+tiro;
                estadoToString[1]="Sacaste Craps, Perdiste D:!!";
                    break;
            case 3: estadoToString[0]="Tiro de salida = "+tiro+"\n Punto = "+punto;
                estadoToString[1]="Estableciste Punto en "+punto+" , Debes seguir lanzando!!!"+"\n pero si sacas 7 antes que "+punto+" perderas";
                    break;
            case 4: estadoToString[0]="Tiro de salida = "+punto+"\n Punto = "+punto+"\nValor del nuevo tiro= "+tiro;
                estadoToString[1]="Volviste a sacar "+punto+" , Ganaste :)";
                    break;
            case 5:estadoToString[0]="Tiro de salida = "+punto+"\n Punto = "+punto+"\nValor del nuevo tiro= "+tiro;
                estadoToString[1]="Sacaste 7 antes que "+punto+" , Perdiste :(";
                    break;
            case 6:estadoToString[0]="Tiro de salida = "+punto+"\n Punto = "+punto+"\nValor del nuevo tiro= "+tiro;
                estadoToString[1]="\nEstas en Punto y debes seguir lanzando.-."+"\npero si sacas 7 antes que "+punto+" perderas:(";
                break;

        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}
