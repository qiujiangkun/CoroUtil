package extendedrenderer.particle.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityIconFX extends EntityRotFX
{
    public EntityIconFX(World par1World, double par2, double par4, double par6, IIcon par8Item)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.setParticleIcon(par8Item);
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleGravity = 0.1F;//Block.blockSnow.blockParticleGravity;
        //this.particleScale /= 2.0F;
        particleTextureJitterX = 3;
        particleTextureJitterY = 3;
    }

    public EntityIconFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, IIcon par14Item)
    {
        this(par1World, par2, par4, par6, par14Item);
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += par8;
        this.motionY += par10;
        this.motionZ += par12;
    }

    public int getFXLayer()
    {
        return renderOrder != -1 ? renderOrder : 2;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f6 = ((float)this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
        float f7 = f6 + 0.015609375F;
        float f8 = ((float)this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
        float f9 = f8 + 0.015609375F;
        float f10 = 0.1F * this.particleScale;

        if (this.particleIcon != null)
        {
        	f6 = particleIcon.getMinU();
            f7 = particleIcon.getMaxU();
            f8 = particleIcon.getMinV();
            f9 = particleIcon.getMaxV();
            /*f6 = this.particleIcon.getInterpolatedU((double)(this.particleTextureJitterX / 4.0F * 16.0F));
            f7 = this.particleIcon.getInterpolatedU((double)((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
            f8 = this.particleIcon.getInterpolatedV((double)(this.particleTextureJitterY / 4.0F * 16.0F));
            f9 = this.particleIcon.getInterpolatedV((double)((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));*/
        }

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)par2 - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)par2 - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)par2 - interpPosZ);
        
        Minecraft mc = Minecraft.getMinecraft();
        float br = ((0.9F + (mc.gameSettings.gammaSetting * 0.1F)) - (mc.theWorld.calculateSkylightSubtracted(par2) * 0.01F)) * mc.theWorld.getSunBrightness(1F);
        /*if (mc.theWorld.getTotalWorldTime() % 20 == 0) {
        	System.out.println("brightness: " + br);
        }*/
        
        br = 0.55F * Math.max(0.01F, br) * (2F);
        
        float f14 = br;//brightness;
        //par1Tessellator.setColorOpaque_F(f14 * this.particleRed, f14 * this.particleGreen, f14 * this.particleBlue);
        par1Tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, Math.max(0F, this.particleAlpha));
        par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f6, (double)f9);
        par1Tessellator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f6, (double)f8);
        par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f7, (double)f8);
        par1Tessellator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f7, (double)f9);
    }
}
