package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public interface ToolsAPI<T> extends FCItemAPI<T>{

public default void initialise(FCToolMaterial material, int attackDamage, int attackSpeed) {
	setFCToolMaterial(material);
	setToolAttackDamage(attackDamage);
	setAttackSpeed(attackSpeed);
}

public default void initialise(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed) {
	initialise(id, modid, name, group);
	initialise(material, attackDamage, attackSpeed);
}
	
	
	@Override public ToolFieldHolder holder();

	
	public default void setFCToolMaterial(FCToolMaterial mat)	{holder().mat = mat;}
	public default void setToolAttackDamage(int damage) {holder().damage = damage;}
	public default void setAttackSpeed(int attackspeed) {holder().attackspeed = attackspeed;}
	
	public default FCToolMaterial getFCToolMaterial()	{return holder().mat;}
	public default int getToolAttackDamage() {return holder().damage;}
	public default int getAttackSpeed() {return holder().attackspeed;}
		
	
}



