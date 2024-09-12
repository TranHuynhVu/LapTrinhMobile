plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.chodoidoute"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chodoidoute"
        minSdk = 24
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // thư viện hình ảnh
    implementation("androidx.cardview:cardview:1.0.0")

    implementation("com.google.android.material:material:1.9.0")

    // thư viện gửi yêu cầu http
    implementation ("com.android.volley:volley:1.2.1")
}