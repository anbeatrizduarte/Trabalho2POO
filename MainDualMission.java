import java.util.Scanner;

public class DualMission extends BaseJogo {
    public DualMission() {
        super();
        Robo robo1 = new Robo("Azul");
        Robo robo2 = new Robo("Vermelho");
        adicionarRobo(robo1);
        adicionarRobo(robo2);
    }

    public void executarJogo(Scanner scanner) {
        System.out.print("\n=== Escolha a posição do alimento ===\n");
        System.out.print("Posição x: ");
        alimentoX = scanner.nextInt();
        System.out.print("Posição y: ");
        alimentoY = scanner.nextInt();
        scanner.nextLine();

        escolherPosAlimento(alimentoX, alimentoY);

        boolean robo1Achou = false;
        boolean robo2Achou = false;

        while (!(robo1Achou && robo2Achou)) {
            for (int i = 0; i < robos.size(); i++) {
                Robo robo = robos.get(i);
                if (!encontrouAlimento(robo)) {
                    robo.moverIA();
                    if (robo1Achou) {
                        System.out.print("Robô " + robos.get(0).getCor() + " encontrou o alimento!");
                    }
                    if (robo2Achou) {
                        System.out.print("Robô " + robos.get(1).getCor() + " encontrou o alimento!");
                    }
                }
            }

            exibirTabuleiro();

            robo1Achou = encontrouAlimento(robos.get(0));
            robo2Achou = encontrouAlimento(robos.get(1));
        }

        System.out.print("\nOs dois robôs encontraram o alimento!");

    }
}
