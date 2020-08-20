/*
Menu de edicao de composicoes
possivel criar composicoes, editar composicoes ja existentes ou novas e
excluir composicoes.

@Author Miguel Zanela, Rafael Puhl

@version 1 junho 2020

*/

import java.util.Scanner;

public class EditorDeComposicoes {

    //implementa toda a logica do menu para criar uma nova composicao, editar uma existente ou exluir uma composicao
    public void EditaComposicoes() {
        Scanner in = new Scanner(System.in);

        boolean opValida;
        boolean erroNumero;
        boolean idValido;
        boolean erroQuanti;
        boolean achou;
        String entrada = "";
        int idComp;
        Patio.carregaPatio();
        System.out.println("\n\n--Bem vindo ao sistema de composicoes--\n");
        System.out.println("---Informe a opcao desejada---");
        opValida = false;

        while (!opValida) {
            erroNumero = true;
            System.out.println("---1 para Criar uma nova composicao---");
            System.out.println("---2 para Editar uma composicao---");
            System.out.println("---3 para Listar todas as composicao disponiveis---");
            System.out.println("---4 para Desfazer uma composicao---");
            System.out.println("---5 para Encerrar o sistema de composicoes---");
            System.out.println("---sair para Sair de qualquer menu a qualquer momento---\n");
            entrada = in.next();
            try {
                Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("**informe somente numero**\n");
                erroNumero = false;
            }

            //opcao 1
            //verifica se existem locomotivas disponiveis para uma nova composicao e cria uma caso exista
            if (entrada.equals("1")) {
                erroQuanti = true;
                idValido = false;
                boolean igual = true;
                int cont = 0;
                idComp = 0;
                for (CarroFerroviario it : Patio.carros) {
                    if (it.getHasInstance() == null && it.getClass().getName().equals("Locomotiva")) {
                        cont++;
                    }
                }
                if (cont > 0) {
                    System.out.println("\n\n--Criar uma nova composicao--");
                    System.out.println("--Para criar uma nova composicao\nPrimeiramente informe o ID desejado para esta composicao\nID composto 3 numeros--\n");
                    while (!idValido) {
                        erroQuanti = true;
                        igual = true;
                        cont = 0;
                        idComp = 0;
                        entrada = in.next();
                        try {
                            Integer.parseInt(entrada);
                        } catch (NumberFormatException e) {
                            if (entrada.toUpperCase().equals("SAIR")) {
                                System.out.println("--Voltando para o menu principal--\n");
                                erroQuanti = false;
                                idValido = true;
                            } else {
                                System.out.println("**Informar Somente numeros**\n");
                                erroQuanti = false;
                            }
                        }
                        if (entrada.length() == 3) {
                            idComp = Integer.parseInt(entrada);
                            for (Composicao it : Patio.comp) {
                                if (it.getIdComposicao() == idComp) {
                                    cont++;
                                }
                            }
                            if (cont == 0) {
                                igual = false;
                            } else if (cont > 0) {
                                erroQuanti = false;
                                System.out.println("**ID da composicao ja utilizado**\n**Por favor informe outro ID**");
                            }
                        }
                        if (!igual) {
                            boolean compOk = false;
                            while (!compOk) {
                                System.out.println("\n--Lista da(s) Locomotiva(s) Disponiveis--\n");
                                for (CarroFerroviario it : Patio.carros) {
                                    if (it.getHasInstance() == null && it.getClass().getName().equals("Locomotiva")) {
                                        System.out.println("==" + it + "==");
                                    }
                                }
                                System.out.println("\n--Informe uma da(s) locomotiva(s) acima para iniciar a composicao--\n");
                                entrada = in.next();
                                boolean idOk = false;
                                for (CarroFerroviario it : Patio.carros) {
                                    if (Integer.toString(it.getIdCarro()).equals(entrada) && it.getClass().getName().equals("Locomotiva") && it.getHasInstance() == null) {
                                        Patio.comp.add(new Composicao(idComp, (Locomotiva) it));
                                        System.out.println("\n--Composicao criada com Sucesso--\n\n");
                                        idOk = true;
                                        compOk = true;
                                        idValido = true;
                                    }
                                }
                                if (entrada.toUpperCase().equals("SAIR")) {
                                    System.out.println("--Voltando para o menu principal--\n");
                                    idOk = true;
                                    compOk = true;
                                    idValido = true;
                                } else if (!idOk) {
                                    System.out.println("--Locomotiva informada INVALIDA informe uma locomotiva listada--");
                                }
                            }
                        } else if (erroQuanti) {
                            System.out.println("**Informar um ID composto por 3 numeros**\n");
                        }
                    }
                } else {
                    System.out.println("--Nenhuma locomotiva disponivel para criar composicoes--\n--Utilize a opcao 4 do menu para Desfazer alguma composicao existente--\n");
                    idValido = true;
                }
            }

            //opcao 2
            //edita uma composicao criada
            //adiciona locomotiva ou vagao, remove ultimo carro, lista locomotivas ou vagoes disponiveis
            else if (entrada.equals("2")) {
                System.out.println("\n\n--Editar uma composicao--");
                if (Patio.comp.size() > 0) {
                    System.out.println("\n\n--Lista com todas as composicoes disponiveis para edicao--");
                    for (Composicao it : Patio.comp) {
                        System.out.println(it);
                    }
                    System.out.println("\n\n--Informe um ID da composicao para começar a edicao--\n");
                    erroQuanti = true;
                    idValido = false;
                    achou = false;
                    idComp = 0;
                    while (!idValido) {
                        achou = false;
                        erroQuanti = true;
                        entrada = in.next();
                        try {
                            Integer.parseInt(entrada);
                        } catch (NumberFormatException e) {
                            if (entrada.toUpperCase().equals("SAIR")) {
                                System.out.println("--Voltando para o menu principal--\n");
                                erroQuanti = false;
                                idValido = true;
                                achou = true;
                            } else {
                                System.out.println("**Informar Somente numeros**\n");
                                erroQuanti = false;
                                achou = true;
                            }
                        }
                        if (entrada.length() == 3) {
                            achou = false;
                            int aux;
                            for (Composicao it : Patio.comp) {
                                aux = Integer.parseInt(entrada);
                                if (it.getIdComposicao() == aux) {
                                    achou = true;
                                    idComp = aux;
                                }
                            }
                            if (achou) {
                                boolean jaEditado = false;
                                erroQuanti = true;
                                while (!jaEditado) {
                                    erroQuanti = true;
                                    boolean livre;
                                    for (Composicao it : Patio.comp) {
                                        if (it.getIdComposicao() == idComp) {
                                            System.out.println("\n--" + it + "--");
                                        }
                                    }
                                    System.out.println("--1 para Inserir uma locomotiva disponivel--");
                                    System.out.println("--2 para Inserir um vagao disponivel--");
                                    System.out.println("--3 para Remover o ultimo elemento da composicao--");
                                    System.out.println("--4 para Listar Locomotivas livres--");
                                    System.out.println("--5 para Listar vagoes livres--");
                                    System.out.println("--6 para Encerrar a edicao de composicoes--\n");
                                    entrada = in.next();
                                    try {
                                        Integer.parseInt(entrada);
                                    } catch (NumberFormatException e) {
                                        if (entrada.toUpperCase().equals("SAIR")) {
                                            System.out.println("--Voltando para o menu principal--\n");
                                            erroQuanti = false;
                                            jaEditado = true;
                                            idValido = true;
                                            achou = true;
                                        } else {
                                            System.out.println("**Informar Somente numeros**\n");
                                            erroQuanti = false;
                                        }
                                    }

                                    if (entrada.equals("1")) {
                                        livre = true;
                                        for (Composicao it : Patio.comp) {
                                            if (it.getIdComposicao() == idComp && it.verificaUltimoCarroVagao()) {
                                                livre = false;
                                            }
                                        }
                                        if (!livre) {
                                            System.out.println("**Nao eh possivel engatar uma locomotiva depois de um vagao**\n");
                                        } else {
                                            int cont = 0;
                                            for (CarroFerroviario it : Patio.carros) {
                                                if (it.getHasInstance() == null && it.getClass().getName().equals("Locomotiva")) {
                                                    System.out.println("==" + it + "==");
                                                    cont++;
                                                }
                                            }
                                            if (cont == 0) {
                                                System.out.println("**Nenhuma locomotiva disponivel para engatar**\n");
                                            } else {
                                                boolean addLocomotiva = false;
                                                System.out.println("\n--Informe uma locomotiva listada para engatar na composicao ID " + idComp + "--\n");
                                                while (!addLocomotiva) {
                                                    erroQuanti = true;
                                                    int idLoc = 0;
                                                    boolean igual = false;
                                                    entrada = in.next();
                                                    try {
                                                        idLoc = Integer.parseInt(entrada);
                                                    } catch (NumberFormatException e) {
                                                        if (entrada.toUpperCase().equals("SAIR")) {
                                                            System.out.println("--Voltando para o menu principal--\n");
                                                            erroQuanti = false;
                                                            jaEditado = true;
                                                            idValido = true;
                                                            achou = true;
                                                            addLocomotiva = true;
                                                        } else {
                                                            System.out.println("**Informar Somente numeros**\n");
                                                            erroQuanti = false;
                                                        }
                                                    }
                                                    for (CarroFerroviario it : Patio.carros) {
                                                        if (it.getHasInstance() == null && it.getClass().getName().equals("Locomotiva") && it.getIdCarro() == idLoc) {
                                                            igual = true;
                                                            erroQuanti = false;
                                                        }
                                                    }
                                                    if (erroQuanti) {
                                                        System.out.println("**ID informado nao corresponde a nenhum da lista de locomotivas livres**\n\n");
                                                    }
                                                    if (igual) {
                                                        for (Composicao it : Patio.comp) {
                                                            if (it.getIdComposicao() == idComp) {
                                                                for (CarroFerroviario ic : Patio.carros) {
                                                                    if (ic.getIdCarro() == idLoc) {
                                                                        it.addLocomotiva((Locomotiva) ic);
                                                                        addLocomotiva = true;
                                                                        System.out.println("**locomotivas adiconada com sucesso na composicao--\n\n");
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else if (entrada.equals("2")) {
                                        int cont = 0;
                                        for (CarroFerroviario it : Patio.carros) {
                                            if (it.getHasInstance() == null && it.getClass().getName().equals("Vagao")) {
                                                System.out.println("==" + it + "==");
                                                cont++;
                                            }
                                        }
                                        if (cont == 0) {
                                            System.out.println("**Nenhum Vagao disponivel para engatar**\n");
                                        } else {
                                            boolean addVagao = false;
                                            System.out.println("\n--Informe um vagao listada para engatar na composicao ID " + idComp + "--\n");
                                            while (!addVagao) {
                                                erroQuanti = true;
                                                int idVag = 0;
                                                boolean igual = false;
                                                entrada = in.next();
                                                try {
                                                    idVag = Integer.parseInt(entrada);
                                                } catch (NumberFormatException e) {
                                                    if (entrada.toUpperCase().equals("SAIR")) {
                                                        System.out.println("--Voltando para o menu principal--\n");
                                                        erroQuanti = false;
                                                        jaEditado = true;
                                                        idValido = true;
                                                        achou = true;
                                                        addVagao = true;
                                                    } else {
                                                        System.out.println("**Informar Somente numeros**\n");
                                                        erroQuanti = false;
                                                    }
                                                }
                                                for (CarroFerroviario it : Patio.carros) {
                                                    if (it.getHasInstance() == null && it.getClass().getName().equals("Vagao") && it.getIdCarro() == idVag) {
                                                        igual = true;
                                                    }
                                                }
                                                if (igual) {
                                                    for (Composicao it : Patio.comp) {
                                                        if (it.getIdComposicao() == idComp) {
                                                            for (CarroFerroviario ic : Patio.carros) {
                                                                if (ic.getIdCarro() == idVag) {
                                                                    if (it.addVagao((Vagao) ic)) {
                                                                        addVagao = true;
                                                                        System.out.println("--Vagao inserido com sucesso na composicao--\n");
                                                                        erroQuanti = false;
                                                                    } else {
                                                                        System.out.println("**Capacidade maxima de carga da composicao excedida, informe outro vagao dentro do limite**\n**Ou digite sair para voltar pro menu principal**\n");
                                                                        erroQuanti = false;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else if (erroQuanti) {
                                                    System.out.println("**ID informado nao corresponde a nenhum da lista de Vagaos livres**\n");
                                                }
                                            }
                                        }
                                    } else if (entrada.equals("3")) {
                                        livre = false;
                                        System.out.println("--Removendo o ultimo carro ferroviario da composicao--\n");
                                        for (Composicao it : Patio.comp) {
                                            if (it.getIdComposicao() == idComp) {
                                                if (it.removeUltimoCarro(idComp)) {
                                                    livre = true;
                                                }
                                            }
                                        }
                                        if (livre) {
                                            System.out.println("--Ultimo carro ferroviario removido com sucesso--");
                                        } else {
                                            System.out.println("**Impossivel remover a primeira locomotiva da composicao**\n**Para remover a primeira locomotiva seleciona a opcao 4 no menu principal**\n");
                                        }
                                    } else if (entrada.equals("4")) {
                                        livre = false;
                                        System.out.println("--Lista com as locomotivas livres--\n");
                                        for (CarroFerroviario it : Patio.carros) {
                                            if (it.getHasInstance() == null && it.getClass().getName().equals("Locomotiva")) {
                                                System.out.println("==" + it + "==");
                                                livre = true;
                                            }
                                        }
                                        if (!livre) {
                                            System.out.println("--Nenhuma locomotiva livre disponivel--");
                                        }
                                    } else if (entrada.equals("5")) {
                                        livre = false;
                                        System.out.println("--Lista com os vagoes livres--\n");
                                        for (CarroFerroviario it : Patio.carros) {
                                            if (it.getHasInstance() == null && it.getClass().getName().equals("Vagao")) {
                                                System.out.println("==" + it + "==");
                                                livre = true;
                                            }
                                        }
                                        if (!livre) {
                                            System.out.println("--Nenhum vagao livre disponivel--");
                                        }
                                    } else if (entrada.equals("6")) {
                                        System.out.println("--Saindo da edicao de composicoes--\n");
                                        erroQuanti = false;
                                        jaEditado = true;
                                        idValido = true;
                                        achou = true;
                                    } else if (erroQuanti) {
                                        System.out.println("**Opcao INVALIDA informe uma opcao VALIDA**\n");
                                    }
                                }
                            } else if (!achou) {
                                System.out.println("**Informar um ID de uma composicao existente**\n");
                            }
                        } else if (erroQuanti) {
                            System.out.println("**Informar um ID composto por 3 numeros**\n");
                        }
                    }
                } else {
                    System.out.println("\n\n--Nao ah nenhuma composicao para ser editada--\n");
                }
            }

            //opcao 3
            //lista todas as composicoes existentes no sistema de composicoes
            else if (entrada.equals("3")) {
                if (Patio.comp.size() > 0) {
                    System.out.println("\n\n--Lista com todas as composicao nesta sessao--\n");
                    for (Composicao it : Patio.comp) {
                        System.out.println("--" + it + "--");
                    }
                } else {
                    System.out.println("--Nenhuma composicao ate o momento--\n\n");
                }
            }

            //opcao 4
            //verifica se existem composicoes para exluir
            //recebe um id, confirma com o usuario o desejo de excluir e caso possitivo, exclui a composicao informada
            else if (entrada.equals("4")) {
                if (Patio.comp.size() > 0) {
                    System.out.println("\n\n--Lista com todas as composicoes nesta sessao--\n");
                    for (Composicao it : Patio.comp) {
                        System.out.println("--" + it + "--");
                    }
                    idValido = false;
                    erroQuanti = true;
                    System.out.println("\n--Informe o ID de uma composicao listada--\n");
                    while (!idValido) {
                        erroQuanti = true;
                        achou = false;
                        entrada = in.next();
                        try {
                            Integer.parseInt(entrada);
                        } catch (NumberFormatException e) {
                            if (entrada.toUpperCase().equals("SAIR")) {
                                System.out.println("--Voltando para o menu principal--\n");
                                idValido = true;
                                erroQuanti = false;
                                achou = true;
                            } else {
                                System.out.println("**Informar Somente numeros**\n");
                                erroQuanti = false;
                                achou = true;
                            }
                        }
                        if (entrada.length() == 3) {
                            idComp = Integer.parseInt(entrada);
                            for (Composicao it : Patio.comp) {
                                if (it.getIdComposicao() == idComp) {
                                    achou = true;
                                }
                            }
                            if (achou) {
                                System.out.println("--deseja realmente excluir a composicao--");
                                boolean excluir = false;
                                int posicaoExcluir = 0;
                                for (int i = 0; i < Patio.comp.size(); i++) {
                                    if (Patio.comp.get(i).getIdComposicao() == idComp) {
                                        posicaoExcluir = i;
                                        System.out.println("--" + Patio.comp.get(i) + "--");
                                        System.out.println("--Para excluir digite S--\n--Para cancelar a operacao digite N--\n");
                                        boolean validar = false;
                                        while (!validar) {
                                            entrada = in.next();
                                            if (entrada.toUpperCase().equals("S")) {
                                                if (Patio.comp.get(i).excluiComp(idComp)) {
                                                    System.out.println("--Composicao excluida com sucesso--\n");
                                                    idValido = true;
                                                    erroQuanti = false;
                                                    validar = true;
                                                    excluir = true;
                                                } else {
                                                    System.out.println("**falha ao excluida a composicao deseja**\n**Para mais informacoes procure o setor de TI**\n");
                                                    idValido = true;
                                                    erroQuanti = false;
                                                    validar = true;
                                                }
                                            } else if (entrada.toUpperCase().equals("N")) {
                                                System.out.println("--Composicao nao excluida--\n");
                                                idValido = true;
                                                erroQuanti = false;
                                                validar = true;

                                            } else if ((entrada.toUpperCase().equals("SAIR"))) {
                                                System.out.println("--Voltando para o menu principal--\n");
                                                idValido = true;
                                                erroQuanti = false;
                                                validar = true;
                                            } else {
                                                System.out.println("**Opcao INVALIDA informe S para excluir ou N para cancelar a operacao**\n");
                                            }
                                        }
                                    }
                                }
                                if (excluir) {
                                    Patio.comp.remove(posicaoExcluir);
                                }
                            } else {
                                System.out.println("\n**Composicao nao encontrada**\n**Informe o ID de uma composicao listada**\n");
                            }

                        } else if (erroQuanti) {
                            System.out.println("**Informar um ID composto por 3 numeros**\n");
                        }
                    }
                } else {
                    System.out.println("\n\n--Nao ah nenhuma composicao para ser excluida--\n");
                }
            }

            //opcao 5
            //salva as composicoes no arquivo txt e encerra o sistema de composicoes
            else if (entrada.equals("5")) { //salvar tudo no arquivo e sair
                System.out.println("\n\n--Encerrar o sistema de composicoes--");
                System.out.println("--Salvando alteracoes...\n--Por favor Aguarde!--");
                try {
                    Patio.gravaPatio();
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
                System.out.println("\n\n--Sistema de composicoes finalizado--");
                opValida = true;
                in.close();
            } else if (erroNumero) {
                System.out.println("**Opcao INVALIDA informe uma opcao VALIDA**\n");
            }
        }
    }
}