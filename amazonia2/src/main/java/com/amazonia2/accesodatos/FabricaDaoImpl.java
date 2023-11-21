package com.amazonia2.accesodatos;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class FabricaDaoImpl implements FabricaDao {
	private final DaoProducto daoProducto;

	public FabricaDaoImpl(String rutaFicheroProperties) {
		try {
			Properties props = new Properties();
			props.load(new FileReader(rutaFicheroProperties));
			
			String tipo = props.getProperty("accesodatos.tipo");
			String url = props.getProperty("accesodatos.url");
			
			String user = props.getProperty("accesodatos.user");
			String pass = props.getProperty("accesodatos.pass");
			
			daoProducto = (DaoProducto)Class.forName(tipo).getDeclaredConstructor(String.class, String.class, String.class).newInstance(url, user, pass);
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new AccesoDatosException("No se ha podido leer el fichero de configuración: " + rutaFicheroProperties, e);
		}
	}

	@Override
	public DaoProducto obtenerDaoProducto() {
		return daoProducto;
	}

}
