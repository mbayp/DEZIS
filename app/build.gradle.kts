plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltCompiler)
    alias(libs.plugins.navsafeargs)
    id("kotlin-parcelize")
}

android {
    namespace = "com.dezis.geeks_dezis"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dezis.geeks_dezis"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.core)
    implementation(libs.conscrypt.android)
    implementation(libs.bottomsheets)

    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview)

    implementation(libs.okHttpClient)
    implementation(libs.logging.interceptor)

    implementation(libs.glide)
    implementation(libs.circleimageview)

    // Scarlet
    implementation("com.tinder.scarlet:scarlet:0.1.12")
    implementation("com.tinder.scarlet:websocket-okhttp:0.1.12")
    implementation("com.tinder.scarlet:stream-adapter-rxjava2:0.1.12")

    //RX
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")

    implementation ("com.makeramen:roundedimageview:2.3.0")

}