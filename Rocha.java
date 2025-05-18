public class Rocha extends Obstaculo {

    public Rocha(int idObstaculo, int posX, int posY) {
        super(idObstaculo, posX, posY);
    }

    @Override
    public void bater(Robo robo) {
        System.out.println(
                "O robô " + robo.getCor() + " foi eliminado pela bomba na posição (" + posX + "," + posY + ")");
        robo.voltarPosicaoAnterior();
    }
}
