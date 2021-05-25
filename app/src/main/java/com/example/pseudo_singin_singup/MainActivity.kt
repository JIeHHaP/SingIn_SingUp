package com.example.pseudo_singin_singup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pseudo_singin_singup.constance.Constance
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reg.*

//variables for the registration data
    private var surname : String = "Empty"
    private var name : String = "Empty"
    private var patronymic : String = "Empty"
    private var ph_nmb : String = "Empty"
    private var email : String = "Empty"
    private var pass : String = "Empty"
    private var ava_img_id : Int = 0


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if (requestCode == Constance.REQUEST_CODE_SIGN_IN){ //if received data for SignIn
            val em = data?.getStringExtra(Constance.EMAIL)
            val p = data?.getStringExtra(Constance.PASSWORD)
            if (email == em && pass == p){ //check Email and password
                ivAvatar.visibility = View.VISIBLE //show imageView avatar
                ivAvatar.setImageResource(ava_img_id) //get avatar img
                val textInfo = "$surname $name $patronymic \n $ph_nmb"
                tvInfo.text = textInfo      //show info
                SignIn_btn.visibility = View.GONE //hide *Sign in* btn
                SignUp_btn.text = "Выйти"   //change *Sign Up* btn text
            } else { //if enter wrong data
                ivAvatar.visibility = View.VISIBLE
                ivAvatar.setImageResource(R.drawable.not_found_img)
                tvInfo.text = R.string.Error.toString()
            }
        } else if (requestCode == Constance.REQUEST_CODE_SIGN_UP) { // if received data for SignUp
            email = data?.getStringExtra(Constance.EMAIL)!!
            surname = data.getStringExtra(Constance.SURNAME)!!
            name = data.getStringExtra(Constance.NAME)!!
            patronymic = data.getStringExtra(Constance.PATRONYMIC)!!
            pass = data.getStringExtra(Constance.PASSWORD)!!
            ph_nmb = data.getStringExtra(Constance.PHONE_NUMBER)!!
            ava_img_id = data.getIntExtra(Constance.AVATAR_ID, 0)
            ivAvatar.visibility = View.VISIBLE //show imageView avatar
            ivAvatar.setImageResource(ava_img_id) //get avatar img
            val textInfo = "$surname $name $patronymic \n $ph_nmb"
            tvInfo.text = textInfo      //show info
            SignIn_btn.visibility = View.GONE //hide *Sign in* btn
            SignUp_btn.text = "Выйти"   //change *Sign Up* btn text

        }

    }

    fun OnClickSignUp(view: View) {
        val intent = Intent(this, RegActivity::class.java) // data transfer fo second activity
        intent.putExtra(
            Constance.SIGN_STATE,
            Constance.SIGN_STATE_UP
        ) // receiving data during registration
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)
    }

    fun OnClickSignIn(view: View){
        if (ivAvatar.visibility == View.VISIBLE && tvInfo.text != R.string.Error.toString()) {
            ivAvatar.visibility = View.GONE //hide imageView avatar
            SignIn_btn.visibility = View.VISIBLE //show *Sign in* btn
            SignUp_btn.text = getString(R.string.SignIn)   //return *Sign Up* btn text
            tvInfo.text = ""
        } else {
            val intent1 = Intent(this, RegActivity::class.java)
            intent1.putExtra(Constance.SIGN_STATE, Constance.SIGN_STATE_IN)
            startActivityForResult(intent1, Constance.REQUEST_CODE_SIGN_IN)
        }
    }




}

