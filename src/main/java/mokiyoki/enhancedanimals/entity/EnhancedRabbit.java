package mokiyoki.enhancedanimals.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static mokiyoki.enhancedanimals.util.handlers.RegistryHandler.ENHANCED_RABBIT;

public class EnhancedRabbit extends EntityAnimal {

    private static final DataParameter<String> SHARED_GENES = EntityDataManager.<String>createKey(EnhancedRabbit.class, DataSerializers.STRING);

    private static final String[] RABBIT_TEXTURES_UNDER = new String[] {
        "under_cream.png", "under_grey.png", "under_white.png"
    };

    // 1 7 13
    private static final String[] RABBIT_TEXTURES_LOWER = new String[] {
        "", "middle_agoutiorange.png", "middle_orange.png", "chinchilla_orange.png",
            "middle_agoutitan.png", "middle_tan.png", "chinchilla_tan.png",
            "middle_agoutilighttan.png", "middle_lighttan.png", "chinchilla_lighttan.png",
            "middle_agouticream.png", "middle_cream.png", "chinchilla_cream.png",
            "middle_agoutigrey.png", "middle_grey.png", "chinchilla_grey.png",
            "middle_agoutiwhite.png", "middle_white.png", "chinchilla_white.png"
    };

    private static final String[] RABBIT_TEXTURES_MIDDLE = new String[] {
            "", "middle_agoutiorange.png", "middle_orange.png", "chinchilla_orange.png",
            "middle_agoutitan.png", "middle_tan.png", "chinchilla_tan.png",
            "middle_agoutilighttan.png", "middle_lighttan.png", "chinchilla_lighttan.png",
            "middle_agouticream.png", "middle_cream.png", "chinchilla_cream.png",
            "middle_agoutigrey.png", "middle_grey.png", "chinchilla_grey.png",
            "middle_agoutiwhite.png", "middle_white.png", "chinchilla_white.png"
    };

    private static final String[] RABBIT_TEXTURES_HIGHER = new String[] {
            "", "middle_agoutiorange.png", "middle_orange.png", "chinchilla_orange.png",
            "middle_agoutitan.png", "middle_tan.png", "chinchilla_tan.png",
            "middle_agoutilighttan.png", "middle_lighttan.png", "chinchilla_lighttan.png",
            "middle_agouticream.png", "middle_cream.png", "chinchilla_cream.png",
            "middle_agoutigrey.png", "middle_grey.png", "chinchilla_grey.png",
            "middle_agoutiwhite.png", "middle_white.png", "chinchilla_white.png"
    };

    // 1 5 9
    private static final String[] RABBIT_TEXTURES_TOP = new String[] {
        "error.png", "agouti_black.png", "agouti_blue.png", "agouti_choc.png", "agouti_lilac.png",
            "agouti_seal_black.png", "agouti_seal_blue.png", "agouti_seal_choc.png", "agouti_seal_lilac.png",
            "agouti_sable_black.png", "agouti_sable_blue.png", "agouti_sable_choc.png", "agouti_sable_lilac.png",
            "agouti_himi_black.png", "agouti_himi_blue.png", "agouti_himi_choc.png", "agouti_himi_lilac.png",
            "tan_black.png", "tan_blue.png", "tan_choc.png", "tan_lilac.png",
            "tan_seal_black.png", "tan_seal_blue.png", "tan_seal_choc.png", "tan_seal_lilac.png",
            "tan_sable_black.png", "tan_sable_blue.png", "tan_sable_choc.png", "tan_sable_lilac.png",
            "tan_himi_black.png", "tan_himi_blue.png", "tan_himi_choc.png", "tan_himi_lilac.png",


    };

    // higher numbers are more white
    private static final String[] RABBIT_TEXTURES_DUTCH = new String[] {
        "", "dutch0.png", "dutch1.png", "dutch2.png"
    };

    // higher numbers are more white
    private static final String[] RABBIT_TEXTURES_BROKEN = new String[] {
        "", "spots_broken.png", "spots_charlie.png"
    };

    // higher numbers are more white
    private static final String[] RABBIT_TEXTURES_HEADSPOT = new String[] {
        "", "spots_ear.png", "spots_star0.png", "spots_star1.png", "spots_star2.png", "spots_snip.png", "spots_stripe.png"
    };

    // higher numbers are more white
    private static final String[] RABBIT_TEXTURES_FOOTSPOT = new String[] {
        "", "spots_boots.png", "spots_righttoes.png", "spots_toes.png"
    };

    // higher numbers are more white
    private static final String[] RABBIT_TEXTURES_BODYSPOT = new String[] {
        "", "spots_collar.png"
    };

    private static final String[] RABBIT_TEXTURES_FUR = new String[] {
       "fur_normal.png", "fur_angora.png", "fur_rex.png", "fur_satin.png"
    };

    private static final String[] RABBIT_TEXTURES_EYES = new String[] {
        "eyes_black.png", "eyes_brown.png", "eyes_amber.png", "eyes_grey.png", "eyes_ruby.png", "eyes_albino.png"
    };

    private static final String[] RABBIT_TEXTURES_VIENNAEYES = new String[] {
        "", "eyes_vienna.png", "eyes_viennaright.png", "eyes_viennaleft.png"
    };

    private static final String[] RABBIT_TEXTURES_SKIN = new String[] {
        "skin_pink.png", "skin_brown.png", "skin_black.png", "skin_white.png"
    };

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.DANDELION_YELLOW, Items.CARROT, Items.GOLDEN_CARROT);

    private final List<String> rabbitTextures = new ArrayList<>();

    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    private int currentMoveTypeDuration;
    private int carrotTicks;

    private static final int WTC = 90;
    private static final int GENES_LENGTH = 50;
    private int[] genes = new int[GENES_LENGTH];
    private int[] mateGenes = new int[GENES_LENGTH];
    private int[] mitosisGenes = new int[GENES_LENGTH];
    private int[] mateMitosisGenes = new int[GENES_LENGTH];

    public EnhancedRabbit(World worldIn) {
        super(ENHANCED_RABBIT, worldIn);
        this.setSize(0.4F, 0.5F);
        this.jumpHelper = new EnhancedRabbit.RabbitJumpHelper(this);
        this.moveHelper = new EnhancedRabbit.RabbitMoveHelper(this);
        this.setMovementSpeed(0.0D);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMate(this, 0.8D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, TEMPTATION_ITEMS, false));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.tasks.addTask(1, new EnhancedRabbit.AIPanic(this, 2.2D));
        this.tasks.addTask(4, new EnhancedRabbit.AIAvoidEntity<>(this, EntityPlayer.class, 8.0F, 2.2D, 2.2D));
        this.tasks.addTask(4, new EnhancedRabbit.AIAvoidEntity<>(this, EntityWolf.class, 10.0F, 2.2D, 2.2D));
        this.tasks.addTask(4, new EnhancedRabbit.AIAvoidEntity<>(this, EntityMob.class, 4.0F, 2.2D, 2.2D));
        this.tasks.addTask(5, new EnhancedRabbit.AIRaidFarm(this));

    }

    protected float getJumpUpwardsMotion() {
        if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || !(this.moveHelper.getY() > this.posY + 0.5D))) {
            Path path = this.navigator.getPath();
            if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
                Vec3d vec3d = path.getPosition(this);
                if (vec3d.y > this.posY + 0.5D) {
                    return 0.5F;
                }
            }

            return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
        } else {
            return 0.5F;
        }
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump() {
        super.jump();
        double d0 = this.moveHelper.getSpeed();
        if (d0 > 0.0D) {
            double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;
            if (d1 < 0.010000000000000002D) {
                this.moveRelative(0.0F, 0.0F, 1.0F, 0.1F);
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)1);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public float getJumpCompletion(float tick) {
        return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + tick) / (float)this.jumpDuration;
    }

    public void setMovementSpeed(double newSpeed) {
        this.getNavigator().setSpeed(newSpeed);
        this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
    }

    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }

    }

    public void startJumping() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(SHARED_GENES, new String());
    }

    public void updateAITasks() {
        if (this.currentMoveTypeDuration > 0) {
            --this.currentMoveTypeDuration;
        }

        //TODO SEAMUS add in carrot raid functions
//        if (this.carrotTicks > 0) {
//            this.carrotTicks -= this.rand.nextInt(3);
//            if (this.carrotTicks < 0) {
//                this.carrotTicks = 0;
//            }
//        }

        if (this.onGround) {
            if (!this.wasOnGround) {
                this.setJumping(false);
                this.checkLandingDelay();
            }

            EnhancedRabbit.RabbitJumpHelper enhancedRabbit$rabbitjumphelper = (EnhancedRabbit.RabbitJumpHelper)this.jumpHelper;
            if (!enhancedRabbit$rabbitjumphelper.getIsJumping()) {
                if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0) {
                    Path path = this.navigator.getPath();
                    Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());
                    if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
                        vec3d = path.getPosition(this);
                    }

                    this.calculateRotationYaw(vec3d.x, vec3d.z);
                    this.startJumping();
                }
            } else if (!enhancedRabbit$rabbitjumphelper.canJump()) {
                this.enableJumpControl();
            }
        }

        this.wasOnGround = this.onGround;
    }

    /**
     * Attempts to create sprinting particles if the entity is sprinting and not in water.
     */
    public void spawnRunningParticles() {
    }

    private void calculateRotationYaw(double x, double z) {
        this.rotationYaw = (float)(MathHelper.atan2(z - this.posZ, x - this.posX) * (double)(180F / (float)Math.PI)) - 90.0F;
    }

    private void enableJumpControl() {
        ((EnhancedRabbit.RabbitJumpHelper)this.jumpHelper).setCanJump(true);
    }

    private void disableJumpControl() {
        ((EnhancedRabbit.RabbitJumpHelper)this.jumpHelper).setCanJump(false);
    }

    private void updateMoveTypeDuration() {
        if (this.moveHelper.getSpeed() < 2.2D) {
            this.currentMoveTypeDuration = 10;
        } else {
            this.currentMoveTypeDuration = 1;
        }

    }

    private void checkLandingDelay() {
        this.updateMoveTypeDuration();
        this.disableJumpControl();
    }


    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 1) {
            this.createRunningParticles();
            this.jumpDuration = 10;
            this.jumpTicks = 0;
        } else {
            super.handleStatusUpdate(id);
        }

    }


    public void setSharedGenes(int[] genes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genes.length; i++) {
            sb.append(genes[i]);
            if (i != genes.length - 1) {
                sb.append(",");
            }
        }
        this.dataManager.set(SHARED_GENES, sb.toString());
    }

    public int[] getSharedGenes() {
        String sharedGenes = ((String) this.dataManager.get(SHARED_GENES)).toString();
        if (sharedGenes.isEmpty()) {
            return null;
        }
        String[] genesToSplit = sharedGenes.split(",");
        int[] sharedGenesArray = new int[genesToSplit.length];

        for (int i = 0; i < sharedGenesArray.length; i++) {
            //parse and store each value into int[] to be returned
            sharedGenesArray[i] = Integer.parseInt(genesToSplit[i]);
        }
        return sharedGenesArray;
    }

    public float getEyeHeight()
    {
        return this.height;
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    public void livingTick()
    {
        super.livingTick();
        if (this.jumpTicks != this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
    }

    public class RabbitJumpHelper extends EntityJumpHelper {
        private final EnhancedRabbit rabbit;
        private boolean canJump;

        public RabbitJumpHelper(EnhancedRabbit rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public boolean getIsJumping() {
            return this.isJumping;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJumpIn) {
            this.canJump = canJumpIn;
        }

        /**
         * Called to actually make the entity jump if isJumping is true.
         */
        public void tick() {
            if (this.isJumping) {
                this.rabbit.startJumping();
                this.isJumping = false;
            }

        }
    }

    private boolean isCarrotEaten() {
        return this.carrotTicks == 0;
    }

    static class RabbitMoveHelper extends EntityMoveHelper {
        private final EnhancedRabbit rabbit;
        private double nextJumpSpeed;

        public RabbitMoveHelper(EnhancedRabbit rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public void tick() {
            if (this.rabbit.onGround && !this.rabbit.isJumping && !((EnhancedRabbit.RabbitJumpHelper)this.rabbit.jumpHelper).getIsJumping()) {
                this.rabbit.setMovementSpeed(0.0D);
            } else if (this.isUpdating()) {
                this.rabbit.setMovementSpeed(this.nextJumpSpeed);
            }

            super.tick();
        }

        /**
         * Sets the speed and location to move to
         */
        public void setMoveTo(double x, double y, double z, double speedIn) {
            if (this.rabbit.isInWater()) {
                speedIn = 1.5D;
            }

            super.setMoveTo(x, y, z, speedIn);
            if (speedIn > 0.0D) {
                this.nextJumpSpeed = speedIn;
            }

        }
    }


    static class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T> {
        private final EnhancedRabbit rabbit;

        public AIAvoidEntity(EnhancedRabbit rabbit, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
            super(rabbit, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
            this.rabbit = rabbit;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            return super.shouldExecute();
        }
    }

    static class AIEvilAttack extends EntityAIAttackMelee {
        public AIEvilAttack(EnhancedRabbit rabbit) {
            super(rabbit, 1.4D, true);
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget) {
            return (double)(4.0F + attackTarget.width);
        }
    }

    static class AIPanic extends EntityAIPanic {
        private final EnhancedRabbit rabbit;

        public AIPanic(EnhancedRabbit rabbit, double speedIn) {
            super(rabbit, speedIn);
            this.rabbit = rabbit;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            super.tick();
            this.rabbit.setMovementSpeed(this.speed);
        }
    }

    static class AIRaidFarm extends EntityAIMoveToBlock {
        private final EnhancedRabbit rabbit;
        private boolean wantsToRaid;
        private boolean canRaid;

        public AIRaidFarm(EnhancedRabbit rabbitIn) {
            super(rabbitIn, (double)0.7F, 16);
            this.rabbit = rabbitIn;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            if (this.runDelay <= 0) {
                if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.rabbit.world, this.rabbit)) {
                    return false;
                }

                this.canRaid = false;
                this.wantsToRaid = this.rabbit.isCarrotEaten();
                this.wantsToRaid = true;
            }

            return super.shouldExecute();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.canRaid && super.shouldContinueExecuting();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            super.tick();
            this.rabbit.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5D, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5D, 10.0F, (float)this.rabbit.getVerticalFaceSpeed());
            if (this.getIsAboveDestination()) {
                World world = this.rabbit.world;
                BlockPos blockpos = this.destinationBlock.up();
                IBlockState iblockstate = world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();
                if (this.canRaid && block instanceof BlockCarrot) {
                    Integer integer = iblockstate.get(BlockCarrot.AGE);
                    if (integer == 0) {
                        world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
                        world.destroyBlock(blockpos, true);
                    } else {
                        world.setBlockState(blockpos, iblockstate.with(BlockCarrot.AGE, Integer.valueOf(integer - 1)), 2);
                        world.playEvent(2001, blockpos, Block.getStateId(iblockstate));
                    }

                    this.rabbit.carrotTicks = 40;
                }

                this.canRaid = false;
                this.runDelay = 10;
            }

        }

        /**
         * Return true to set given position as destination
         */
        protected boolean shouldMoveTo(IWorldReaderBase worldIn, BlockPos pos) {
            Block block = worldIn.getBlockState(pos).getBlock();
            if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid) {
                pos = pos.up();
                IBlockState iblockstate = worldIn.getBlockState(pos);
                block = iblockstate.getBlock();
                if (block instanceof BlockCarrot && ((BlockCarrot)block).isMaxAge(iblockstate)) {
                    this.canRaid = true;
                    return true;
                }
            }

            return false;
        }
    }

    protected SoundEvent getJumpSound() {
        return SoundEvents.ENTITY_RABBIT_JUMP;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_RABBIT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_RABBIT_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_RABBIT_DEATH;
    }

    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_RABBIT_JUMP, 0.15F, 1.0F);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }

    public EntityAgeable createChild(EntityAgeable ageable) {
        this.mateGenes = ((EnhancedRabbit) ageable).getGenes();
        mixMateMitosisGenes();
        mixMitosisGenes();
        EnhancedRabbit enhancedrabbit = new EnhancedRabbit(this.world);
        enhancedrabbit.setGrowingAge(0);
        int[] babyGenes = getBunnyGenes();
        enhancedrabbit.setGenes(babyGenes);
        enhancedrabbit.setSharedGenes(babyGenes);
        return enhancedrabbit;
    }

    public void writeAdditional(NBTTagCompound compound) {
        super.writeAdditional(compound);

        //store this rabbits's genes
        NBTTagList geneList = new NBTTagList();
        for (int i = 0; i < genes.length; i++) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setInt("Gene", genes[i]);
            geneList.add(nbttagcompound);
        }
        compound.setTag("Genes", geneList);

        //store this rabbits's mate's genes
        NBTTagList mateGeneList = new NBTTagList();
        for (int i = 0; i < mateGenes.length; i++) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setInt("Gene", mateGenes[i]);
            mateGeneList.add(nbttagcompound);
        }
        compound.setTag("FatherGenes", mateGeneList);
    }

    /**
     * (abstract) Protected helper method to read subclass entity assets from NBT.
     */
    public void readAdditional(NBTTagCompound compound) {
        super.readAdditional(compound);

        NBTTagList geneList = compound.getList("Genes", 10);
        for (int i = 0; i < geneList.size(); ++i) {
            NBTTagCompound nbttagcompound = geneList.getCompound(i);
            int gene = nbttagcompound.getInt("Gene");
            genes[i] = gene;
        }

        NBTTagList mateGeneList = compound.getList("FatherGenes", 10);
        for (int i = 0; i < mateGeneList.size(); ++i) {
            NBTTagCompound nbttagcompound = mateGeneList.getCompound(i);
            int gene = nbttagcompound.getInt("Gene");
            mateGenes[i] = gene;
        }

        setSharedGenes(genes);

    }

    @OnlyIn(Dist.CLIENT)
    public String getRabbitTexture() {
        if (this.rabbitTextures.isEmpty()) {
            this.setTexturePaths();
        }
        return this.rabbitTextures.stream().collect(Collectors.joining("/","enhanced_rabbit",""));

    }

    @OnlyIn(Dist.CLIENT)
    public String[] getVariantTexturePaths() {
        if (this.rabbitTextures.isEmpty()) {
            this.setTexturePaths();
        }

        return this.rabbitTextures.stream().toArray(String[]::new);
    }

    @OnlyIn(Dist.CLIENT)
    private void setTexturePaths() {
        int[] genesForText = getSharedGenes();
        if (genesForText != null) {
            int under = 0;
            int lower = 0;
            int middle = 0;
            int higher = 0;
            int top = 0;
            int dilutions = 4;
            int tints = 6;
            int dutch = 0;
            int broken = 0;
            int spothead = 0;
            int spotfoot = 0;
            int spotbody = 0;
            int fur = 0;
            int eyes = 0;
            int vienna = 0;
            int skin = 0;
            // i is a random modifier
            String i = getCachedUniqueIdString();


            if(genesForText[4] == 5 && genesForText[5] == 5){
                //Red Eyed White (albino)
                under = 2;
                eyes = 5;

            }else if(genesForText[14] == 2 && genesForText[15] == 2){
                //Blue Eyed White
                under = 2;
                vienna = 1;

            }else {

                if ( genesForText[4] == 1 || genesForText[5] == 1 ){
                    if (genesForText[0] == 1 || genesForText[1] == 1) {
                        //agouti
                    } else if (genesForText[0] == 2 || genesForText[1] == 2) {
                        //otter
                    } else {
                        //self
                    }
                }else if ( genesForText[4] == 2 || genesForText[5] == 2 ){
                    if (genesForText[0] == 1 || genesForText[1] == 1) {
                        //agouti
                    } else if (genesForText[0] == 2 || genesForText[1] == 2) {
                        //otter
                    } else {
                        //self
                    }
                }else if ( genesForText[4] == 3 && genesForText[5] == 3 ){
                    if (genesForText[0] == 1 || genesForText[1] == 1) {
                        //agouti
                    } else if (genesForText[0] == 2 || genesForText[1] == 2) {
                        //otter
                    } else {
                        //self
                    }
                }else if ( genesForText[4] == 3 || genesForText[5] == 3 ){
                    if (genesForText[0] == 1 || genesForText[1] == 1) {
                        //agouti
                    } else if (genesForText[0] == 2 || genesForText[1] == 2) {
                        //otter
                    } else {
                        //self
                    }
                }else if ( genesForText[4] == 4 || genesForText[5] == 4 ){
                    if (genesForText[0] == 1 || genesForText[1] == 1) {
                        //agouti
                    } else if (genesForText[0] == 2 || genesForText[1] == 2) {
                        //otter
                    } else {
                        //self
                    }
                }

                //top layer "black" colour variations
                if (genesForText[2] == 2 && genesForText[3] == 2) {
                    if (genesForText[6] == 2 && genesForText[7] == 2) {
                        //lilac
                        under = under - 1;
                        middle = middle + 1;
                        top = top + 3;
                    } else {
                        //chocolate
                        middle = middle + 1;
                        top = top + 2;
                    }
                } else {
                    if (genesForText[6] == 2 && genesForText[7] == 2) {
                        //blue
                        middle = middle + 1;
                        top = top + 1;
                    }
                }
                if (genesForText[10] == 2 && genesForText[11] == 2) {
                    broken = 2;
                } else if (genesForText[10] == 2 || genesForText[11] == 2) {
                    broken = 1;
                }
                if (genesForText[12] == 2 && genesForText[13] == 2) {
                    dutch = 1;
                }
                //Vienna Eyes and Spots
                if (genesForText[14] == 2 || genesForText[15] == 2) {
                    //NOT GENETIC VARIATIONS
                    if (Character.isDigit(i.charAt(2)) && Character.isLetter(i.charAt(5))) {
                        vienna = 1;
                    } else if (Character.isDigit(i.charAt(5))) {
                        if ((i.charAt(5)) <= 4) {
                            vienna = 4;
                        } else {
                            vienna = 2;
                        }
                    }
                    //spothead 0-6
                    if (Character.isDigit(i.charAt(4)) && Character.isLetter(i.charAt(6))) {
                        spothead = 1;
                    } else if (Character.isDigit(i.charAt(6))) {
                        if ((i.charAt(6)) <= 4) {
                            spothead = 2;
                        } else {
                            spothead = 3;
                        }
                    } else if (Character.isDigit(i.charAt(7))) {
                        if (i.charAt(7) >= 5) {
                            spothead = 4;
                        } else {
                            spothead = 5;
                        }
                    } else {
                        if (Character.isLetter(i.charAt(8))) {
                            spothead = 6;
                        }
                    }
                    //END OF NON GENETIC VARIATIONS
                }
            }

                //coat genes 26 angora, 28 rex, 30 satin
                if(genesForText[28] == 2 && genesForText[29] == 2){
                    fur = 2;
                }else if (genesForText[26] == 2 && genesForText[27] == 2){
                    fur = 1;
                }else if (genesForText[30] == 2 && genesForText[31] == 2){
                    fur = 3;
                }




            this.rabbitTextures.add(RABBIT_TEXTURES_UNDER[under]);
            if (lower != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_LOWER[lower]);
            }
            if(middle != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_MIDDLE[middle]);
            }
            if(higher != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_HIGHER[higher]);
            }
//            if(top != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_TOP[0]);
//            }
            if(dutch != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_DUTCH[dutch]);
            }
            if(broken != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_BROKEN[broken]);
            }
            if(spothead != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_HEADSPOT[spothead]);
            }
            if(spotfoot != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_FOOTSPOT[spotfoot]);
            }
            if(spotbody != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_BODYSPOT[spotbody]);
            }
            this.rabbitTextures.add(RABBIT_TEXTURES_FUR[fur]);
            this.rabbitTextures.add(RABBIT_TEXTURES_EYES[eyes]);
            if(vienna != 0) {
                this.rabbitTextures.add(RABBIT_TEXTURES_VIENNAEYES[vienna]);
            }
            this.rabbitTextures.add(RABBIT_TEXTURES_SKIN[skin]);


        } //if genes are not null end bracket

    } // setTexturePaths end bracket

    public void mixMitosisGenes() {
        punnetSquare(mitosisGenes, genes);
    }

    public void mixMateMitosisGenes() {
        punnetSquare(mateMitosisGenes, mateGenes);
    }

    public void punnetSquare(int[] mitosis, int[] parentGenes) {
        Random rand = new Random();
        for (int i = 0; i < genes.length; i = (i + 2)) {
            boolean mateOddOrEven = rand.nextBoolean();
            if (mateOddOrEven) {
                mitosis[i] = parentGenes[i + 1];
                mitosis[i + 1] = parentGenes[i];
            } else {
                mitosis[i] = parentGenes[i];
                mitosis[i + 1] = parentGenes[i + 1];
            }
        }
    }

    public int[] getBunnyGenes() {
        Random rand = new Random();
        int[] bunnyGenes = new int[GENES_LENGTH];

        for (int i = 0; i < genes.length; i++) {
            boolean thisOrMate = rand.nextBoolean();
            if (thisOrMate) {
                bunnyGenes[i] = mitosisGenes[i];
            } else {
                bunnyGenes[i] = mateMitosisGenes[i];
            }
        }

        return bunnyGenes;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata, @Nullable NBTTagCompound itemNbt) {
        livingdata = super.onInitialSpawn(difficulty, livingdata, itemNbt);
        int[] spawnGenes;

        if (livingdata instanceof GroupData) {
            spawnGenes = ((GroupData) livingdata).groupGenes;
        } else {
            spawnGenes = createInitialGenes();
            livingdata = new GroupData(spawnGenes);
        }

        this.genes = spawnGenes;
        setSharedGenes(genes);
        return livingdata;
    }

    private int[] createInitialGenes() {
        int[] initialGenes = new int[GENES_LENGTH];
        //TODO create biome WTC variable [hot and dry biomes, cold biomes ] WTC is neutral biomes "all others"


        //[ 0=minecraft wildtype, 1=jungle wildtype, 2=savanna wildtype, 3=cold wildtype, 4=swamp wildtype ]
//        int wildType = 0;
//        Biome biome = this.world.getBiome(new BlockPos(this));

//        if (biome.getDefaultTemperature() >= 0.9F && biome.getRainfall() > 0.8F) // hot and wet (jungle)
//        {
//            wildType = 1;
//        }


/**
 * Genes List
 */

        /**
         * Colour Genes
         */

        //Agouti [ Agouti, Tan, Self ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[0] = (ThreadLocalRandom.current().nextInt(3) + 1);

        } else {
            initialGenes[0] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[1] = (ThreadLocalRandom.current().nextInt(3) + 1);

        } else {
            initialGenes[1] = (1);
        }

        //Brown/Chocolate [ wildtype, brown ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[2] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[2] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[3] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[3] = (1);
        }

        //Colour Completion [ Wildtype+, Dark Chinchilla, Light Chinchilla, Himalayan, Albino ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[4] = (ThreadLocalRandom.current().nextInt(5) + 1);

        } else {
            initialGenes[4] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[5] = (ThreadLocalRandom.current().nextInt(5) + 1);

        } else {
            initialGenes[5] = (1);
        }

        //Dilute [ wildtype, dilute ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[6] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[6] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[7] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[7] = (1);
        }

        //E Locus [ Dominant Black, Steel, Wildtype, Japanese Brindle, Non Extension ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[8] = (ThreadLocalRandom.current().nextInt(4) + 1);

        } else {
            initialGenes[8] = (3);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[9] = (ThreadLocalRandom.current().nextInt(4) + 1);

        } else {
            initialGenes[9] = (3);
        }

        //Spotted [ wildtype, spotted ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[10] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[10] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[11] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[11] = (1);
        }

        //Dutch [ wildtype, dutch]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[12] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[12] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[13] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[13] = (1);
        }

        //Vienna [ wildtype, vienna]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[14] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[14] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[15] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[15] = (1);
        }

        //Wideband [ wildtype, wideband]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[16] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[16] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[17] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[17] = (1);
        }

        //Silver [ wildtype, silver]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[18] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[18] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[19] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[19] = (1);
        }

        //Lutino [ wildtype, lutino]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[20] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[20] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[21] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[21] = (1);
        }

        /**
         * Coat Genes
         */

        //Furless [ wildtype, furless]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[22] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[22] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[23] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[23] = (1);
        }

        //Lion Mane [ wildtype, lion mane]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[24] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[24] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[25] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[25] = (1);
        }

        //Angora [ wildtype, angora]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[26] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[26] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[27] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[27] = (1);
        }

        //Rex [ wildtype, rex]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[28] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[28] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[29] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[29] = (1);
        }

        //Satin [ wildtype, satin]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[30] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[30] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[31] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[31] = (1);
        }

        //Waved [ wildtype, waved]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[32] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[32] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[33] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[33] = (1);
        }

        /**
         * Shape and Size Genes
         */

        //Dwarf [ wildtype, dwarf]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[34] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[34] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[35] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[35] = (1);
        }

        //Lop1 [ wildtype, halflop, lop1]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[36] = (ThreadLocalRandom.current().nextInt(3) + 1);

        } else {
            initialGenes[36] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[37] = (ThreadLocalRandom.current().nextInt(3) + 1);

        } else {
            initialGenes[37] = (1);
        }

        //Lop2 [ wildtype, lop2]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[38] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[38] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[39] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[39] = (1);
        }

        //long ears [ wildtype, longer ears, longest ears]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[40] = (ThreadLocalRandom.current().nextInt(3) + 1);

        } else {
            initialGenes[40] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[41] = (ThreadLocalRandom.current().nextInt(3) + 1);

        } else {
            initialGenes[41] = (1);
        }

        //ear length bias [ normal ears, shorter ears, longest ears ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[42] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[42] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[43] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[43] = (1);
        }

        //longer body [ normal length, longer body ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[44] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[44] = (1);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[45] = (ThreadLocalRandom.current().nextInt(2) + 1);

        } else {
            initialGenes[45] = (1);
        }

        //Size tendency [ small, normal, small2, big, extra large ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[46] = (ThreadLocalRandom.current().nextInt(5) + 1);

        } else {
            initialGenes[46] = (2);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[47] = (ThreadLocalRandom.current().nextInt(5) + 1);

        } else {
            initialGenes[47] = (2);
        }

        //Size Enhancer [ big, normal, small ]
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[48] = (ThreadLocalRandom.current().nextInt(3) + 1);

        } else {
            initialGenes[48] = (2);
        }
        if (ThreadLocalRandom.current().nextInt(100) > WTC) {
            initialGenes[49] = (ThreadLocalRandom.current().nextInt(3) + 1);

        } else {
            initialGenes[49] = (2);
        }

        return initialGenes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int[] getGenes() {
        return this.genes;
    }

    public static class GroupData implements IEntityLivingData {

        public int[] groupGenes;

        public GroupData(int[] groupGenes) {
            this.groupGenes = groupGenes;
        }

    }
}
