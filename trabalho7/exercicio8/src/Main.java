import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int teste = scanner.nextInt();
        int contador = 0;
        while (contador < teste) {
            int largura = scanner.nextInt();
            int altura = scanner.nextInt();
            Grafo grafo = new Grafo(largura * altura + 1);
            ArrayList<Integer> fogos = new ArrayList<>();
            int posicaoInicial=0;
            scanner.nextLine();
            String[] mapa = new String[altura];
            for (int i = 0; i < altura; i++) {
                mapa[i] = scanner.nextLine();
            }
            int cont = 0;
            boolean impossivel = false;
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < largura; j++) {
                    int aux1 = i - 1;
                    int aux2 = j;
                    if (mapa[i].charAt(j) != '#') {
                        if(mapa[i].charAt(j) == '@')
                            posicaoInicial = i*largura+j+1;
                        if (mapa[i].charAt(j)=='*')
                            fogos.add(i*largura+j+1);
                        grafo.setValores(i*largura+j+1, mapa[i].charAt(j));
                        if ((aux1 >= 0) && (aux1 < altura) && (aux2 >= 0) && (aux2 < largura)){
                            if(mapa[aux1].charAt(aux2)!='#'){
                                grafo.novaAresta(i * largura + j + 1, aux1 * largura + aux2 + 1);
                                grafo.setValores(aux1*largura+aux2+1, mapa[aux1].charAt(aux2));
                                if((mapa[i].charAt(j) == '@')&&(mapa[aux1].charAt(aux2)=='*'))
                                    cont++;
                            }
                            else
                                cont++;
                        }
                        aux1 = i;
                        aux2 = j + 1;
                        if ((aux1 >= 0) && (aux1 < altura) && (aux2 >= 0) && (aux2 < largura)){
                            if(mapa[aux1].charAt(aux2)!='#'){
                                grafo.novaAresta(i * largura + j + 1, aux1 * largura + aux2 + 1);
                                grafo.setValores(aux1*largura+aux2+1, mapa[aux1].charAt(aux2));
                                if((mapa[i].charAt(j) == '@')&&(mapa[aux1].charAt(aux2)=='*'))
                                    cont++;
                            }
                            else
                                cont++;
                        }

                        aux1 = i + 1;
                        aux2 = j;
                        if ((aux1 >= 0) && (aux1 < altura) && (aux2 >= 0) && (aux2 < largura)){
                            if(mapa[aux1].charAt(aux2)!='#'){
                                grafo.novaAresta(i * largura + j + 1, aux1 * largura + aux2 + 1);
                                grafo.setValores(aux1*largura+aux2+1, mapa[aux1].charAt(aux2));
                                if((mapa[i].charAt(j) == '@')&&(mapa[aux1].charAt(aux2)=='*'))
                                    cont++;
                            }
                            else
                                cont++;
                        }
                        aux1 = i;
                        aux2 = j - 1;
                        if ((aux1 >= 0) && (aux1 < altura) && (aux2 >= 0) && (aux2 < largura)){
                            if(mapa[aux1].charAt(aux2)!='#'){
                                grafo.novaAresta(i * largura + j + 1, aux1 * largura + aux2 + 1);
                                grafo.setValores(aux1*largura+aux2+1, mapa[aux1].charAt(aux2));
                                if((mapa[i].charAt(j) == '@')&&(mapa[aux1].charAt(aux2)=='*'))
                                    cont++;
                            }
                            else
                                cont++;
                        }
                    }
                }
            if(cont == 4)
                impossivel = true;
            if(impossivel)
                break;
            }
            if(impossivel)
                System.out.println("IMPOSSIBLE");
            else {
                int[] distTo = new int[largura * altura + 1];
                int[] edgeTo = new int[largura * altura + 1];
                int[][] disToFogos = new int[fogos.size()][largura * altura + 1];
                int saida = grafo.bfs_menorCaminho(posicaoInicial, distTo, edgeTo, largura, altura);
                for (int i = 0; i < fogos.size(); i++) {
                    grafo.bfs(fogos.get(i), disToFogos[i]);
                }
                int segundo = ehPossivel(disToFogos, edgeTo, distTo, saida, posicaoInicial);
                if (segundo == 0)
                    System.out.println("IMPOSSIBLE");
                else
                    System.out.println(segundo);
            }
            contador++;
        }
    }

    public static int ehPossivel(int[][]distFogos, int[]edgeTo, int[]distTo, int saida, int posInicial){
        if((distTo[saida]==0)&&(saida!=posInicial))
            return 0;
        int i=saida;
        while(true){
            for(int j = 0; j<distFogos.length; j++) {
                if (distFogos[j][i] <= distTo[i])
                    return 0;
            }
            i = edgeTo[i];
            if(i==posInicial)
                break;
        }
        return distTo[saida]+1;
    }
}