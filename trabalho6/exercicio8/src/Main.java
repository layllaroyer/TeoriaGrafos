import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

    public class Main {
        public static void main(String[] args) {
            int quantEdificios = 0;
            int quantCabos = 0;

            try {
                FileReader arq = new FileReader("arquivo.txt");
                BufferedReader lerArq = new BufferedReader(arq);
                String linha = lerArq.readLine(); // lê a primeira linha
                while (linha != null) {
                    if(linha.length() == 1)
                        quantEdificios = Integer.parseInt(String.valueOf(linha.charAt(0)));
                    else if(linha.length() == 2)
                        quantEdificios = Integer.parseInt(String.valueOf(linha.charAt(0)))*10 + Integer.parseInt(String.valueOf(linha.charAt(1)));
                    else if(linha.length()==3)
                        quantEdificios = Integer.parseInt(String.valueOf(linha.charAt(0)))*100 + Integer.parseInt(String.valueOf(linha.charAt(1)))*10 +Integer.parseInt(String.valueOf(linha.charAt(2)));

                    Grafo grafo = new Grafo(quantEdificios);
                    for(int i = 0; i<quantEdificios;i++){
                        linha = lerArq.readLine();
                        double x = 0;
                        double y = 0;
                        int aux = linha.indexOf(' ');
                        for(int j = 0; j<aux; j++){
                            x = x + Integer.parseInt(String.valueOf(linha.charAt(j)))*Math.pow(10,aux-(j+1));
                        }
                        int aux2 = linha.length() - (aux+2);
                        for(int j = aux+1; j<linha.length(); j++){
                            y = y + Integer.parseInt(String.valueOf(linha.charAt(j)))*Math.pow(10,aux2);
                            aux2--;
                        }
                       // System.out.println(x+" "+y);
                        Vertice v = new Vertice(x,y);
                        grafo.inserirVertice(v);
                    }
                    linha = lerArq.readLine();
                    if(linha.length() == 1)
                        quantCabos = Integer.parseInt(String.valueOf(linha.charAt(0)));
                    else if(linha.length() == 2)
                        quantCabos = Integer.parseInt(String.valueOf(linha.charAt(0)))*10 + Integer.parseInt(String.valueOf(linha.charAt(1)));
                    else if(linha.length()==3)
                        quantCabos = Integer.parseInt(String.valueOf(linha.charAt(2)))*100 + Integer.parseInt(String.valueOf(linha.charAt(1)))*10 +Integer.parseInt(String.valueOf(linha.charAt(0)));
                    else if(linha.length()==4)
                        quantCabos = Integer.parseInt(String.valueOf(linha.charAt(3)))*1000 + Integer.parseInt(String.valueOf(linha.charAt(2)))*100 +Integer.parseInt(String.valueOf(linha.charAt(1)))*10 +Integer.parseInt(String.valueOf(linha.charAt(0)));

                    for (int i=0; i<quantCabos; i++){
                        linha = lerArq.readLine();
                        int aux = linha.indexOf(' ');
                        int v1 = 0 ;
                        int v2 = 0;
                        for(int j = 0; j<aux; j++){
                            Double aux2 = Math.pow(10,aux-(j+1));
                            int aux3 = aux2.intValue();
                            v1 = v1+ Integer.parseInt(String.valueOf(linha.charAt(j)))*aux3;
                        }
                        int aux4 = linha.length()-(aux+2);
                        for(int j = aux+1; j<linha.length();j++){
                            Double aux2 = Math.pow(10,aux4);
                            int aux3 = aux2.intValue();
                            v2 = v2+ Integer.parseInt(String.valueOf(linha.charAt(j)))*aux3;
                            aux4--;
                        }
                        double dist = grafo.calculaDist(v1-1,v2-1);
                        grafo.inserirAresta(v1-1,v2-1, dist);
                    }
                    linha = lerArq.readLine();
                    grafo.kruskalMST();
                }
                arq.close();
            } catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n",
                        e.getMessage());
            }
        }

   }