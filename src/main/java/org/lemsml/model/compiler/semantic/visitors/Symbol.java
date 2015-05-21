package org.lemsml.model.compiler.semantic.visitors;

import static tec.units.ri.AbstractUnit.ONE;

import javax.measure.Unit;

import org.lemsml.model.compiler.IDimensionalEvaluable;
import org.lemsml.model.compiler.ISymbol;

public class Symbol<T> implements ISymbol<T> {

	private Double value;
	private String name;
	private Unit<?> unit = ONE;
	private T type;
	private IDimensionalEvaluable dimensionalValue;

	public Symbol(String name, T instance) {
		this.name = name;
		this.type = instance;
	}

	@Override
	public Double evaluate() {
		return this.value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Unit<?> getUnit() {
		return this.unit;
	}

	@Override
	public T getType() {
		return this.type;
	}

	@Override
	public void setType(T type) {
		// TODO Auto-generated method stub

	}

	public void setDimensionalValue(IDimensionalEvaluable quant) {
		this.dimensionalValue = quant;
		this.value = quant.evaluate();
		this.unit = quant.getUnit();
	}

	public IDimensionalEvaluable getDimensionalValue() {
		return this.dimensionalValue;
	}

}