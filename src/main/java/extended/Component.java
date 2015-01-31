package extended;

import javax.xml.bind.annotation.XmlTransient;

import org.lemsml.model.ComponentType;

@XmlTransient
public class Component extends org.lemsml.model.Component
{
	ComponentType _ComponentType;

	public ComponentType get_ComponentType()
	{
		return _ComponentType;
	}

	public void set_ComponentType(ComponentType _ComponentType)
	{
		this._ComponentType = _ComponentType;
	}

}
