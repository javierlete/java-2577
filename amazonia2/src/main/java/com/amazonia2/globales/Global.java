package com.amazonia2.globales;

import com.amazonia2.logicanegocio.AdminNegocio;
import com.amazonia2.logicanegocio.UsuarioNegocio;

public class Global {
	public static final Fabrica FABRICA = new FabricaImpl("C:/sqlite/amazonia2.properties");
	
	public static final UsuarioNegocio UN = FABRICA.obtenerUsuarioNegocio();
	public static final AdminNegocio AN = FABRICA.obtenerAdminNegocio();
}
