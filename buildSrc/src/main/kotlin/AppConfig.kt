import org.gradle.api.JavaVersion

object AppConfig {
    const val CompileSdk = 32
    const val MinSdk = 23
    const val TargetSdk = 32

    val JDKVersion = JavaVersion.VERSION_1_8
    val JvmTarget = JDKVersion.toString()

    const val ApplicationId = "github.owlmail.app"
    const val AppVersionCode = 1
    const val AppVersionName = "0.0.1"
}
