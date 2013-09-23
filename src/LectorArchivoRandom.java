import java.io.*;

/**
 * Clase capaz de manipular un archivo de tipo <code>RandomAccessFile</code> 
 * @version 1.0, (c)2010
 * @author TGI-BBDD
 */
public class LectorArchivoRandom extends Object {

	/**
	 * Fichero de acceso aleatorio utilizado para contener los registros.
	 * @see java.io.RandomAccessFile
	 */
	private RandomAccessFile archivo;

	/**
	 * Crea un objeto de tipo <code>LectorArchivoRamdom</code> .
	 * Abre el archivo  indicado por el par·metro <code>nombreArchivo</code>
	 * en modo lectur/escritura.
	 * @param nombreArchivo Nombre del archivo a abrir.
	 */
	public LectorArchivoRandom(String nombreArchivo)
	{
		try
		{
			this.archivo = new RandomAccessFile( nombreArchivo, "rw" );
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println("nombre del archivo incorrecto");
			System.exit(1);
		}
	}

	/**
	 * genera el contenido del fichero
	 */
	public void generaContenido()
	{
		try{
			this.archivo.writeInt(-2);
			this.archivo.writeInt(18);
			this.escribirCadena("Manolo", 25, this.archivo);
			this.escribirCadena("Garcia Garcia", 30, this.archivo);
			this.archivo.writeBoolean(true);

			this.archivo.writeInt(-2);
			this.archivo.writeInt(11);
			this.escribirCadena("Maria", 25, this.archivo);
			this.escribirCadena("Juarez Salina", 30, this.archivo);
			this.archivo.writeBoolean(false);
		}
		catch (IOException ioe){
			System.out.println("error IO en la escritura en el fichero");
			System.exit(3);
		}
	}


	/**
	 * Lee el contenido del archivo asociado a la propiedad "archivo" y lo vuelca por pantalla. Se lee en el mismo orden/formato que se ha escrito
	 */
	public void volcar()
	{
		int c1, c2;
		String c3, c4;
		boolean c5;

		try
		{
			// ponemos el puntero de L/E al comienzo del fichero
			this.archivo.seek(0);
			// la lectura del fichero debe respetar el formato y el orden de los tipos que se usaron para generar el contenido
			for (int i=0; i<2; i++) {
				c1 = this.archivo.readInt();
				c2 = this.archivo.readInt();
				c3 = this.leerCadena(25, this.archivo);
				c4 = this.leerCadena(30, this.archivo);
				c5 = this.archivo.readBoolean();
				System.out.println("R" + i + "-> c1: " + c1 + "; c2: " + c2 + "; c3: " + c3 + "; c4: " + c4 + "; c5: " + c5);
			}
		}
		catch (IOException ioe)
		{
			System.out.println("error en la lectura del fichero");
			System.exit(3);
		}
	}

	/**
	 * Cierra el archivo asociado a la propiedad "archivo"
	 */
	public void cerrar()
	{
		try
		{
			this.archivo.close();
		}
		catch (IOException ioe)
		{
			System.out.println("error IO al cerrar el fichero");
			System.exit(3);
		}
	}

	// este método escribe el contenido del parámetro cadena, con tantos caracteres como indica el segundo parámetro, en el archivo que se pasa como tercer parámetro
	// Si cadena tiene menos bytes se rellena con blancoas. Si cadena tiene más bytes se trunca su tamaño
	private void escribirCadena( String cadena, int longitud, RandomAccessFile archivo) throws IOException
	{
		StringBuffer bufer = null;

		if ( cadena != null )
			bufer = new StringBuffer( cadena );
		else
			bufer = new StringBuffer( longitud );
		bufer.setLength( longitud );
		archivo.writeChars( bufer.toString() );
	}

	// este método lee del archivo que se pasa como segundo parámetro tantos caracteres como indique el primer parámetro

	private String leerCadena( int longitud, RandomAccessFile archivo ) throws IOException
	{
		char cadena[] = new char[longitud];

		for ( int cuenta = 0; cuenta < cadena.length; cuenta++ ) {
			cadena[cuenta] = archivo.readChar();
		}
		return new String( cadena ).replace( '\0', ' ' );
	}

}

