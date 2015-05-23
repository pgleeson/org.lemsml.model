package org.lemsml.model.test;

import java.io.File;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;
import org.lemsml.model.compiler.LEMSCompilerFrontend;
import org.lemsml.model.exceptions.LEMSCompilerException;
import org.lemsml.model.extended.Component;
import org.lemsml.model.extended.Lems;


public class DimensionalAnalysisTest extends BaseTest {

	private File schema;
	private File lemsDoc;
	private LEMSCompilerFrontend compiler;

	@Before
	public void setUp() throws Throwable {
		schema = getLocalFile("/Schemas/LEMS_v0.9.0.xsd");
		lemsDoc = getLocalFile("/examples/dimensional-analysis-test/dimensional.xml");
		compiler = new LEMSCompilerFrontend(lemsDoc, schema);
	}

	@Test(expected = LEMSCompilerException.class)
	public void testWrongParameter() throws Throwable {
		Lems fakeLems = compiler.generateLEMSDocument();
		Component fakeHO = new Component();
		fakeHO.setType("HarmonicOscillator");
		//intentionally causing an unit mismatch error to be catched (mass in metres...)
		fakeHO.getOtherAttributes().put(new QName("m"), "1m");
		fakeHO.getOtherAttributes().put(new QName("k"), "1N_per_m");
		fakeLems.getComponents().add(fakeHO);
		LEMSCompilerFrontend.semanticAnalysis(fakeLems);

		

	}

}