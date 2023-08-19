package trabalho2.exercicio2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String nomeArquivo = "C:\\Users\\gusta\\Desktop\\Trabalho\\TeoriaGrafos\\trabalho2\\exercicio2\\dados1.txt";
        ArrayList<String> palavrasIndividuais = new ArrayList<>();
        List<String[]> paresDePalavras = new ArrayList<>();
        Set<String> palavrasComEspaco = new HashSet<>();
        Grafo g = new Grafo();

        int numero = 0;
        try {
            FileReader fileReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String primeiraLinha = bufferedReader.readLine();
            numero = Integer.parseInt(primeiraLinha);

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                if (linha.contains(" ")) {

                    String[] palavras = linha.split(" ");
                    if (!palavrasComEspaco.contains(linha)) {
                        paresDePalavras.add(palavras);
                        palavrasComEspaco.add(linha);
                    }
                } else {

                    palavrasIndividuais.add(linha);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        String[] todasAsPalavras = new String[palavrasIndividuais.size() + paresDePalavras.size() * 2];
        int index = 0;
        for (String palavra : palavrasIndividuais) {
            todasAsPalavras[index] = palavra;
            index++;
        }
        for (String[] par : paresDePalavras) {
            todasAsPalavras[index] = par[0];
            todasAsPalavras[index + 1] = par[1];
            index += 2;
        }

        Set<String> palavrasUnicas = new HashSet<>();
        palavrasUnicas.addAll(palavrasIndividuais);
        for (String[] par : paresDePalavras) {
            palavrasUnicas.add(par[0]);
            palavrasUnicas.add(par[1]);
        }

        String[] palavrasUnicasArray = palavrasUnicas.toArray(new String[0]);
        Vertice[] v = new Vertice[palavrasUnicasArray.length];
        for (int i = 0; i < palavrasUnicasArray.length; i++) {
             v[i] = g.addVertice(palavrasUnicasArray[i]);
        }

        for(int i = 0; i<numero; i++){
            for(int j = 0; j<numero; j++) {
                for (int k = 0; k < numero; k++) {
                    if (v[i].nome.equals(paresDePalavras.get(k)[0])) {
                        if ((v[j].nome.equals(paresDePalavras.get(k)[1]))) {
                            g.addAresta(v[i], v[j]);
                        }
                    }
                }
            }
        }


        g.imprimirArestas();
        g.checagem(palavrasIndividuais);
    }
}















