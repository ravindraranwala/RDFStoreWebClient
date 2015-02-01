package com.msc.rdf.web.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

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
	private static final Logger LOGGER = Logger.getLogger(RDFStoreAction.class);
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
			LOGGER.error(
					"Graph Processing Exception occurred while building the RDF Graph",
					e);
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
		LOGGER.info("Submitting the SPARQL Query to the RDF store.");
		String[] splitQueryStr = sparql.split("(?=SELECT)");
		try {
			result = rdfGraphProcessingEngineService.queryRdfModel(
					splitQueryStr[0], splitQueryStr[1]);

			LOGGER.info(result);
		} catch (RDFGraphProcessisngException e) {
			LOGGER.error(
					"Graph Processing Exception occurred while executing the SPARQL query",
					e);
		}

		return "SUCCESS";
	}

	public String dropRDFStore() {
		rdfStoreDaoService.dropRDFStore();
		return "SUCCESS";
	}

	public String createRDFStore() {
		try {
			LOGGER.info("Creating the RDF store.");
			InputStream input = getClass().getClassLoader()
					.getResourceAsStream("/config.properties");
			// Reading the properties files.
			// InputStream input = new FileInputStream(
			// "src/main/resources/config.properties");
			// load a properties file
			Properties prop = new Properties();
			prop.load(input);

			// get the property
			String[] inputDataFiles = prop.getProperty("inputFiles").split(",");

			rdfStoreDaoService.createRDFStore(rdfGraphProcessingEngineService
					.getRDFDataSet(inputDataFiles));

			LOGGER.info("RDF store was created successfully.");
		} catch (FileNotFoundException e) {
			LOGGER.error("The given file can not be found", e);
		} catch (IOException e) {
			LOGGER.error("I/O error occurred", e);
		}
		return "SUCCESS";

	}

	public String getResult() {
		LOGGER.info("Returning the results of the SPARQL query for rendering.");
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
