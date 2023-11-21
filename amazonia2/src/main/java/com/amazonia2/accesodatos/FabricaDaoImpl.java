package com.amazonia2.accesodatos;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FabricaDaoImpl implements FabricaDao {
	private final DaoProducto daoProducto;

	public FabricaDaoImpl(String rutaFicheroProperties) {
		try {
			Properties props = new Properties();
			props.load(new FileReader(rutaFicheroProperties));
			
			String tipo = props.getProperty("accesodatos.tipo");
			String url = props.getProperty("accesodatos.url");
			
			switch(tipo) {
			case "sqlite":
				daoProducto = new DaoProductoSqlite(url);
				break;
//		case "mysql":
//			String user = props.getProperty("accesodatos.user");
//			String pass = props.getProperty("accesodatos.pass");
//			
//			daoProducto = new DaoProductoMySql(url, user, pass);
//			break;
			default:
				throw new AccesoDatosException("No se reconoce el tipo proporcionado: " + tipo);
			}
		} catch (IOException e) {
			throw new AccesoDatosException("No se ha podido leer el fichero de configuración: " + rutaFicheroProperties, e);
		}
	}

	@Override
	public DaoProducto obtenerDaoProducto() {
		return daoProducto;
	}

}
