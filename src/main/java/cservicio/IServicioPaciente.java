package cservicio;

import crepositorio.IRepositorio;
import crepositorio.RepositorioPaciente;
public interface IServicioPaciente extends IServicio{

	IRepositorio repositorio = ctx.getBean(RepositorioPaciente.class);
}
