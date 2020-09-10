package com.juliarman.broadcastreceiver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_sms_receiver.*
import java.nio.file.attribute.FileTime.from
import java.util.Date.from

class SmsReceiverActivity : AppCompatActivity(), View.OnClickListener {


    companion object{
        const val EXTRA_SMS_NO = "extra_sms_no"
        const val EXTRA_SMS_MESSAGE = "extra_sms_message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_receiver)

        title = getString(R.string.incoming_message)
        val senderNo = intent.getStringExtra(EXTRA_SMS_NO)
        val senderMessage = intent.getStringExtra(EXTRA_SMS_MESSAGE)
        tv_from.text = getString(R.string.from, senderNo)
        tv_message.text = senderMessage
        btn_close.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when(v.id){

            R.id.btn_close ->{
                finish()
            }
        }
    }
}