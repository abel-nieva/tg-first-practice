
public class PruebasLectorArchivoRandom {

	public static void main(String[] args)
	{
		LectorArchivoRandom lector = new LectorArchivoRandom("Archivo1.dato");
		lector.generaContenido();

		// ya hemos generado el contenido del fichero, ahora lo volvamos por pantalla
		lector.volcar();
		lector.cerrar();
	}

}
