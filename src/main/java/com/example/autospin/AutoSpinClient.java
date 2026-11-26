package com.example.autospin;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class AutoSpinClient implements ClientModInitializer {
    public static final String MOD_ID = "autospin";

    private static final float MIN_SPEED = 2.0f;
    private static final float MAX_SPEED = 60.0f;
    private static final float SPEED_STEP = 3.0f;

    private boolean spinning = false;
    private float degreesPerTick = 12.0f;

    private KeyMapping toggleKey;
    private KeyMapping speedUpKey;
    private KeyMapping speedDownKey;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath(MOD_ID, "general")
        );

        toggleKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.autospin.toggle",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                category
        ));

        speedUpKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.autospin.speed_up",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_BRACKET,
                category
        ));

        speedDownKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.autospin.speed_down",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_BRACKET,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {
                spinning = false;
                return;
            }

            while (toggleKey.consumeClick()) {
                spinning = !spinning;
                if (spinning) {
                    client.player.displayClientMessage(
                            Component.translatable("message.autospin.enabled", degreesPerTick),
                            true
                    );
                } else {
                    client.player.displayClientMessage(
                            Component.translatable("message.autospin.disabled"),
                            true
                    );
                }
            }

            while (speedUpKey.consumeClick()) {
                degreesPerTick = Math.min(MAX_SPEED, degreesPerTick + SPEED_STEP);
                client.player.displayClientMessage(
                        Component.translatable("message.autospin.speed", degreesPerTick),
                        true
                );
            }

            while (speedDownKey.consumeClick()) {
                degreesPerTick = Math.max(MIN_SPEED, degreesPerTick - SPEED_STEP);
                client.player.displayClientMessage(
                        Component.translatable("message.autospin.speed", degreesPerTick),
                        true
                );
            }

            if (spinning && !client.isPaused() && client.screen == null) {
                client.player.setYRot(client.player.getYRot() + degreesPerTick);
            }
        });
    }
}
