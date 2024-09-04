// Android Core Dependencies
object AndroidDependencies {
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompatVersion}"
    const val material = "com.google.android.material:material:${Version.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayoutVersion}"
    const val activityKtx = "androidx.activity:activity-ktx:${Version.activityKtxVersion}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleVersion}"
    const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.8.4" // Custom version
}

// Network Dependencies
object NetworkDependencies {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okHttpInterceptorVersion}"
}

// Image Dependencies
object ImageDependencies {
    const val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    const val circleImageView = "de.hdodenhof:circleimageview:${Version.circleImageViewVersion}"
}

// Database Dependencies
object DatabaseDependencies {
    const val roomKtx = "androidx.room:room-ktx:${Version.roomVersion}"
    const val roomCommon = "androidx.room:room-common:${Version.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.roomVersion}"
    const val roomTesting = "androidx.room:room-testing:2.5.0" // Custom version
}

// Test Dependencies
object TestDependencies {
    const val mockitoCore = "org.mockito:mockito-core:${Version.mockitoVersion}"
    const val mockitoInline = "org.mockito:mockito-inline:${Version.mockitoVersion}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutinesTestVersion}"
    const val coreTesting = "androidx.arch.core:core-testing:${Version.coreTestingVersion}"
    const val robolectric = "org.robolectric:robolectric:${Version.robolectricVersion}"
    const val androidxTestCore = "androidx.test:core:${Version.androidxTestCoreVersion}"
    const val junit = "junit:junit:${Version.junitVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"
    const val testRunner = "androidx.test:runner:${Version.testRunnerVersion}"
    const val testRules = "androidx.test:rules:${Version.testRulesVersion}"
    const val junitKtx = "androidx.test.ext:junit-ktx:${Version.junitKtxVersion}"
    const val extJunit = "androidx.test.ext:junit:${Version.extJunitVersion}"
}

//// Hilt Dependencies
//object HiltDependencies {
//    const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hiltVersion}"
//    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Version.hiltVersion}"
//}

//// Coroutine Dependencies
object CoroutineDependencies {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutinesTestVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesTestVersion}"
}