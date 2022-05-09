package net.treset.audio_hotkeys.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.treset.audio_hotkeys.gui.OverlayManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("TAIL"), cancellable = true)
    public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info) {

        OverlayManager.renderOverlay(matrices);
    }
}
