package co.edu.unab.peliculas.datos;

import co.edu.unab.peliculas.domain.Pelicula;
import co.edu.unab.peliculas.excepciones.*;
import java.io.*;
import java.util.*;

public class AccesoDatosImpl implements IAccesoDatos{

    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEx {
        File archivo=new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo=new File(nombreRecurso);
        List<Pelicula>peliculas=new ArrayList();
        try {
            BufferedReader entrada=new BufferedReader(new FileReader(archivo));
            String linea=null;
            linea=entrada.readLine();
            while(linea !=null){
                Pelicula pelicula= new Pelicula(linea);
                peliculas.add(pelicula);
                linea=entrada.readLine();
                
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al listar pelicula: "+ex.getMessage());
        } catch (IOException ex) {
            throw new LecturaDatosEx("Excepcion al listar pelicula: "+ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        File archivo=new File(nombreRecurso);
        try {
            PrintWriter salida=new PrintWriter(new FileWriter(archivo,anexar));
            salida.println(pelicula.getNombre());
            salida.close();
            System.out.println("Se ha escrito informacion al archivo: "+pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir pelicula: "+ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        File archivo=new File(nombreRecurso);
        String resultado=null;
        try {
            BufferedReader entrada=new BufferedReader(new FileReader(archivo));
            String linea=null;
            linea=entrada.readLine();
            int indice=1;
            while (linea !=null) {                
                if (buscar != null && buscar.equalsIgnoreCase(linea)) {
                    resultado="Pelicula: "+linea+", encontrada en el indice "+indice;
                    break;
                }
                linea=entrada.readLine();
                indice++;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: "+ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al buscar pelicula: "+ex.getMessage());
        }
        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        File archivo=new File(nombreRecurso);
        try {
            PrintWriter salida=new PrintWriter(new FileWriter(archivo));
            salida.close();
            System.out.println("Se ha creado el archivo");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new AccesoDatosEx("Excepcion al crear archivo: "+ex.getMessage());
        }
    }

    @Override
    public void borrar(String nomnbreRecurso){
        File archivo=new File(nomnbreRecurso);
        if (archivo.exists()) {
            archivo.delete(); 
        }
        System.out.println("Se ha borrado el archivo");
    }                     
}
