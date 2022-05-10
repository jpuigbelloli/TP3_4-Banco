package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import src.Banco;
import src.Cliente;
import src.Cuenta;

public class TestCliente {

	@Test
	public void queSePuedaCrearUnCliente() {

		Cliente miCliente = new Cliente(39333188, "Jose", "Puig");
		Assert.assertEquals(39333188, miCliente.getDni(), 0.01);

	}

	@Test
	public void testQueAgregaClienteAunaCuenta() {

		Cliente cliente = new Cliente(39333188, "Jose", "Puig");
		Cuenta cuenta = new Cuenta(1, 1000.0, cliente);

		Integer id = 2;
		Double saldo = 2000.0;
		Integer dni = 38690766;
		String nombre = "Barbara";
		String apellido = "Berroa";

		Cuenta cuenta2 = new Cuenta(id, saldo, dni, nombre, apellido);
		assertEquals(39333188, cuenta.getCliente().getDni(), 0.0);

	}

	@Test
	public void testQueVerificaQueSeRealizaUnaExtracion() {
		Cliente cliente = new Cliente(39333188, "Jose", "Puig");
		Cuenta cuenta = new Cuenta(1, 1000.0, cliente);

		cuenta.extraer(300.0);
		assertEquals(700.0, cuenta.getSaldo(), 0.0);
	}

	@Test
	public void testQueVerificaQueSeRealizaUnDeposito() {
		Cliente cliente = new Cliente(39333188, "Jose", "Puig");
		Cuenta cuenta = new Cuenta(1, 1000.0, cliente);

		cuenta.depositar(300.0);
		assertEquals(1300.0, cuenta.getSaldo(), 0.0);
	}

	@Test
	public void testQueagregaUnaCuentaAlBanco() {
		Cliente cliente = new Cliente(39333188, "Jose", "Puig");
		Cuenta cuenta = new Cuenta(1, 1000.0, cliente);

		Banco banco = new Banco("BBVA");
		assertTrue(banco.agregarCuenta(cuenta));
	}

	@Test
	public void testQueBuscaUnaCuentaPorId() {
		Cliente cliente = new Cliente(39333188, "Jose", "Puig");
		Cuenta cuenta = new Cuenta(1, 1000.0, cliente);
		Cuenta cuenta2 = new Cuenta(2, 6000.0, cliente);
		Banco banco = new Banco("BBVA");
		banco.agregarCuenta(cuenta);
		banco.agregarCuenta(cuenta2);

		Cuenta cuentaEncontrada = banco.buscarCuentaPorId(2);
		assertEquals(2, cuentaEncontrada.getId().intValue());
	}

	@Test
	public void testQueVerificaUnaTransferenciaExitosaEntreDosCuentas() {
		Cliente cliente = new Cliente(39333188, "Jose", "Puig");
		Cuenta cuenta = new Cuenta(1, 1000.0, cliente);
		Cliente cliente2 = new Cliente(36896944, "Agustin", "Puig Belloli");
		Cuenta cuenta2 = new Cuenta(2, 6000.0, cliente2);
		Banco banco = new Banco("BBVA");
		banco.agregarCuenta(cuenta);
		banco.agregarCuenta(cuenta2);

		Integer idCuentaOrigen = 1;
		Integer idCuentaDestino = 2;
		Double monto = 200.0;
		assertTrue(banco.transferir(idCuentaOrigen, idCuentaDestino, monto));

		assertEquals(800.0, cuenta.getSaldo(), 0.01);
		assertEquals(6200.0, cuenta2.getSaldo(), 0.01);
	}
	
	
	
	

}
