package com.juliarman.broadcastreceiver

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.juliarman.broadcastreceiver.PermissionManager.PermissionManager.check
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var downloadReceiver: BroadcastReceiver

    companion object{
        private const val SMS_REQUEST_CODE = 101
        const val ACTION_DOWNLOAD_STATUS = "download_status"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_permission.setOnClickListener(this)
        btn_download.setOnClickListener(this)

        downloadReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d(DownloadService.TAG, "Download Selesai")
                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show()
            }
        }

        val downloadIntentFilter = IntentFilter(ACTION_DOWNLOAD_STATUS)
        registerReceiver(downloadReceiver, downloadIntentFilter)
    }

    override fun onClick(v: View) {
        when {

            v.id == R.id.btn_permission -> PermissionManager.PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)

            v.id == R.id.btn_download -> {

                val downloadServiceIntent = Intent(this, DownloadService::class.java)
                startService(downloadServiceIntent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(downloadReceiver)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
       if (requestCode == SMS_REQUEST_CODE){
           when{

               grantResults[0] == PackageManager.PERMISSION_GRANTED-> Toast.makeText(this, "Sms Permisison Diterima", Toast.LENGTH_SHORT).show()
               else-> Toast.makeText(this, "Sms Permission Di tolak", Toast.LENGTH_SHORT).show()
           }
       }
       }
    }