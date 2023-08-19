package trabalho2.exercicio2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String nomeArquivo = "C:\\Users\\gusta\\Desktop\\Trabalho\\TeoriaGrafos\\trabalho2\\exercicio2\\dados1.txt";
        List<String> palavrasIndividuais = new ArrayList<>();
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
                    // Dividir linha em pares de palavras
                    String[] palavras = linha.split(" ");
                    if (!palavrasComEspaco.contains(linha)) {
                        paresDePalavras.add(palavras);
                        palavrasComEspaco.add(linha);
                    }
                } else {
                    // Adicionar palavra individual à lista
                    palavrasIndividuais.add(linha);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        System.out.println("\n\nPalavras Checagem:");
        for (String palavra : palavrasIndividuais) {
            System.out.println(palavra);
        }

        System.out.println("\n\nPares de Voos (arestas):");
        for (String[] par : paresDePalavras) {
            System.out.println(par[0] + " " + par[1]);
        }

        // Adicionar todas as palavras a um array (com repetições)
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

        // Adicionar palavras únicas a um array (sem repetição)
        Set<String> palavrasUnicas = new HashSet<>();
        palavrasUnicas.addAll(palavrasIndividuais);
        for (String[] par : paresDePalavras) {
            palavrasUnicas.add(par[0]);
            palavrasUnicas.add(par[1]);
        }

        // Convertendo o conjunto de palavras únicas de volta para um array
        String[] palavrasUnicasArray = palavrasUnicas.toArray(new String[0]);
        System.out.println("\n\nPalavras cidades ( vertices )");
        Vertice[] v = new Vertice[palavrasUnicasArray.length];
        for (int i = 0; i < palavrasUnicasArray.length; i++) {
            //System.out.println(palavrasUnicasArray[i]);
             v[i] = g.addVertice(palavrasUnicasArray[i]);
        }

        String teste = v[0].nome;
        System.out.println(teste);

        for(int i = 0; i<numero; i++){
            for(int j = 0; j<numero; j++) {
                for (int k = 0; k < numero; k++) {
                    if (v[i].nome.equals(paresDePalavras.get(k)[0])) {
                        if ((v[j].nome.equals(paresDePalavras.get(k)[1]))) {
                            System.out.println("vdd2");
                            g.addAresta(v[i], v[j]);
                        }
                    }
                }
            }
        }


        g.imprimirArestas();
        g.checagem((ArrayList<String>) palavrasIndividuais);

        //System.out.println(paresDePalavras.get(0)[0] + " " + paresDePalavras.get(0)[1]);
        //System.out.println(paresDePalavras.get(1)[0] + " " + paresDePalavras.get(1)[1]);
        //System.out.println(paresDePalavras.get(2)[0] + " " + paresDePalavras.get(2)[1]);
        //System.out.println("Vertice na classe grafo");
        //g.imprimeVertice();

    }
}















//        try {
//            FileReader fileReader = new FileReader(nomeArquivo);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String primeiraLinha = bufferedReader.readLine();
//            int n = Integer.parseInt(primeiraLinha);
//            String linha;
//            ArrayList<String> palavras = new ArrayList<>();
//            System.out.println(n);
//            while ((linha = bufferedReader.readLine()) != null) {
//                palavras.add(Arrays.toString(linha.split(" ")));
//            }
//            System.out.println(palavras.get(0));
//
//            System.out.println();
//            bufferedReader.close();
//        } catch (IOException e) {
//            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
//        }


//        Grafo g = new Grafo();
//        Vertice a = g.addVertice("Arlington");
//        Vertice s = g.addVertice("San_Antonio");
//        Vertice b = g.addVertice("Baltimore");
//        Vertice n = g.addVertice("New_York");
//        Vertice d = g.addVertice("Dallas");
//
//
//        Aresta as = g.addAresta(a,s);
//        Aresta sb = g.addAresta(s, b);
//        Aresta bn = g.addAresta(b, n);
//        Aresta nd = g.addAresta(n, d);
//        Aresta ba = g.addAresta(b, a);
//
//
//        b.imprimir();
//
//        System.out.println(g);
//        g.imprimirArestas();
//
//        ArrayList<String> cidades = new ArrayList<String>();
//
//        cidades.add("San_Antonio");
//        cidades.add("Baltimore");
//        cidades.add("New_York");
//
//        g.checagem(cidades);






