package com.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.model.DadosCarro;
import com.model.DadosMarca;
import com.model.DadosModelos;
import com.model.Veiculo;
import com.services.ConsumoApi;
import com.services.ConverteDados;



public class Principal {
	private ConsumoApi consumo = new ConsumoApi();
	private Scanner leitura = new Scanner(System.in);
	private ConverteDados conversor = new ConverteDados();
	private  final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
	private String op;
	private String marca, codigoCarro, anoCarro;
	public void exibeMenu() {
			System.out.println("********-Tabela Fipe*******\n"
					+ "Selecione o tipo de automovél\n"
					+ "Carros\n"
					+ "Motos\n"
					+ "Caminhões");
			System.out.println("***************");
			System.out.print("Digite: ");
			op = leitura.nextLine().toLowerCase();
			var json = consumo.obterDados(ENDERECO + op.replace(" ", "+") + "/marcas");
			while(true) {		
				if(op.contains("carros") || op.contains("motos") || op.contains("caminhoes")) {		
					json = consumo.obterDados(ENDERECO + op.replace(" ", "+") + "/marcas");
					break;
				}else {
					System.out.println("Opção Inválida! Tente Novamente!");
					System.out.print("Digite: ");
					op = leitura.nextLine().toLowerCase();
				}
			}
			
			var marcas = conversor.obterLista(json,DadosMarca.class);
			marcas.stream().sorted(Comparator.comparing(DadosMarca::codigo))
			.forEach(System.out::println);
			
			System.out.println("Escreva o nome da marca: ");
			System.out.print("Digite: ");
			marca = leitura.nextLine();

			Optional<String> codigoMarca = Optional.empty();
			while (true) {
				codigoMarca = marcas.stream().filter(n -> n.nome().equalsIgnoreCase(marca)).map(DadosMarca::codigo).findFirst();
				if(codigoMarca.isPresent()) {
					break;
				}else {
					System.out.println("Marca inválida! Tente novamnte!");
					System.out.print("Digite: ");
					marca = leitura.nextLine();
				}			
			}
			
			json = consumo.obterDados(ENDERECO + op.replace(" ", "+") + "/marcas/" + codigoMarca.get()  +"/modelos");
				
			
	     	var modeloLista = conversor.obterDados(json, DadosModelos.class);
	     	modeloLista.modelos().stream()
	     	.sorted(Comparator.comparing(DadosCarro::codigo))
	     	.forEach(System.out::println);
	     	
	     	System.out.println("Digite um trecho do veículo a ser buscado: ");
	     	System.out.print("Digite: ");
	     	codigoCarro = leitura.nextLine();
	     	List<DadosCarro> modelosFiltrados = modeloLista.modelos().stream()
	     			.filter(n -> n.nome().toLowerCase().contains(codigoCarro.toLowerCase()))
	     			.collect(Collectors.toList());
	     	while(true) {
	     		if(!modelosFiltrados.isEmpty()) {
	     			break;
	     		}else {
	     			System.out.println("Não foi encontrado correspondência alguma!\nTente novamente!");
	     			System.out.print("Digite: ");
	    	     	codigoCarro = leitura.nextLine();
	     			modelosFiltrados = modeloLista.modelos().stream()
	    	     			.filter(n -> n.nome().toLowerCase().contains(codigoCarro.toLowerCase()))
	    	     			.collect(Collectors.toList());
	     		}
	     	}
	     	
	   
	     	
	     	System.out.println("Modelos encontrados: ");
	     	modelosFiltrados.forEach(System.out::println);
	     	
	     	
	     	System.out.println("Digite o código do modelo desejado: ");
	     	System.out.print("Digite: ");
	     	var codigoModelo = leitura.nextLine();
	    
	     	json = consumo.obterDados(ENDERECO + op.replace(" ", "+") + "/marcas/" + codigoMarca.get()  +"/modelos/" + codigoModelo + "/anos");
	     	List<DadosCarro> anos = conversor.obterLista(json, DadosCarro.class);
	     	List<Veiculo> veiculos = new ArrayList<>();
	     	for (int i = 0; i < anos.size(); i++) {
	     		json = consumo.obterDados(ENDERECO + op.replace(" ", "+") + "/marcas/" + codigoMarca.get()  +"/modelos/" + codigoModelo + "/anos/" + anos.get(i).codigo());
	     		Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
	     		veiculos.add(veiculo);
	     	}
	     	System.out.println("Veículos Filtrados: ");
	     	veiculos.forEach(System.out::println);
			
	}
}
