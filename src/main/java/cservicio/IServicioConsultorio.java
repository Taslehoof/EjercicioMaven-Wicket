package cservicio;

import crepositorio.*;

public interface IServicioConsultorio extends IServicio{
	
	IRepositorio repositorio = ctx.getBean(RepositorioConsultorio.class);
	IRepositorio repositorioPaciente = ctx.getBean(RepositorioPaciente.class);
	IRepositorio repositorioMedico = ctx.getBean(RepositorioMedico.class);

}
