package com.shubham.notesapp

import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shubham.notesapp.databinding.ActivityRegisterBinding
import com.shubham.notesapp.repository.Repository

class RegisterActivity : AppCompatActivity(), View.OnFocusChangeListener {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initViewModel()

        binding.etName.onFocusChangeListener = this
        binding.etEmail.onFocusChangeListener = this
        binding.etPassword.onFocusChangeListener = this
        binding.etName.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        binding.etPassword.typeface = Typeface.DEFAULT
        binding.etPassword.transformationMethod = PasswordTransformationMethod()

        binding.buttonRegister.setOnClickListener {

            viewModel.registerUser(binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )

            viewModel.registerUserResponse.observe(this, { response ->
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity,
                        response.body()?.message.toString(),
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RegisterActivity,
                        response.body()?.message.toString(),
                        Toast.LENGTH_SHORT).show()
                    Log.i("registerResponse", response.errorBody().toString())
                }
            })
        }
    }

    private fun initViewModel() {
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                R.id.etName -> {
                    if (hasFocus) {
                        if (binding.textInputLayoutName.isErrorEnabled) {
                            binding.textInputLayoutName.isErrorEnabled = false
                        }
                    } else {
                        validateName()
                    }
                }
                R.id.etEmail -> {
                    if (hasFocus) {
                        if (binding.textInputLayoutEmail.isErrorEnabled) {
                            binding.textInputLayoutEmail.isErrorEnabled = false
                        }
                    } else {
                        validateEmail()
                    }
                }
                R.id.etPassword -> {
                    if (hasFocus) {
                        if (binding.textInputLayoutPassword.isErrorEnabled) {
                            binding.textInputLayoutPassword.isErrorEnabled = false
                        }
                    } else {
                        validatePassword()
                    }
                }
            }
        }
    }

    private fun validateName(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.etName.text.toString()
        if (value.isEmpty()) {
            errorMessage = getString(R.string.msg_required_name)
        }
        if (errorMessage != null) {
            binding.textInputLayoutName.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateEmail(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.etEmail.text.toString()
        if (value.isEmpty()) {
            errorMessage = getString(R.string.msg_required_email)
        } else if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            errorMessage = getString(R.string.msg_required_valid_email)
        }
        if (errorMessage != null) {
            binding.textInputLayoutEmail.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validatePassword(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.etPassword.text.toString()
        if (value.isEmpty()) {
            errorMessage = getString(R.string.msg_required_password)
        } else if (value.length < 6) {
            errorMessage = getString(R.string.msg_required_password_length)
        }
        if (errorMessage != null) {
            binding.textInputLayoutPassword.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }
}