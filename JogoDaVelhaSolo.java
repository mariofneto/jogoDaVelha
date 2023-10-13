import java.util.*;

public class JogoDaVelhaSolo {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] tabuleiro =  {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', ' ', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', ' ', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        System.out.println("*** JOGO DA VELHA DO MARIN ***\n");
        mostrarTabuleiro(tabuleiro);

        while(true){
            System.out.println("Escolha uma posição (1-9): ");
            int posicaoEscolhidaPlayer = sc.nextInt();

            while(playerPositions.contains(posicaoEscolhidaPlayer) || cpuPositions.contains(posicaoEscolhidaPlayer)){
                System.out.println("Posição escolhida, tente outra: ");
                posicaoEscolhidaPlayer = sc.nextInt();
            }
            modificarTabuleiro(tabuleiro, posicaoEscolhidaPlayer, "player");

            String resultado = checarVencedor();
            if(resultado.length() > 0){
                System.out.println(resultado);
                mostrarTabuleiro(tabuleiro);
                break;
            }


            Random random = new Random();
            int posicaoEscolhidaCpu = random.nextInt(9) + 1;

            while(playerPositions.contains(posicaoEscolhidaCpu) || cpuPositions.contains(posicaoEscolhidaCpu)){
                posicaoEscolhidaCpu = random.nextInt(9) + 1;
            }
            modificarTabuleiro(tabuleiro, posicaoEscolhidaCpu, "cpu");
            mostrarTabuleiro(tabuleiro);
            resultado = checarVencedor();

            if(resultado.length() > 0){
                System.out.println(resultado);
                mostrarTabuleiro(tabuleiro);
                break;
            }

            System.out.println(resultado);

        }


    }

    public static void mostrarTabuleiro(char[][] tabuleiro){
        for(char[] c : tabuleiro){
            System.out.println(c);
        }
        System.out.println();
    }

    public static void modificarTabuleiro(char[][] tabuleiro, int position, String playerOuCpu){
        char simbolo = ' ';

        if(playerOuCpu.equals("player")){
            simbolo = 'X';
            playerPositions.add(position);
        } else if(playerOuCpu.equals("cpu")){
            simbolo = 'O';
            cpuPositions.add(position);
        }

        switch (position){
            case 1: tabuleiro[0][0] = simbolo; break;
            case 2: tabuleiro[0][2] = simbolo; break;
            case 3: tabuleiro[0][4] = simbolo; break;
            case 4: tabuleiro[2][0] = simbolo; break;
            case 5: tabuleiro[2][2] = simbolo; break;
            case 6: tabuleiro[2][4] = simbolo; break;
            case 7: tabuleiro[4][0] = simbolo; break;
            case 8: tabuleiro[4][2] = simbolo; break;
            case 9: tabuleiro[4][4] = simbolo; break;
            default: break;
        }
    }

    public static String checarVencedor(){
        List<List> casasGanhadoras = new ArrayList<List>();
        String resultado = "";

        List colunaEsquerda = Arrays.asList(1,4,7);
        List colunaMeio = Arrays.asList(2,5,8);
        List colunaDireita = Arrays.asList(3,6,9);
        List linhaCima = Arrays.asList(1,2,3);
        List linhaMeio = Arrays.asList(4,5,6);
        List linhaBaixo = Arrays.asList(7,8,9);
        List diagonal1 = Arrays.asList(1,5,9);
        List diagonal2 = Arrays.asList(3,5,7);

        casasGanhadoras.add(colunaEsquerda);
        casasGanhadoras.add(colunaMeio);
        casasGanhadoras.add(colunaDireita);
        casasGanhadoras.add(linhaCima);
        casasGanhadoras.add(linhaMeio);
        casasGanhadoras.add(linhaBaixo);
        casasGanhadoras.add(diagonal1);
        casasGanhadoras.add(diagonal2);

        for(List l : casasGanhadoras){
            if(playerPositions.containsAll(l)){
                return "Jogador venceu!";
            }else if(cpuPositions.containsAll(l)){
                return "CPU venceu!";
            }else if(playerPositions.size() + cpuPositions.size() == 9){
                return "Deu velha :)";
            }
        }
        return "";
    }

}
