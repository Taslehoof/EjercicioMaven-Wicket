package cpresentacion;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import cdominio.Paciente;
import cservicio.IServicioPaciente;
public class ListarPaciente extends Principal implements IServicioPaciente {
	
	public ListarPaciente() {
		
		List<Paciente> listaPacientes = (List<Paciente>) repositorio.listar();
		
		ListView lv = new ListView("tr-lista-pacientes", listaPacientes) {

			@Override
			protected void populateItem(ListItem item) {
				// TODO Auto-generated method stub
				
				final Paciente paciente = (Paciente) item.getModelObject();

				item.add(new Label("NumeroAfiliado", paciente.getNumeroAfiliado()));
				item.add(new Label("nombre", paciente.getNombre()));
				item.add(new Label("apellido", paciente.getApellido()));
				item.add(new Label("documento", paciente.getDocumento()));
				item.add(new Label("ObraSocial", paciente.getObraSocial()));
				
				item.add(new Link("editar") {
					@Override
					public void onClick() {
						setResponsePage(new CargarPaciente(paciente));
					}

					@Override
					public MarkupContainer setDefaultModel(IModel model) {
						// TODO Auto-generated method stub
						return null;
					}			
				});
			}
		};
		add(lv);
	}
}

