package cpresentacion;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class Principal extends WebPage{
	
	public Principal() {
		
		add( new BookmarkablePageLink("cargar-paciente",CargarPaciente.class));
		add( new BookmarkablePageLink("listar-paciente",ListarPaciente.class));
		
		add( new BookmarkablePageLink("cargar-medico",CargarMedico.class));
		add( new BookmarkablePageLink("listar-medico",ListarMedico.class));
		
		add( new BookmarkablePageLink("cargar-consultorio",CargarConsultorio.class));
		add( new BookmarkablePageLink("listar-consultorio",ListarConsultorio.class));
	}

}
