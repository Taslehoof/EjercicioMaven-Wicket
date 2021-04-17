package cpresentacion;

import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import cdominio.Medico;
import cservicio.IServicioMedico;

public class ListarMedico extends Principal implements IServicioMedico{

	public ListarMedico() {
		
		List<Medico> listaMedicos = (List<Medico>) repositorio.listar();
		
		add(new ListView("listar-medicos", listaMedicos) {
		    protected void populateItem(ListItem item) {
		        final Medico medico = (Medico) item.getModelObject();
		        item.add(new Label("nombre", medico.getNombre()));
		        item.add(new Label("apellido", medico.getApellido()));
		        item.add(new Label("documento", medico.getDocumento()));
		        item.add(new Label("NumeroMatricula", medico.getNumeroMatricula()));
		        item.add(new Label("especialidad", medico.getEspecialidad()));
		        
		        item.add(new Link("editar") {
					@Override 
					public void onClick() {
						setResponsePage(new CargarMedico(medico)); 
					}

					@Override
					public MarkupContainer setDefaultModel(IModel model) {
						// TODO Auto-generated method stub
						return null;
					}
				});
		    }
		});
	}
}
