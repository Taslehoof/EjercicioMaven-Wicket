package crepositorio;

import java.util.List;
import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cdominio.*;

@Component
public class RepositorioPaciente implements IRepositorio {

	@Autowired
	PersistenceManagerFactory pmf;

	public void cargar(Object paciente) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(paciente);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Paciente> listar() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Paciente> pacientes;
		try {
			tx.begin();
			Extent ex = pm.getExtent(Paciente.class);
			Query q = pm.newQuery(ex);
			List<Paciente> pacientesAttached = q.executeList();
			pacientes = (List<Paciente>) pm.detachCopyAll(pacientesAttached);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return pacientes;
	}

	//@Override
	public void actualizar(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Paciente pacienteDetached = (Paciente) object;
			
			Paciente paciente = pm.getObjectById(Paciente.class, pacienteDetached.getId());
			
			paciente.setNumeroAfiliado(pacienteDetached.getNumeroAfiliado());
			paciente.setDocumento(pacienteDetached.getDocumento());
			paciente.setNombre(pacienteDetached.getNombre());
			paciente.setApellido(pacienteDetached.getApellido());
			paciente.setObraSocial(pacienteDetached.getObraSocial());

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}
