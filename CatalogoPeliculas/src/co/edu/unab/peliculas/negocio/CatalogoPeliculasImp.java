package co.edu.unab.peliculas.negocio;

import co.edu.unab.peliculas.domain.Pelicula;
import co.edu.unab.peliculas.datos.*;
import co.edu.unab.peliculas.excepciones.*;
import java.util.List;


public class CatalogoPeliculasImp implements ICatalogoPeliculas{
    private final IAccesoDatos datos;
    
    public CatalogoPeliculasImp(){
        this.datos=new AccesoDatosImpl();
    }

    
    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula=new Pelicula(nombrePelicula);
        boolean anexar=false;
        try {
            anexar=datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula, NOMBRE_RECURSO, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
        
    }

    @Override
    public void listarPeliculas() {
        try {
            List<Pelicula>peliculas=this.datos.listar(NOMBRE_RECURSO);
            for (Pelicula pelicula:peliculas) {
                System.out.println("Pelicula= "+pelicula);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }  
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado= null;
        try {
            resultado= this.datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
        System.out.println("resultado = " + resultado);
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if(this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }
            else{
                datos.crear(NOMBRE_RECURSO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar catalogo de peliculas ");
            ex.printStackTrace(System.out);
        }
    }
}
