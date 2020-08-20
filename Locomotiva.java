/*
Cria um carro ferroviario do tipo locomotiva, extende a classe carro ferroviario.

@Author Miguel Zanela, Rafael Puhl

@version 1 junho 2020

*/

public class Locomotiva extends CarroFerroviario {
    private final int capacidadeTracionar;

    //construtor da locomotiva, pede os atributos do construtor super e tambem da capacidade de vagoes suportados pela locomotiva
    public Locomotiva(int idLocomotiva, double pesoMaximo, int capacidadeTracionar) {
        super(idLocomotiva, pesoMaximo);
        this.capacidadeTracionar = capacidadeTracionar;
    }    

    //retorna capacidade de vagoes suportados pela locomotiva
    public int getTracionarMaximo() {
        return this.capacidadeTracionar;
    }

    //imprime o toString da classe pai e o numero maximo de vagoes suportados pela locomotiva    
    @Override
    public String toString() {
        return super.toString()+", numero maximo de vagoes = "+this.capacidadeTracionar;
    }
}