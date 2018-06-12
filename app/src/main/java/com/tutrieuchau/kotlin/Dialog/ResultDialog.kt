package com.tutrieuchau.kotlin.Dialog

import android.app.DialogFragment
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.tutrieuchau.kotlin.Action.CompletedStatus
import com.tutrieuchau.kotlin.R

class ResultDialog() : DialogFragment() {
    lateinit var title :String
    lateinit var message :String
    var type : CompletedStatus = CompletedStatus.Success
    lateinit var callback : ResultDialogCallBackInteface
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        title = arguments["title"] as String
        message = arguments["message"] as String
        type = arguments["type"] as CompletedStatus
        callback = context as ResultDialogCallBackInteface
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.result_dialog, container,false)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        val txtTitle = view?.findViewById(R.id.resultTitle) as TextView
        txtTitle?.text = title
        val txtMessage = view?.findViewById(R.id.resultMessage) as TextView
        txtMessage?.text = message
        val btnOk = view?.findViewById(R.id.btnDismiss) as Button
        val imgIcon = view?.findViewById(R.id.resultImgIcon) as ImageView
        if (type == CompletedStatus.Failed){
            btnOk.setBackgroundResource(R.drawable.border_round_red)
            imgIcon.setBackgroundResource(R.drawable.circle_red)
            imgIcon.setImageResource(R.drawable.close)
        }

        btnOk.setOnClickListener {
            callback.onDismiss(type)
            dismiss()
        }

        return if(view != null)  view else super.onCreateView(inflater, container, savedInstanceState)
    }
}
interface ResultDialogCallBackInteface{
    fun onDismiss(status: CompletedStatus)
}