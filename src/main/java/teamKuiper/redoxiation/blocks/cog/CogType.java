package teamKuiper.redoxiation.blocks.cog;

import net.minecraft.util.IStringSerializable;

public enum CogType implements IStringSerializable {
	WOODEN(0, "wooden", 0, 0),
	STONE(1, "stone", 0, 0),
	IRON(2, "iron", 0, 0),
	NONE(3, "none", 0, 0);
	
	int value;
	String name;
	float maxAngularVelocity;
	float frictionTorque;
	
	CogType(int value, String name, float maxAngularVelocity, float frictionTorque) {
		this.value = value;
		this.name= name;
		this.maxAngularVelocity = maxAngularVelocity;
		this.frictionTorque = frictionTorque;
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
	}

	public float getMaxAngularVelocity() {
		return maxAngularVelocity;
	}

	public float getFrictionTorque() {
		return frictionTorque;
	}
}
