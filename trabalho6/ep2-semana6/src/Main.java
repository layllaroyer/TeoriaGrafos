import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner ent=new Scanner(System.in);
        int ntestes=ent.nextInt();
        int contador=0;
        while(contador!=ntestes){
            int nreceptores=ent.nextInt();
            int n=nreceptores+1;
            //Grafo g=new Grafo(n);
            ArrayList<Integer> X= new ArrayList<Integer>();
            ArrayList<Integer> Y= new ArrayList<Integer>();
            int x=0;
            while(true){
                x=ent.nextInt();
                if(x==-1){
                    break;
                }
                X.add(x);
                int y=ent.nextInt();
                Y.add(y);
            }
            Grafo g =new Grafo(X.size());
            for(int i=0;i<X.size()-1;i++){
                int peso=calculapeso(i,X,Y);
                if(i+1<X.size()){
                    g.adicionarAresta(i,i+1,peso);
                }
            }
            ArrayList<Aresta> mst = g.kruskalMST();
            Aresta a= mst.get(0);
            int menorpeso=a.peso;
            for(int j=1;j< mst.size();j++) {
               a= mst.get(j);
               menorpeso=min(menorpeso,a.peso);
            }
            System.out.println(menorpeso);
            contador++;
        }
    }
    public static int calculapeso(int i, ArrayList<Integer>eixox,ArrayList<Integer>eixoy){
        double peso=0;
        if(i+1<eixox.size()){
            double parte1=eixox.get(i+1)-eixox.get(i);
            double parte2=eixoy.get(i+1)-eixoy.get(i);
            double parte3=Math.pow(parte1,2);
            double parte4=Math.pow(parte2,2);
            double parte5=parte3+parte4;
            peso=Math.sqrt(parte5);
        }
        return (int)peso;
    }
    public static int min(int num1, int num2){
        if(num1<num2)
            return num1;
        return num2;
    }
}
