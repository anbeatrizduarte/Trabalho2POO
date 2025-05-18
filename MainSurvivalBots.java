import java.util.Scanner;

public class MainSurvivalBots extends BaseJogo {

    public MainSurvivalBots() {
        super();
        Robo robo1 = new Robo("Azul");
        Robo robo2 = new Robo("Vermelho");
        adicionarRobo(robo1);
        adicionarRobo(robo2);
    }

    private void verificarColisoes(Robo robo) {
        for (int i = 0; i < bombas.size(); i++) {
            Bomba bomba = bombas.get(i);
            if (bomba.getX() == robo.getX() && bomba.getY() == robo.getY()) {
                bomba.bater(robo);
                return; // sai depois do primeiro choque
            }
        }

        for (int i = 0; i < rochas.size(); i++) {
            Rocha rocha = rochas.get(i);
            if (rocha.getX() == robo.getX() && rocha.getY() == robo.getY()) {
                rocha.bater(robo);
                return;
            }
        }
    }

    public void executarJogo(Scanner scanner) {
        System.out.print("\n=== Escolha a posição do alimento ===\n");
        System.out.print("Posição x: ");
        alimentoX = scanner.nextInt();
        System.out.print("Posição y: ");
        alimentoY = scanner.nextInt();
        scanner.nextLine();

        escolherPosAlimento(alimentoX, alimentoY);

        boolean robo1AchouAlimento = false;
        boolean robo2AchouAlimento = false;

        System.out.print("\n=== Escolha a posição das bombas ===\n");
        System.out.print("Quantidades de bombas: ");
        int numBombas = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numBombas; i++) {
            System.out.print("Bomba " + (i + 1) + " - Posição x: ");
            int bombaX = scanner.nextInt();
            System.out.print("Bomba " + (i + 1) + " - Posição y: ");
            int bombaY = scanner.nextInt();
            scanner.nextLine();

            Bomba bomba = new Bomba(i + 1, bombaX, bombaY);
            bombas.add(bomba);
        }

        System.out.print("\n=== Escolha a posição das rochas ===\n");
        System.out.print("Quantidades de rochas: ");
        int numRochas = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numRochas; i++) {
            System.out.print("Rocha " + (i + 1) + " - Posição x: ");
            int rochaX = scanner.nextInt();
            System.out.print("Rocha " + (i + 1) + " - Posição y: ");
            int rochaY = scanner.nextInt();
            scanner.nextLine();

            Rocha rocha = new Rocha(i + 1, rochaX, rochaY);
            rochas.add(rocha);
        }

        while (!(robo1AchouAlimento || robo2AchouAlimento)) {
            for (int i = 0; i < robos.size(); i++) {
                Robo robo = robos.get(i);
                if (!encontrouAlimento(robo)) {
                    try {
                        robo.moverIA();
                        verificarColisoes(robo);
                    } catch (MovimentoInvalidoException e) {
                        System.out.println("Erro de movimentação: " + e.getMessage());
                    }
                }
            }

            exibirTabuleiro();

            robo1AchouAlimento = encontrouAlimento(robos.get(0));
            robo2AchouAlimento = encontrouAlimento(robos.get(1));
        }

        if (robo1AchouAlimento) {
            System.out.println("Robo " + robos.get(0).getCor() + " encontrou o alimento!");
        }
        if (robo2AchouAlimento && !robo1AchouAlimento) {
            System.out.println("Robo " + robos.get(1).getCor() + " encontrou o alimento!");
        }
    }
}