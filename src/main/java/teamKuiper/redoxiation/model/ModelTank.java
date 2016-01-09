package teamKuiper.redoxiation.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTank extends ModelBase
{
  //fields
    ModelRenderer top1;
    ModelRenderer top2;
    ModelRenderer top3;
    ModelRenderer inside1;
    ModelRenderer inside2;
    ModelRenderer floor1;
    ModelRenderer floor2;
    ModelRenderer floor3;
    ModelRenderer pillar1;
    ModelRenderer pillar2;
    ModelRenderer pillar3;
    ModelRenderer pillar4;
  
  public ModelTank()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      top1 = new ModelRenderer(this, 0, 0);
      top1.addBox(-5F, 0F, -7F, 10, 1, 14);
      top1.setRotationPoint(0F, 8F, 0F);
      top1.setTextureSize(128, 128);
      top1.mirror = true;
      setRotation(top1, 0F, 0F, 0F);
      top2 = new ModelRenderer(this, 0, 16);
      top2.addBox(-7F, 0F, -5F, 14, 1, 10);
      top2.setRotationPoint(0F, 8F, 0F);
      top2.setTextureSize(128, 128);
      top2.mirror = true;
      setRotation(top2, 0F, 0F, 0F);
      top3 = new ModelRenderer(this, 0, 28);
      top3.addBox(-6F, 0F, -6F, 12, 1, 12);
      top3.setRotationPoint(0F, 8F, 0F);
      top3.setTextureSize(128, 128);
      top3.mirror = true;
      setRotation(top3, 0F, 0F, 0F);
      inside1 = new ModelRenderer(this, 49, 0);
      inside1.addBox(-7F, 0F, -5F, 14, 14, 10);
      inside1.setRotationPoint(0F, 9F, 0F);
      inside1.setTextureSize(128, 128);
      inside1.mirror = true;
      setRotation(inside1, 0F, 0F, 0F);
      inside2 = new ModelRenderer(this, 49, 25);
      inside2.addBox(-5F, 0F, -7F, 10, 14, 14);
      inside2.setRotationPoint(0F, 9F, 0F);
      inside2.setTextureSize(128, 128);
      inside2.mirror = true;
      setRotation(inside2, 0F, 0F, 0F);
      floor1 = new ModelRenderer(this, 0, 42);
      floor1.addBox(-5F, 0F, -7F, 10, 1, 14);
      floor1.setRotationPoint(0F, 23F, 0F);
      floor1.setTextureSize(128, 128);
      floor1.mirror = true;
      setRotation(floor1, 0F, 0F, 0F);
      floor2 = new ModelRenderer(this, 0, 58);
      floor2.addBox(-7F, 0F, -5F, 14, 1, 10);
      floor2.setRotationPoint(0F, 23F, 0F);
      floor2.setTextureSize(128, 128);
      floor2.mirror = true;
      setRotation(floor2, 0F, 0F, 0F);
      floor3 = new ModelRenderer(this, 0, 70);
      floor3.addBox(-6F, 0F, -6F, 12, 1, 12);
      floor3.setRotationPoint(0F, 23F, 0F);
      floor3.setTextureSize(128, 128);
      floor3.mirror = true;
      setRotation(floor3, 0F, 0F, 0F);
      pillar1 = new ModelRenderer(this, 49, 54);
      pillar1.addBox(0F, 0F, 0F, 1, 14, 1);
      pillar1.setRotationPoint(5F, 9F, 5F);
      pillar1.setTextureSize(128, 128);
      pillar1.mirror = true;
      setRotation(pillar1, 0F, 0F, 0F);
      pillar2 = new ModelRenderer(this, 54, 54);
      pillar2.addBox(-1F, 0F, 0F, 1, 14, 1);
      pillar2.setRotationPoint(-5F, 9F, 5F);
      pillar2.setTextureSize(128, 128);
      pillar2.mirror = true;
      setRotation(pillar2, 0F, 0F, 0F);
      pillar3 = new ModelRenderer(this, 59, 54);
      pillar3.addBox(0F, 0F, -1F, 1, 14, 1);
      pillar3.setRotationPoint(5F, 9F, -5F);
      pillar3.setTextureSize(128, 128);
      pillar3.mirror = true;
      setRotation(pillar3, 0F, 0F, 0F);
      pillar4 = new ModelRenderer(this, 64, 54);
      pillar4.addBox(-1F, 0F, -1F, 1, 14, 1);
      pillar4.setRotationPoint(-5F, 9F, -5F);
      pillar4.setTextureSize(128, 128);
      pillar4.mirror = true;
      setRotation(pillar4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    top1.render(f5);
    top2.render(f5);
    top3.render(f5);
    inside1.render(f5);
    inside2.render(f5);
    floor1.render(f5);
    floor2.render(f5);
    floor3.render(f5);
    pillar1.render(f5);
    pillar2.render(f5);
    pillar3.render(f5);
    pillar4.render(f5);
  }

    public void renderModel(float f, float f1) {
        setRotation(top1, 0F, f1, 0F);
        setRotation(top2, 0F, f1, 0F);
        setRotation(top3, 0F, f1, 0F);
        setRotation(inside1, 0F, f1, 0F);
        setRotation(inside2, 0F, f1, 0F);
        setRotation(floor1, 0F, f1, 0F);
        setRotation(floor2, 0F, f1, 0F);
        setRotation(floor3, 0F, f1, 0F);
        setRotation(pillar1, 0F, f1, 0F);
        setRotation(pillar2, 0F, f1, 0F);
        setRotation(pillar3, 0F, f1, 0F);
        setRotation(pillar4, 0F, f1, 0F);
        top1.render(f);
        top2.render(f);
        top3.render(f);
        inside1.render(f);
        inside2.render(f);
        floor1.render(f);
        floor2.render(f);
        floor3.render(f);
        pillar1.render(f);
        pillar2.render(f);
        pillar3.render(f);
        pillar4.render(f);
    }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
