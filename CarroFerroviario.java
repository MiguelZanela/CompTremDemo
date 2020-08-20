/*
Cria um objeto de carros ferroviarios, classe pai de vagao e locomotiva,
retorna informacoes sobre os carros.

@Author Miguel Zanela, Rafael Puhl

@version 1 junho 2020

fiz uma alteração
*/

public abstract class CarroFerroviario{
    private int idCarro;
    private double pesoMaximo;
    private Composicao composicao;

    //construtor super
    public CarroFerroviario(int idCarro, double pesoMaximo){
        this.pesoMaximo = pesoMaximo;
        this.idCarro = idCarro;
        this.composicao = null;
    }

    //recebe uma composicao e define em qual instancia de composicao o atual objeto esta
    public void setHasInstance(Composicao composicao){
        this.composicao = composicao;
    }

    //retorna uma instancia de composicao caso o carro tenha ou null caso nao
    public Composicao getHasInstance(){
        return this.composicao;
    }

    //retorna id do carro foi alterado para teste
    public int getIdCarro(){
        return this.idCarro;
    }

    //retorna peso maximo de carga do carro
    public double getPesoMaxima(){
        return this.pesoMaximo;
    }

    //imprime nome do carro e a capacidade de carga
    @Override
    public String toString() {
        String tipoCarro = "";
        if(this.getClass().getName() == "Vagao"){
            tipoCarro = "ID Vagao ====  ";
        }
        else{
            tipoCarro = "ID Locomotiva = ";
        }
        return tipoCarro+this.idCarro+", capacidade maxima em toneladas = "+this.pesoMaximo;
    }

}