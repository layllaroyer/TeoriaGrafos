import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String l = br.readLine();
            if(l.equals("0 0"))System.exit(0);
            String numeros[] = l.split(" ");
            int N = Integer.parseInt(numeros[0]);
            int M = Integer.parseInt(numeros[1]);
            Grafo g = new Grafo(N);

            for(int i=0; i<M; i++){
                l = br.readLine();
                numeros = l.split(" ");
                int u = Integer.parseInt(numeros[0]);
                int v = Integer.parseInt(numeros[1]);
                int w = Integer. parseInt(numeros[2]);
                g.addAresta(u,v,w);
            }
            g.kruskalMST();
        }
    }
}