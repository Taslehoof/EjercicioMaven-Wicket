package cpresentacion;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import cdominio.Consultorio;
import cservicio.IServicioConsultorio;

public class ListarConsultorio extends Principal implements IServicioConsultorio{
	
	@SuppressWarnings("unchecked")
	public ListarConsultorio() {

		List<Consultorio> listaConsultorios = (List<Consultorio>) repositorio.listar();

		add(new ListView("lista-consultorios", listaConsultorios) {
			protected void populateItem(ListItem item) {
				final ModalWindow modal;
				final Consultorio consultorio = (Consultorio) item.getModelObject();
				item.add(new Label("nombre", consultorio.getNombre()));
				item.add(new Label("medico", consultorio.getMedico()));

				item.add(modal = new ModalWindow("pacientes"));
				modal.setResizable(false);
				modal.setInitialWidth(1000);
				modal.setInitialHeight(500);
		
				modal.setPageCreator(new ModalWindow.PageCreator() {
					public Page createPage() {
						return new PacientesWindowsModal(consultorio.getPacientes());
					}
				});

				item.add(new AjaxLink<Void>("mostrarPacientes") {
					@Override
					public void onClick(AjaxRequestTarget target) {
						modal.show(target);
					}
				});

				item.add(new Link("editar") {
					@Override
					public void onClick() {
						setResponsePage(new CargarConsultorio(consultorio));
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
