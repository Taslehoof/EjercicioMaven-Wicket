package crepositorio;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cdominio.Medico;

@Component
public class RepositorioMedico implements IRepositorio {
	
	@Autowired
	PersistenceManagerFactory pmf;

	// @Override
	public void cargar(Object medico) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Medico medicoParaAgregar = (Medico) medico;
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(medico);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	// @Override
	public List<Medico> listar() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Medico> medicos;
		try {
			tx.begin();
			Extent ex = pm.getExtent(Medico.class);
			Query q = pm.newQuery(ex);
			List<Medico> medicosAttached = q.executeList();

			medicos = (List<Medico>) pm.detachCopyAll(medicosAttached);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return medicos;
	}

	public void actualizar(Object object) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Medico medicoDetached = (Medico) object;
			
			Medico medico = pm.getObjectById(Medico.class, medicoDetached.getId());

			medico.setNombre(medicoDetached.getNombre());
			medico.setApellido(medicoDetached.getApellido());
			medico.setDocumento(medicoDetached.getDocumento());
			medico.setNumeroMatricula(medicoDetached.getNumeroMatricula());
			medico.setEspecialidad(medicoDetached.getEspecialidad());

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}
