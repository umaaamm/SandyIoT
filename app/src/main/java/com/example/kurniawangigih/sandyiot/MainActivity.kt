package com.example.kurniawangigih.sandyiot

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

import java.io.UnsupportedEncodingException

class MainActivity : AppCompatActivity() {
    //Dekrlarasi variable global
    lateinit var client: MqttAndroidClient
    lateinit var clientId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //kondisi untuk cek koneksi internet
        if (checkInternet()) {
            //pemberitahuan jika koneksi ada
            Toast.makeText(MainActivity@ this, "Ada Internet", Toast.LENGTH_LONG).show();

            //koneksi ke MQTT

        } else {
            Toast.makeText(MainActivity@ this, "Tidak Ada internet", Toast.LENGTH_LONG).show();
        }

    }

    fun checkInternet(): Boolean {
        var connectStatus = true
        val ConnectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected == true) {
            connectStatus = true
        } else {
            connectStatus = false
        }
        return connectStatus
    }

    //untuk periksa kondisi reload
    fun cek() {
        val topic = "cek"
        val payload = "Periksa Kondisi"
        var encodedPayload = ByteArray(0)
        try {
            encodedPayload = payload.toByteArray(charset("UTF-8"))
            val message = MqttMessage(encodedPayload)
            client.publish(topic, message)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }


}
