object ApplicationId {
    const val id = "com.michael.pokemon"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"

    const val buildToolsVersion = "29.0.2"
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val minSdkVersion = 17
}

object Version {
    //common
    const val kotlin = "1.3.61"
    const val gradle = "3.5.1"
    const val material = "1.1.0-alpha05"
    const val multiDex = "2.0.1"

    //androidx
    const val constraintLayout = "1.1.2"
    const val appCompat = "1.0.0"
    const val recyclerView = "1.0.0"

    //network
    const val retrofit = "2.6.0"
    const val rxretrofit = "2.4.0"
    const val okhttpLogging = "3.11.0"
    const val mockWebServer = "3.11.0"

    //thread
    const val coroutines = "1.3.0-M2"
    const val coroutinesAdapter = "0.9.2"

    //media
    const val glide = "4.8.0"

    //data
    const val gson = "2.8.5"

    //jetpack
    const val archLifecycle = "2.0.0"
    const val ktx = "1.1.0"

    //di
    const val dagger = "2.21"

    //misc
    const val deeplink = "4.1.0"
    const val fastAdapter = "4.1.0"
    const val facebookShimmer = "0.5.0"
    const val gradleDependencyGraph = "0.6.0-SNAPSHOT"

    //testing
    const val jUnit = "4.12"
    const val testRunner = "1.1.0-alpha4"
    const val espresso = "3.1.1"
    const val mockito = "3.0.0"
    const val mockk = "1.9.3"
    const val mockTestRunner = "0.3.1"
    const val rules = "1.1.0"
    const val ext = "1.0.0"
    const val jacoco = "0.8.3"

    //debugging
    const val stetho = "1.5.1"
    const val fbFlipper = "0.32.2"
    const val soLoader = "0.8.2"
    const val leakCanary = "1.6.1"
}

object Android {
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    val cardView = "androidx.cardview:cardview:${Version.appCompat}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    val gson = "com.google.code.gson:gson:${Version.gson}"
    val design = "com.google.android.material:material:${Version.material}"
    val multiDex = "androidx.multidex:multidex:${Version.multiDex}"
}

object Jetpack {
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Version.archLifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.archLifecycle}"
    val ktx = "androidx.core:core-ktx:${Version.ktx}"
}

object Dependencies {
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    val gradle = "com.android.tools.build:gradle:${Version.gradle}"
}

object Retrofit {
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    val scalarConverter = "com.squareup.retrofit2:converter-scalars:${Version.retrofit}"
    val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Version.rxretrofit}"
    val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLogging}"
    val coroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.coroutinesAdapter}"
    val mock = "com.squareup.retrofit2:retrofit-mock:${Version.retrofit}"
}

object Coroutines {
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
}

object Glide {
    val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"
}

object Dagger {
    val dagger = "com.google.dagger:dagger:${Version.dagger}"
    val android = "com.google.dagger:dagger-android:${Version.dagger}"
    val androidSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
    val compiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    val processor = "com.google.dagger:dagger-android-processor:${Version.dagger}"
}

object Testing {
    val jUnit = "junit:junit:${Version.jUnit}"
    val testRunner = "androidx.test:runner:${Version.testRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    val coreTesting = "androidx.arch.core:core-testing:${Version.archLifecycle}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Version.espresso}"
    val espressoIdleResources =
        "androidx.test.espresso:espresso-idling-resource:${Version.espresso}"
    val espressoIntent = "androidx.test.espresso:espresso-intents:${Version.espresso}"
    val mockito = "org.mockito:mockito-inline:${Version.mockito}"
    val mockKtRunner = "de.jodamob.kotlin:kotlin-runner-junit4:${Version.mockTestRunner}"
    val mockk = "io.mockk:mockk:${Version.mockk}"
    val mockkInstrument = "io.mockk:mockk-android:${Version.mockk}"
    val androidX = "androidx.arch.core:core-testing:${Version.archLifecycle}"
    val runner = "androidx.test:runner:${Version.rules}"
    val rules = "androidx.test:rules:${Version.rules}"
    val core = "androidx.test:core:${Version.rules}"
    val jacoco = "org.jacoco:org.jacoco.core:${Version.jacoco}"

    val extJunit = "androidx.test.ext:junit:${Version.ext}"
    val extTruth = "androidx.test.ext:truth:${Version.ext}"
}

object FastAdapter {
    val core = "com.mikepenz:fastadapter:${Version.fastAdapter}"
    val scroll = "com.mikepenz:fastadapter-extensions-scroll:${Version.fastAdapter}"
    val ui = "com.mikepenz:fastadapter-extensions-ui:${Version.fastAdapter}"
}

object Debugging {
    val fbFlipper = "com.facebook.flipper:flipper:${Version.fbFlipper}"
    val fbFlipperNoop = "com.facebook.flipper:flipper-noop:${Version.fbFlipper}"
    val fbFlipperNetwork = "com.facebook.flipper:flipper-network-plugin:${Version.fbFlipper}"
    val soLoader = "com.facebook.soloader:soloader:${Version.soLoader}"
    val fbFlipperLeakCanary = "com.facebook.flipper:flipper-leakcanary-plugin:${Version.fbFlipper}"
    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"
    val leakCanaryNoop = "com.squareup.leakcanary:leakcanary-android-no-op:${Version.leakCanary}"
    val stetho = "com.facebook.stetho:stetho:${Version.stetho}"
    val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${Version.stetho}"
}

object Misc {
    val deeplink = "com.airbnb:deeplinkdispatch:${Version.deeplink}"
    val deeplinkProcessor = "com.airbnb:deeplinkdispatch-processor:${Version.deeplink}"
    val shimmer = "com.facebook.shimmer:shimmer:${Version.facebookShimmer}"
    val gradleDependencyGraph =
        "com.vanniktech:gradle-dependency-graph-generator-plugin:${Version.gradleDependencyGraph}"
}