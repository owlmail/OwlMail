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

    const val Lint = "com.android.tools.lint:lint-api:${DependencyVersion.Lint}"

    // moshi
    const val Moshi = "com.squareup.moshi:moshi-kotlin:${DependencyVersion.Moshi}"
    const val MoshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:${DependencyVersion.Moshi}"
    const val MoshiRetrofitConverter =
        "com.squareup.retrofit2:converter-moshi:${DependencyVersion.MoshiRetrofitConverter}"

    // paging
    const val Paging = "androidx.paging:paging-runtime:${DependencyVersion.Paging}"

    // navigation
    const val Navigation =
        "androidx.navigation:navigation-fragment-ktx:${DependencyVersion.Navigation}"
    const val NavigationUI = "androidx.navigation:navigation-ui-ktx:${DependencyVersion.Navigation}"
    const val FeaturesNavigation =
        "androidx.navigation:navigation-dynamic-features-fragment:${DependencyVersion.Navigation}"

    const val ViewPager = "androidx.viewpager2:viewpager2:${DependencyVersion.ViewPager}"
    const val Coil = "io.coil-kt:coil-base:${DependencyVersion.Coil}"

    // room
    const val RoomKTX = "androidx.room:room-ktx:${DependencyVersion.Room}"
    const val RoomCompiler = "androidx.room:room-compiler:${DependencyVersion.Room}"
}
