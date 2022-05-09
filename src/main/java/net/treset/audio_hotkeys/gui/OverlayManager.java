package net.treset.audio_hotkeys.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.treset.audio_hotkeys.HotkeyMod;
import net.treset.vanillaconfig.tools.TextTools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class OverlayManager {
    public static AudioOverlay renderOverlay;
    public static boolean shouldRender = false;
    public static CompletableFuture<Void> activeKeeper;
    private static final Identifier SPRITESHEET = new Identifier(HotkeyMod.MOD_ID, "textures/gui/speaker.png");

    public static void drawOverlay(AudioOverlay ol) {
        if(activeKeeper != null) activeKeeper.cancel(true);
        renderOverlay = ol;
        activeKeeper = CompletableFuture.runAsync(() -> keepOverlayActive(ol));
    }

    public static void keepOverlayActive(AudioOverlay ol) {
        shouldRender = true;
        try {
            TimeUnit.MILLISECONDS.sleep(ol.timeActive);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ol != renderOverlay) return;
        shouldRender = false;
    }

    public static void renderOverlay(MatrixStack matrices) {
        if(!shouldRender || renderOverlay == null) return;

        RenderSystem.setShaderTexture(0, SPRITESHEET);
        DrawableHelper.drawTexture(matrices, 5, 5, renderOverlay.coordinate.getCoordinates()[0], renderOverlay.coordinate.getCoordinates()[1], 24, 24, 48, 48);
        DrawableHelper.drawTextWithShadow(matrices, MinecraftClient.getInstance().textRenderer, Text.of(TextTools.translateOrDefault(renderOverlay.text)), 31, 13, 0xffffff);
    }
}
