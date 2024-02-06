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

import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.zhuruoling.notResponding.Setting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AccessibilityOptionsScreen.class)
public class OptionsScreenMixin {
    @Inject(method = "getOptions",
            at = @At(value = "RETURN"),
            cancellable = true)
    private static void inj(GameOptions gameOptions, CallbackInfoReturnable<SimpleOption<?>[]> cir){
        var oldArray = cir.getReturnValue();
        SimpleOption<?>[] newArray = new SimpleOption[oldArray.length+1];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        newArray[newArray.length-1] = Setting.getInstance().getMaskAlpha();
        cir.setReturnValue(newArray);
    }
}
