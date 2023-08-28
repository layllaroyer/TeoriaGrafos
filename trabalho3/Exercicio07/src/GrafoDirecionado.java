import java.util.*;

class GrafoDirecionado {
    private List<List<Integer>> grafo;
    private int[] grauEntrada;

    public GrafoDirecionado(int n) {
        grafo = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            grafo.add(new ArrayList<>());
        }
        grauEntrada = new int[n + 1];
    }

    public void adicionarRua(int u, int v) {
        grafo.get(u).add(v);
        grauEntrada[v]++;
    }

    public List<int[]> ordenacaoTopologica() {
        List<int[]> resultado = new ArrayList<>();
        Queue<Integer> fila = new LinkedList<>();

        for (int i = 1; i < grauEntrada.length; i++) {
            if (grauEntrada[i] == 0) {
                fila.add(i);
            }
        }

        while (!fila.isEmpty()) {
            int u = fila.poll();
            for (int v : grafo.get(u)) {
                grauEntrada[v]--;
                if (grauEntrada[v] == 0) {
                    fila.add(v);
                }
                resultado.add(new int[]{u, v});
            }
        }

        return resultado;
    }
}