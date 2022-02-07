package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
class MainActivity : AppCompatActivity() {
    private lateinit var cameraM:CameraManager
    private lateinit var powerBtn:ImageButton
    var isFlash=false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        powerBtn=findViewById(R.id.power)
        cameraM=getSystemService(Context.CAMERA_SERVICE)as CameraManager
        powerBtn.setOnClickListener {
            flashLightOnOrOff(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashLightOnOrOff(v: View?) {
        if(isFlash)
        {
            val cameraListId=cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId,false)
            isFlash=false
            Log.d("TAG", "flashLightOnOrOff2: ")
            powerBtn.setImageResource(R.drawable.ic_power_off)
            textMsg("Flash Light is off!",this)

        }
        else
        {
            val cameraListId=cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId,true)
            isFlash=true
            Log.d("TAG", "flashLightOnOrOff: ")
            powerBtn.setImageResource(R.drawable.ic_power_on)
            textMsg("Flash Light is on!",this)

        }

    }

    private fun textMsg(s: String, c: Context) {
        Toast.makeText(c,s,Toast.LENGTH_SHORT).show()
    }

}