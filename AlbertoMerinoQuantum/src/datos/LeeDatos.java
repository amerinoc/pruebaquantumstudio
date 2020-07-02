package datos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import principal.Producto;

public class LeeDatos extends Conexion {
	static String clave = "API-KEY";
	static String valor = "E9F852CD97283461E254DA265A27D2BDA07F245CB5F8A6EE622355FCEC63EB8C";
	//listas con las que guardar los objetos
	static List<Producto> listprod = new ArrayList<Producto>();
	static List<Producto> prodliquidables = new ArrayList<Producto>();
	static List<Producto> prodactuales = new ArrayList<Producto>();
	
	public static void main(String[] args) {
		// llamada al metodo principal
		datosServidor();
		double totalliquidable = 0.0;
		int actualesagotados = 0;
		// calculo del coste total de productos liquidables
		for (Producto prodliq : prodliquidables) {
			totalliquidable += (prodliq.getPrecio() * prodliq.getStock());
		} // fin bucle productos liquidables
		for (Producto prodactuales : prodactuales) {
			switch (prodactuales.getDisponibilidad()) {
			case "AGOTADO":
				actualesagotados++;
				break;
			default:
				break;
			} // fin switch productos agotados
		} // fin bucle productos actuales
		System.out.println("TOTAL LIQUIDABLE: " + totalliquidable);
		System.out.println("PRODUCTOS ACTUALES AGOTADOS: " + actualesagotados);
		
	} // fin metodo main()
	
	public static void datosServidor() {
		try {
			// se realiza la conexion al servidor
			HttpURLConnection con = Conexion.conectaServidor(clave,valor);
			if(con != null) {
				// si la conexion es correcta se obtienen los datos que almacena
				String output;
				BufferedReader buffread = new BufferedReader(new InputStreamReader(con.getInputStream()));
				// se almacenan todos los datos en una sola varible con StringBuilder
				StringBuilder sbuilder = new StringBuilder();
				while((output = buffread.readLine()) != null) {
					sbuilder.append(output);
				}
				// metodos para rellenar la lista principal y las listas filtradas por origen
				guardaObjetos(sbuilder,listprod);
				filtraObjetos(listprod, prodliquidables, prodactuales);
				productosActuales();
				con.disconnect();
			} // fin if conexion es nula
		} catch (IOException e) {
			e.printStackTrace();
		} // fin catch IOException			
	}
	public static void productosActuales() {
		// calculo del tipo de IVA y disponibilidad
				for (Producto prodtax : prodactuales) {
					switch ((int)prodtax.getTax()) {
					case 21:
						prodtax.setTaxtype("NORMAL");
						break;
					case 10:
						prodtax.setTaxtype("REDUCIDO");
						break;
					case 4:
						prodtax.setTaxtype("SÚPER-REDUCIDO");
						break;
					case 0:
						prodtax.setTaxtype("EXENTO");
						break;	
					default:
						prodtax.setTaxtype("DESCONOCIDO");
						break;
					} // fin switch tipo de IVA
					if(prodtax.getStock() == 0) {
						prodtax.setDisponibilidad("AGOTADO");
					} else if (prodtax.getStock() >= 1 || prodtax.getStock() <= 5) {
						prodtax.setDisponibilidad("ÚLTIMAS UNIDADES");
					} else if(prodtax.getStock() > 5) {
						prodtax.setDisponibilidad("EN STOCK");
					} // fin if disponibilidad de producto
				} // fin foreach lista de productos actuales
	}
	
	public static void filtraObjetos(List<Producto> listprod, List<Producto> prodliquidables, List<Producto> prodactuales) {
		// se repasa la lista principal con todos los productos y se filtran segun el campo origin
		for (Producto producto : listprod) {
			if(producto.getOrigin().trim().equalsIgnoreCase("LIQUIDACION") || producto.getOrigin().trim().equalsIgnoreCase("CADUCIDADCORTA")) {
				prodliquidables.add(producto);		
			} else if(producto.getOrigin().trim().equalsIgnoreCase("DEMANDA") || producto.getOrigin().trim().equalsIgnoreCase("STOCK")) {
				prodactuales.add(producto);
			}// fin if filtro liquidables o actuales
		} // fin foreach lista principal de productos
	} // fin metodo filtraObjetos
	
	public static void guardaObjetos(StringBuilder sbuilder, List<Producto> listprod) {
		// se transforma el StringBuilder en un array de JSON desde el que sacar los datos a un objeto
		JSONArray jsonprod = new JSONArray(sbuilder.toString());
		for(int i = 0; i < jsonprod.length(); i++) {
			Producto prod = new Producto();
			prod.setName(jsonprod.getJSONObject(i).get("name").toString());
			prod.setEan(jsonprod.getJSONObject(i).get("ean").toString());
			prod.setSku(jsonprod.getJSONObject(i).get("sku").toString());
			prod.setDescription(jsonprod.getJSONObject(i).get("description").toString());
			prod.setManufacturer(jsonprod.getJSONObject(i).get("manufacturer").toString());
			prod.setOrigin(jsonprod.getJSONObject(i).get("origin").toString());
			prod.setLastUpdate(jsonprod.getJSONObject(i).get("lastUpdate").toString());
			prod.setStock(Integer.parseInt(jsonprod.getJSONObject(i).get("stock").toString()));
			prod.setPrecio(Double.parseDouble(jsonprod.getJSONObject(i).get("cost").toString()));
			prod.setTax(Double.parseDouble(jsonprod.getJSONObject(i).get("tax").toString()));
			// se guardan los objetos en su lista
			listprod.add(prod);
		} // fin bucle json a objeto
		
	} // fin metodo guardaObjetos
} // fin de la clase

