package mokiyoki.enhancedanimals.renderer;

import com.google.common.collect.Maps;
import mokiyoki.enhancedanimals.entity.EnhancedSheep;
import mokiyoki.enhancedanimals.entity.util.Colouration;
import mokiyoki.enhancedanimals.model.ModelEnhancedSheep;
import mokiyoki.enhancedanimals.renderer.texture.EnhancedLayeredTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class RenderEnhancedSheep extends MobRenderer<EnhancedSheep, ModelEnhancedSheep<EnhancedSheep>> {

    private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.<String, ResourceLocation>newHashMap();
    private static final String ENHANCED_SHEEP_TEXTURE_LOCATION = "eanimod:textures/entities/sheep/";
    private static final ResourceLocation ERROR_TEXTURE_LOCATION = new ResourceLocation("eanimod:textures/entities/sheep/sheep.png");

    public RenderEnhancedSheep(EntityRendererManager render) {
        super(render, new ModelEnhancedSheep<>(), 0.6F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    public ResourceLocation getEntityTexture(EnhancedSheep entity) {
        String s = entity.getSheepTexture();
        entity.colouration.setDyeColour(EnhancedSheep.getDyeRgb(entity.getFleeceDyeColour()));
        Colouration colourRGB = entity.colouration;

        if (s == null || s.isEmpty() || colourRGB == null) {
            return ERROR_TEXTURE_LOCATION;
        }

        s = s + colourRGB.getRGBStrings();

        ResourceLocation resourcelocation = LAYERED_LOCATION_CACHE.get(s);

        if (resourcelocation == null) {

            String[] textures = entity.getVariantTexturePaths();

            if (textures == null || textures.length == 0) {
                return ERROR_TEXTURE_LOCATION;
            }

            try {
                resourcelocation = new ResourceLocation(s);
                Minecraft.getInstance().getTextureManager().loadTexture(resourcelocation, new EnhancedLayeredTexture(ENHANCED_SHEEP_TEXTURE_LOCATION, textures, null, entity.colouration));
                LAYERED_LOCATION_CACHE.put(s, resourcelocation);
            } catch (IllegalStateException e) {
                return ERROR_TEXTURE_LOCATION;
            }
        }

        return resourcelocation;
    }

}
