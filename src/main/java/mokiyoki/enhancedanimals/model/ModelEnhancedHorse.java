package mokiyoki.enhancedanimals.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mokiyoki.enhancedanimals.config.EanimodCommonConfig;
import mokiyoki.enhancedanimals.entity.EnhancedHorse;
import mokiyoki.enhancedanimals.items.CustomizableCollar;
import mokiyoki.enhancedanimals.items.CustomizableSaddleEnglish;
import mokiyoki.enhancedanimals.items.CustomizableSaddleVanilla;
import mokiyoki.enhancedanimals.items.CustomizableSaddleWestern;
import mokiyoki.enhancedanimals.model.util.ModelHelper;
import mokiyoki.enhancedanimals.renderer.EnhancedRendererModelNew;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ModelEnhancedHorse <T extends EnhancedHorse> extends EntityModel<T> {
    private Map<Integer, HorseModelData> horseModelDataCache = new HashMap<>();

    private int clearCacheTimer = 0;

    private final ModelRenderer chest1;
    private final ModelRenderer chest2;

    private final ModelRenderer head;
    private final ModelRenderer noseArch0;
    private final ModelRenderer noseArch1;
    private final ModelRenderer noseArch2;
    private final ModelRenderer nose0;
    private final ModelRenderer nose1;
    private final ModelRenderer nose2;
    private final ModelRenderer eyeLeft;
    private final ModelRenderer eyeRight;
    private final ModelRenderer earL;
    private final ModelRenderer earR;
    private final ModelRenderer jaw0;
    private final ModelRenderer jaw1;
    private final ModelRenderer jaw2;
    private final ModelRenderer tongue;
    private final ModelRenderer maneJoiner;
    private final ModelRenderer neck;
    private final EnhancedRendererModelNew body;
    private final ModelRenderer tail;
    private final ModelRenderer leg1A;
    private final ModelRenderer leg1B;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer hock3;
    private final ModelRenderer hock4;
    private final ModelRenderer hoof1;
    private final ModelRenderer hoof2;
    private final ModelRenderer hoof3;
    private final ModelRenderer hoof4;
    private final EnhancedRendererModelNew saddle;
    private final EnhancedRendererModelNew saddleWestern;
    private final EnhancedRendererModelNew saddleEnglish;
    private final EnhancedRendererModelNew saddleHorn;
    private final EnhancedRendererModelNew saddlePomel;
    private final EnhancedRendererModelNew saddleSideL;
    private final EnhancedRendererModelNew stirrup2DWideL;
    private final EnhancedRendererModelNew stirrup2DWideR;
    private final EnhancedRendererModelNew stirrup3DNarrowL;
    private final EnhancedRendererModelNew stirrup3DNarrowR;
    private final EnhancedRendererModelNew stirrup;
    private final EnhancedRendererModelNew saddleSideR;
    private final EnhancedRendererModelNew saddlePad;
    private final ModelRenderer bridle;

    private final List<EnhancedRendererModelNew> saddles = new ArrayList<>();

    private Integer currentHorse = null;

    public ModelEnhancedHorse() {
        HorseModelData horseModelData = getHorseModelData();
        int heightMod = horseModelData.heightMod;
        this.textureWidth = 256;
        this.textureHeight = 256;

        this.head = new ModelRenderer(this, 0, 35);
        this.head.addBox(-3.5F, -0.5F, -6.51F, 7, 7, 7, -0.5F);

        this.noseArch0 = new ModelRenderer(this, 45, 0);
        this.noseArch0.addBox(-2.5F, -3.5F, -3.5F, 5, 4, 4, -0.5F);

        this.noseArch1 = new ModelRenderer(this, 45, 0);
        this.noseArch1.addBox(-2.5F, -4.5F, -3.5F, 5, 5, 4, -0.5F);

        this.noseArch2 = new ModelRenderer(this, 45, 0);
        this.noseArch2.addBox(-2.5F, -5.5F, -3.5F, 5, 6, 4, -0.5F);

        this.nose0 = new ModelRenderer(this, 45, 10);
        this.nose0.addBox(-2.5F, -3.6F, -3.6F, 5, 4, 4, -0.4F);

        this.nose1 = new ModelRenderer(this, 45, 10);
        this.nose1.addBox(-2.5F, -4.6F, -3.6F, 5, 5, 4, -0.4F);

        this.nose2 = new ModelRenderer(this, 45, 10);
        this.nose2.addBox(-2.5F, -5.6F, -3.6F, 5, 6, 4, -0.4F);

//        this.head.setTextureOffset(0, 0);
//        this.head.addBox(-4.01F, 0.0F, -6.0F, 3, 4, 4, -1.0F);
//        this.head.addBox(1.01F, 0.0F, -6.0F, 3, 4, 4, -1.0F);

        this.head.setTextureOffset(94, 0);
        this.head.addBox(-1.5F, -1.5F, -4.5F, 3, 3, 6, -0.5F); //mane piece 1
        this.head.setRotationPoint(0.0F, -14.0F, -1.0F);

        this.eyeLeft = new ModelRenderer(this, 0, 0);
        this.eyeLeft.addBox(2.01F, 0.0F, -7.01F, 0, 4, 4, -1.0F);

        this.eyeRight = new ModelRenderer(this, 3, 0);
        this.eyeRight.addBox(-2.01F, 0.0F, -7.01F, 0, 4, 4, -1.0F);

        this.earL = new ModelRenderer(this, 6, 0);
        this.earL.addBox(-2.0F, -3.0F, -0.5F, 2, 3, 1);
        this.earL.setRotationPoint(-1.0F, 0.0F, -1.0F);

        this.earR = new ModelRenderer(this, 0, 0);
        this.earR.addBox(0.0F, -3.0F, -0.5F, 2, 3, 1);
        this.earR.setRotationPoint(1.0F, 0.0F, -1.0F);

        this.jaw0 = new ModelRenderer(this, 72,0);
        this.jaw0.addBox(-2.0F, -9.0F, -6.0F, 4, 7, 4, -0.1F);

        this.jaw1 = new ModelRenderer(this, 72,0);
        this.jaw1.addBox(-2.0F, -10.0F, -6.0F, 4, 8, 4, -0.1F);

        this.jaw2 = new ModelRenderer(this, 72,0);
        this.jaw2.addBox(-2.0F, -11.0F, -6.0F, 4, 9, 4, -0.1F);

        this.tongue = new ModelRenderer(this, 0, 15);
        this.tongue.addBox(-2.0F, 2.25F, -9.0F, 4, 1, 7, -0.11F);
        this.tongue.setRotationPoint(0.0F, 0.0F, -1.5F);

        this.maneJoiner = new ModelRenderer(this, 98, 9);
        this.maneJoiner.addBox(-1.5F, -1.49F, -0.49F, 3, 2, 2, -0.51F); //mane piece 2
        this.maneJoiner.setRotationPoint(0.0F, -13.0F, 0.0F);

        this.neck = new ModelRenderer(this, 69, 15);
        this.neck.addBox(-2.5F, -14.0F, -7.0F, 5, 17, 8, -1.0F);
        this.neck.setTextureOffset(97, 13);
        this.neck.addBox(-1.5F, -13.5F, -0.5F, 3, 18, 3, -0.5F); // mane piece 3
        this.neck.setRotationPoint(0.0F, 1.0F, -5.0F);

        this.body = new EnhancedRendererModelNew(this, 0, 0, "Body");
        this.body.addBox(-5.5F, -0.5F, -10.5F, 11, 11, 23, -0.5F);
        this.body.setRotationPoint(0.0F, 1.0F, 0.0F);

        this.tail = new ModelRenderer(this, 29, 15);
        this.tail.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
        this.tail.setRotationPoint(0.0F, 0.0F, 12.0F);

        this.leg1A = new ModelRenderer(this, 6, 53);
        this.leg1A.addBox(0.0F, 0.5F, 0.0F, 5, 8, 5, -1.0F);  //13
        this.leg1A.setRotationPoint(-6.0F, 9.5F, -9.0F);

        this.leg1B = new ModelRenderer(this, 6, 53);
        this.leg1B.addBox(0.0F, -1.0F, -2.0F, 5, 7, 5, -1.001F);  //13
        this.leg1B.setRotationPoint(0.0F, 7.5F, 2.0F);

        this.leg2 = new ModelRenderer(this, 26, 53);
        this.leg2.addBox(0.0F, 0.5F, 0.0F, 5, 13 - heightMod, 5, -1.0F);
        this.leg2.setRotationPoint(1.0F, 9.5F, -9.0F);

        this.leg3 = new ModelRenderer(this, 46, 53);
        this.leg3.addBox(0.0F, 2.0F, 0.0F, 5, 12 - heightMod, 5, -1.0F);  //12
        this.leg3.setRotationPoint(-6.0F, 9.5F, 8.0F);

        this.leg4 = new ModelRenderer(this, 66, 53);
        this.leg4.addBox(0.0F, 2.0F, 0.0F, 5, 12 - heightMod, 5, -1.0F);
        this.leg4.setRotationPoint(1.0F, 9.5F, 8.0F);

        this.hock3 = new ModelRenderer(this, 47, 41);
        this.hock3.addBox(0.75F, -4.0F, -0.75F, 4, 7, 5, 0.0F);
        this.hock3.setRotationPoint(-6.0F, 9.5F, 8.0F);

        this.hock4 = new ModelRenderer(this, 67, 41);
        this.hock4.addBox(0.25F, -4.0F, -0.75F, 4, 7, 5, 0.0F);
        this.hock4.setRotationPoint(1.0F, 9.5F, 8.0F);

        this.hoof1 = new ModelRenderer(this, 6, 71);
        this.hoof1.addBox(0.0F, -0.5F, -2.4F, 5, 3, 4, -0.5F);
        this.hoof1.setRotationPoint(0.0F, 4.75F, 0.5F);

        this.hoof2 = new ModelRenderer(this, 26, 71);
        this.hoof2.addBox(0.0F, 11.75F, 0.1F, 5, 3, 4, -0.5F);

        this.hoof3 = new ModelRenderer(this, 46, 71);
        this.hoof3.addBox(0.0F, 11.75F, 0.1F, 5, 3, 4, -0.5F);

        this.hoof4 = new ModelRenderer(this, 66, 71);
        this.hoof4.addBox(0.0F, 11.75F, 0.1F, 5, 3, 4, -0.5F);

        this.neck.addChild(head);
        this.neck.addChild(maneJoiner);
        this.head.addChild(earL);
        this.head.addChild(earR);
        this.head.addChild(jaw0);
        this.head.addChild(jaw1);
        this.head.addChild(jaw2);
        this.jaw0.addChild(tongue);
        this.jaw1.addChild(tongue);
        this.jaw2.addChild(tongue);
        this.head.addChild(eyeLeft);
        this.head.addChild(eyeRight);
        this.leg1A.addChild(this.leg1B);
        this.leg1B.addChild(this.hoof1);

            /**
             * Equipment stuff
             */

        this.chest1 = new ModelRenderer(this, 188, 0);
        this.chest1.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
        this.chest1.setRotationPoint(-8.0F, 1.0F, 8.0F);
        this.chest1.rotateAngleY = ((float)Math.PI / 2F);
        this.chest2 = new ModelRenderer(this, 188, 11);
        this.chest2.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
        this.chest2.setRotationPoint(5.0F, 1.0F, 8.0F);
        this.chest2.rotateAngleY = ((float)Math.PI / 2F);

        this.saddle = new EnhancedRendererModelNew(this, 194, 24, "Saddle");
        this.saddle.addBox(-8.0F, -1.0F, -6.0F, 16, 10, 15, -1.0F);

        this.saddleWestern = new EnhancedRendererModelNew(this, 210, 0, "WesternSaddle");
        this.saddleWestern.addBox(-5.0F, -2.0F, -5.0F, 10, 2, 13, 0.0F);
        this.saddleWestern.setTextureOffset(210, 15);
        this.saddleWestern.addBox(-4.0F, -3.0F, 5.0F, 8, 2, 4, 0.0F);
        this.saddleWestern.setTextureOffset(230, 15);
        this.saddleWestern.addBox(-3.5F, -4.0F, 8.0F, 7, 2, 2, 0.0F);

        this.saddleEnglish = new EnhancedRendererModelNew(this, 211, 1, "EnglishSaddle");
        this.saddleEnglish.addBox(-5.0F, -1.0F, -4.0F, 10, 2, 12, 0.0F);
        this.saddleEnglish.setTextureOffset(210, 15);
        this.saddleEnglish.addBox(-4.0F, -1.5F, 5.0F, 8, 2, 4, 0.0F);
        this.saddleEnglish.setTextureOffset(230, 15);
        this.saddleEnglish.addBox(-3.5F, -2.0F, 7.5F, 7, 2, 2, 0.0F);

        this.saddleHorn = new EnhancedRendererModelNew(this, 234, 19, "SaddleHorn");
        this.saddleHorn.addBox(-4.0F, -2.0F, -3.0F, 8, 2, 3, 0.0F);

        this.saddlePomel = new EnhancedRendererModelNew(this, 243, 0, "SaddlePomel");
        this.saddlePomel.addBox(-1.0F, -3.0F, -2.0F, 2, 4, 2, -0.25F);
        this.saddlePomel.setRotationPoint(0.0F, -2.0F, -2.0F);

        this.saddleSideL = new EnhancedRendererModelNew(this, 234, 49, "SaddleLeft");
        this.saddleSideL.addBox(0.0F, 0.0F, 0.0F, 3, 4, 8);

        this.saddleSideR = new EnhancedRendererModelNew(this, 234, 61, "SaddleRight");
        this.saddleSideR.addBox(-3.0F, 0.0F, 0.0F, 3, 4, 8);

        this.stirrup2DWideL = new EnhancedRendererModelNew(this, 248, 24, "2DStirrupL");
        this.stirrup2DWideL.addBox(0.0F, 0.0F, 0.0F, 0, 10, 4); // strap

        this.stirrup2DWideR = new EnhancedRendererModelNew(this, 248, 24, "2DStirrupR");
        this.stirrup2DWideR.addBox(0.0F, 0.0F, 0.0F, 0, 10, 4); // strap

        this.stirrup3DNarrowL = new EnhancedRendererModelNew(this, 249, 27, "3DStirrupL");
        this.stirrup3DNarrowL.addBox(-1.0F, 0.0F, 0.0F, 1, 10, 1); // strap

        this.stirrup3DNarrowR = new EnhancedRendererModelNew(this, 251, 27, "3DStirrupR");
        this.stirrup3DNarrowR.addBox(0.0F, 0.0F, 0.0F, 1, 10, 1);

        this.stirrup = new EnhancedRendererModelNew(this, 210, 0, "Stirrup");
        this.stirrup.addBox(-0.5F, 9.5F, -1.0F, 1, 1, 1);
        this.stirrup.setTextureOffset(214, 0);
        this.stirrup.addBox(-0.5F, 9.5F, 1.0F, 1, 1, 1);
        this.stirrup.setTextureOffset(210, 2);
        this.stirrup.addBox(-0.5F, 10.5F, -1.5F, 1, 3, 1);
        this.stirrup.setTextureOffset(214, 2);
        this.stirrup.addBox(-0.5F, 10.5F, 1.5F, 1, 3, 1);
        this.stirrup.setTextureOffset(211, 7);
        this.stirrup.addBox(-0.5F, 12.5F, -0.5F, 1, 1, 2);

        this.saddlePad = new EnhancedRendererModelNew(this, 194, 24, "SaddlePad");
        this.saddlePad.addBox(-8.0F, -1.0F, -6.0F, 16, 10, 15, -1.0F);

        this.body.addChild(saddle);
        this.saddle.addChild(stirrup3DNarrowL);
        this.saddle.addChild(stirrup3DNarrowR);
        this.saddleHorn.addChild(saddlePomel);

        //western
        this.body.addChild(saddleWestern);
        this.saddleWestern.addChild(saddleHorn);
        this.saddleWestern.addChild(saddleSideL);
        this.saddleWestern.addChild(saddleSideR);
        this.saddleWestern.addChild(saddlePad);
        this.saddleWestern.addChild(stirrup2DWideL);
        this.saddleWestern.addChild(stirrup2DWideR);
        this.stirrup2DWideL.addChild(stirrup);
        this.stirrup2DWideR.addChild(stirrup);
        //english
        this.body.addChild(saddleEnglish);
        this.saddleEnglish.addChild(saddleHorn);
        this.saddleEnglish.addChild(saddleSideL);
        this.saddleEnglish.addChild(saddleSideR);
        this.saddleEnglish.addChild(saddlePad);
        this.saddleEnglish.addChild(stirrup3DNarrowL);
        this.saddleEnglish.addChild(stirrup3DNarrowR);
        this.stirrup3DNarrowL.addChild(stirrup);
        this.stirrup3DNarrowR.addChild(stirrup);

        this.textureWidth = 128;
        this.textureHeight = 128;

        this.bridle = new ModelRenderer(this, 0, 53);
        this.bridle.addBox(-3.5F, -0.0F, -6.01F, 7, 6, 6, 0.05F);
        this.bridle.setTextureOffset(0, 40);
        this.bridle.addBox(-2.5F, -0.0F, -10.0F, 5, 6, 5, 0.05F);
        this.head.addChild(this.bridle);
        this.head.addChild(this.noseArch0);
        this.head.addChild(this.noseArch1);
        this.head.addChild(this.noseArch2);
        this.noseArch0.addChild(this.nose0);
        this.noseArch0.addChild(this.nose1);
        this.noseArch0.addChild(this.nose2);
        this.noseArch1.addChild(this.nose0);
        this.noseArch1.addChild(this.nose1);
        this.noseArch1.addChild(this.nose2);
        this.noseArch2.addChild(this.nose0);
        this.noseArch2.addChild(this.nose1);
        this.noseArch2.addChild(this.nose2);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        HorseModelData horseModelData = getHorseModelData();

        if (horseModelData.sleeping) {
            this.eyeLeft.showModel = false;
            this.eyeRight.showModel = false;
        } else {
            this.eyeLeft.showModel = true;
            this.eyeRight.showModel = true;
        }

        this.noseArch0.showModel = false;
        this.noseArch1.showModel = false;
        this.noseArch2.showModel = false;
        this.nose0.showModel = false;
        this.nose1.showModel = false;
        this.nose2.showModel = false;
        this.jaw0.showModel = false;
        this.jaw1.showModel = false;
        this.jaw2.showModel = false;
        if (horseModelData.faceShape >= 0) {
            switch (horseModelData.faceLength) {
                case 0:
                    this.noseArch0.showModel = true;
                    this.jaw0.showModel = true;
                    break;
                case 1:
                    this.noseArch1.showModel = true;
                    this.jaw1.showModel = true;
                    break;
                default:
                case 2:
                    this.noseArch2.showModel = true;
                    this.jaw2.showModel = true;
                    break;
            }
            this.nose0.showModel = true;
        } else {
            switch (horseModelData.faceLength) {
                case 0:
                    this.nose0.showModel = true;
                    this.jaw0.showModel = true;
                    break;
                case 1:
                    this.nose1.showModel = true;
                    this.jaw1.showModel = true;
                    break;
                default:
                case 2:
                    this.nose2.showModel = true;
                    this.jaw2.showModel = true;
                    break;
            }
            this.noseArch0.showModel = true;
        }

        this.chest1.showModel = false;
        this.chest2.showModel = false;
        if (horseModelData.hasChest) {
            this.chest1.showModel = true;
            this.chest2.showModel = true;
            this.chest1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            this.chest2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        }

        this.bridle.showModel = true;

//        this.head.render(scale);
        this.neck.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        renderBodyandSaddle(horseModelData.unrenderedModels, matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tail.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg1A.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.hock3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.hock4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
//        this.hoof1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.hoof2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.hoof3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.hoof4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

    }

    private void renderBodyandSaddle(List<String> unrenderedModels, MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
            Map<String, List<Float>> mapOfScale = new HashMap<>();
            float saddleScale = 0.75F;

            this.saddleWestern.showModel = false;
            this.saddleEnglish.showModel = false;
            this.saddle.showModel = false;
            this.saddlePomel.showModel = false;

//            float antiScale = 1.25F;
            List<Float> scalingsForSaddle = ModelHelper.createScalings(saddleScale,saddleScale,saddleScale, 0.0F, -saddleScale*0.01F, (saddleScale - 1.0F)*0.04F);
//            List<Float> scalingsForPad = createScalings(antiScale, 0.0F, -antiScale*0.01F, (antiScale - 1.0F)*0.04F);
//            mapOfScale.put("SaddlePad", scalingsForPad);sadd

            if (getHorseModelData().saddle.getItem() instanceof CustomizableSaddleWestern) {
                this.saddleWestern.showModel = true;
                this.saddlePomel.showModel = true;
                mapOfScale.put("WesternSaddle", scalingsForSaddle);
            } else if (getHorseModelData().saddle.getItem() instanceof CustomizableSaddleEnglish) {
                this.saddleEnglish.showModel = true;
                mapOfScale.put("EnglishSaddle", scalingsForSaddle);
            } else if (getHorseModelData().saddle.getItem() instanceof CustomizableSaddleVanilla){
                //vanilla saddle
                this.saddle.showModel = true;
                mapOfScale.put("Saddle", scalingsForSaddle);
            }
            this.body.render(matrixStackIn, bufferIn , mapOfScale, unrenderedModels, false, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        HorseModelData horseModelData = getCreateHorseModelData(entitylivingbaseIn);
        this.currentHorse = entitylivingbaseIn.getEntityId();
        int heightMod = horseModelData.heightMod;

        char[] uuidArry = horseModelData.uuidArray;

        if (uuidArry != null) {
            if (Character.isDigit(uuidArry[16])){
                if (uuidArry[16] - 48 == 0) {
                    //0
                    this.hock3.rotationPointZ = 8.5F;
                    this.hock4.rotationPointZ = 8.5F;
                } else if (uuidArry[16] - 48 == 1) {
                    //1
                    this.hock3.rotationPointZ = 8.4F;
                    this.hock4.rotationPointZ = 8.4F;
                } else if (uuidArry[16] - 48 == 2) {
                    //2
                    this.hock3.rotationPointZ = 8.3F;
                    this.hock4.rotationPointZ = 8.3F;
                } else if (uuidArry[16] - 48 == 3) {
                    //3
                    this.hock3.rotationPointZ = 8.2F;
                    this.hock4.rotationPointZ = 8.2F;
                } else if (uuidArry[16] - 48 == 4) {
                    //4
                    this.hock3.rotationPointZ = 8.1F;
                    this.hock4.rotationPointZ = 8.1F;
                } else if (uuidArry[16] - 48 == 5) {
                    //5
                    this.hock3.rotationPointZ = 8.0F;
                    this.hock4.rotationPointZ = 8.0F;
                } else if (uuidArry[16] - 48 == 6) {
                    //6
                    this.hock3.rotationPointZ = 7.9F;
                    this.hock4.rotationPointZ = 7.9F;
                } else if (uuidArry[16] - 48 == 7) {
                    //7
                    this.hock3.rotationPointZ = 7.8F;
                    this.hock4.rotationPointZ = 7.8F;
                } else if (uuidArry[16] - 48 == 8) {
                    //0
                    this.hock3.rotationPointZ = 8.5F;
                    this.hock4.rotationPointZ = 8.5F;
                } else {
                    //1
                    this.hock3.rotationPointZ = 8.4F;
                    this.hock4.rotationPointZ = 8.4F;
                }
            } else {
                char test = uuidArry[16];
                switch (test){
                    case 'a':
                        //2
                        this.hock3.rotationPointZ = 8.3F;
                        this.hock4.rotationPointZ = 8.3F;
                        break;
                    case 'b':
                        //3
                        this.hock3.rotationPointZ = 8.2F;
                        this.hock4.rotationPointZ = 8.2F;
                        break;
                    case 'c':
                        //4
                        this.hock3.rotationPointZ = 8.1F;
                        this.hock4.rotationPointZ = 8.1F;
                        break;
                    case 'd':
                        //5
                        this.hock3.rotationPointZ = 8.0F;
                        this.hock4.rotationPointZ = 8.0F;
                        break;
                    case 'e':
                        //6
                        this.hock3.rotationPointZ = 7.9F;
                        this.hock4.rotationPointZ = 7.9F;
                        break;
                    case 'f':
                        //7
                        this.hock3.rotationPointZ = 7.8F;
                        this.hock4.rotationPointZ = 7.8F;
                        break;
                }
            }

            if (Character.isDigit(uuidArry[17])){
                if (uuidArry[17] - 48 == 0) {
                    //a
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.5F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.5F;
                } else if (uuidArry[17] - 48 == 1) {
                    //b
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.35F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.35F;
                } else if (uuidArry[17] - 48 == 2) {
                    //c
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.25F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.25F;
                } else if (uuidArry[17] - 48 == 3) {
                    //d
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.15F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.15F;
                } else if (uuidArry[17] - 48 == 4) {
                    //e
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.05F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.05F;
                } else if (uuidArry[17] - 48 == 5) {
                    //f
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 0.95F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 0.95F;
                } else if (uuidArry[17] - 48 == 6) {
                    //g
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 0.85F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 0.85F;
                } else if (uuidArry[17] - 48 == 7) {
                    //h
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 0.75F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 0.75F;
                } else if (uuidArry[17] - 48 == 8) {
                    //a
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.5F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.5F;
                } else {
                    //b
                    this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.35F;
                    this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.35F;
                }
            } else {
                char test = uuidArry[17];
                switch (test){
                    case 'a':
                        //c
                        this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.25F;
                        this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.25F;
                        break;
                    case 'b':
                        //d
                        this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.15F;
                        this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.15F;
                        break;
                    case 'c':
                        //e
                        this.leg3.rotationPointZ = this.hock3.rotationPointZ + 1.05F;
                        this.leg4.rotationPointZ = this.hock4.rotationPointZ + 1.05F;
                        break;
                    case 'd':
                        //f
                        this.leg3.rotationPointZ = this.hock3.rotationPointZ + 0.95F;
                        this.leg4.rotationPointZ = this.hock4.rotationPointZ + 0.95F;
                        break;
                    case 'e':
                        //g
                        this.leg3.rotationPointZ = this.hock3.rotationPointZ + 0.85F;
                        this.leg4.rotationPointZ = this.hock4.rotationPointZ + 0.85F;
                        break;
                    case 'f':
                        //h
                        this.leg3.rotationPointZ = this.hock3.rotationPointZ + 0.75F;
                        this.leg4.rotationPointZ = this.hock4.rotationPointZ + 0.75F;
                        break;
                }
            }
        }

        this.tongue.rotateAngleX = (float)Math.PI * -0.5F;

        /**
         * other
         */

        this.leg1B.rotateAngleX = (float)Math.PI * 0.4F;
        this.hoof1.rotateAngleX = (float)Math.PI * 0.2F;

        /**
         *  experimental head variations
         */

//        float noseAngle = horseModelData.faceShape; // dish-roman [-0.175, 0.075]
        float noseAngle = 0.0F; // dish-roman [-0.175, 0.075]


        this.noseArch0.setRotationPoint(0,0, -6.0F);
//        this.nose0.rotationPointZ = noseAngle < 0 ? (-3.0F - (noseAngle * 5.0F)) : -3.0F;

        this.noseArch0.rotateAngleX = (float)Math.PI/2.0F + (float)Math.PI * (Math.abs(noseAngle));
        this.nose0.rotateAngleX = ((float)Math.PI * (noseAngle < 0 ? (3.0F*noseAngle/2.0F) : (2.0F*noseAngle/3.0F)));
//        this.jaw0.rotateAngleX = (-0.175F + (noseAngle < 0 ? noseAngle/2.0F : ((float)Math.PI * (2.0F*noseAngle/3.0F)))) + (float)Math.PI/2.0F;

        ModelHelper.copyModelRotations(noseArch0, noseArch1, noseArch2);
        ModelHelper.copyModelRotations(nose0, nose1, nose2);

        this.noseArch1.rotationPointZ = this.noseArch0.rotationPointZ;
        this.noseArch2.rotationPointZ = this.noseArch0.rotationPointZ;

        if (noseAngle >= 0) {
            this.nose0.rotationPointY = -3.0F + -horseModelData.faceLength;
        } else {
            this.nose0.rotationPointY = -3.0F + (-noseAngle * 5.0F);
        }

        this.nose1.setRotationPoint(0.0F, this.nose0.rotationPointY, 0);
        this.nose2.setRotationPoint(0.0F, this.nose0.rotationPointY, 0);

        this.jaw0.rotateAngleX = (float)Math.PI * 0.45F;
//        this.jaw0.rotateAngleX = (float)Math.PI * 0.41F;
//        this.jaw0.rotationPointZ = -1.25F;
        this.jaw0.rotationPointZ = -2.0F;

        this.jaw1.rotationPointZ = this.jaw0.rotationPointZ;
        this.jaw2.rotationPointZ = this.jaw0.rotationPointZ;
        this.jaw1.rotateAngleX = this.jaw0.rotateAngleX;
        this.jaw2.rotateAngleX = this.jaw0.rotateAngleX;

//        int jawLengthMod = horseModelData.faceShape > 0 ? (int)(2.9999F*(-horseModelData.faceShape)/0.175F) : 0;
//
//        jawLengthMod = horseModelData.faceLength - jawLengthMod;
//
//        switch (jawLengthMod) {
//            default:
//            case 0 :
//                this.tongue.rotationPointZ = -2.0F - (noseAngle >= 0 ? noseAngle * -2.0F : noseAngle * 2.75F);
//                this.jaw0.setRotationPoint(0.0F, 0.70F - (noseAngle >= 0 ? 0.0F : noseAngle), this.tongue.rotationPointZ);
//                break;
//            case 1 :
//                this.tongue.rotationPointZ = -3.0F - (noseAngle >= 0 ? 0.0F : noseAngle * 2.75F);
//                this.jaw1.setRotationPoint(0.0F, 0.70F - (noseAngle >= 0 ? 0.0F : noseAngle), this.tongue.rotationPointZ);
//                break;
//            case 2 :
//                this.tongue.rotationPointZ = -4.0F - (noseAngle >= 0 ? 0.0F : noseAngle * 2.75F);
//                this.jaw2.setRotationPoint(0.0F, 0.70F - (noseAngle >= 0 ? 0.0F : noseAngle), this.tongue.rotationPointZ);
//                break;
//
//        }

        /**
         *  experimental size variations
         */

        this.body.rotationPointY = 1.0F + heightMod;
        this.neck.rotationPointY = 1.0F + heightMod + (int)(heightMod/2);
        this.neck.rotationPointZ = -5.0F  + (int)(heightMod/2);
        this.hock3.rotationPointY = 9.5F + heightMod;
        this.hock4.rotationPointY = 9.5F + heightMod;
        this.tail.rotationPointY = 0.0F + heightMod;
        this.leg1A.rotationPointY = 9.5F + heightMod;
        this.leg2.rotationPointY = 9.5F + heightMod;
        this.leg3.rotationPointY = 9.5F + heightMod;
        this.leg4.rotationPointY = 9.5F + heightMod;
        this.chest1.rotationPointY = 1.0F + heightMod;
        this.chest2.rotationPointY = 1.0F + heightMod;

    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        HorseModelData horseModelData = getHorseModelData();

        if (horseModelData!=null) {
            int heightMod = horseModelData.heightMod;
            this.neck.rotateAngleX = headPitch * 0.017453292F + 0.8F;
            this.neck.rotateAngleY = ((netHeadYaw * 0.017453292F) * 0.40F);
            this.head.rotateAngleY = ((netHeadYaw * 0.017453292F) * 0.14F);
            this.maneJoiner.rotateAngleY = ((netHeadYaw * 0.017453292F) * 0.07F);

            this.tail.rotateAngleX = 0.6F;

            this.leg1A.rotateAngleX = MathHelper.cos(limbSwing * 0.3332F) * 1.4F * limbSwingAmount;
            this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.3332F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.hock3.rotateAngleX = MathHelper.cos(limbSwing * 0.3332F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.3332F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.hock4.rotateAngleX = MathHelper.cos(limbSwing * 0.3332F) * 1.4F * limbSwingAmount;
            this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.3332F) * 1.4F * limbSwingAmount;

//        ModelHelper.copyModelPositioning(leg1A, hoof1);
            ModelHelper.copyModelPositioning(leg2, hoof2);
            ModelHelper.copyModelPositioning(leg3, hoof3);
            ModelHelper.copyModelPositioning(leg4, hoof4);

            this.hoof1.rotationPointY = this.hoof1.rotationPointY - heightMod;
            this.hoof2.rotationPointY = this.hoof2.rotationPointY - heightMod;
            this.hoof3.rotationPointY = this.hoof3.rotationPointY - heightMod;
            this.hoof4.rotationPointY = this.hoof4.rotationPointY - heightMod;

            this.earL.rotateAngleZ = -0.15F;
            this.earR.rotateAngleZ = 0.15F;


            Item saddle = horseModelData.saddle.getItem();
            if (saddle instanceof CustomizableSaddleWestern) {
                this.saddleSideL.setRotationPoint(5.0F, -1.0F, -5.25F);
                this.saddleSideR.setRotationPoint(-5.0F, -1.0F, -5.25F);
                this.saddleHorn.setRotationPoint(0.0F, -2.0F, -2.0F);
                this.saddleHorn.rotateAngleX = (float) Math.PI / 8.0F;
                this.saddlePomel.setRotationPoint(0.0F, -1.5F, -0.5F);
                this.saddlePomel.rotateAngleX = -0.2F;
                this.stirrup2DWideL.setRotationPoint(7.5F, 0.0F, -3.5F);
                this.stirrup2DWideR.setRotationPoint(-7.5F, 0.0F, -3.5F);
            } else if (saddle instanceof CustomizableSaddleEnglish) {
                this.saddleSideL.setRotationPoint(3.25F, -0.5F, -4.0F);
                this.saddleSideR.setRotationPoint(-3.25F, -0.5F, -4.0F);
                this.saddleHorn.setRotationPoint(0.0F, -1.0F, -1.0F);
                this.saddleHorn.rotateAngleX = (float) Math.PI / 4.5F;
                this.stirrup3DNarrowL.setRotationPoint(7.25F, -0.25F, -1.5F);
                this.stirrup3DNarrowR.setRotationPoint(-7.25F, -0.25F, -1.5F);
            } else if (saddle instanceof CustomizableSaddleVanilla) {
                this.stirrup3DNarrowL.setRotationPoint(8.0F, 0.0F, 0.0F);
                this.stirrup3DNarrowR.setRotationPoint(-8.0F, 0.0F, 0.0F);
            }
        }
    }

    private class HorseModelData {
        int[] horseGenes;
        char[] uuidArray;
        String birthTime;
        float size = 1.0F;
        boolean isFemale;
        int lastAccessed = 0;
        boolean sleeping = false;
        int blink = 0;
        long clientGameTime = 0;
        List<String> unrenderedModels = new ArrayList<>();
        ItemStack saddle;
        boolean bridle = false;
        boolean harness = false;
        boolean collar = false;
        boolean hasChest = false;
        int heightMod = 0; //[-2 to 5]
        float faceShape = 0.0F;
        int faceLength = 0;
        int adultAge;
    }

    private HorseModelData getHorseModelData() {
        if (this.currentHorse == null || !horseModelDataCache.containsKey(this.currentHorse)) {
            return new HorseModelData();
        }
        return horseModelDataCache.get(this.currentHorse);
    }

    private HorseModelData getCreateHorseModelData(T enhancedHorse) {
        clearCacheTimer++;
        if(clearCacheTimer > 50000) {
            horseModelDataCache.values().removeIf(value -> value.lastAccessed==1);
            for (HorseModelData horseModelData : horseModelDataCache.values()){
                horseModelData.lastAccessed = 1;
            }
            clearCacheTimer = 0;
        }

        if (horseModelDataCache.containsKey(enhancedHorse.getEntityId())) {
            HorseModelData horseModelData = horseModelDataCache.get(enhancedHorse.getEntityId());
            horseModelData.lastAccessed = 0;

            horseModelData.sleeping = enhancedHorse.isAnimalSleeping();
            horseModelData.blink = enhancedHorse.getBlink();
            horseModelData.birthTime = enhancedHorse.getBirthTime();
            horseModelData.clientGameTime = (((ClientWorld)enhancedHorse.world).getWorldInfo()).getGameTime();
            int collarSlot = hasCollar(enhancedHorse.getEnhancedInventory());
            horseModelData.collar = collarSlot!=0;
            horseModelData.saddle = collarSlot!=1 ? enhancedHorse.getEnhancedInventory().getStackInSlot(1) : ItemStack.EMPTY;
            horseModelData.bridle = !enhancedHorse.getEnhancedInventory().getStackInSlot(3).isEmpty() && collarSlot!=3;
            horseModelData.harness = !enhancedHorse.getEnhancedInventory().getStackInSlot(5).isEmpty() && collarSlot!=5;
            horseModelData.hasChest = !enhancedHorse.getEnhancedInventory().getStackInSlot(0).isEmpty();
            horseModelData.unrenderedModels = new ArrayList<>();

            return horseModelData;
        } else {
            HorseModelData horseModelData = new HorseModelData();
            if (enhancedHorse.getSharedGenes()!=null) {
                horseModelData.horseGenes = enhancedHorse.getSharedGenes().getAutosomalGenes();
            }
            horseModelData.size = enhancedHorse.getAnimalSize();
            horseModelData.sleeping = enhancedHorse.isAnimalSleeping();
            horseModelData.blink = enhancedHorse.getBlink();
            horseModelData.uuidArray = enhancedHorse.getCachedUniqueIdString().toCharArray();
            horseModelData.birthTime = enhancedHorse.getBirthTime();
            horseModelData.clientGameTime = ((((ClientWorld)enhancedHorse.world).getWorldInfo()).getGameTime());

            int collarSlot = hasCollar(enhancedHorse.getEnhancedInventory());
            horseModelData.collar = collarSlot!=0;
            horseModelData.saddle = collarSlot!=1 ? enhancedHorse.getEnhancedInventory().getStackInSlot(1) : ItemStack.EMPTY;
            horseModelData.bridle = !enhancedHorse.getEnhancedInventory().getStackInSlot(3).isEmpty() && collarSlot!=3;
            horseModelData.harness = !enhancedHorse.getEnhancedInventory().getStackInSlot(5).isEmpty() && collarSlot!=5;
            horseModelData.hasChest = !enhancedHorse.getEnhancedInventory().getStackInSlot(0).isEmpty();
            horseModelData.heightMod = enhancedHorse.getShape();
            horseModelData.faceShape = enhancedHorse.getFaceShape();
            horseModelData.faceLength = enhancedHorse.getFaceLength();
            horseModelData.adultAge = EanimodCommonConfig.COMMON.adultAgeCow.get();

            if(horseModelData.horseGenes != null) {
                horseModelDataCache.put(enhancedHorse.getEntityId(), horseModelData);
            }

            return horseModelData;
        }
    }

    private int hasCollar(Inventory inventory) {
        for (int i = 1; i < 6; i++) {
            if (inventory.getStackInSlot(i).getItem() instanceof CustomizableCollar) {
                return i;
            }
        }
        return 0;
    }

    private class Phenotype {
        float faceShape;
        float faceLength;

    }

}
