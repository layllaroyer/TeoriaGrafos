import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int INF = 99999;
        Scanner scanner = new Scanner(System.in);
        int testes = scanner.nextInt();
        int contador = 0;
        int [] resposta=new int[testes];
        while( contador!=testes) {
            int predios = scanner.nextInt();
            int estradas = scanner.nextInt();

            int grafo[][] = new int[predios][predios];
            for(int i=0;i<predios;i++){
                for(int j=0;j<predios;j++){
                    grafo[i][j]=INF;
                }
            }
            for (int i = 0; i < predios; ++i) {
                grafo[i][i] = 0;
            }

            for (int i = 0; i < estradas; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                grafo[a][b] =1;
                grafo[b][a]=1;
            }
            int origem = scanner.nextInt();
            int destino = scanner.nextInt();

            // Floyd-Warshall
            for (int k = 0; k < predios; k++) {
                for (int i = 0; i < predios; i++) {
                    for (int j = 0; j < predios; j++) {
                        grafo[i][j] = min(grafo[i][j], grafo[i][k] + grafo[k][j]);
                    }
                }
            }

            int minTime = 0;
            for (int i = 0; i < predios; ++i){
                if (grafo[origem][i] != INF && grafo[i][destino] != INF) {
                    minTime = max(minTime, grafo[origem][i] + grafo[i][destino]);
                }
            }
            resposta[contador] = minTime;
            contador++;
        }
        for(int i=0;i<testes;i++){
            System.out.printf("Case %d: %d\n",i+1,resposta[i]);
        }
    }
    public static int min(int num1, int num2){
        if(num1<num2)
            return num1;
        return num2;
    }
    public static int max(int num1, int num2){
        if(num1>num2)
            return num1;
        return num2;
    }

}