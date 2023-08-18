package trabalho2.exeercicio1.src;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        ArrayList<String> palavras = new ArrayList<String>();
        Digrafo grafo = new Digrafo(26);
        Scanner scanner = new Scanner(System.in);
        String auxiliar;
        auxiliar = scanner.nextLine();
        while(auxiliar.charAt(0)!='#'){
            palavras.add(auxiliar);
            auxiliar = scanner.nextLine();
        }
        for(int i=0; i<palavras.size()-1;i++){
            String auxiliar1 = palavras.get(i);
            String auxiliar2 = palavras.get(i+1);
            for(int j=0; j<auxiliar1.length() && j<auxiliar2.length(); j++){
                if(auxiliar1.charAt(j) != auxiliar2.charAt(j)){
                    grafo.novaAresta(auxiliar1.charAt(j)-'A', auxiliar2.charAt(j)-'A');
                    grafo.setValores(auxiliar1.charAt(j)-'A', auxiliar1.charAt(j));
                    grafo.setValores(auxiliar2.charAt(j)-'A', auxiliar2.charAt(j));
                    break;
                }
            }
        }
        OrdemTopologica ordem = new OrdemTopologica(grafo);
        ordem.imprimeOrdem();
    }
}