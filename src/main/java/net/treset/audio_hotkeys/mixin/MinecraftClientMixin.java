package net.treset.audio_hotkeys.mixin;


import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    /*@Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        HotkeyMod.LOGGER.info("Mixin init.");
    }*/

    @Inject(method = "render", at = @At("TAIL"), cancellable = true)
    public void onClientRender(CallbackInfo info) {
    }
}
