import java.util.ArrayList;
import java.util.Scanner;

public abstract class BaseJogo {
    protected String[][] tabuleiro;
    protected int tamanhoTabuleiro = 5;
    protected ArrayList<Robo> robos;
    protected int alimentoX = 0, alimentoY = 0;
    protected Scanner scanner = new Scanner(System.in);

    public BaseJogo() {
        this.tabuleiro = new String[tamanhoTabuleiro][tamanhoTabuleiro];
        this.robos = new ArrayList<>();
        inicializarTabuleiro();
    }

    public void inicializarTabuleiro() {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = "[ ]";
            }
        }
    }

    public void escolherPosAlimento(int x, int y) {
        alimentoX = x;
        alimentoY = y;
    }

    protected void adicionarRobo(Robo robo) {
        robos.add(robo);
    }

    protected void exibirTabuleiro() {
        inicializarTabuleiro();
        tabuleiro[alimentoX][alimentoY] = "[A]";
        for (int i = 0; i < robos.size(); i++) {
            Robo robo = robos.get(i);
            tabuleiro[robo.getX()][robo.getY()] = "[R" + robo.getCor().substring(0, 1).toUpperCase() + "]";
        }

        System.out.print("\n=== Tabuleiro ===\n");
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }

    }

    protected boolean encontrouAlimento(Robo robo) {
        return robo.getX() == alimentoX && robo.getY() == alimentoY;
    }

    public abstract void executarJogo(Scanner scanner);

}
