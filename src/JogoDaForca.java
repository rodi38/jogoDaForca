import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca {
    private static final String[] palavrasChave = {"Goiaba", "Carro", "Elefante", "Coca"};

    public static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        String[][] matriz = {
                {"", "_", "_", "_", "_", "_", "_", " "},
                {"|/ ", "", "", "", "", "", "|", " "},
                {"| ", "", "", "", "", "", "", " "},
                {"| ", "", "", "", "", "", "", " "},
                {"|  ", "", "", "", "", "", "", " "},
                {"| ", "", "", "", "", "", "", " "},
                {"| ", "", "", "", "", "", "", " "},
                {"|", "", "", "", "", "", "", " "}};
        String palavraSorteada = selecionaPalavra();
        String palavraVencedora = "";
        imprimeMenu(matriz);
        boolean confirmaJogo = iniciaJogo();
        String[] chuteCorreto = new String[palavraSorteada.length()];
        boolean flag;
        if (confirmaJogo){
            flag = true;
        } else {
            System.out.println("Encerrando o jogo...");
            flag = false;
        }
        int contaErros = 0;
        while (flag) {
            System.out.print("Digite uma letra: ");
            String chute = scanner.nextLine().toUpperCase();

            if ((chute.length() > 1) || !(chute.charAt(0) >= 65) || !(chute.charAt(0) <= 90)) {
                System.out.println("Dado incorreto, digite APENAS uma letra!");
                continue;
            }
            if (!palavraSorteada.contains(chute)){
                contaErros++;
            }
            for (int i = 0; i < palavraSorteada.length(); i++) {
                if (palavraSorteada.charAt(i) == chute.charAt(0)) {
                    chuteCorreto[i] = chute;
                }
            }
            verificaErro(matriz, contaErros);
            imprimeStatus(matriz, chuteCorreto, contaErros);
            if (contaErros == 4){
                System.out.println("\n4 erros, você perdeu...");
                flag = false;
            }

            if (String.join("", chuteCorreto).equals(palavraSorteada)) {
                palavraVencedora = String.join("", chuteCorreto);
                System.out.printf("\nParabens, a palavra correta eh: %s", palavraVencedora);
                flag = false;
            }

        }

    }
    public static String selecionaPalavra() {
        int numeroAleatorio = random.nextInt(palavrasChave.length);
        return palavrasChave[numeroAleatorio].toUpperCase();
    }

    public static boolean iniciaJogo() {
        boolean flag = true;
        String confirm = "";
        System.out.println("Deseja começar o jogo? S/N");
        while (flag){
                confirm = scanner.nextLine().toUpperCase();
                if (confirm.equals("N") || confirm.equals("S") ){
                    flag = false;
                }else {
                    System.out.println("Digite S para sim ou N para nao.");
                }
        }
        return confirm.equals("S");
    }

    public static void verificaErro(String[][] matriz, int quantidadeErros){
        if (quantidadeErros == 1){
            matriz[2][6] = "[-]";
        } else if (quantidadeErros == 2) {
            matriz[3][6] = "/|\\";
        } else if (quantidadeErros == 3) {
            matriz[4][6] = "|";
        } else if (quantidadeErros == 4) {
            matriz[5][6] = "/-\\";
        }
    }
    public static void imprimeStatus(String[][] matriz, String[] chutes, int quantidadeErros){
        for (int i = 0; i < matriz.length ; i++) {
            System.out.print("");
            for (int j = 0; j < matriz[i].length; j++) {
                if(j == 1){
                    System.out.print("  " + matriz[i][j]);
                    continue;
                }
                System.out.print(" "+matriz[i][j]);
            }
            System.out.println();
        }
        for (int i = 0; i < chutes.length; i++) {
            if(chutes[i] == null){
                chutes[i] = "_";
            }
        }
        System.out.print("\t" + Arrays.toString(chutes).replace(",", " ") +
                "Você errou " + quantidadeErros+" vezes, voce so pode errar mais  " + (4-quantidadeErros) +
                " vezes "+"\n");
    }
    public static void imprimeMenu(String[][] matriz) {
        System.out.println("Bem vindo ao jogo da forca");
        System.out.println();
        System.out.println("  _ _ _ _ _ _ _ ");
        System.out.println(" |/        |");
        System.out.println(" |        [-]");
        System.out.println(" |        /|\\");
        System.out.println(" |         |");
        System.out.println(" |        /-\\ ");
        System.out.println(" |");
        System.out.println(" |");
        System.out.println("\nVocê pode errar apenas 4 vezes até o personagem ser enforcado, tome cuidado!\n");
    }
}
