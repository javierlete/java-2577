package com.ipartek.ejercicios.poo; // convencion.todo.minusculas

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

// ConvencionNotacionPascal
public class Cuenta {
	// 1. VARIABLES DE INSTANCIA
	private Long id;
	private String nombreCliente; // convencionNotacionCamello
	private String numero;
	private Double tipoInteres;
	private BigDecimal saldo = BigDecimal.ZERO;

	// 5. CONSTRUCTOR DE TODOS LOS CAMPOS
	public Cuenta(Long id, String nombreCliente, String numero, Double tipoInteres, BigDecimal saldo) {
		// 6. SUSTITUIR CÓDIGO POR SETTERS
		setId(id);
		setNombreCliente(nombreCliente);
		setNumero(numero);
		setTipoInteres(tipoInteres);
		setSaldo(saldo); // BigDecimal
	}
	
	// 6.5 CONSTRUCTOR CON COMPATIBILIDAD DE TIPOS
	public Cuenta(Long id, String nombreCliente, String numero, Double tipoInteres, Integer saldo) {
		this(id, nombreCliente, numero, tipoInteres, (BigDecimal)null);
		setSaldo(saldo); // Integer
	}
	
	public Cuenta(Long id, String nombreCliente, String numero, Double tipoInteres, Double saldo) {
		this(id, nombreCliente, numero, tipoInteres, (BigDecimal)null);
		setSaldo(saldo); // Double
	}
	
	// 7. RESTO DE CONSTRUCTORES NECESARIOS LLAMANDO A THIS
	public Cuenta(String nombreCliente, String numero, Double tipoInteres, BigDecimal saldo) {
		this(null, nombreCliente, numero, tipoInteres, saldo);
	}
	
	// 6.5 CONSTRUCTOR CON COMPATIBILIDAD DE TIPOS
	public Cuenta(String nombreCliente, String numero, Double tipoInteres, Integer saldo) {
		this(null, nombreCliente, numero, tipoInteres, saldo);
	}
	
	public Cuenta(String nombreCliente, String numero, Double tipoInteres, Double saldo) {
		this(null, nombreCliente, numero, tipoInteres, saldo);
	}
	
	public Cuenta(Cuenta cuenta) {
		this(cuenta.id, cuenta.nombreCliente, cuenta.numero, cuenta.tipoInteres, cuenta.saldo);
	}
	
	public Cuenta() {
		this(null, null, null, null, (BigDecimal)null);
	}
	
	// 2. SETTERS Y GETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Double getTipoInteres() {
		return tipoInteres;
	}
	public void setTipoInteres(Double tipoInteres) {
		this.tipoInteres = tipoInteres;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	// 2.5 REVISAR LA PROTECCION DE LOS SETTERS Y GETTERS
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	// 2.7 SOBRECARGAS PARA COMPATIBILIDAD DE TIPOS DE DATOS
	public void setSaldo(Integer saldo) {
		setSaldo(new BigDecimal(saldo));
	}
	public void setSaldo(Double saldo) {
		setSaldo(new BigDecimal(saldo, new MathContext(2)));
	}
	
	// 3. HASHCODE Y EQUALS
	@Override
	public int hashCode() {
		return Objects.hash(id, nombreCliente, numero, saldo, tipoInteres);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombreCliente, other.nombreCliente)
				&& Objects.equals(numero, other.numero) && Objects.equals(saldo, other.saldo)
				&& Objects.equals(tipoInteres, other.tipoInteres);
	}
	
	// 4. TOSTRING
	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", nombreCliente=" + nombreCliente + ", numero=" + numero + ", tipoInteres="
				+ tipoInteres + ", saldo=" + saldo + "]";
	}
	
	// 8. MÉTODOS PROPIOS
	public boolean ingreso(BigDecimal cantidad) {
		if(cantidad.compareTo(BigDecimal.ZERO) < 0) {
//			throw new RuntimeException("No se admiten ingresos de cantidades negativas");
			return false;
		}
		
		setSaldo(getSaldo().add(cantidad));
		
		return true;
	}
	
	public boolean ingreso(Integer cantidad) {
		return ingreso(new BigDecimal(cantidad));
	}
	
	public boolean reintegro(BigDecimal cantidad) {
		if(cantidad.compareTo(BigDecimal.ZERO) < 0) {
//			throw new RuntimeException("No se admiten reintegros de cantidades negativas");
			return false;
		}
		// saldo < cantidad compareTo devuelve -1 si es menor, 0 si es igual o 1 si es mayor
		// saldo < cantidad => saldo.compareTo(cantidad) < 0 ((ESE CERO SIEMPRE ES CERO))
		if(saldo.compareTo(cantidad) < 0) {
//			throw new RuntimeException("Saldo insuficiente para el reintegro");
			return false;
		}
		
		setSaldo(getSaldo().subtract(cantidad));
		
		return true;
	}
	
	public boolean reintegro(Integer cantidad) {
		return reintegro(new BigDecimal(cantidad));
	}
	
	public boolean transferencia(Cuenta destino, BigDecimal importe) {
//		boolean esCorrecta;
//		
//		esCorrecta = reintegro(importe);
//		
//		if(!esCorrecta) {
//			return false;
//		}
//		
//		return destino.ingreso(importe);
		
		return reintegro(importe) && destino.ingreso(importe);
	}
	
	public boolean transferencia(Cuenta destino, Integer importe) {
		return transferencia(destino, new BigDecimal(importe));
	}
	
	public static boolean transferencia(Cuenta origen, Cuenta destino, BigDecimal importe) {
		return origen.transferencia(destino, importe);
	}
	
	public static boolean transferencia(Cuenta origen, Cuenta destino, Integer importe) {
		return origen.transferencia(destino, new BigDecimal(importe));
	}
}
