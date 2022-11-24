package com.yelemang.androidmvidemo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.yelemang.androidmvidemo.base.BaseState
import com.yelemang.androidmvidemo.databinding.ActivityMainBinding
import com.yelemang.androidmvidemo.ext.click
import com.yelemang.androidmvidemo.ext.toast
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btSuccess.click {
            //成功
            lifecycleScope.launch {
                mainViewModel.intent.send(MainIntent.LoginIntent("1234","1234"))
            }
        }

        binding.btFailure.click {
            //失败
            lifecycleScope.launch {
                mainViewModel.intent.send(MainIntent.LoginIntent("12134","12134"))
            }
        }

        //State处理
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when(it){
                    is BaseState.InitState->{
                        toast("初始化中...")
                    }
                    is MainState.LoginSuccessState->{
                        toast("登录成功")
                    }
                    is MainState.LoginFailureState->{
                        Toast.makeText(this@MainActivity,it.errorMsg,Toast.LENGTH_SHORT).show()
                    }
                    is BaseState.LoadingState->{
                        //显示Loading视图
                        binding.loading.visibility = View.VISIBLE
                    }
                    is BaseState.HideState->{
                        //隐藏Loading视图
                        binding.loading.visibility = View.GONE
                    }
                }
            }
        }
    }
}