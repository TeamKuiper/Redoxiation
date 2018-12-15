package teamKuiper.redoxiation.blocks.cog;

import teamKuiper.redoxiation.blocks.IVariantType;

public enum CogType implements IVariantType {
	WOODEN(0, "wooden", 0, 0),
	STONE(1, "stone", 0, 0),
	IRON(2, "iron", 0, 0),
	NONE(3, "none", 0, 0);
	
	int metadata;
	String name;
	float maxAngularVelocity;
	float frictionTorque;
	
	CogType(int metadata, String name, float maxAngularVelocity, float frictionTorque) {
		this.metadata = metadata;
		this.name= name;
		this.maxAngularVelocity = maxAngularVelocity;
		this.frictionTorque = frictionTorque;
	}
	
	public int getMetadata() {
		return metadata;
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
