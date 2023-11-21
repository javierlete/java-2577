package com.amazonia2.globales;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.amazonia2.accesodatos.DaoProducto;
import com.amazonia2.logicanegocio.UsuarioNegocio;

public class FabricaImpl implements Fabrica {
	private final DaoProducto daoProducto;
	private final UsuarioNegocio usuarioNegocio;

	public FabricaImpl(String rutaFicheroProperties) {
		try {
			Properties props = new Properties();
			props.load(new FileReader(rutaFicheroProperties));
			
			String tipo = props.getProperty("accesodatos.tipo");
			String url = props.getProperty("accesodatos.url");
			
			String user = props.getProperty("accesodatos.user");
			String pass = props.getProperty("accesodatos.pass");
			
			String tipoUsuarioNegocio = props.getProperty("logicanegocio.tipo");
			
			daoProducto = (DaoProducto)Class.forName(tipo).getDeclaredConstructor(String.class, String.class, String.class).newInstance(url, user, pass);
			
			System.out.println(tipoUsuarioNegocio);
			Class<?> clase = Class.forName(tipoUsuarioNegocio);
			Constructor<?> constructor = clase.getConstructor();
			Object objeto = constructor.newInstance();
			usuarioNegocio = (UsuarioNegocio)objeto;
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("No se ha podido leer el fichero de configuración: " + rutaFicheroProperties, e);
		}
	}

	@Override
	public DaoProducto obtenerDaoProducto() {
		return daoProducto;
	}

	@Override
	public UsuarioNegocio obtenerUsuarioNegocio() {
		return usuarioNegocio;
	}

}
