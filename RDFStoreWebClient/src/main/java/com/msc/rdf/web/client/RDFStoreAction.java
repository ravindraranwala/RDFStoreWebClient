package com.msc.rdf.web.client;

import java.util.logging.Logger;

import com.datastax.driver.core.ResultSet;
import com.msc.research.cassandra.dao.CassandraRDFStoreDaoServiceImpl;
import com.msc.research.cassandra.dao.RDFStoreDaoService;
import com.msc.research.cassandra.exception.RDFGraphProcessisngException;
import com.msc.research.cassandra.graph.JenaRDFGraphProcessingEngine;
import com.msc.research.cassandra.graph.RDFGraphProcessingEngineService;
import com.opensymphony.xwork2.ActionSupport;

public class RDFStoreAction extends ActionSupport {
	private static final RDFGraphProcessingEngineService rdfGraphProcessingEngineService = JenaRDFGraphProcessingEngine
			.newInstance();
	private static final RDFStoreDaoService rdfStoreDaoService = CassandraRDFStoreDaoServiceImpl
			.newInstance();
	private static final Logger logger = Logger.getLogger(RDFStoreAction.class
			.getName());

	private String sparql;

	private String result = null;

	public String getSparql() {
		return sparql;
	}

	public void setSparql(String sparql) {
		this.sparql = sparql;
	}

	public String buildGraph() {
		try {
			// read RDF triples from the RDF store.
			ResultSet resultSet = rdfStoreDaoService.getRdfData();

			// Then build the RDF graph model.
			rdfGraphProcessingEngineService.build(resultSet);
		} catch (RDFGraphProcessisngException e) {
			e.printStackTrace();
		} finally {
			/*
			 * when the interaction with the DAO layer is completed, merely
			 * close the connection.
			 */
			rdfStoreDaoService.close();
		}
		return "SUCCESS";
	}

	public String executeQuery() {
		String[] splitQueryStr = sparql.split("(?=SELECT)");
		try {
			result = rdfGraphProcessingEngineService.queryRdfModel(
					splitQueryStr[0], splitQueryStr[1]);

			logger.info(result);
		} catch (RDFGraphProcessisngException e) {
			e.printStackTrace();
		}

		return "SUCCESS";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
