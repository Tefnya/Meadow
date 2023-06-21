package net.satisfyu.meadow.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.satisfyu.meadow.registry.TagRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "cannotConnect(Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private static void checkCannotConnect(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(TagRegistry.CAN_NOT_CONNECT)) cir.setReturnValue(true);
    }
}
