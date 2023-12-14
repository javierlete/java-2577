package com.amazonia2.accesodatos;

public interface Dao<T> {
	default Iterable<T> obtenerTodos() {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default T obtenerPorId(Long id) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default T insertar(T objeto) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default T modificar(T objeto) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}

	default void borrar(Long id) {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}
	
	default long cuantosHay() {
		throw new UnsupportedOperationException("NO IMPLEMENTADO");
	}
}
