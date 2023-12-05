package com.amazonia2.logicanegocio;

import com.amazonia2.accesodatos.ConcurrenciaAccesoDatosException;
import com.amazonia2.accesodatos.ModificarNoExistenteAccesoDatosException;
import com.amazonia2.entidades.Producto;
import com.amazonia2.entidades.Rol;
import com.amazonia2.globales.Global;

import lombok.extern.java.Log;

@Log
public class AdminNegocioImpl extends UsuarioNegocioImpl implements AdminNegocio {
	
	// private static final Logger log = Logger.getLogger(AdminNegocioImpl.class.getName());
	
	@Override
	public Producto insertarProducto(Producto producto) {
		return Global.FABRICA.obtenerDaoProducto().insertar(producto);
	}

	@Override
	public Producto modificarProducto(Producto producto) {
		try {
			return Global.FABRICA.obtenerDaoProducto().modificar(producto);
		} catch (ConcurrenciaAccesoDatosException e) {
			throw new ModificacionDeModificadoLogicaNegocioException("Error de modificación de un producto ya modificado", e);
		}catch(ModificarNoExistenteAccesoDatosException e) {
			throw new ModificacionDeBorradoLogicaNegocioException("Error de modificación de un producto ya borrado", e);
		} catch (Exception e) {
			throw new LogicaNegocioException("Error no esperado al modificar el producto", e);
		}
	}

	@Override
	public void borrarProducto(Long id) {
		log.info("Se va a borrar el id " + id);
		Global.FABRICA.obtenerDaoProducto().borrar(id);		
	}

	@Override
	public Iterable<Rol> obtenerTodosLosRoles() {
		return Global.FABRICA.obtenerDaoRol().obtenerTodos();
	}
	
}
