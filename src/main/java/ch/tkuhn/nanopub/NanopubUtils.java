package ch.tkuhn.nanopub;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.openrdf.model.Statement;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.trig.TriGWriter;

public class NanopubUtils {

	private NanopubUtils() {}  // no instances allowed

	public static List<Statement> getStatements(Nanopub nanopub) {
		List<Statement> s = new ArrayList<>();
		s.addAll(nanopub.getHead());
		s.addAll(nanopub.getAssertion());
		s.addAll(nanopub.getProvenance());
		s.addAll(nanopub.getPubinfo());
		return s;
	}

	public static void writeAsTrigFile(Nanopub nanopub, OutputStream out)
			throws RDFHandlerException {
		TriGWriter writer = new CustomTrigWriter(out);
		writer.startRDF();
		String s = nanopub.getUri().toString();
		writer.handleNamespace("this", s);
		writer.handleNamespace("sub", s + ".");
		writer.handleNamespace("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		writer.handleNamespace("xsd", "http://www.w3.org/2001/XMLSchema#");
		writer.handleNamespace("dc", "http://purl.org/dc/terms/");
		writer.handleNamespace("pav", "http://swan.mindinformatics.org/ontologies/1.2/pav/");
		writer.handleNamespace("np", "http://www.nanopub.org/nschema#");
		for (Statement st : getStatements(nanopub)) {
			writer.handleStatement(st);
		}
		writer.endRDF();
	}

}