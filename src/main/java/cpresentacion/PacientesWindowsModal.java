package cpresentacion;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import cdominio.Paciente;

public class PacientesWindowsModal extends WebPage{
	
	public PacientesWindowsModal(final List<Paciente> listaPacientes) {

		add(new ListView("lista-pacientes", listaPacientes) {
			protected void populateItem(ListItem item) {
				final Paciente paciente = (Paciente) item.getModelObject();
				item.add(new Label("documento", paciente.getDocumento()));
				item.add(new Label("nombre", paciente.getNombre()));
				item.add(new Label("apellido", paciente.getApellido()));
				item.add(new Label("ObraSocial", paciente.getObraSocial()));
				item.add(new Label("NumeroAfiliado", paciente.getNumeroAfiliado()));
			}
		});
	}
}