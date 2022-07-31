import org.gradle.api.JavaVersion

object OwlMailVersion {
    const val CompileSdk = 32
    const val MinSdk = 23
    const val TargetSdk = 32

    val JDKVersion = JavaVersion.VERSION_1_8
    val JvmTarget = JDKVersion.toString()

    const val ApplicationId = "github.owlmail.app"
    const val AppVersionCode = 1
    const val AppVersionName = "1.0"


    const val CoreKTX = "1.8.0"
    const val AppCompat = "1.4.2"
    const val Material = "1.6.1"
    const val ConstraintLayout = "2.1.4"
    const val Junit = "4.13.2"
    const val JunitAndroid = "1.1.3"
    const val Espresso = "3.4.0"


    const val DaggerHilt = "2.43"
}