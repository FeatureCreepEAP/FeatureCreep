package mx.kenzie.mimic;

import java.io.Serializable;
import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.util.Optional;


public class FieldErasure implements Constable, Serializable{

public	Class<?> owner;
public	String name;
public	Class<?> type;
	
public FieldErasure(Class<?> owner, String name, Class<?> type)  {
    this.owner=owner;
	this.name=name;
	this.type=type;
}	
	
	
    @Override
    public Optional<? extends ConstantDesc> describeConstable() {
        return Optional.empty();
    }
    

public String name()
{
return name;	
}

public Class<?> type()
{
return type;	
}
public Class<?> owner()
{
return owner;	
}


}