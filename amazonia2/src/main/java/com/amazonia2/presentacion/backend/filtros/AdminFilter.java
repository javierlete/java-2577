package com.amazonia2.presentacion.backend.filtros;

import java.io.IOException;

import com.amazonia2.entidades.Usuario;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter implements Filter {
 static final long serialVersionUID = 1707667304509984820L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		
		if(usuario == null || usuario.getRol().getId() != 1) {
			req.setAttribute("error", "Debes ser administrador para entrar en esa parte de la web");
			req.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(req, res);
			
			return;
		}
		
		chain.doFilter(request, response);
	}
}
