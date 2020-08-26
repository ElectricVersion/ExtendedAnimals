package mokiyoki.enhancedanimals.entity.Genetics;

import mokiyoki.enhancedanimals.init.LlamaBreeds;
import mokiyoki.enhancedanimals.util.Breed;
import mokiyoki.enhancedanimals.util.Genes;
import mokiyoki.enhancedanimals.util.Reference;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LlamaGeneticsInitialiser extends AbstractGeneticsInitialiser{
    List<Breed> breeds = new ArrayList<>();

    public LlamaGeneticsInitialiser() {
        this.breeds.add(LlamaBreeds.SURI);
    }

    public Genes generateNewGenetics(IWorld world, BlockPos pos, boolean generateBreed) {
        return super.generateNewGenetics(world, pos, generateBreed, this.breeds);
    }

    public Genes generateWithBreed(IWorld world, BlockPos pos, String breed) {
        return super.generateWithBreed(world, pos, this.breeds, breed);
    }

    @Override
    public Genes generateLocalWildGenetics(Biome biome, boolean isFlat) {
        int[] autosomalGenes = new int[Reference.LLAMA_AUTOSOMAL_GENES_LENGTH];
        //TODO create biome WTC variable [hot and dry biomes, cold biomes ] WTC is neutral biomes "all others"

        /**
         * Colour Genes
         */

        //Endurance genes [ wildtype, stronger1, stronger2]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[0] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[0] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[1] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[1] = (1);
        }


        //Strength genes [ wildtype, stronger1, stronger2]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[2] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[2] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[3] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[3] = (1);
        }

        //Attack genes [ wildtype, stronger1, stronger2]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[4] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[4] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[5] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[5] = (1);
        }

        //Dominant White [ dominant white, wildtype ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[6] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[6] = (2);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[7] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[7] = (2);
        }

        //Roan [ roan, wildtype ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[8] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[8] = (2);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[9] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[9] = (2);
        }

        //Piebald [ piebald, wildtype ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[10] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[10] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[11] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[11] = (1);
        }

        //Tuxedo [ tuxedo, wildtype ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[12] = (ThreadLocalRandom.current().nextInt(2)+1);
        } else {
            autosomalGenes[12] = (2);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[13] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[13] = (2);
        }

        //Extention [ black, wildtype, self ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[14] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[14] = (2);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[15] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[15] = (2);
        }

        //Agouti [ PaleShaded, Shaded, RedTrimmedBlack, Bay, Mahogany, BlackTan, rBlack]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[16] = (ThreadLocalRandom.current().nextInt(7)+1);

        } else {
            autosomalGenes[16] = (2);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[17] = (ThreadLocalRandom.current().nextInt(7)+1);

        } else {
            autosomalGenes[17] = (2);
        }

        //Banana Ears genes [ no banana, banana, bananaless ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[18] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[18] = (2);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[19] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[19] = (2);
        }

        //Suri coat genes [ normal, suri ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[20] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[20] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[21] = (ThreadLocalRandom.current().nextInt(2)+1);

        } else {
            autosomalGenes[21] = (1);
        }

        //Coat Length genes [ normal, Longer, Longest ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[22] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[22] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[23] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[23] = (1);
        }

        //Coat Length suppressor [ normal, shorter ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[24] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[24] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[25] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[25] = (1);
        }

        //Coat Length amplifier [ normal, double ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[26] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[26] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[27] = (ThreadLocalRandom.current().nextInt(3)+1);

        } else {
            autosomalGenes[27] = (1);
        }

        //nose placement genes [ +0.1, +0.15/+0.05, 0, -0.1 ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[28] = (ThreadLocalRandom.current().nextInt(4)+1);

        } else {
            autosomalGenes[28] = (2);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[29] = (ThreadLocalRandom.current().nextInt(4)+1);

        } else {
            autosomalGenes[29] = (2);
        }

        //nose placement genes [ +0.1, +0.15/0.05, 0, -0.1 ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[30] = (ThreadLocalRandom.current().nextInt(4)+1);

        } else {
            autosomalGenes[30] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[31] = (ThreadLocalRandom.current().nextInt(4)+1);

        } else {
            autosomalGenes[31] = (1);
        }

        //nose placement genes [ +0.2, +0.15, 0, -0.15, -0.2 ]
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[32] = (ThreadLocalRandom.current().nextInt(5)+1);

        } else {
            autosomalGenes[32] = (1);
        }
        if(ThreadLocalRandom.current().nextInt(100)>WTC){
            autosomalGenes[33] = (ThreadLocalRandom.current().nextInt(5)+1);

        } else {
            autosomalGenes[33] = (1);
        }

        return new Genes(autosomalGenes);
    }
}
