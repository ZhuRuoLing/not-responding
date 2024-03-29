/*
 * This file is part of the NotResponding project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2024  ZhuRuoLing and contributors
 *
 * NotResponding is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NotResponding is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NotResponding.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.zhuruoling.notResponding.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.zhuruoling.notResponding.Setting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;draw()V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    void inj(float tickDelta, long startTime, boolean tick, CallbackInfo ci, boolean bl, MatrixStack matrixStack, DrawContext drawContext){
        drawContext.fill(0,0,drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), Setting.getInstance().getMaskAlphaColor());
    }
}
