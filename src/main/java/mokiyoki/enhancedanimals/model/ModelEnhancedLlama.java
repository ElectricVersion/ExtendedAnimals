package mokiyoki.enhancedanimals.model;

import com.mojang.blaze3d.platform.GlStateManager;
import mokiyoki.enhancedanimals.entity.EnhancedLlama;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ModelEnhancedLlama <T extends EnhancedLlama> extends EntityModel<T> {

    private Map<Integer, LlamaModelData> llamaModelDataCache = new HashMap<>();
    private int clearCacheTimer = 0;

    private final RendererModel chest1;
    private final RendererModel chest2;

    private boolean banana = false;
    private boolean suri = false;

    private final RendererModel nose;
    private final RendererModel headShaved;
    private final RendererModel neck;
    private final RendererModel neckWool0;
    private final RendererModel neckWool1;
    private final RendererModel neckWool2;
    private final RendererModel neckWool3;
    private final RendererModel neckWool4;
    private final RendererModel earsR;
    private final RendererModel earsL;
    private final RendererModel earsTopR;
    private final RendererModel earsTopL;
    private final RendererModel earsTopBananaR;
    private final RendererModel earsTopBananaL;
    private final RendererModel body;
    private final RendererModel bodyShaved;
    private final RendererModel body1;
    private final RendererModel body2;
    private final RendererModel body3;
    private final RendererModel body4;
    private final RendererModel tail;
    private final RendererModel tail1;
    private final RendererModel tail2;
    private final RendererModel tail3;
    private final RendererModel tail4;
    private final RendererModel leg1;
    private final RendererModel leg1Wool1;
    private final RendererModel leg1Wool2;
    private final RendererModel leg1Wool3;
    private final RendererModel leg1Wool4;
    private final RendererModel leg2;
    private final RendererModel leg2Wool1;
    private final RendererModel leg2Wool2;
    private final RendererModel leg2Wool3;
    private final RendererModel leg2Wool4;
    private final RendererModel leg3;
    private final RendererModel leg3Wool1;
    private final RendererModel leg3Wool2;
    private final RendererModel leg3Wool3;
    private final RendererModel leg3Wool4;
    private final RendererModel leg4;
    private final RendererModel leg4Wool1;
    private final RendererModel leg4Wool2;
    private final RendererModel leg4Wool3;
    private final RendererModel leg4Wool4;
    private final RendererModel toeOuterFrontR;
    private final RendererModel toeInnerFrontR;
    private final RendererModel toeOuterFrontL;
    private final RendererModel toeInnerFrontL;
    private final RendererModel toeOuterBackR;
    private final RendererModel toeInnerBackR;
    private final RendererModel toeOuterBackL;
    private final RendererModel toeInnerBackL;

    public ModelEnhancedLlama()
    {
        this.textureWidth = 96;
        this.textureHeight = 96;

        float xMove = -6.0F;
        float headAdjust = -2.0F;

//        this.head.setTextureOffset(28, 0);
//        this.head.addBox(-2.0F, -12.0F, -4.0F, 4, 4, 4, 0.0F); //nose
//        this.head.setRotationPoint(0, 5, -12.0F);

        this.nose = new RendererModel(this,28, 0);
        this.nose.addBox(-2.0F, 0.0F, -7.0F, 4, 4, 4, 0.0F); //nose
        this.nose.setRotationPoint(0, 0, 0);

        this.headShaved = new RendererModel(this, 0, 0);
        this.headShaved.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 6, 0.0F); //head and neck
        this.headShaved.setRotationPoint(0.0F, -11.0F, 1.0F);
//        this.headShaved.setTextureOffset(28, 0);
//        this.headShaved.addBox(-2.0F, -12.0F, -4.0F, 4, 4, 4, 0.0F); //nose

        this.neck = new RendererModel(this,0, 12);
        this.neck.addBox(-4.0F, -10.0F, -1.1F, 8, 12, 6, -1.0F); //head and neck
//        this.neck.addBox(-4.0F, -2.0F, -1.1F, 8, 6, 6, -1.0F); //head and neck
        this.neck.setRotationPoint(0, 5, -6);

        this.neckWool0 = new RendererModel(this, 0, 12);
        this.neckWool0.addBox(-4.0F, -8.0F, headAdjust, 8, 12, 6, 0.0F); //head and neck
//        this.neckWool0.setTextureOffset(0, 13);
//        this.neckWool0.addBox(-4.0F, 0.0F, headAdjust, 8, 4, 6, 0.0F); //head and neck

        this.neckWool1 = new RendererModel(this, 0, 12);
        this.neckWool1.addBox(-4.0F, -8.5F, headAdjust, 8, 12, 6, 0.5F); //head and neck
//        this.neckWool1.setTextureOffset(0, 13);
//        this.neckWool1.addBox(-4.0F, 0.5F, headAdjust, 8, 4, 6, 0.5F); //head and neck

        this.neckWool2 = new RendererModel(this, 0, 12);
        this.neckWool2.addBox(-4.0F, -7.5F, headAdjust, 8, 12, 6, 1.0F); //head and neck
//        this.neckWool2.setTextureOffset(0, 13);
//        this.neckWool2.addBox(-4.0F, 2.0F, headAdjust, 8, 4, 6, 1.0F); //head and neck

        this.neckWool3 = new RendererModel(this, 0, 12);
        this.neckWool3.addBox(-4.0F, -7.0F, headAdjust, 8, 12, 6, 1.5F); //head and neck
//        this.neckWool3.setTextureOffset(0, 13);
//        this.neckWool3.addBox(-4.0F, 3.0F, headAdjust, 8, 4, 6, 1.5F); //head and neck

        this.neckWool4 = new RendererModel(this, 0, 12);
        this.neckWool4.addBox(-4.0F, -6.5F, headAdjust, 8, 12, 6, 2.0F); //head and neck
//        this.neckWool4.setTextureOffset(0, 13);
//        this.neckWool4.addBox(-4.0F, 4.0F, headAdjust, 8, 4, 6, 2.0F); //head and neck

        this.earsR = new RendererModel(this, 44, 0);
        this.earsR.addBox(-4.0F, -6.0F, 0.0F, 3, 3, 2, 0.0F); //ear right

        this.earsL = new RendererModel(this, 54, 0);
        this.earsL.addBox(1.0F, -6.0F, 0.0F, 3, 3, 2, 0.0F); //ear left

        this.earsTopR = new RendererModel(this, 64, 0);
        this.earsTopR.addBox(-4.0F, -7.0F, 0.0F, 3, 1, 2, 0.0F); //ear right

        this.earsTopL = new RendererModel(this, 74, 0);
        this.earsTopL.addBox(1.0F, -7.0F, 0.0F, 3, 1, 2, 0.0F); //ear left

        this.earsTopBananaR = new RendererModel(this, 64, 0);
        this.earsTopBananaR.addBox(-3.5F, -7.0F, 0.0F, 3, 1, 2, 0.0F); //ear right
        this.earsTopBananaL = new RendererModel(this, 74, 0);
        this.earsTopBananaL.addBox(0.5F, -7.0F, 0.0F, 3, 1, 2, 0.0F); //ear left

        this.body = new RendererModel(this, 0, 39);
        this.body.addBox(-6.0F, 0.0F, 0.0F, 12, 10, 18);
        this.body.setRotationPoint(0.0F, 2.0F, -8.0F);

        this.bodyShaved = new RendererModel(this, 0, 39);
        this.bodyShaved.addBox(-6.0F, 1.0F, 0.0F, 12, 10, 18, -1.0F);
        this.bodyShaved.setRotationPoint(0.0F, 2.0F, -2.0F);

        this.body1 = new RendererModel(this, 0, 39);
        this.body1.addBox(-6.0F, 0.0F, 0.0F, 12, 10, 18, 0.5F);
        this.body1.setRotationPoint(0.0F, 2.0F, -2.0F);

        this.body2 = new RendererModel(this, 0, 39);
        this.body2.addBox(-6.0F, 0.0F, 0.0F, 12, 10, 18, 1.0F);
        this.body2.setRotationPoint(0.0F, 2.0F, -2.0F);

        this.body3 = new RendererModel(this, 0, 39);
        this.body3.addBox(-6.0F, 0.0F, 0.0F, 12, 10, 18, 1.5F);
        this.body3.setRotationPoint(0.0F, 2.0F, -2.0F);

        this.body4 = new RendererModel(this, 0, 39);
        this.body4.addBox(-6.0F, 0.0F, 0.0F, 12, 10, 18, 2.0F);
        this.body4.setRotationPoint(0.0F, 2.0F, -2.0F);

        this.chest1 = new RendererModel(this, 74, 44);
        this.chest1.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
        this.chest1.setRotationPoint(-8.5F, 3.0F, 4.0F);
        this.chest1.rotateAngleY = ((float)Math.PI / 2F);
        this.chest2 = new RendererModel(this, 74, 57);
        this.chest2.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
        this.chest2.setRotationPoint(5.5F, 3.0F, 4.0F);
        this.chest2.rotateAngleY = ((float)Math.PI / 2F);

        this.tail = new RendererModel(this, 42, 39);
        this.tail.addBox(-3.0F, -2.0F, 15.0F, 6, 6, 6);

        this.tail1 = new RendererModel(this, 42, 39);
        this.tail1.addBox(-3.0F, -2.0F, 15.0F, 6, 6, 6, 0.25F);

        this.tail2 = new RendererModel(this, 42, 39);
        this.tail2.addBox(-3.0F, -2.0F, 15.0F, 6, 6, 6, 0.5F);

        this.tail3 = new RendererModel(this, 42, 39);
        this.tail3.addBox(-3.0F, -2.0F, 15.0F, 6, 6, 6, 0.75F);

        this.tail4 = new RendererModel(this, 42, 39);
        this.tail4.addBox(-3.0F, -2.0F, 15.0F, 6, 6, 6, 1.0F);

        this.leg1 = new RendererModel(this, 0, 68);
        this.leg1.addBox(0.0F, 0.0F, 0.0F, 3, 11, 3);
        this.leg1.setRotationPoint(-5.0F, 12.0F,-7.0F);

        this.leg1Wool1 = new RendererModel(this, 0, 68);
        this.leg1Wool1.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.5F);
        this.leg1Wool1.setRotationPoint(-5.0F, 12.0F,-1.0F);

        this.leg1Wool2 = new RendererModel(this, 0, 68);
        this.leg1Wool2.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 1.0F);
        this.leg1Wool2.setRotationPoint(-5.0F, 12.0F,-1.0F);

        this.leg1Wool3 = new RendererModel(this, 0, 68);
        this.leg1Wool3.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 1.5F);
        this.leg1Wool3.setRotationPoint(-5.0F, 12.0F,-1.0F);

        this.leg1Wool4 = new RendererModel(this, 0, 68);
        this.leg1Wool4.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 2.0F);
        this.leg1Wool4.setRotationPoint(-5.0F, 12.0F,-1.0F);

        this.leg2 = new RendererModel(this, 12, 68);
        this.leg2.addBox(0.0F, 0.0F, 0.0F, 3, 11, 3);
        this.leg2.setRotationPoint(2.0F, 12.0F,-1.0F + xMove);

        this.leg2Wool1 = new RendererModel(this, 12, 68);
        this.leg2Wool1.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.5F);
        this.leg2Wool1.setRotationPoint(2.0F, 12.0F,-1.0F);

        this.leg2Wool2 = new RendererModel(this, 12, 68);
        this.leg2Wool2.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 1.0F);
        this.leg2Wool2.setRotationPoint(2.0F, 12.0F,-1.0F);

        this.leg2Wool3 = new RendererModel(this, 12, 68);
        this.leg2Wool3.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 1.5F);
        this.leg2Wool3.setRotationPoint(2.0F, 12.0F,-1.0F);

        this.leg2Wool4 = new RendererModel(this, 12, 68);
        this.leg2Wool4.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 2.0F);
        this.leg2Wool4.setRotationPoint(2.0F, 12.0F,-1.0F);

        this.leg3 = new RendererModel(this, 0, 82);
        this.leg3.addBox(0.0F, 0F, 0.0F, 3, 11, 3);
        this.leg3.setRotationPoint(-5.0F, 12.0F,12.0F + xMove);

        this.leg3Wool1 = new RendererModel(this, 0, 82);
        this.leg3Wool1.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.5F);
        this.leg3Wool1.setRotationPoint(-5.0F, 12.0F,12.0F);

        this.leg3Wool2 = new RendererModel(this, 0, 82);
        this.leg3Wool2.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 1.0F);
        this.leg3Wool2.setRotationPoint(-5.0F, 12.0F,12.0F);

        this.leg3Wool3 = new RendererModel(this, 0, 82);
        this.leg3Wool3.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 1.5F);
        this.leg3Wool3.setRotationPoint(-5.0F, 12.0F,12.0F);

        this.leg3Wool4 = new RendererModel(this, 0, 82);
        this.leg3Wool4.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 2.0F);
        this.leg3Wool4.setRotationPoint(-5.0F, 12.0F,12.0F);

        this.leg4 = new RendererModel(this, 12, 82);
        this.leg4.addBox(0.0F, 0.0F, 0.0F, 3, 11, 3);
        this.leg4.setRotationPoint(2.0F, 12.0F,12.0F + xMove);

        this.leg4Wool1 = new RendererModel(this, 12, 82);
        this.leg4Wool1.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.5F);
        this.leg4Wool1.setRotationPoint(2.0F, 12.0F,12.0F);

        this.leg4Wool2 = new RendererModel(this, 12, 82);
        this.leg4Wool2.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 1.0F);
        this.leg4Wool2.setRotationPoint(2.0F, 12.0F,12.0F);

        this.leg4Wool3 = new RendererModel(this, 12, 82);
        this.leg4Wool3.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 1.5F);
        this.leg4Wool3.setRotationPoint(2.0F, 12.0F,12.0F);

        this.leg4Wool4 = new RendererModel(this, 12, 82);
        this.leg4Wool4.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 2.0F);
        this.leg4Wool4.setRotationPoint(2.0F, 12.0F,12.0F);

        this.toeOuterFrontR = new RendererModel(this, 26, 70);
        this.toeOuterFrontR.addBox(-0.85F, 10.0F, -2.5F, 3, 3, 4, -0.75F);
        this.toeInnerFrontR = new RendererModel(this, 44, 70);
        this.toeInnerFrontR.addBox(0.75F, 10.0F, -2.5F, 3, 3, 4, -0.75F);

        this.toeOuterFrontL = new RendererModel(this, 62, 70);
        this.toeOuterFrontL.addBox(0.85F, 10.0F, -2.5F, 3, 3, 4, -0.75F);
        this.toeInnerFrontL = new RendererModel(this, 80, 70);
        this.toeInnerFrontL.addBox(-0.75F, 10.0F, -2.5F, 3, 3, 4, -0.75F);

        this.toeOuterBackR = new RendererModel(this, 26, 84);
        this.toeOuterBackR.addBox(-0.85F, 10.0F, -2.5F, 3, 3, 4, -0.75F);
        this.toeInnerBackR = new RendererModel(this, 44, 84);
        this.toeInnerBackR.addBox(0.75F, 10.0F, -2.5F, 3, 3, 4, -0.75F);

        this.toeOuterBackL = new RendererModel(this, 62, 84);
        this.toeOuterBackL.addBox(0.85F, 10.0F, -2.5F, 3, 3, 4, -0.75F);
        this.toeInnerBackL = new RendererModel(this, 80, 84);
        this.toeInnerBackL.addBox(-0.75F, 10.0F, -2.5F, 3, 3, 4, -0.75F);


//        leg1.addChild(toeOuterFrontR);
//        leg1.addChild(toeInnerFrontR);
//        leg2.addChild(toeOuterFrontL);
//        leg2.addChild(toeInnerFrontL);
//        leg3.addChild(toeOuterBackR);
//        leg3.addChild(toeInnerBackR);
//        leg4.addChild(toeOuterBackL);
//        leg4.addChild(toeInnerBackL);

//        head.addChild(nose);
        this.neck.addChild(headShaved);
        this.headShaved.addChild(nose);
        this.headShaved.addChild(earsL);
        this.headShaved.addChild(earsR);
        this.headShaved.addChild(earsTopL);
        this.headShaved.addChild(earsTopR);
        this.headShaved.addChild(earsTopBananaL);
        this.headShaved.addChild(earsTopBananaR);
    }

    @Override
    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        EnhancedLlama enhancedLlama = (EnhancedLlama) entityIn;

        LlamaModelData llamaModelData = getLlamaModelData(entityIn);

        int[] genes = llamaModelData.llamaGenes;
        int coatlength = llamaModelData.coatlength;
        boolean sleeping = llamaModelData.sleeping;

        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, coatlength, sleeping);

        float size = 1;

        if (genes[0] < 3) {
            size = size - 0.025F;
            if (genes[0] < 2) {
                size = size - 0.025F;
            }
        }
        if (genes[1] < 3) {
            size = size - 0.025F;
            if (genes[1] < 2) {
                size = size - 0.025F;
            }
        }
        if (genes[2] < 3) {
            size = size - 0.025F;
            if (genes[2] < 2) {
                size = size - 0.025F;
            }
        }
        if (genes[3] < 3) {
            size = size - 0.025F;
            if (genes[3] < 2) {
                size = size - 0.025F;
            }
        }

        // banana ears
        if (genes[18] != 1 && genes[19] != 1) {
            if (genes[18] == 2 || genes[19] == 2) {
                banana = true;
            }
        }

        if (genes[20] == 2 && genes[21] == 2) {
            suri = true;
        }

        if (this.isChild) {
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.6F, 0.6F, 0.6F);
            GlStateManager.translatef(0.0F, 15.0F * scale, 0.0F);

            this.neck.render(scale);

            if (banana){
                this.earsTopR.isHidden = true;
                this.earsTopL.isHidden = true;
            }else {
                this.earsTopBananaR.isHidden = true;
                this.earsTopBananaL.isHidden = true;
            }

            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 20.0F * scale, 0.0F);

            this.body.render(scale);
            this.tail.render(scale);

            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.7F, 0.5F);
            GlStateManager.translatef(0.0F, 10.0F * scale, 0.0F);

            this.leg1.render(scale);
            this.leg2.render(scale);
            this.leg3.render(scale);
            this.leg4.render(scale);
            if (!sleeping) {
                this.toeOuterFrontR.render(scale);
                this.toeInnerFrontR.render(scale);
                this.toeOuterFrontL.render(scale);
                this.toeInnerFrontL.render(scale);
                this.toeOuterBackR.render(scale);
                this.toeInnerBackR.render(scale);
                this.toeOuterBackL.render(scale);
                this.toeInnerBackL.render(scale);
            }

            GlStateManager.popMatrix();

        } else {

            GlStateManager.pushMatrix();
            GlStateManager.scalef(size, size, size);
            GlStateManager.translatef(0.0F, -1.5F + 1.5F / size, 0.0F);
//            this.earsR.render(scale);
//            this.earsL.render(scale);

            this.neck.render(scale);

            if (banana){
                this.earsTopR.isHidden = true;
                this.earsTopL.isHidden = true;
            }else {
                this.earsTopBananaR.isHidden = true;
                this.earsTopBananaL.isHidden = true;
            }

            this.leg1.render(scale);
            this.leg2.render(scale);
            this.leg3.render(scale);
            this.leg4.render(scale);
            if (!sleeping) {
                this.toeOuterFrontR.render(scale);
                this.toeInnerFrontR.render(scale);
                this.toeOuterFrontL.render(scale);
                this.toeInnerFrontL.render(scale);
                this.toeOuterBackR.render(scale);
                this.toeInnerBackR.render(scale);
                this.toeOuterBackL.render(scale);
                this.toeInnerBackL.render(scale);
            }

            if (enhancedLlama.hasChest()) {
                this.chest1.render(scale);
                this.chest2.render(scale);
            }

//            if (coatlength == -1 ) {
//
//            } else {

//                this.head.render(scale);

                if (coatlength == 0 ) {
                    this.neckWool0.render(scale);
                    this.body.render(scale);
                    this.tail.render(scale);
                } else if (coatlength == 1 ) {
                    this.neckWool1.render(scale);
                    this.body1.render(scale);
                    this.tail1.render(scale);
                    this.leg1Wool1.render(scale);
                    this.leg2Wool1.render(scale);
                    this.leg3Wool1.render(scale);
                    this.leg4Wool1.render(scale);
                } else if (coatlength == 2 ) {
                    this.neckWool1.render(scale);
                    this.body2.render(scale);
                    this.tail2.render(scale);
                    this.leg1Wool2.render(scale);
                    this.leg2Wool2.render(scale);
                    this.leg3Wool2.render(scale);
                    this.leg4Wool2.render(scale);
                } else if (coatlength == 3 ) {
                    this.neckWool3.render(scale);
                    this.body3.render(scale);
                    this.tail3.render(scale);
                    this.leg1Wool3.render(scale);
                    this.leg2Wool3.render(scale);
                    this.leg3Wool3.render(scale);
                    this.leg4Wool3.render(scale);
                } else if (coatlength == 4 ) {
                    this.neckWool4.render(scale);
                    this.body4.render(scale);
                    this.tail4.render(scale);
                    this.leg1Wool4.render(scale);
                    this.leg2Wool4.render(scale);
                    this.leg3Wool4.render(scale);
                    this.leg4Wool4.render(scale);
                } else {
                    //this.headShaved.render(scale);
                    this.bodyShaved.render(scale);
                    this.tail.render(scale);
                }
//            }
            GlStateManager.popMatrix();


        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, int coatLength, boolean sleeping) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.neck.rotateAngleY = netHeadYaw * 0.017453292F;

        LlamaModelData llamaModelData = getLlamaModelData(entityIn);

        int[] sharedGenes = llamaModelData.llamaGenes;

//        this.body.rotateAngleX = ((float)Math.PI / 2F);
        float slep = 1.0F;
        if (!sleeping) {
            this.neck.rotateAngleX = headPitch * 0.017453292F;
            this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }

        copyModelAngles(neck, neckWool0);
        copyModelAngles(neck, neckWool1);

        copyModelAngles(body, bodyShaved);
        copyModelAngles(body, body1);

        copyModelAngles(leg1, leg1Wool1);
        copyModelAngles(leg2, leg2Wool1);
        copyModelAngles(leg3, leg3Wool1);
        copyModelAngles(leg4, leg4Wool1);

        copyModelAngles(body, tail);
        copyModelAngles(body, tail1);

        if ( suri && coatLength >= 0 ) {
            if (coatLength >= 1) {
                this.neckWool1.rotationPointY = this.neck.rotationPointY + (coatLength/2.0F);
                this.body1.rotationPointY = this.body.rotationPointY + (coatLength/2.0F);
                this.tail1.rotationPointY = this.body.rotationPointY + (coatLength/3.0F);
                this.leg1Wool1.rotationPointY = this.leg1.rotationPointY + (coatLength/2.0F);
                this.leg2Wool1.rotationPointY = this.leg2.rotationPointY + (coatLength/2.0F);
                this.leg3Wool1.rotationPointY = this.leg3.rotationPointY + (coatLength/2.0F);
                this.leg4Wool1.rotationPointY = this.leg4.rotationPointY + (coatLength/2.0F);
            }
            if (sleeping) {
                neckWool1.rotationPointY = neckWool1.rotationPointY - 1.0F;
            }
            copyModelAngles(neckWool1, neckWool2);
            copyModelAngles(neckWool1, neckWool3);
            copyModelAngles(neckWool1, neckWool4);
        }

//        copyModelAngles(head, earsR);
//        copyModelAngles(head, earsL);
//        copyModelAngles(head, earsTopR);
//        copyModelAngles(head, earsTopL);
//        copyModelAngles(head, earsTopBananaR);
//        copyModelAngles(head, earsTopBananaL);

        copyModelAngles(body, body2);
        copyModelAngles(body, body3);
        copyModelAngles(body, body4);

        copyModelAngles(tail1, tail2);
        copyModelAngles(tail1, tail3);
        copyModelAngles(tail1, tail4);

        //TODO fix the toes so they angle properly and maintain the angle while they walk

        copyModelAngles(leg1Wool1, leg1Wool2);
        copyModelAngles(leg1Wool1, leg1Wool3);
        copyModelAngles(leg1Wool1, leg1Wool4);
        copyModelAngles(leg1, toeOuterFrontR);
        copyModelAngles(leg1, toeInnerFrontR);


        copyModelAngles(leg2Wool1, leg2Wool2);
        copyModelAngles(leg2Wool1, leg2Wool3);
        copyModelAngles(leg2Wool1, leg2Wool4);
        copyModelAngles(leg2, toeOuterFrontL);
        copyModelAngles(leg2, toeInnerFrontL);

        copyModelAngles(leg3Wool1, leg3Wool2);
        copyModelAngles(leg3Wool1, leg3Wool3);
        copyModelAngles(leg3Wool1, leg3Wool4);
        copyModelAngles(leg3, toeOuterBackR);
        copyModelAngles(leg3, toeInnerBackR);

        copyModelAngles(leg4Wool1, leg4Wool2);
        copyModelAngles(leg4Wool1, leg4Wool3);
        copyModelAngles(leg4Wool1, leg4Wool4);
        copyModelAngles(leg4, toeOuterBackL);
        copyModelAngles(leg4, toeInnerBackL);

        setNoseRotations(sharedGenes);
    }

    private void setNoseRotations(int[] sharedGenes) {
        if (this.isChild) {
            this.nose.rotationPointZ = 1.5F;
        }
        else {
            this.nose.rotationPointZ = 0.0F;
        }

        //range from -12.1 to 10.5
        float noseHeight;
        if (sharedGenes[28] == 1) {
            noseHeight = 0.1F;
        } else if (sharedGenes[28] == 2) {
            noseHeight = 0.15F;
        } else if (sharedGenes[28] == 3) {
            noseHeight = 0.0F;
        } else {
            noseHeight = -0.1F;
        }

        if (sharedGenes[29] == 1) {
            noseHeight = noseHeight + 0.1F;
        } else if (sharedGenes[28] == 2) {
            noseHeight = noseHeight + 0.05F;
        } else if (sharedGenes[28] == 3) {
            noseHeight = noseHeight + 0.0F;
        } else {
            noseHeight = noseHeight - 0.1F;
        }

        if (sharedGenes[30] == 1) {
            noseHeight = noseHeight + 0.1F;
        } else if (sharedGenes[30] == 2) {
            noseHeight = noseHeight + 0.15F;
        } else if (sharedGenes[30] == 3) {
            noseHeight = noseHeight + 0.0F;
        } else {
            noseHeight = noseHeight - 0.1F;
        }

        if (sharedGenes[31] == 1) {
            noseHeight = noseHeight + 0.1F;
        } else if (sharedGenes[31] == 2) {
            noseHeight = noseHeight + 0.05F;
        } else if (sharedGenes[31] == 3) {
            noseHeight = noseHeight + 0.0F;
        } else {
            noseHeight = noseHeight - 0.1F;
        }

        if (sharedGenes[32] == 1) {
            noseHeight = noseHeight + 0.2F;
        } else if (sharedGenes[32] == 2) {
            noseHeight = noseHeight + 0.15F;
        } else if (sharedGenes[32] == 3) {
            noseHeight = noseHeight + 0.0F;
        } else if (sharedGenes[32] == 4) {
            noseHeight = noseHeight - 0.15F;
        } else {
            noseHeight = noseHeight - 0.2F;
        }

        if (sharedGenes[33] == 1) {
            noseHeight = noseHeight + 0.2F;
        } else if (sharedGenes[33] == 2) {
            noseHeight = noseHeight + 0.15F;
        } else if (sharedGenes[33] == 3) {
            noseHeight = noseHeight + 0.0F;
        } else if (sharedGenes[33] == 4) {
            noseHeight = noseHeight - 0.15F;
        } else {
            noseHeight = noseHeight - 0.2F;
        }

        this.nose.rotationPointY = -0.3F - noseHeight;
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    @Override
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        LlamaModelData llamaModelData = getLlamaModelData(entitylivingbaseIn);

        boolean sleeping = llamaModelData.sleeping;
        float onGround;

        this.body.rotationPointY = 2.0F;

        if (sleeping) {
            onGround = sleepingAnimation(isChild);
        } else {
            onGround = standingAnimation();
        }

        this.neck.rotationPointY = onGround;
    }

    private float sleepingAnimation(boolean isChild) {
        float onGround;

        if (isChild) {
            onGround = 20.0F;
            this.body.rotationPointY = 14.0F;
        } else {
            onGround = 20.0F;
            this.body.rotationPointY = 11.0F;
        }

        this.neck.rotateAngleX = 1.6F;
        this.neck.rotationPointZ = -9.0F;

        this.headShaved.rotateAngleX = -1.6F;

        this.leg1.rotateAngleX = 1.575F;
        this.leg1.rotateAngleY = 0.2F;
        this.leg2.rotateAngleX = 1.575F;
        this.leg2.rotateAngleY = -0.2F;
        this.leg3.rotateAngleX = -1.575F;
        this.leg4.rotateAngleX = -1.575F;

        this.chest1.rotationPointY = 12.0F;
        this.chest2.rotationPointY = 12.0F;

        this.leg1.setRotationPoint(-5.0F, 24.0F, -10.0F);
        this.leg2.setRotationPoint(2.0F, 24.0F, -10.0F);
        this.leg3.setRotationPoint(-5.0F, 21.0F, 10.0F);
        this.leg4.setRotationPoint(2.0F, 21.0F, 10.0F);
        return onGround;
    }

    private float standingAnimation() {
        float onGround;
        onGround = 5.0F;

        this.body.rotationPointY = 2.0F;
        this.leg1.rotateAngleY = 0.0F;
        this.leg3.rotateAngleY = 0.0F;

        this.neck.rotationPointZ = -10.0F;

        this.headShaved.rotateAngleX = 0.0F;

        this.chest1.rotationPointY = 3.0F;
        this.chest2.rotationPointY = 3.0F;

        this.leg1.setRotationPoint(-5.0F, 12.0F,-7.0F);
        this.leg2.setRotationPoint(2.0F, 12.0F,-7.0F);
        this.leg3.setRotationPoint(-5.0F, 12.0F,6.0F);
        this.leg4.setRotationPoint(2.0F, 12.0F,6.0F);

        return onGround;
    }

    public static void copyModelAngles(RendererModel source, RendererModel dest) {
        dest.rotateAngleX = source.rotateAngleX;
        dest.rotateAngleY = source.rotateAngleY;
        dest.rotateAngleZ = source.rotateAngleZ;
        dest.rotationPointX = source.rotationPointX;
        dest.rotationPointY = source.rotationPointY;
        dest.rotationPointZ = source.rotationPointZ;
    }

    private class LlamaModelData {
        int[] llamaGenes;
        int coatlength;
        boolean sleeping;
        int lastAccessed = 0;
//        int dataReset = 0;
    }

    private LlamaModelData getLlamaModelData(T enhancedLlama) {
        clearCacheTimer++;
        if(clearCacheTimer > 100000) {
            llamaModelDataCache.values().removeIf(value -> value.lastAccessed==1);
            for (LlamaModelData llamaModelData : llamaModelDataCache.values()){
                llamaModelData.lastAccessed = 1;
            }
            clearCacheTimer = 0;
        }

        if (llamaModelDataCache.containsKey(enhancedLlama.getEntityId())) {
            LlamaModelData llamaModelData = llamaModelDataCache.get(enhancedLlama.getEntityId());
            llamaModelData.lastAccessed = 0;
//            llamaModelData.dataReset++;
//            if (llamaModelData.dataReset > 5000) {
//                llamaModelData.dataReset = 0;
//            }
            llamaModelData.coatlength = enhancedLlama.getCoatLength();
            llamaModelData.sleeping = enhancedLlama.isAnimalSleeping();

            return llamaModelData;
        } else {
            LlamaModelData llamaModelData = new LlamaModelData();
            llamaModelData.llamaGenes = enhancedLlama.getSharedGenes();
            llamaModelData.coatlength = enhancedLlama.getCoatLength();
            llamaModelData.sleeping = enhancedLlama.isAnimalSleeping();

            llamaModelDataCache.put(enhancedLlama.getEntityId(), llamaModelData);

            return llamaModelData;
        }
    }

}
