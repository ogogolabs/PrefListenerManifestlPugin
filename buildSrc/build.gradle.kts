val agpVersion = "8.7.3"

group = "com.ogogo_labs.preflistener-manifest-modifier-plugin"
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
    plugins {
        register("ManifestModifier") {
            id = "preflistener-manifest-modifier"
            implementationClass = "ManifestModifierPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle-api:$agpVersion")

}
