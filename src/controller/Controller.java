package controller;

import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.logic.Modelo;
import model.logic.MovieCatalog;
import view.View;

public class Controller {

	/* Instancia del catalogo*/
	private MovieCatalog catalogo;

	/* Instancia de la Vista*/
	private View view;

	private static final String PATH_CASTING_PELICULAS = "./data/MoviesCastingRaw-small.csv";

	private static final String PATH_INFO_PELICULAS = "./data/SmallMoviesDetailsCleaned.csv";

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		catalogo = new MovieCatalog();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				view.printMessage("--------- \nSe están cargando los datos ");
				catalogo.readData(PATH_INFO_PELICULAS, PATH_CASTING_PELICULAS);
				view.printMessage("Catálogo creado");
				view.printMovieInfo(catalogo.getFirstMovie());
				view.printMovieInfo(catalogo.getLastMovie());
				view.printMessage("Se encontraron  " + catalogo.getMovieCount() + " películas " +"\n---------");						
				break;

			case 2:
				view.printMessage("--------- \nDar el nombre del director a buscar:");
				String mensaje = lector.next();
				try {
					view.printMoviesInfo(catalogo.goodMoviesByDirectorName(mensaje));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}						
				break;

			case 3: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
