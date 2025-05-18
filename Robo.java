import java.util.Random;

public class Robo {
    private int x;
    private int y;
    private String cor;
    private Random random;

    protected Robo(String cor) {
        this.x = 0;
        this.y = 0;
        this.cor = cor;
        this.random = new Random();
    }

    protected void mover(String sentido) throws MovimentoInvalidoException {
        switch (sentido.toLowerCase()) {
            case "up":
                y++;
                break;
            case "down":
                y--;
                break;
            case "right":
                x++;
                break;
            case "left":
                x--;
                break;
            default:
                System.out.println("Direção inválida. Posição atual: (" + x + ", " + y + ")");
        }

        if (x < 0 || y < 0) {
            throw new MovimentoInvalidoException(
                    "Movimento inválido! A posição do robô não pode ser negativa. Tentativa: (" + x + ", " + y + ")");
        }

        System.out.println(this);
    }

    protected void mover(int sentido) throws MovimentoInvalidoException {
        int novoX = x, novoY = y;

        switch (sentido) {
            case 1:
                novoY++;
                break;
            case 2:
                novoY--;
                break;
            case 3:
                novoX++;
                break;
            case 4:
                novoX--;
                break;
            default:
                throw new MovimentoInvalidoException("Direção inválida: " + sentido);
        }

        if (novoX < 0 || novoY < 0 || novoX > 4 || novoY > 4) {
            throw new MovimentoInvalidoException(
                    "Movimento inválido do! Posição resultante (" + novoX + ", " + novoY + ") fora do tabuleiro.");
        }

        x = novoX;
        y = novoY;
        System.out.println(this);
    }

    protected void moverIA() {
        while (true) {
            int sentido = random.nextInt(4) + 1; // 1 a 4

            try {
                mover(sentido);
                break;
            } catch (MovimentoInvalidoException e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }

        }
    }

    protected int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected String getCor() {
        return cor;
    }

    protected boolean encontrouAlimento(int alimentoX, int alimentoY) {
        return getX() == alimentoX && getY() == alimentoY;

    }

    public String toString() {
        return "Robo " + cor + " na posição (" + x + ", " + y + ")";
    }
}
