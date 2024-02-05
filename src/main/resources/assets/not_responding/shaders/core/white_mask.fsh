#version 120

uniform sampler2D DiffuseSampler;

varying vec2 texCoord;

uniform float MaskAmount;

void main(){
    vec4 diffuseColor = texture2D(DiffuseSampler, texCoord);
    vec4 whiteColor = vec4(1.0, 1.0, 1.0, 1.0);
    vec4 outColor = mix(diffuseColor, whiteColor, MaskAmount);
    gl_FragColor = vec4(outColor.rgb, 1.0);
}
