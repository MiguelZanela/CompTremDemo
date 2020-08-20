/*
armazena uma lista de carros ferroviarios e as composiçoes, 
le de arquivos txt as locomotivas, vagoes e composicoes.

@Author Miguel Zanela, Rafael Puhl

@version 1 junho 2020

*/

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.util.Scanner;

public class Patio {
    public static List<CarroFerroviario> carros = new ArrayList<>();
    public static List<Composicao> comp = new ArrayList<>();

    //le as informaçoes sobre locomotivas, vagoes e composicoes e adicona elas nas list adequadas
    //OBS, caso seja executado em windows é necessario alterar a "/" por "\\" na variavel nameComplete
    //OBS, caso seja executado em linux é necessario alterar a "\\" por "/" na variavel nameComplete
    public static void carregaPatio(){
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir+"/"+"Locomotiva.txt"; // linux = "/", windows = "\\"
        Path path = Paths.get(nameComplete);

        int id, idComp, tracionar;
        double peso;
        String linha;
        String[] parts;
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
            while(sc.hasNextLine()){
                linha = sc.nextLine();
                parts = linha.split(";");
                    id = Integer.parseInt(parts[0]);
                    peso = Double.parseDouble(parts[1]);
                    tracionar = Integer.parseInt(parts[2]);
                    carros.add(new Locomotiva(id,peso,tracionar));
            }

        }
        catch (IOException e){
            System.err.format("Dados do arquivo locomotiva corrompidos, verifique seus dados\n"+e);
        }
        currDir = Paths.get("").toAbsolutePath().toString();
        nameComplete = currDir+"/"+"Vagao.txt"; // linux = "/", windows = "\\"
        path = Paths.get(nameComplete);         
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
            while(sc.hasNextLine()){
                linha = sc.nextLine();
                parts = linha.split(";");
                    id = Integer.parseInt(parts[0]);
                    peso = Double.parseDouble(parts[1]);
                    carros.add(new Vagao(id,peso));
            }
        }
        catch (IOException e){
            System.err.format("Dados do arquivo vagao corrompidos, verifique seus dados\n"+e);
        }
        currDir = Paths.get("").toAbsolutePath().toString();
        nameComplete = currDir+"/"+"Composicao.txt"; // linux = "/", windows = "\\"
        path = Paths.get(nameComplete);         
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
            while(sc.hasNextLine()){
                linha = sc.nextLine();
                parts = linha.split(";");
                if(parts.length == 2){
                    idComp = Integer.parseInt(parts[0]);
                    id = Integer.parseInt(parts[1]);                    
                    for(CarroFerroviario ca: carros){    
                        if(ca.getIdCarro() == id){
                            if(ca.getClass().getName().equals("Locomotiva")){
                                comp.add(new Composicao(idComp,(Locomotiva) ca));
                            }
                        }
                    }
                }                
                else if(parts.length >= 3){
                    idComp = Integer.parseInt(parts[0]);
                    id = Integer.parseInt(parts[1]);
                    for(CarroFerroviario ca: carros){    
                        if(ca.getIdCarro() == id){
                            if(ca.getClass().getName().equals("Locomotiva")){
                                comp.add(new Composicao(idComp,(Locomotiva) ca));
                            }
                        }
                    }
                    for(int i=2;i<parts.length;i++){
                        id = Integer.parseInt(parts[i]);
                        for(CarroFerroviario ca: carros){
                            if(ca.getIdCarro() == id){
                                if(ca.getClass().getName().equals("Locomotiva")){
                                    for(Composicao it: comp){
                                        if(it.getIdComposicao() == idComp){
                                            it.addLocomotiva((Locomotiva) ca);
                                        }
                                    }
                                }
                                else if(ca.getClass().getName().equals("Vagao")){
                                    for(Composicao it: comp){
                                        if(it.getIdComposicao() == idComp){
                                            it.addVagao((Vagao) ca);
                                        }
                                    }
                                }
                            }
                        }                        
                    }
                }                
            }
        }
        catch (IOException e){
            System.err.format("Dados do arquivo composicao corrompidos, verifique seus dados\n"+e);
        }
    }

    //grava as informaçoes sobre composicoes e adiciona elas no arquivo txt adequado
    //OBS, caso seja executado em windows é necessario alterar a "/" por "\\" na variavel nameComplete
    //OBS, caso seja executado em linux é necessario alterar a "\\" por "/" na variavel nameComplete
    public static void gravaPatio(){
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir+"/"+"Composicao.txt"; // linux = "/", windows = "\\"
        Path path = Paths.get(nameComplete);

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, StandardCharsets.UTF_8))){
            for(Composicao it: comp){                
                writer.print(it.getIdCompTotal());                
                writer.print("\n");
            }
        }
        catch (IOException e){
          System.err.format("Dados para gravar no arquivo composicao corrompidos, verifique seus dados\n"+e);
        }
    }
}