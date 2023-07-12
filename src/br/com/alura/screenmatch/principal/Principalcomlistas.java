package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;

import java.sql.SQLOutput;
import java.util.*;

public class Principalcomlistas {
    public static void main(String[] args) {

        Filme meuFilme = new Filme("O poderoso chefão", 1970);
        meuFilme.avalia(8);
        Filme outroFilme = new Filme("Avatar", 2023);
        outroFilme.avalia(10);
        Filme crepusculo = new Filme("Crepusculo", 2008);
        crepusculo.avalia(9);
        Serie lost = new Serie("Lost", 2000);
        lost.avalia(5);

        //ArrayList<Titulo> lista = new ArrayList<>(); = List<Titulo> lista = new LinkedList<>();
        List<Titulo> lista = new LinkedList<>();

        lista.add(crepusculo);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(lost);

        for (Titulo item : lista){
            System.out.println(item.getNome());
            //Filme filme = (Filme) item;
            if(item instanceof Filme filme && filme.getClassificacao() > 0){
                System.out.println("Classificação: " + filme.getClassificacao());
            }
        }
        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Adam Sandler");
        buscaPorArtista.add("Brad Pitt");
        buscaPorArtista.add("Will Smith");
        buscaPorArtista.add("Margot Robbin");

        //Esse método coloca em ordem
        Collections.sort(buscaPorArtista);
        System.out.println(buscaPorArtista);
        Collections.sort(lista);
        System.out.println(lista);
        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println(lista);
    }
}
