package nl.teamdiopside.seamless.forge.mixin;

import com.teamabnormals.upgrade_aquatic.common.block.FloweringRushBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import nl.teamdiopside.seamless.annotation.mixin.ConditionalMixin;
import nl.teamdiopside.seamless.compat.Mods;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ConditionalMixin(mods = Mods.UPGRADE_AQUATIC)
@Mixin(FloweringRushBlock.class)
public abstract class FloweringRushBlockMixin extends TallFlowerBlock implements SimpleWaterloggedBlock, BonemealableBlock {

    @Shadow @Final private static VoxelShape SHAPE;

    public FloweringRushBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "m_5940_", at = @At("HEAD"), cancellable = true)
    public void getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(state.getValue(HALF) == DoubleBlockHalf.LOWER ? Block.box(2, 0, 2, 14, 16, 14) : SHAPE);
    }
}
