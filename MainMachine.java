import java.util.Scanner;

public class MainMachine extends BaseJogo{

    public MainMachine() {
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


        while(!(robo1Achou || robo2Achou)) {
            for (int i = 0; i < robos.size(); i++) {
                Robo robo = robos.get(i);
                if (!encontrouAlimento(robo)) {
                    robo.moverIA();
                }
            }

            exibirTabuleiro();

            robo1Achou = encontrouAlimento(robos.get(0));
            robo2Achou = encontrouAlimento(robos.get(1));
        }

        if (robo1Achou) {
            System.out.println("Robo " + robos.get(0).getCor() + " encontrou o alimento!");
        }
        if (robo2Achou && !robo1Achou) {
            System.out.println("Robo " + robos.get(1).getCor() + " encontrou o alimento!");
        }
    }
}
