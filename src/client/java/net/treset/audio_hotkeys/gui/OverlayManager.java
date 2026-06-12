package net.treset.audio_hotkeys.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.treset.vanillaconfig.tools.TextTools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class OverlayManager {
    public static AudioOverlay renderOverlay;
    public static boolean shouldRender = false;
    public static CompletableFuture<Void> activeKeeper;

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

    public static void renderOverlay(GuiGraphicsExtractor ctx) {
        if(!shouldRender || renderOverlay == null || Minecraft.getInstance().gui.hud.isHidden()) return;

        ctx.blitSprite(RenderPipelines.GUI_TEXTURED, renderOverlay.sprite.getIdentifier(), 5, 5, 24, 24);
        ctx.text(Minecraft.getInstance().font, Component.literal(TextTools.translateOrDefault(renderOverlay.text)), 31, 13, 0xFFFFFFFF);
    }
}
