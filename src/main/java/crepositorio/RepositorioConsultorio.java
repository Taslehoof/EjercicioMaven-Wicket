package crepositorio;

import java.util.ArrayList;
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
public class RepositorioConsultorio implements IRepositorio {

	@Autowired
	PersistenceManagerFactory pmf;

 //	@Override
	public void cargar(Object consultorio) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Consultorio nuevoConsultorio = (Consultorio) consultorio;

			Medico medico = pm.getObjectById(Medico.class, nuevoConsultorio.getMedico().getId());

			nuevoConsultorio.setMedico(medico);

			List<Paciente> listaPacientes = new ArrayList<Paciente>();
			for (Paciente pac : nuevoConsultorio.getPacientes()) {
				listaPacientes.add(pm.getObjectById(Paciente.class, pac.getId()));
			}
			nuevoConsultorio.setPacientes(listaPacientes);
			pm.makePersistent(nuevoConsultorio);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

 //	@Override
	public List<Consultorio> listar() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Consultorio> consultorios = new ArrayList<Consultorio>();
		try {
			tx.begin();
			Extent ex = pm.getExtent(Consultorio.class);
			Query q = pm.newQuery(ex);
			List<Consultorio> consultoriosAttached = q.executeList();

			for (Consultorio consul : consultoriosAttached) {
				Consultorio consultorio = new Consultorio();
				consultorio.setId(consul.getId());
				consultorio.setNombre(consul.getNombre());
				consultorio.setMedico(pm.detachCopy(consul.getMedico()));
				consultorio.setPacientes((List<Paciente>) pm.detachCopyAll(consul.getPacientes()));
				consultorios.add(consultorio);
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return consultorios;
	}

	//@Override
	public void actualizar(Object object) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Consultorio consultorioDetached = (Consultorio) object;

			Consultorio consultorio = pm.getObjectById(Consultorio.class, consultorioDetached.getId());
			consultorio.setNombre(consultorioDetached.getNombre());

			Medico medico = pm.getObjectById(Medico.class, consultorioDetached.getMedico().getId());
			consultorio.setMedico(medico);
			List<Paciente> pacientes = new ArrayList<Paciente>();
			for (Paciente paciente : consultorioDetached.getPacientes()) {
				pacientes.add(pm.getObjectById(Paciente.class, paciente.getId()));
			}
			consultorio.setPacientes(pacientes);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}