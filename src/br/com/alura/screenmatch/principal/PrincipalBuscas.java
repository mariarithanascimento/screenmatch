package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalBuscas {
    public static void main(String[]args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")){

            System.out.println("Digite um filme para busca: ");
            busca = input.nextLine();

            if(busca.equalsIgnoreCase("sair")){
                break;
            }

            try{
                //String endereco = "https://www.omdbapi.com/?t=" + busca + "&apikey=ef7bb1bd";
                String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ","+") + "&apikey=ef7bb1bd";
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);

                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(" ");
                System.out.println(meuTituloOmdb);
                System.out.println(" ");

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("*Titulo já convertido*");
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);

            } catch (NumberFormatException e){
                System.out.println("Ocorreu um erro: ");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Algum erro de argumento, verifique o endereço.");
            }catch (ErroDeConversaoDeAnoException e){
                System.out.println(e.getMensagem());
            }

        }
        System.out.println(titulos);
        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
        System.out.println("Programa Finalizado.");

    }
}
