public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numeroCaso = 1;

        while (true) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n == 0 && m == 0) {
                break;
            }

            GrafoDirecionado grafo = new GrafoDirecionado(n);

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                grafo.adicionarRua(u, v);
            }

            List<int[]> resultado = grafo.ordenacaoTopologica();

            System.out.println(numeroCaso);
            for (int[] rua : resultado) {
                System.out.println(rua[0] + " " + rua[1]);
            }
            System.out.println("#");
            numeroCaso++;
        }
    }
}