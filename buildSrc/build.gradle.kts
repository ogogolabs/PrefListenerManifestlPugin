val agpVersion = "8.7.3"

group = "com.ogogo_labs.preflistener_manifest_modifier_plugin"
version = "0.1"

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    website = "https://github.com/ogogolabs/PrefListenerManifestlPugin"
    vcsUrl = "https://github.com/ogogolabs/PrefListenerManifestlPugin"
    plugins {
        register("ManifestModifier") {
            id = "com.ogogo_labs.preflistener_manifest_modifier_plugin"
            implementationClass = "com.ogogo_labs.preflistener_manifest_modifier_plugin.ManifestModifierPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle-api:$agpVersion")

}
