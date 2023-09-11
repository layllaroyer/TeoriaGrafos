import java.util.Comparator;

public class Comparador implements Comparator<Aresta> {
        @Override
        public int compare(Aresta a1, Aresta a2) {
            if (a1.origem < a2.origem) {
                return -1;
            } else if (a1.origem > a2.origem) {
                return 1;
            } else {
                // Se os primeiros elementos forem iguais, compare os segundos elementos
                if (a1.destino < a2.destino) {
                    return -1;
                } else if (a1.destino > a2.destino) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
}
