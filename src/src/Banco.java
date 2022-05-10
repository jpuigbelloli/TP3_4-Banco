package src;

import java.util.LinkedList;

public class Banco {

	private String nombre;
	private LinkedList<Cuenta> cuentas = new LinkedList<Cuenta>();

	public Banco(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LinkedList<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(LinkedList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Boolean agregarCuenta(Cuenta cuenta) {
		if (!cuentas.contains(cuenta))
			return cuentas.add(cuenta);
		else
			return false;
	}

	public Cuenta buscarCuentaPorId(int id) {
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getId().equals(id))
				return cuenta;
		}
		return null;
	}

	public boolean transferir(Integer idCuentaOrigen, Integer idCuentaDestino, Double monto) {
		
		Boolean exito = false;
		if (monto > 0) {
			Cuenta cuentaOrigen = buscarCuentaPorId(idCuentaOrigen);
			Cuenta cuentaDestino = buscarCuentaPorId(idCuentaDestino);
			if(cuentaDestino != null && cuentaOrigen != null) {
				cuentaOrigen.extraer(monto);
				cuentaDestino.depositar(monto);
				exito = true;
			}
		}
		return exito;
	}

}
