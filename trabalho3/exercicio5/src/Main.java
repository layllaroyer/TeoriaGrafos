import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> guarda = new ArrayList<>();
        while (true) {
            int numAmigos = scanner.nextInt();
            int numConexoes = scanner.nextInt();

            if (numAmigos == 0 && numConexoes == 0) {
                break;
            }

            Grafo<Integer> grafoOriginal = new Grafo<>();
            for (int i = 0; i < numAmigos; i++) {
                grafoOriginal.addVertice(i);
            }

            for (int i = 0; i < numConexoes; i++) {
                int amigo1 = scanner.nextInt();
                int amigo2 = scanner.nextInt();
                grafoOriginal.addAresta(amigo1, amigo2);
            }

            guarda.add(grafoOriginal.findBridges());
        }
        for (int i = 0; i < guarda.size(); i++) {
            String item = guarda.get(i);
           if(item.equals("No bridges found"))
           {
               System.out.println("No");
           }
           else
           {
               System.out.println("Yes");
           }
        }

    }
}

