package com.test.androidapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.test.androidapp.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val vb by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(vb.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        vb.button.setOnClickListener {
            val flutterIntent = FlutterActivity
                .withCachedEngine(MyApp.FLUTTER_ENGINE_ID) // 指定缓存的引擎ID
                .build(this)
            startActivity(flutterIntent)

            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                val map = mapOf("platform" to "Android")
                FlutterNativeProtocolManager.callFlutterMethod("getPageName",map,object : MethodChannel.Result{
                    override fun success(result: Any?) {
                        Log.d("MainActivity", "success: $result")
                    }

                    override fun error(
                        errorCode: String,
                        errorMessage: String?,
                        errorDetails: Any?
                    ) {
                        Log.d("MainActivity", "getPageName error: $errorCode $errorMessage $errorDetails")
                    }

                    override fun notImplemented() {
                        Log.d("MainActivity", "getPageName notImplemented")
                    }
                })
            }
        }
    }
}