package compiler.semantic.visitors;

import static tec.units.ri.AbstractUnit.ONE;
import static tec.units.ri.util.SI.AMPERE;
import static tec.units.ri.util.SI.CANDELA;
import static tec.units.ri.util.SI.KELVIN;
import static tec.units.ri.util.SI.KILOGRAM;
import static tec.units.ri.util.SI.METRE;
import static tec.units.ri.util.SI.MOLE;
import static tec.units.ri.util.SI.SECOND;

import javax.measure.Unit;

import org.lemsml.visitors.BaseVisitor;
import org.lemsml.visitors.DepthFirstTraverserImpl;
import org.lemsml.visitors.TraversingVisitor;

import extended.Dimension;
import extended.Lems;

/**
 * @author borismarin
 *
 */
public class AddUOMToDimensionVisitor extends TraversingVisitor<Boolean, Throwable>
{

	private Lems lems;

	public AddUOMToDimensionVisitor(Lems lems)
	{
		super(new DepthFirstTraverserImpl<Throwable>(), new BaseVisitor<Boolean, Throwable>());
		this.lems = lems;
	}

	public javax.measure.Dimension LemsDimensionToUOM(Dimension lemsDim)
	{
		Unit<?> dim = ONE;
		dim = dim.multiply(AMPERE.pow(lemsDim.getI().intValue()));
		dim = dim.multiply(CANDELA.pow(lemsDim.getJ().intValue()));
		dim = dim.multiply(KELVIN.pow(lemsDim.getK().intValue()));
		dim = dim.multiply(METRE.pow(lemsDim.getL().intValue()));
		dim = dim.multiply(KILOGRAM.pow(lemsDim.getM().intValue()));
		dim = dim.multiply(MOLE.pow(lemsDim.getN().intValue()));
		dim = dim.multiply(SECOND.pow(lemsDim.getT().intValue()));
		return dim.getDimension();
	}

	@Override
	public Boolean visit(Dimension dimension) throws Throwable
	{
		dimension.setDimension(LemsDimensionToUOM(dimension));
		lems.getNameToDimension().put(dimension.getName(), dimension.getDimension());
		return true;
	}

	public Lems getLems()
	{
		return lems;
	}

}
