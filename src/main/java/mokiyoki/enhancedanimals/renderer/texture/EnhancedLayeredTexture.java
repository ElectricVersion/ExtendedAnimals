package mokiyoki.enhancedanimals.renderer.texture;

import com.google.common.collect.Lists;
import mokiyoki.enhancedanimals.entity.util.Colouration;
import mokiyoki.enhancedanimals.entity.util.Colouration.RGBHolder;
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

/**
 * Created by saemon on 8/09/2018.
 */
@OnlyIn(Dist.CLIENT)
public class EnhancedLayeredTexture extends Texture {

    private static final Logger LOGGER = LogManager.getLogger();
    protected final List<String> layeredTextureNames;
    protected final List<String> maskingTextureNames = new ArrayList<>();
    //TODO there has to be a better way to do this.
    protected  int dyeRGB = 0;
    protected  int blackRGB = 0;
    protected  int redRGB = 0;
    protected  int eyeLRGB = 0;
    protected  int eyeRRGB = 0;
    protected  int dyeSaddleRGB = 0;
    protected  int dyeArmourRGB = 0;
    protected  int dyeBridleRGB = 0;
    protected  int dyeHarnessRGB = 0;
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

        if (colouration.getDyeColour()!=null) {
            this.dyeRGB = getDecimalFromRGB(colouration.getDyeColour());
        }

        if (colouration.getMelaninColour()!=null) {
            this.blackRGB = getDecimalFromRGB(colouration.getDyeColour());
        }

        if (colouration.getPheomelaninColour()!=null) {
            this.redRGB = getDecimalFromRGB(colouration.getDyeColour());
        }

        if (colouration.getLeftEyeColour()!=null) {
            this.eyeLRGB = getDecimalFromRGB(colouration.getDyeColour());
        }

        if (colouration.getRightEyeColour()!=null) {
            this.eyeRRGB = getDecimalFromRGB(colouration.getDyeColour());
        }

        if (colouration.getSaddleColour()!=null) {
            this.dyeSaddleRGB = getDecimalFromRGB(colouration.getDyeColour());
        }

        if (colouration.getArmourColour()!=null) {
            this.dyeArmourRGB = getDecimalFromRGB(colouration.getDyeColour());
        }

        if (colouration.getBridleColour()!=null) {
            this.dyeBridleRGB = getDecimalFromRGB(colouration.getDyeColour());
        }

        if (colouration.getHarnessColour()!=null) {
            this.dyeHarnessRGB = getDecimalFromRGB(colouration.getDyeColour());
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
            } else if(s.startsWith("d_br") && dyeBridleRGB !=0) {
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
                            } else if(s1.startsWith("d_br") && dyeBridleRGB !=0) {
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
                            }

                            for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                                for(int j = 0; j < nativeimage1.getWidth(); ++j) {
                                    nativeimage.blendPixel(j, i, nativeimage1.getPixelRGBA(j, i));
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
        String s = iterator.next();
        try (
                IResource iresource = manager.getResource(new ResourceLocation(modLocation+s));
                NativeImage firstGroupImage = NativeImage.read(iresource.getInputStream());

        ) {
            if(s.startsWith("c_") && dyeRGB!=0) {
                for(int i = 0; i < firstGroupImage.getHeight(); ++i) {
                    for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
                        blendDye(j, i, dyeRGB, firstGroupImage);
                    }
                }
            } else if(s.startsWith("r_") && redRGB !=0) {
                for(int i = 0; i < firstGroupImage.getHeight(); ++i) {
                    for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
                        blendDye(j, i, redRGB, firstGroupImage);
                    }
                }
            } else if(s.startsWith("b_") && blackRGB !=0) {
                for(int i = 0; i < firstGroupImage.getHeight(); ++i) {
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

            while(true) {
                String s1 = iterator.next();
                if (s1.equals("alpha_group_end")) {
                    for(int i = 0; i < firstGroupImage.getHeight(); ++i) {
                        for (int j = 0; j < firstGroupImage.getWidth(); ++j) {
                            blendAlpha(j, i, maskingImage, firstGroupImage);
                        }
                    }
                    for(int i = 0; i < firstGroupImage.getHeight(); ++i) {
                        for(int j = 0; j < firstGroupImage.getWidth(); ++j) {
                            baseImage.blendPixel(j, i, firstGroupImage.getPixelRGBA(j, i));
                        }
                    }

                    break;
                }


                if (s1 != null) {
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

                        for(int i = 0; i < nativeimage1.getHeight(); ++i) {
                            for(int j = 0; j < nativeimage1.getWidth(); ++j) {
                                firstGroupImage.blendPixel(j, i, nativeimage1.getPixelRGBA(j, i));
                            }
                        }
                    }
                }
            }
        } catch (IOException ioexception) {
            LOGGER.error("Couldn't load layered image", (Throwable)ioexception);
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
                                nativeimage.blendPixel(j, i, nativeimage1.getPixelRGBA(j, i));
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

    private int getDecimalFromRGB(RGBHolder colour) {
        int alpha = (int) (0.4 * 255.0F);
        int blue = (int) (colour.getBlue() * 255.0F);
        int green = (int) (colour.getGreen() * 255.0F);
        int red = (int) (colour.getRed() * 255.0F);

        return alpha << 24 | blue << 16 | green << 8 | red;
    }

}
