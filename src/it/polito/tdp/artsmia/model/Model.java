package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	private ArtsmiaDAO dao;
	private List<ArtObject> artObjects;
	private Graph<ArtObject, DefaultWeightedEdge> graph;

	public Model() {
		dao = new ArtsmiaDAO();
		artObjects = new ArrayList<>();
		graph = new SimpleWeightedGraph<ArtObject, DefaultWeightedEdge>(DefaultWeightedEdge.class);
	}

	public void creaGrafo() {
		artObjects.addAll(dao.listObjects());
		Graphs.addAllVertices(this.graph, this.artObjects);
		List<ArtObject> presenti = new ArrayList<ArtObject>(dao.getPresenti());
//		System.out.println(this.artObjects);

		// Aggiungi gli archi (con il loro peso)

		for (ArtObject ao : presenti) {
			for (ArtObject ao2 : presenti) {
				if (ao.getId() < ao2.getId()
//						&& ao.getId() > 12696 && ao.getId() < 19490 && ao2.getId() > 12696 && ao2.getId() < 19490
				) {
					System.out.format("(%d, %d) peso %d\n", ao.getId(), ao2.getId(), this.exhibitionComuni(ao, ao2));
					Graphs.addEdgeWithVertices(graph, ao, ao2, this.exhibitionComuni(ao, ao2));
				}
			}
		}
//
//		for (ArtObject ao : this.artObjects) {
//			List<ArtObjectAndCount> connessi = dao.listArtObjectAndCount(ao);
//			for (ArtObjectAndCount c : connessi) {
//				ArtObject dest = new ArtObject(c.getArtObjectID(), null, null, null, 0, null, null, null, null, null, 0,
//						null, null, null, null, null);
//				Graphs.addEdge(this.graph, ao, dest, c.getCount());
//				System.out.format("(%d, %d) peso %d\n", ao.getId(), dest.getId(), c.getCount());
//			}
//		}

	}

	private int exhibitionComuni(ArtObject aop, ArtObject aoa) {
		ArtsmiaDAO dao = new ArtsmiaDAO();

		int comuni = dao.contaExhibitionComuni(aop, aoa);
		return comuni;
	}

	public int getVertici(int id) {
		for (ArtObject a : this.artObjects) {
			if (a.getId() == id) {
				ConnectivityInspector<ArtObject, DefaultWeightedEdge> c = new ConnectivityInspector<ArtObject, DefaultWeightedEdge>(
						this.graph);

				return c.connectedSetOf(a).size();
			}
		}
		return 0;
	}

}
