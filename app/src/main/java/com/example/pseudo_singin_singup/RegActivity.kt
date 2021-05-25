package com.example.pseudo_singin_singup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pseudo_singin_singup.constance.Constance
import kotlinx.android.synthetic.main.activity_reg.*

class RegActivity : AppCompatActivity() {
    private var signState = "Empty" //variable for the selected button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)



            signState = intent.getStringExtra(Constance.SIGN_STATE)!! // which button was pressed?
            if (signState == Constance.SIGN_STATE_IN) {  // pressed *Sign In*
                // hide elements
                etSurname.visibility = View.GONE
                etName.visibility = View.GONE
                etPatronymic.visibility = View.GONE
                etPhone.visibility = View.GONE
                bnt_ava.visibility = View.GONE
                ivAvatar.visibility = View.GONE
            }

            if (signState == Constance.SIGN_STATE_UP) { // pressed *Sign Up*
                val intent = Intent()
                intent.putExtra(
                    Constance.SURNAME,
                    etSurname.text.toString()
                )
                // transferring data to MainActivity

                intent.putExtra(Constance.NAME, etName.text.toString())
                intent.putExtra(Constance.PATRONYMIC, etPatronymic.text.toString())
                intent.putExtra(Constance.PHONE_NUMBER, etPhone.text.toString())
                intent.putExtra(Constance.EMAIL, etEmail.text.toString())
                intent.putExtra(Constance.PASSWORD, etPassword.text.toString())

                //if avatar select - transferring image id
                if (ivAvatar.visibility == View.VISIBLE)intent.putExtra(Constance.AVATAR_ID, R.drawable.avataaars)
                setResult(RESULT_OK, intent)
                finish()

                //if button press - SignIn transferring Email/Password
            } else if (signState == Constance.SIGN_STATE_IN){
                intent.putExtra(Constance.EMAIL, etEmail.text.toString())
                intent.putExtra(Constance.PASSWORD, etPassword.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }

            //click on Avatar button - show img
            ivAvatar.visibility = View.VISIBLE


        }
    }
