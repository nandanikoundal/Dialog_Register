package com.nandani.custom_dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nandani.custom_dialog.databinding.CustomLayoutBinding


class MainActivity : AppCompatActivity() {
    lateinit var tv_name : TextView
    lateinit var tv_email : TextView
    lateinit var tv : TextView
    lateinit var btn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_name=findViewById(R.id.tv_name)
        tv_email=findViewById(R.id.tv_email)
        tv=findViewById(R.id.tv)
        btn=findViewById(R.id.btn)

        btn.setOnClickListener{
            var dialogBinding= CustomLayoutBinding.inflate(layoutInflater)
            dialogBinding.etName.setText(tv_name.text.toString())
            dialogBinding.etEmail.setText(tv_email.text.toString())
            if(tv.text=="Male"){
                dialogBinding.Male.setChecked(true)
            }
            else if (tv.text=="Female"){
                dialogBinding.Female.setChecked(true)
            }
            else {
                dialogBinding.other.setChecked(true)
                dialogBinding.etother.visibility= View.VISIBLE
                dialogBinding.etother.setText(tv.text)
            }
            var dialog = Dialog( this)
            dialog.setCancelable(false)
            dialog.setContentView(dialogBinding.root)
            dialog.show()
            dialogBinding.Gender.setOnCheckedChangeListener { radioGroup, i ->
                when(i){
                    R.id.other->{
                        dialogBinding.etother.visibility=View.VISIBLE
                    }
                    else->{
                        dialogBinding.etother.visibility=View.INVISIBLE
                    }
                }
            }

            dialogBinding.btn1.setOnClickListener{
                if(dialogBinding.etName.text.toString().isNullOrEmpty()){
                    Toast.makeText( this,"Enter Name",Toast.LENGTH_SHORT ).show()
                    return@setOnClickListener
                }
                else if (dialogBinding.etEmail.text.toString().isNullOrEmpty()){
                    Toast.makeText( this,"Enter Email",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else if (dialogBinding.etother.visibility==View.VISIBLE && dialogBinding.etother.text.toString().isNullOrEmpty()){
                    Toast.makeText( this,"Specify Other Gender",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else{
                    tv_name.setText(dialogBinding.etName.text.toString())
                    tv_email.setText(dialogBinding.etEmail.text.toString())
                    if(dialogBinding.other.isChecked){
                        tv.setText(dialogBinding.etother.text.toString())
                    }
                    else if (dialogBinding.Male.isChecked){
                        tv.setText("Male")
                    }
                    else if (dialogBinding.Female.isChecked){
                        tv.setText("Female")
                    }
                    dialog.dismiss()
                }
            }
        }
    }
}
