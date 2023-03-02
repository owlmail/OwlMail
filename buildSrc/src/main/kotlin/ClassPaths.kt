object ClassPaths {
    const val DaggerHiltClassPath =
        "com.google.dagger:hilt-android-gradle-plugin:${ClassPathVersion.DaggerHilt}"

    const val GoogleServicesClassPath =
        "com.google.gms:google-services:${ClassPathVersion.GoogleServicesClassPath}"

    const val FirebasePerformanceClassPath =
        "com.google.firebase:perf-plugin:${ClassPathVersion.FirebasePerformanceClassPath}"

    const val FirebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${ClassPathVersion.FirebaseCrashlyticsClassPath}"

    const val NavigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${ClassPathVersion.Navigation}"

    const val Detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${ClassPathVersion.Detekt}"

    const val KtLint = "org.jlleitschuh.gradle:ktlint-gradle:${ClassPathVersion.KtLint}"
}
