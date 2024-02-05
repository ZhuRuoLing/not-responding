package net.zhuruoling.notResponding;

import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.resource.ResourceFactory;

public class Shader {
    private static ShaderProgram whiteMaskProgram;

    public static void whiteMaskProgramLoadCallback(ResourceFactory factory) throws Exception {
        whiteMaskProgram = new ShaderProgram(factory, "white_mask", VertexFormats.POSITION_COLOR);
    }
}
