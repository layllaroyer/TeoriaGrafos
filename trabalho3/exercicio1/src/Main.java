import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int quantLugares = 0;
        ArrayList<String> linhasArquivo = new ArrayList<String>();
        try {
            FileReader arq = new FileReader("arquivo.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine(); // lê a primeira linha
            while(linha != null){
                if(linha.charAt(0) != '0'){
                    if(linha.length() == 1){
                        quantLugares = Integer.parseInt(String.valueOf(linha.charAt(0)))+1;
                       // System.out.println("Tamanho do grafo"+quantLugares);
                    }
                    else{
                        linhasArquivo.add(linha);
                        System.out.println(linha);
                    }
                    linha = lerArq.readLine();
                }
                else {
                    linha = lerArq.readLine();
                    if(linha.charAt(0) == '0')
                        linha = lerArq.readLine();
                    preencheGrafo(linhasArquivo, quantLugares);
                    linhasArquivo.clear();
                }
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }

    public static void preencheGrafo(ArrayList<String> linhasArquivo, int quantLugares){
        Grafo lugares = new Grafo(quantLugares);
        for(int i = 0; i<linhasArquivo.size(); i++){
            String auxiliar = linhasArquivo.get(i);
            int vertice1 = Integer.parseInt(String.valueOf(auxiliar.charAt(0))), vertice2 = 0;
            for(int j = 1; j< auxiliar.length(); j++){
                if(auxiliar.charAt(j) != ' ') {
                    if (((j+1)!=auxiliar.length())&&(auxiliar.charAt(j+1) != ' ')) {
                        int aux1 = Integer.parseInt(String.valueOf(auxiliar.charAt(j)));
                        int aux2 = Integer.parseInt(String.valueOf(auxiliar.charAt(j + 1)));
                        vertice2 = aux1 * 10 + aux2;
                        j++;
                    } else
                        vertice2 = Integer.parseInt(String.valueOf(auxiliar.charAt(j)));
                    //System.out.println(vertice2);
                    lugares.novaAresta(vertice1, vertice2);
                }
            }
        }
        System.out.println(lugares.adj[2]);
    }
}