plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "ir.rezazarchi.hamrahorder"
    compileSdk = 35

    defaultConfig {
        applicationId = "ir.rezazarchi.hamrahorder"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        android.buildFeatures.buildConfig = true

        buildConfigField(
            "String",
            "MAPIR_API_KEY",
            "\"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjRmZWQ3YTVlNGZlZTBjMmIwMTljOGQ5MDY2YTVmZGMxYTA2NjA1MjRmNzBmNGZjNTU0ZmU1MmU5OWJiMTMzZjlhMmJkNmRjYWZjOWNmMWNhIn0.eyJhdWQiOiIzMTEzMiIsImp0aSI6IjRmZWQ3YTVlNGZlZTBjMmIwMTljOGQ5MDY2YTVmZGMxYTA2NjA1MjRmNzBmNGZjNTU0ZmU1MmU5OWJiMTMzZjlhMmJkNmRjYWZjOWNmMWNhIiwiaWF0IjoxNzM5ODY5NTAwLCJuYmYiOjE3Mzk4Njk1MDAsImV4cCI6MTc0MjM3NTEwMCwic3ViIjoiIiwic2NvcGVzIjpbImJhc2ljIl19.J9qq8aOfiI_qHnSqBb3KV1Oct9ekhCGTKtUornIvxvSMVIKpMqMeLUbb64RvD54T8ZmEEbyqUDv-0n7OP6mWIX02vqobQHJazSXq6DQbK77XzSr7yZqhYeSNgNuC9kaBZFGuhuQkkqbu6hm4MlENlrZgBxSTyBt3cyJff7JtprwKMNi0IHmzn0bSihgYdai0IklvgOd-jRWuLT2Iwpp_5XUKD1Wcw4VmYakGwtPqM1Y_x1AAe5BcMVe4xhzraLc8G7ZRPoAvkyui1pTOlrmDM-WuFjlmXf9VFVQs6Aptho19suGZuwR8ZwupQFoN3frOBFgzCkvyHhddeuckLO8fuA\""
        )

        buildConfigField(
            "String",
            "USER_NAME",
            "\"09822222222\""
        )

        buildConfigField(
            "String",
            "PASSWORD",
            "\"Sana12345678\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.bundles.coil)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.retrofit)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.map.android.sdk)
    testImplementation(libs.junit)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.mockk)
    testImplementation(libs.slf4j.simple)
    testImplementation(libs.assertk)
    testImplementation(libs.turbine)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}