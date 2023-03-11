package mokiyoki.enhancedanimals.renderer.texture;

import mokiyoki.enhancedanimals.entity.util.Colouration;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.ArrayList;
import java.util.List;

import static mokiyoki.enhancedanimals.renderer.texture.TexturingUtils.*;

public class TextureGrouping {

    private List<TextureGrouping> textureGroupings = new ArrayList<>();
    private List<TextureLayer> textureLayers = new ArrayList<>();

    private TexturingType texturingType;

    public TextureGrouping(TexturingType texturingType) {
        this.texturingType = texturingType;
    }

    public NativeImage processGrouping(String modLocation, ResourceManager manager, Colouration colouration, int x, int y) {
        try {
            List<NativeImage> groupImages = new ArrayList<>();

            for (TextureGrouping group : textureGroupings) {
                NativeImage groupCompiledImage = group.processGrouping(modLocation, manager, colouration, x, y);
                if (groupCompiledImage != null) groupImages.add(groupCompiledImage);
            }

            for (TextureLayer layer : textureLayers) {
                if (!layer.getTexture().isEmpty()) {
                    createTexture(layer, modLocation, manager, x, y);
                    applyLayerSpecifics(layer, colouration);
                    groupImages.add(layer.getTextureImage());
                }
            }

            return applyGroupMerging(groupImages);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Exception occurred in Texturing Grouping" + textureGroupings.toString(), e);
        }
    }

    private NativeImage applyGroupMerging(List<NativeImage> groupImages) {
        if (!groupImages.isEmpty()) {
            NativeImage baseImage = groupImages.remove(0);

            switch(texturingType) {
                case MERGE_GROUP -> layerGroups(baseImage, groupImages);
                case MASK_GROUP -> maskAlpha(baseImage, groupImages);
                case AVERAGE_GROUP -> blendAverage(baseImage, groupImages);
                case CUTOUT_GROUP -> cutoutTextures(baseImage, groupImages);
            }

            return baseImage;
        }

        return null;
    }

    private void cutoutTextures(NativeImage cutoutImage, List<NativeImage> groupImages) {
        if (!groupImages.isEmpty()) {
            NativeImage image = groupImages.remove(0);
            if (!groupImages.isEmpty()) {
                layerGroups(image, groupImages);
            }
            int h = cutoutImage.getHeight();
            int w = cutoutImage.getWidth();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    cutoutAlpha(j, i, cutoutImage, image);
                }
            }
        }
    }

    private void layerGroups(NativeImage compiledImage, List<NativeImage> groupImages) {
        for (NativeImage applyImage : groupImages) {
            applyPixelLayer(compiledImage, applyImage);
        }
    }

    private void maskAlpha(NativeImage alphaMaskImage, List<NativeImage> groupImages) {
        if (!groupImages.isEmpty()) {
            NativeImage mergeGroup = groupImages.get(0);
            //First merge image groups
            if (groupImages.size() > 1) {
                NativeImage baseImage = groupImages.get(0);
                for (int i = 1; i < groupImages.size(); i++) {
                    applyPixelLayer(baseImage, groupImages.get(i));
                }
                mergeGroup = baseImage;
            }

            applyAlphaMaskBlend(alphaMaskImage, mergeGroup);
        }
    }

    private void blendAverage(NativeImage compiledImage, List<NativeImage> groupImages) {
        applyAverageBlend(compiledImage, groupImages);
    }

    private void applyLayerSpecifics(TextureLayer layer, Colouration colouration) {
        switch(layer.getTexturingType()) {
            case APPLY_RED:
                layer.setTextureImage(applyRGBBlend(layer.getTextureImage(), colouration.getPheomelaninColour()));
                break;
            case APPLY_BLACK:
                layer.setTextureImage(applyRGBBlend(layer.getTextureImage(), colouration.getMelaninColour()));
                break;
            case APPLY_COLLAR_COLOUR:
                layer.setTextureImage(applyRGBBlend(layer.getTextureImage(), colouration.getCollarColour()));
                break;
            case APPLY_BRIDLE_COLOUR:
                layer.setTextureImage(applyRGBBlend(layer.getTextureImage(), colouration.getBridleColour()));
                break;
            case APPLY_SADDLE_COLOUR:
                layer.setTextureImage(applyRGBBlend(layer.getTextureImage(), colouration.getSaddleColour()));
                break;
            case APPLY_DYE:
                layer.setTextureImage(applyBGRBlend(layer.getTextureImage(), colouration.getDyeColour()));
                break;
            case APPLY_EYE_LEFT_COLOUR:
                layer.setTextureImage(applyBGRBlend(layer.getTextureImage(), colouration.getLeftEyeColour()));
                break;
            case APPLY_EYE_RIGHT_COLOUR:
                layer.setTextureImage(applyBGRBlend(layer.getTextureImage(), colouration.getRightEyeColour()));
                break;
            case APPLY_RGB:
                layer.setTextureImage(applyBGRBlend(layer.getTextureImage(), layer.getRGB()));
                break;
        }
    }

    public void addGrouping(TextureGrouping textureGrouping) {
            this.textureGroupings.add(textureGrouping);
    }

    public void addTextureLayers(TextureLayer textureLayer) {
        this.textureLayers.add(textureLayer);
    }

    public void setTexturingType(TexturingType texturingType) {
        this.texturingType = texturingType;
    }

    public boolean isPopulated() {
        if (this.textureGroupings.size() > 0 || this.textureLayers.size() > 0) {
            return true;
        }
        return false;
    }
}