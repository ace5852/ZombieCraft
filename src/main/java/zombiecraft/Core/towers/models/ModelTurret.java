// Date: 8/17/2013 10:13:27 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package zombiecraft.Core.towers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTurret extends ModelBase
{
  //fields
    ModelRenderer base;
    ModelRenderer armbottom;
    ModelRenderer head;
    ModelRenderer armtop;
    ModelRenderer barrel0;
    ModelRenderer barrel0Piece;
  
  public ModelTurret()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      base = new ModelRenderer(this, 0, 18);
      base.addBox(0F, 0F, 0F, 12, 1, 12);
      base.setRotationPoint(-6F, 23F, -6F);
      base.setTextureSize(64, 32);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      armbottom = new ModelRenderer(this, 20, 0);
      armbottom.addBox(0F, 0F, 0F, 4, 9, 2);
      armbottom.setRotationPoint(-2F, 17F, 3F);
      armbottom.setTextureSize(64, 32);
      armbottom.mirror = true;
      setRotation(armbottom, -0.7853982F, 0F, 0F);
      head = new ModelRenderer(this, 0, 0);
      head.addBox(0F, 0F, 0F, 4, 3, 6);
      head.setRotationPoint(-2F, 15F, 2F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, -3.149738F, 0F, 0F);
      armtop = new ModelRenderer(this, 4, 9);
      armtop.addBox(0F, 0F, 0F, 2, 6, 2);
      armtop.setRotationPoint(-1F, 15F, -2F);
      armtop.setTextureSize(64, 32);
      armtop.mirror = true;
      setRotation(armtop, 0.6981317F, 0F, 0F);
      barrel0 = new ModelRenderer(this, 12, 9);
      barrel0.addBox(0F, 0F, 0F, 1, 1, 3);
      barrel0.setRotationPoint(-0.5F, 13F, -7F);
      barrel0.setTextureSize(64, 32);
      barrel0.mirror = true;
      setRotation(barrel0, 0F, 0F, 0F);
      barrel0Piece = new ModelRenderer(this, 12, 13);
      barrel0Piece.addBox(0F, 0F, 0F, 2, 2, 1);
      barrel0Piece.setRotationPoint(-1F, 12.5F, -5F);
      barrel0Piece.setTextureSize(64, 32);
      barrel0Piece.mirror = true;
      setRotation(barrel0Piece, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    base.render(f5);
    armbottom.render(f5);
    head.render(f5);
    armtop.render(f5);
    barrel0.render(f5);
    barrel0Piece.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}