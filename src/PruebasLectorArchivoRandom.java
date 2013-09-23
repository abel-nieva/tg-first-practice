import java.io.FileNotFoundException;
import java.io.IOException;

public class PruebasLectorArchivoRandom {

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		try
		{
			LectorArchivoRandom lector = new LectorArchivoRandom("Archivo1.dato");
			lector.generaContenido();
			
			// ya hemos generado el contenido del fichero, ahora lo volcamos por pantalla
			lector.volcar();
			lector.cerrar();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Nombre del archivo incorrecto");
			System.exit(1);
		}
		catch (IOException ioe){
			System.out.println("Error IO en el fichero");
			System.exit(3);
		}
	}
}
