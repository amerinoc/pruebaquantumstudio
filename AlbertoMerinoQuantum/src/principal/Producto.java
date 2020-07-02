package principal;
// clase Producto con los datos a recoger
public class Producto {
	String name, ean, sku, description, manufacturer, origin, lastUpdate, taxtype, disponibilidad;
	int stock;
	double precio, tax;
	
	public Producto() {}
	public Producto(String name, String ean, String sku, String description, String manufacturer, String origin,
			String lastUpdate, String taxtype, String disponibilidad, int stock, double precio, double tax) {
		this.name = name;
		this.ean = ean;
		this.sku = sku;
		this.description = description;
		this.manufacturer = manufacturer;
		this.origin = origin;
		this.lastUpdate = lastUpdate;
		this.taxtype = taxtype;
		this.disponibilidad = disponibilidad;
		this.stock = stock;
		this.precio = precio;
		this.tax = tax;
	} // fin constructor

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEan() {
		return ean;
	}
	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getTaxtype() {
		return taxtype;
	}
	public void setTaxtype(String taxtype) {
		this.taxtype = taxtype;
	}
	public String getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	@Override
	public String toString() {
		return "Producto [name=" + name + ", ean=" + ean + ", sku=" + sku + ", description=" + description
				+ ", manufacturer=" + manufacturer + ", origin=" + origin + ", lastUpdate=" + lastUpdate + ", stock="
				+ stock + ", precio=" + precio + ", tax=" + tax + "]";
	} // fin getters/setters
} // fin de la clase
