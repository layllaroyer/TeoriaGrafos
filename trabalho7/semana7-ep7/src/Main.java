import java.util.*;

class Aresta {
    int destino;
    int peso;

    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

class GrafoPonderado {
    int numVertices;
    List<List<Aresta>> adjacencia;

    public GrafoPonderado(int numVertices) {
        this.numVertices = numVertices;
        this.adjacencia = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            this.adjacencia.add(new ArrayList<>());
        }
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        Aresta aresta = new Aresta(destino, peso);
        adjacencia.get(origem).add(aresta);
    }

    public static int encontrarNumeroMaisProximo(ArrayList<Integer> array, int valorDesejado) {
        int numeroMaisProximo = array.get(0); // Suponha que o primeiro elemento seja o mais próximo inicialmente

        for (int i = 1; i < array.size(); i++) {
            int diferencaAtual = Math.abs(array.get(i) - valorDesejado);
            int diferencaAnterior = Math.abs(numeroMaisProximo - valorDesejado);

            if (diferencaAtual < diferencaAnterior) {
                numeroMaisProximo = array.get(i);
            }
        }

        return numeroMaisProximo;
    }
    public void bfs(int valorAlvo) {
        ArrayList<Integer> array;
        Queue<Integer> fila = new LinkedList<>();
        boolean[] visitados = new boolean[numVertices];
        ArrayList<Integer> elementos = new ArrayList<>();
        fila.offer(0);
        visitados[0] = true;

        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();

            for (Aresta aresta : adjacencia.get(verticeAtual)) {
                int destino = aresta.destino;
                int peso = aresta.peso;

                if (!visitados[destino]) {
                    visitados[destino] = true;
                    fila.offer(destino);
                    elementos.add(peso);
                }
            }
        }
        Collections.sort(elementos);
        int p = 0; // Inicialize 'p'
        int aux = 0;
        ArrayList<Integer> historico = new ArrayList<>();
        int armazena = 0;
        ArrayList<Integer> guarda = new ArrayList<>();
        guarda.add(valorAlvo);
        while (valorAlvo != 0 ) {
            if (!(historico.contains(valorAlvo))) {
                historico.add(valorAlvo);
                int numeroMaisProximo = encontrarNumeroMaisProximo(elementos, valorAlvo);
                boolean contemNegativo = false;
                for (int elemento : elementos) {
                    if (elemento < 0) {
                        contemNegativo = true;
                        break;
                    }
                }
                boolean existemPositivosMaiores = false;

                if (valorAlvo < 0) {
                    int valorAbsoluto = Math.abs(valorAlvo);

                    for (int elemento : elementos) {
                        if (elemento > valorAbsoluto) {
                            existemPositivosMaiores = true;
                            break;
                        }
                    }
                }
                if (valorAlvo < 0 && !(contemNegativo)) {
                    break;
                }

                valorAlvo -= numeroMaisProximo;
                if (valorAlvo < 0 && armazena > 0) {
                    break;
                }
                // Certifique-se de que guarda tenha tamanho suficiente antes de acessá-lo.
                if (p < guarda.size() && valorAlvo > 0 && guarda.get(p) < 0) {
                    break;
                }

                guarda.add(valorAlvo);
                aux++;
                p++;
            } else {
                break;
            }
        }
        int tamanho = guarda.size();
        if (valorAlvo == 0) {
            System.out.printf("%d ",aux);
            System.out.printf("%d\n",Math.abs(valorAlvo));
        } else if (historico.size() >= 2) {
            System.out.printf("%d ",aux);
            System.out.printf("%d\n",Math.abs(guarda.get(tamanho-1)));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int ntestes= scanner.nextInt();
        int contador=0;
        while(contador<ntestes){
            int numArestas = scanner.nextInt();
            int numVertices = numArestas + 1;
            GrafoPonderado grafo = new GrafoPonderado(numVertices);
            int valorAlvo = scanner.nextInt();
            for (int j = 0; j < numArestas; j++) {
                int peso = scanner.nextInt();
                grafo.adicionarAresta(0, j+1, peso);
            }
            grafo.bfs(valorAlvo);
            contador++;
        }
    }
}