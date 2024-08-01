package nl.teamdiopside.seamless.forge.mixin;

import com.teamabnormals.upgrade_aquatic.common.block.PickerelweedDoublePlantBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
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
@Mixin(PickerelweedDoublePlantBlock.class)
public abstract class PickerelweedDoublePlantBlockMixin extends Block implements BonemealableBlock, SimpleWaterloggedBlock {

    public PickerelweedDoublePlantBlockMixin(Properties properties) {
        super(properties);
    }

    @Shadow @Final public static EnumProperty<DoubleBlockHalf> HALF;

    @Shadow @Final protected static VoxelShape SHAPE;

    @Inject(method = "m_5940_", at = @At("HEAD"), cancellable = true)
    public void getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(state.getValue(HALF) == DoubleBlockHalf.LOWER ? Block.box(2, 0, 2, 14, 16, 14) : SHAPE);
    }
}
