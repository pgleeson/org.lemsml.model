package org.lemsml.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.lemsml.model.ComponentType;
import org.lemsml.model.Parameter;

import compiler.parser.LEMSXMLReader;
import compiler.parser.XMLUtils;

import extended.Lems;

public class SimplePendulumTest extends BaseTest {

	private File schema;
	private File lemsdoc;

	@Before
	public void setUp() {
		schema = getLocalFile("/Schemas/LEMS_v0.9.0.xsd");
		lemsdoc = getLocalFile("/examples/opensourcechaos/standalone_pend.xml");
	}

	@Test
	public void validate() {
		assertTrue(XMLUtils.validate(lemsdoc, schema));
	}

	@Test
	public void testUnmarshalling() {

		Lems lems = LEMSXMLReader.unmarshall(lemsdoc, schema);
		ComponentType pendCompType = lems.getComponentType().get(0);

		String desc = pendCompType.getDescription();
		assertEquals(desc,
				"Equations of motion for a simple pendulum with mass _m_ and length _l_ ");

		List<Parameter> ParameterList = pendCompType.getParameter();
		assertEquals(ParameterList.get(0).getDescription(), "Mass of the bob");
	}


}
