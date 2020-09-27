package mokiyoki.enhancedanimals.renderer.texture;

import com.google.common.collect.Lists;
import mokiyoki.enhancedanimals.entity.util.Colouration;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static net.minecraft.client.renderer.texture.NativeImage.getAlpha;
import static net.minecraft.client.renderer.texture.NativeImage.getCombined;
import static net.minecraft.client.renderer.texture.NativeImage.getGreen;
import static net.minecraft.client.renderer.texture.NativeImage.getBlue;
import static net.minecraft.client.renderer.texture.NativeImage.getRed;

/**
 * Created by saemon on 8/09/2018.
 */
@OnlyIn(Dist.CLIENT)
public class EnhancedLayeredTexture extends Texture {

    private static final Logger LOGGER = LogManager.getLogger();
    protected final List<String> layeredTextureNames;
    protected final List<String> maskingTextureNames = new ArrayList<>();
    protected  int dyeRGB = 0;
    protected  int blackRGB = 0;
    protected  int redRGB = 0;
    protected  int eyeLRGB = 0;
    protected  int eyeRRGB = 0;
    protected  int dyeSaddleRGB = 0;
    protected  int dyeArmourRGB = 0;
    protected  int dyeBridleRGB = 0;
    protected  int dyeHarnessRGB = 0;
    protected  int dyeCollarRGB = 0;
    //TODO add banner part colouring? will probably need an array
    protected String modLocation = "";

    public EnhancedLayeredTexture(String modLocation, String[] textureNames, String[] maskingTextureNames, Colouration colouration) {
        this.layeredTextureNames = Lists.newArrayList(textureNames);
        if (maskingTextureNames != null) {
            this.maskingTextureNames.addAll(Lists.newArrayList(maskingTextureNames));
        }

        if (this.layeredTextureNames.isEmpty()) {
            throw new IllegalStateException("Layered texture with no layers.");
        }
        this.modLocation = modLocation;

        if (colouration.getDyeColour()!=-1) {
            this.dyeRGB = colouration.getDyeColour();
        }

        if (colouration.getMelaninColour()!=-1) {
            this.blackRGB = colouration.getMelaninColour();
        }

        if (colouration.getPheomelaninColour()!=-1) {
            this.redRGB = colouration.getPheomelaninColour();
        }

        if (colouration.getLeftEyeColour()!=-1) {
            this.eyeLRGB = colouration.getLeftEyeColour();
        }

        if (colouration.getRightEyeColour()!=-1) {
            this.eyeRRGB = colouration.getRightEyeColour();
        }

        if (colouration.getSaddleColour()!=-1) {
            this.dyeSaddleRGB = colouration.getSaddleColour();
        }

        if (colouration.getArmourColour()!=-1) {
            this.dyeArmourRGB = colouration.getArmourColour();
        }

        if (colouration.getBridleColour()!=-1) {
            this.dyeBridleRGB = colouration.getBridleColour();
        }

        if (colouration.getHarnessColour()!=-1) {
            this.dyeHarnessRGB = colouration.getHarnessColour();
        }

        if (colouration.getCollarColour()!=-1) {
            this.dyeCollarRGB = colouration.getCollarColour();
        }
    }

    @Override
    public void loadTexture(IResourceManager manager) throws IOException {
        NativeImage maskingImage = null;
        if (!this.maskingTextureNames.isEmpty()) {
            try {
                maskingImage = generateMaskingImage(manager);
            } catch (IOException ioexception) {
                LOGGER.error("Couldn't load masking image", (Throwable)ioexception);
            }
        }

        Iterator<String> iterator = this.layeredTextureNames.iterator();
        String s = iterator.next();

        try (
                IResource iresource = manager.getResource(new ResourceLocation(modLocation+s));
                NativeImage nativeimage = NativeImage.read(iresource.getInputStream());

        ) {
            if(s.startsWith("c_") && dyeRGB!=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, dyeRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("r_") && redRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, redRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("b_") && blackRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, blackRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("eyel_") && eyeLRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, eyeLRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("eyer_") && eyeRRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, eyeRRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("d_s") && dyeSaddleRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, dyeSaddleRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("d_a") && dyeArmourRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, dyeSaddleRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("d_b") && dyeBridleRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, dyeSaddleRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("d_h") && dyeHarnessRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, dyeSaddleRGB, nativeimage);
                    }
                }
            } else if(s.startsWith("d_c") && dyeCollarRGB !=0) {
                for(int i = 0; i < nativeimage.getHeight(); ++i) {
                    for (int j = 0; j < nativeimage.getWidth(); ++j) {
                        blendDye(j, i, dyeCollarRGB, nativeimage);
                    }
                }
            }
            while(true) {
                if (!iterator.hasNext()) {
                    TextureUtil.prepareImage(this.getGlTextureId(), nativeimage.getWidth(), nativeimage.getHeight());
                    nativeimage.uploadTextureSub(0, 0, 0, false);
                    break;
                }

                String s1 = iterator.next();
                if (s1 != null) {
                    if (s1.equals("alpha_group_start") || s1.equals("alpha_group_end")) {
                        if (maskingImage != null) {
                            combineAlphaGroup(maskingImage, nativeimage, iterator, manager);
                        }
                    } else {
                        try (
                                IResource iresource1 = manager.getResource(new ResourceLocation(modLocation+s1));
                                NativeImage nativeimage1 = NativeImage.read(iresource1.getInputStream());
                        ) {

                            if(s1.startsWith("c_") && dyeRGB!=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, dyeRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("r_") && redRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, redRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("b_") && blackRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, blackRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("eyel_") && eyeLRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, eyeLRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("eyer_") && eyeRRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, eyeRRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("d_s") && dyeSaddleRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, dyeSaddleRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("d_a") && dyeArmourRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, dyeArmourRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("d_b") && dyeBridleRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, dyeBridleRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("d_h") && dyeHarnessRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, dyeHarnessRGB, nativeimage1);
                                    }
                                }
                            } else if(s1.startsWith("d_c") && dyeCollarRGB !=0) {
                                for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendDye(j, i, dyeCollarRGB, nativeimage1);
                                    }
                                }
                            }

                            for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                for(int j = 0; j < nativeimage1.getWidth(); ++j) {
                                    blendPixel(nativeimage, j, i, nativeimage1.getPixelRGBA(j, i));
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException ioexception) {
            LOGGER.error("Couldn't load layered image", (Throwable)ioexception);
        }

    }

    private void combineAlphaGroup(NativeImage maskingImage, NativeImage baseImage, Iterator<String> iterator, IResourceManager manager) {
        if (iterator.hasNext()) {
            String s = iterator.next();
            try (
                    IResource iresource = manager.getResource(new ResourceLocation(modLocation + s));
                    NativeImage firstGroupImage = NativeImage.read(iresource.getInputStream());

            ) {
                if (s.startsWith("c_") && dyeRGB != 0) {
                    for (int i = 0; i < firstGroupImage.getHeight(); ++i) {
                        for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
                            blendDye(j, i, dyeRGB, firstGroupImage);
                        }
                    }
                } else if (s.startsWith("r_") && redRGB != 0) {
                    for (int i = 0; i < firstGroupImage.getHeight(); ++i) {
                        for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
                            blendDye(j, i, redRGB, firstGroupImage);
                        }
                    }
                } else if (s.startsWith("b_") && blackRGB != 0) {
                    for (int i = 0; i < firstGroupImage.getHeight(); ++i) {
                        for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
                            blendDye(j, i, blackRGB, firstGroupImage);
                        }
                    }
                }
//            else if(s.startsWith("eyel_") && eyeLRGB !=0) {
//                for(int i = 0; i < firstGroupImage.getHeight(); ++i) {
//                    for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
//                        blendDye(j, i, eyeLRGB, firstGroupImage);
//                    }
//                }
//            } else if(s.startsWith("eyer_") && eyeRRGB !=0) {
//                for(int i = 0; i < firstGroupImage.getHeight(); ++i) {
//                    for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
//                        blendDye(j, i, eyeRRGB, firstGroupImage);
//                    }
//                }
//            }

                while (true) {
                    if (iterator.hasNext()) {
                        String s1 = iterator.next();
                        if (s1.equals("alpha_group_end")) {
                            for (int i = 0; i < firstGroupImage.getHeight(); ++i) {
                                for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
                                    blendAlpha(j, i, maskingImage, firstGroupImage);
                                }
                            }
                            for (int i = 0; i < firstGroupImage.getHeight(); ++i) {
                                for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
                                    blendPixel(baseImage, j, i, firstGroupImage.getPixelRGBA(j, i));
                                }
                            }

                            break;
                        }


                        if (s1 != null) {
                            try (
                                    IResource iresource1 = manager.getResource(new ResourceLocation(modLocation + s1));
                                    NativeImage nativeimage1 = NativeImage.read(iresource1.getInputStream());
                            ) {
                                if (s1.startsWith("c_") && dyeRGB != 0) {
                                    for (int i = 0; i < nativeimage1.getHeight(); ++i) {
                                        for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                            blendDye(j, i, dyeRGB, nativeimage1);
                                        }
                                    }
                                } else if (s1.startsWith("r_") && redRGB != 0) {
                                    for (int i = 0; i < nativeimage1.getHeight(); ++i) {
                                        for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                            blendDye(j, i, redRGB, nativeimage1);
                                        }
                                    }
                                } else if (s1.startsWith("b_") && blackRGB != 0) {
                                    for (int i = 0; i < nativeimage1.getHeight(); ++i) {
                                        for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                            blendDye(j, i, blackRGB, nativeimage1);
                                        }
                                    }
                                }
//                        else if(s1.startsWith("eyel_") && eyeLRGB !=0) {
//                            for(int i = 0; i < nativeimage1.getHeight(); ++i) {
//                                for (int j = 0; j < nativeimage1.getWidth(); ++j) {
//                                    blendDye(j, i, eyeLRGB, nativeimage1);
//                                }
//                            }
//                        } else if(s1.startsWith("eyer_") && eyeRRGB !=0) {
//                            for(int i = 0; i < nativeimage1.getHeight(); ++i) {
//                                for (int j = 0; j < nativeimage1.getWidth(); ++j) {
//                                    blendDye(j, i, eyeRRGB, nativeimage1);
//                                }
//                            }
//                        }

                                for (int i = 0; i < nativeimage1.getHeight(); ++i) {
                                    for (int j = 0; j < nativeimage1.getWidth(); ++j) {
                                        blendPixel(firstGroupImage, j, i, nativeimage1.getPixelRGBA(j, i));
                                    }
                                }
                            }
                        }
                    } else {
                        break;
                    }
                }
            } catch (IOException ioexception) {
                LOGGER.error("Couldn't load layered image", (Throwable) ioexception);
            }
        }
    }

    private NativeImage generateMaskingImage(IResourceManager manager) throws IOException {
        Iterator<String> iterator = this.maskingTextureNames.iterator();
        String s = iterator.next();

                IResource iresource = manager.getResource(new ResourceLocation(modLocation+s));
                NativeImage nativeimage = NativeImage.read(iresource.getInputStream());

            while(true) {
                if (!iterator.hasNext()) {
                    TextureUtil.prepareImage(this.getGlTextureId(), nativeimage.getWidth(), nativeimage.getHeight());
                    return nativeimage;
                }

                String s1 = iterator.next();
                if (s1 != null) {
                    try (
                            IResource iresource1 = manager.getResource(new ResourceLocation(modLocation+s1));
                            NativeImage nativeimage1 = NativeImage.read(iresource1.getInputStream());
                    ) {
                        for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                            for(int j = 0; j < nativeimage1.getWidth(); ++j) {
                                blendPixel(nativeimage, j, i, nativeimage1.getPixelRGBA(j, i));
                            }
                        }
                    }
                }
            }
    }

    public void blendDye(int xIn, int yIn, int rgbDye, NativeImage nativeimage) {
        int i = nativeimage.getPixelRGBA(xIn, yIn);

        float f1 = (float)(rgbDye >> 16 & 255) / 255.0F;
        float f2 = (float)(rgbDye >> 8 & 255) / 255.0F;
        float f3 = (float)(rgbDye >> 0 & 255) / 255.0F;
        float originalAlpha = (float)(i >> 24 & 255) / 255.0F;
        float f5 = (float)(i >> 16 & 255) / 255.0F;
        float f6 = (float)(i >> 8 & 255) / 255.0F;
        float f7 = (float)(i >> 0 & 255) / 255.0F;

        if(originalAlpha != 0.0) {
            float f10 = (f1 * 255 ) * f5;
            float f11 = (f2 * 255 ) * f6;
            float f12 = (f3 * 255 ) * f7;

            int j = (int)(originalAlpha * 255.0F);
            int k = (int)(f10);
            int l = (int)(f11);
            int i1 = (int)(f12);

            nativeimage.setPixelRGBA(xIn, yIn, j << 24 | k << 16 | l << 8 | i1 << 0);
        }
    }

    public void blendAlpha(int xIn, int yIn, NativeImage maskingImage, NativeImage nativeImage) {
        int i = nativeImage.getPixelRGBA(xIn, yIn);
        int iAlpha = maskingImage.getPixelRGBA(xIn, yIn);

        float originalAlpha = (float)(i >> 24 & 255) / 255.0F;
        float maskingAlpha = (float)(iAlpha >> 24 & 255) / 255.0F;
        float f5 = (float)(i >> 16 & 255) / 255.0F;
        float f6 = (float)(i >> 8 & 255) / 255.0F;
        float f7 = (float)(i >> 0 & 255) / 255.0F;

        if(originalAlpha != 0.0) {

            int j = (int)(maskingAlpha * 255.0F);
            int k = (int)(f5*255);
            int l = (int)(f6*255);
            int i1 = (int)(f7*255);

            nativeImage.setPixelRGBA(xIn, yIn, j << 24 | k << 16 | l << 8 | i1 << 0);
        }
    }

    public void blendPixel(NativeImage baseImage, int xIn, int yIn, int colIn) {
        int i = baseImage.getPixelRGBA(xIn, yIn);
        float f = (float)getAlpha(colIn) / 255.0F;
        float f1 = (float)getBlue(colIn) / 255.0F;
        float f2 = (float)getGreen(colIn) / 255.0F;
        float f3 = (float)getRed(colIn) / 255.0F;
        float f4 = (float)getAlpha(i) / 255.0F;
        float f5 = (float)getBlue(i) / 255.0F;
        float f6 = (float)getGreen(i) / 255.0F;
        float f7 = (float)getRed(i) / 255.0F;
        float f8 = 1.0F - f;
        float f9 = f * f + f4 * f8;
        float f10 = f1 * f + f5 * f8;
        float f11 = f2 * f + f6 * f8;
        float f12 = f3 * f + f7 * f8;
        if (f9 > 1.0F) {
            f9 = 1.0F;
        }

        if (f10 > 1.0F) {
            f10 = 1.0F;
        }

        if (f11 > 1.0F) {
            f11 = 1.0F;
        }

        if (f12 > 1.0F) {
            f12 = 1.0F;
        }

        int j = (int)(f9 * 255.0F);
        int k = (int)(f10 * 255.0F);
        int l = (int)(f11 * 255.0F);
        int i1 = (int)(f12 * 255.0F);
        baseImage.setPixelRGBA(xIn, yIn, getCombined(j, k, l, i1));
    }

}
