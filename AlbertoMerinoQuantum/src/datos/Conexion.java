package datos;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class Conexion {
	
	public static HttpURLConnection conectaServidor(String clave, String valor) {
		HttpURLConnection con = null;
		try {
			// se añade una direccion y el metodo de conexion comprobando su funcionamiento
			URL link = new URL("https://syncprovider.quantumstudio.es/test/products");
			con = (HttpURLConnection) link.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty(clave, valor);
			// se compreba si el codigo de respuesta del servidor es 200, que nos garantiza la conexion
			if(con.getResponseCode() != 200) {
				System.out.println("La conexión no ha sido posible. Error: " + con.getResponseCode());
				return null;
			}// fin if comprobacion codigo
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} // fin catch MalformedURL
		catch (IOException e) {
			e.printStackTrace();
		} // fin catch IOException
		return con;		
	} // fin metodo conectaServidor()
} // fin de la clase
