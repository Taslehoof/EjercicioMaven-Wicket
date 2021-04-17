package cservicio;

import crepositorio.IRepositorio;
import crepositorio.RepositorioMedico;

public interface IServicioMedico extends IServicio{

	IRepositorio repositorio = ctx.getBean(RepositorioMedico.class);
}
