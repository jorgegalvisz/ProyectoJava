package co.edu.unab.peliculas.presentacion;

import co.edu.unab.peliculas.negocio.CatalogoPeliculasImp;
import co.edu.unab.peliculas.negocio.ICatalogoPeliculas;
import java.util.Scanner;

public class CatalogoPeliculasPresentacion {

    public static void main(String[] args) {
        int opcion = -1;
        Scanner scanner = new Scanner(System.in);
        ICatalogoPeliculas catalogo=new CatalogoPeliculasImp();
        while (opcion !=0) {            
            System.out.println("Elige una opcion: \n"
            +"1. iniciar Catalogo pelicula\n"
            +"2. Agregar Pelicula\n"        
            +"3. Listar pelicula\n"
            +"4. Buscar pelicula\n"
            +"0. Salir");
            opcion=Integer.parseInt(scanner.nextLine());
            switch(opcion){
                case 1:
                    catalogo.iniciarCatalogoPeliculas();
                    break;
                case 2:
                    System.out.println("Introduce el nombre de la pelicula");
                    String nombrePelicula=scanner.nextLine();
                    catalogo.agregarPelicula(nombrePelicula);
                    break;
                case 3:
                    catalogo.listarPeliculas();
                    break;
                case 4:
                    System.out.println("Introduce el nombre de la pelicula a buscar");
                    String buscar=scanner.nextLine();
                    catalogo.buscarPelicula(buscar);
                    break;
                case 0:
                    System.out.println("Hasta Pronto!");
                    break;
                default:
                    System.out.println("Opcion no reconocida");
                    break;
            }
            
        }

        
    }
}
