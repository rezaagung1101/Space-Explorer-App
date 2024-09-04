plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    // Tambahkan dependency yang diperlukan untuk menulis script dalam buildSrc
    implementation(kotlin("stdlib"))
//    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20") // Sesuaikan dengan versi Kotlin Anda
}