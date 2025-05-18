import java.util.Scanner;

public class MainDualMission extends BaseJogo {
    Robo roboNormal = new Robo("Azul");
    RoboInteligente roboInteligente = new RoboInteligente("Vermelho");

    public MainDualMission() {
        super();
        adicionarRobo(roboNormal);
        adicionarRobo(roboInteligente);
    }

    public void executarJogo(Scanner scanner) {
        System.out.print("\n=== Escolha a posição do alimento ===\n");
        System.out.print("Posição x: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Posição y: ");
        int alimentoY = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        escolherPosAlimento(alimentoX, alimentoY);

        boolean roboNormalAchou = false;
        boolean roboInteligenteAchou = false;

        while (!(roboNormalAchou && roboInteligenteAchou)) {
            exibirTabuleiro();
            if (!roboNormalAchou) {
                try {
                    roboNormal.moverIA();
                    // Verificar se encontrou o alimento
                    if (roboNormal.encontrouAlimento(alimentoX, alimentoY)) {
                        roboNormalAchou = true;
                        System.out.println("Robô Azul encontrou o alimento!");
                    }
                    if(!roboNormalAchou && roboInteligenteAchou) {
                        System.out.println("Robô Vermelho já encontrou o alimento!");
                    }
                } catch (MovimentoInvalidoException e) {
                    System.out.println("Erro de movimentação: " + e.getMessage());
                }
            }

            if (!roboInteligenteAchou) {
                try {
                    roboInteligente.moverIA();
                    // Verificar se encontrou o alimento
                    if (roboInteligente.encontrouAlimento(alimentoX, alimentoY)) {
                        roboInteligenteAchou = true;
                        System.out.println("Robô Vermelho encontrou o alimento!");
                    }

                    if(roboNormalAchou && !roboInteligenteAchou) {
                        System.out.println("Robô Azul já encontrou o alimento!");
                    }
                } catch (MovimentoInvalidoException e) {
                    System.out.println("Erro de movimentação: " + e.getMessage());
                }
            }
        }

        System.out.println("\nOs dois robôs encontraram o alimento!");
    }
}