package crepositorio;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public interface IRepositorio {
	
	@Autowired
	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory();
	
	void cargar(Object object);

	List<?> listar();

	void actualizar(Object object);

}