/*
armazena uma lista de carros ferroviarios, 
adiciona carros, retira ultimo carro, retorna informacoes sobre composicoes
exclui composicoes e imprime informacoes.

@Author Miguel Zanela, Rafael Puhl

@version 1 junho 2020

*/

import java.util.List;
import java.util.ArrayList;

public class Composicao {
    private int idComposicao;
    private List<CarroFerroviario> comp;
    private int totalTracionarDisponivel;
    private int totalTracionar;
    private double totalCargaDisponivel;

    //contrutor da classe, recebe id da composicao e um carro ferroviario do tipo locomotiva
    //atualizando os sistemas
    public Composicao(int idComposicao, Locomotiva locomotiva) {
        this.idComposicao = idComposicao;
        comp = new ArrayList<>();
        comp.add(locomotiva);
        locomotiva.setHasInstance(this);
        this.totalTracionar = locomotiva.getTracionarMaximo();
        this.totalTracionarDisponivel = locomotiva.getTracionarMaximo();
        this.totalCargaDisponivel = locomotiva.getPesoMaxima();
    }

    //retorna id da composicao
    // volta pro id antigo
    public int getIdComposicao() {
        return this.idComposicao;
    }

    //hushauhsuahsuhaushau
    //retorna o id da composicao e todos os carros que essa composicao possui
    public String getIdCompTotal() {
        String aux = "";
        String aux1 = "" + this.idComposicao;
        for (CarroFerroviario it : comp) {
            aux = aux + ";" + it.getIdCarro();
        }
        return aux1 + aux;
    }

    //adiciona um carro ferroviario do tipo locomotiva e calcula capacidade maxima de vagoes para puxar e peso maximo
    // outra alteração
    public boolean addLocomotiva(Locomotiva locomotiva) {
        if (verificaUltimoCarroVagao()) {
            return false;
        }
        this.totalTracionar = this.totalTracionar + locomotiva.getTracionarMaximo();
        this.totalTracionarDisponivel = (int) (totalTracionar - (totalTracionar * 0.1));
        this.totalCargaDisponivel = this.totalCargaDisponivel + locomotiva.getPesoMaxima();
        locomotiva.setHasInstance(this);
        return comp.add(locomotiva);
    }

    //adiciona um carro ferroviario do tipo vagao 
    //calcula capacidade maxima de vagoes para puxar e peso maximo
    public boolean addVagao(Vagao vagao) {
        if (this.totalCargaDisponivel < vagao.getPesoMaxima() || this.totalTracionarDisponivel <= 0) {
            return false;
        }
        this.totalTracionarDisponivel = this.totalTracionarDisponivel - 1;
        this.totalCargaDisponivel = this.totalCargaDisponivel - vagao.getPesoMaxima();
        vagao.setHasInstance(this);
        return comp.add(vagao);
    }

    //exclui a composicao completa
    public boolean excluiComp(int idComp) {
        if (this.idComposicao == idComp) {
            for (CarroFerroviario it : comp) {
                it.setHasInstance(null);
            }
            return true;
        } else {
            return false;
        }
    }

    //exclui ultimo elemento da composicao desde que a composicao nao tenha somente um carro
    //recalcula peso maximo suportado pela composicao e numero de vagoes maximo
    public boolean removeUltimoCarro(int idComp) {
        if (this.idComposicao == idComp) {
            if (comp.size() > 1) {
                boolean ehVagao = false;
                if (this.comp.get(comp.size() - 1).getClass().getName() == "Locomotiva") {
                    this.totalTracionar = this.totalTracionar - ((Locomotiva) this.comp.get(comp.size() - 1)).getTracionarMaximo();
                    this.totalCargaDisponivel = totalCargaDisponivel - ((Locomotiva) this.comp.get(comp.size() - 1)).getPesoMaxima();
                } else {
                    this.totalCargaDisponivel = this.totalCargaDisponivel + (this.comp.get(comp.size() - 1).getPesoMaxima());
                    this.totalTracionarDisponivel = this.totalTracionarDisponivel + 1;
                    ehVagao = true;
                }
                this.comp.get(comp.size() - 1).setHasInstance(null);
                comp.remove(comp.size() - 1);
                if (comp.size() == 1) {
                    this.totalTracionarDisponivel = totalTracionar;
                } else if (comp.size() != 1 && ehVagao == false) {
                    this.totalTracionarDisponivel = (int) (totalTracionar - (totalTracionar * 0.1));
                }
                return true;
            }
        }
        return false;
    }

    //verifica se o ultimo carro da composicao eh um vagao
    public boolean verificaUltimoCarroVagao() {
        for (CarroFerroviario it : comp) {
            if (it.getClass().getName() == "Vagao") {
                return true;
            }
        }
        return false;
    }

    //imprime id da composicao, capacidade de vagoes suportados e o peso maximo suportado juntamente com esses valores atualizados
    @Override
    public String toString() {
        String aux = "";
        String aux1 = "id da composicao = " + this.idComposicao + "\n";
        String aux2 = this.totalTracionarDisponivel + " = Capacidade em vagoes disponivel para tracionar" + "\n" + this.totalCargaDisponivel + " = Peso maximo carregado pela composicao disponivel" + "\n";
        int cont = 1;
        for (CarroFerroviario it : comp) {
            aux = aux + "posicao = " + cont + "|" + it + "\n";
            cont++;
        }
        return aux1 + aux + aux2;

    }

}
