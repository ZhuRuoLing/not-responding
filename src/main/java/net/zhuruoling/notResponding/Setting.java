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

package net.zhuruoling.notResponding;

import com.mojang.serialization.Codec;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.Properties;

public class Setting {
    private static final Setting instance = new Setting();
    private static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve("not-responding.properties");
    private final SimpleOption<Integer> maskAlpha = new SimpleOption<>(
            "not_responding.option.alpha",
            SimpleOption.emptyTooltip(),
            GameOptions::getGenericValueText,
            new SimpleOption.ValidatingIntSliderCallbacks(0, 255),
            Codec.intRange(0,255),
            0xaa,
            value -> this.write()
    );

    public int getMaskAlphaColor() {
        return (maskAlpha.getValue() % 256) << 24 | 0xffffff;
    }

    public static Setting getInstance() {
        return instance;
    }

    public SimpleOption<Integer> getMaskAlpha() {
        return maskAlpha;
    }

    public void write(){
        try {
            var fileWriter = new FileWriter(configPath.toFile());
            var prop = new Properties();
            prop.put("alpha", maskAlpha.getValue().toString());
            prop.store(fileWriter, "Not Responding");
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void load(){
        if (!configPath.toFile().exists()){
            try {
                configPath.toFile().createNewFile();
                write();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            var fileReader = new FileReader(configPath.toFile());
            var prop = new Properties();
            prop.load(fileReader);
            String stringValue = (String) prop.get("alpha");
            if (stringValue == null) {
                prop.put("alpha", Integer.toString(0xaa));
                stringValue = Integer.toString(0xaa);
            }
            maskAlpha.setValue(Integer.parseInt(stringValue));
            fileReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
