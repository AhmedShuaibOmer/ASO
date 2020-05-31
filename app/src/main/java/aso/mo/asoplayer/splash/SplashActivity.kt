package aso.mo.asoplayer.splash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.edit
import aso.mo.asoplayer.R
import aso.mo.asoplayer.common.BaseActivity
import aso.mo.asoplayer.getStarted.GetStartedActivity
import aso.mo.asoplayer.main.MainActivity
import aso.mo.asoplayer.main.common.data.CloudKeys
import aso.mo.asoplayer.main.common.data.Constants
import aso.mo.asoplayer.onBoarding.OnBoardingActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity() {

    private val preferences: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO Remove this
        goToNextScreen()


        setContentView(R.layout.activity_splash)

        Firebase.firestore.collection("properties").document("keys").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result!!.toObject(CloudKeys::class.java)
                    result?.let {
                        preferences.edit {
                            putString(Constants.acrHost, it.acrHost)
                            putString(Constants.acrKey, it.acrKey)
                            putString(Constants.acrKeyFile, it.acrKeyFile)
                            putString(Constants.acrSecret, it.acrSecret)
                            putString(Constants.acrSecretFile, it.acrSecretFile)
                            putString(Constants.lastFmKey, it.lastFmKey)
                            putString(Constants.spotifyClientId, it.spotifyClientId)
                            putString(Constants.spotifySecret, it.spotifySecret)
                        }
                        goToNextScreen()
                    }
                }
            }
    }

    private fun goToNextScreen() {
        val nextActivity =
            if (!preferences.getBoolean(OnBoardingActivity.HAS_SEEN_ON_BOARDING, false)) {
                OnBoardingActivity::class.java
            } else if (!isPermissionGranted(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                GetStartedActivity::class.java
            } else {
                MainActivity::class.java
            }
        val intent = Intent(this, nextActivity)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
