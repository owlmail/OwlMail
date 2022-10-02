object Dependencies {

    // Core
    const val CoreKTX = "androidx.core:core-ktx:${DependencyVersion.CoreKTX}"
    const val AppCompat = "androidx.appcompat:appcompat:${DependencyVersion.AppCompat}"

    // Ui
    const val Material = "com.google.android.material:material:${DependencyVersion.Material}"
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${DependencyVersion.ConstraintLayout}"

    // Hilt
    const val DaggerHilt = "com.google.dagger:hilt-android:${DependencyVersion.DaggerHilt}"
    const val DaggerHiltCompiler =
        "com.google.dagger:hilt-android-compiler:${DependencyVersion.DaggerHilt}"


    // Firebase
    const val FirebaseBom =
        "com.google.firebase:firebase-bom:${DependencyVersion.FirebaseBom}"
    const val FirebasePerformance = "com.google.firebase:firebase-perf-ktx"
    const val FirebaseRemoteConfig = "com.google.firebase:firebase-config-ktx"
    const val FirebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val FirebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    // Test
    const val Junit = "junit:junit:${DependencyVersion.Junit}"
    const val JunitAndroid = "androidx.test.ext:junit:${DependencyVersion.JunitAndroid}"
    const val EspressoCore = "androidx.test.espresso:espresso-core:${DependencyVersion.Espresso}"

    // Coroutines
    const val CoroutineCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependencyVersion.Coroutines}"
    const val CoroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersion.Coroutines}"
}
