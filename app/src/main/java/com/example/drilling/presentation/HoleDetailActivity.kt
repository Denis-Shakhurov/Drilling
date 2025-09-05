package com.example.drilling.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager2.widget.ViewPager2
import com.example.drilling.R
import com.example.drilling.presentation.hole.HoleDetailViewModel
import com.example.drilling.presentation.hole.state.HoleEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.launchAndCollectIn

@AndroidEntryPoint
class HoleDetailActivity : AppCompatActivity() {
    private lateinit var tvHoleName: TextView
    private lateinit var btnHoleInfo: Button
    private lateinit var viewPager: ViewPager2

    private val viewModel: HoleDetailViewModel by viewModels()

//    private fun viewModels() {
//        TODO("Not yet implemented")
//    }

    private var holeId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hole_detail)

        holeId = intent.getLongExtra("hole_id", 0L)
        if (holeId == 0L) {
            finish()
            return
        }

        initViews()
        setupViewPager()
        setupListeners()
        observeState()
        observeEvents()
        viewModel.loadHoleData(holeId)
    }

    private fun initViews() {
        tvHoleName = findViewById(R.id.tvHoleName)
        btnHoleInfo = findViewById(R.id.btnHoleInfo)
        viewPager = findViewById(R.id.viewPager)
    }

    private fun setupViewPager() {
        // Здесь будет настройка ViewPager с фрагментами
    }

    private fun setupListeners() {
        btnHoleInfo.setOnClickListener {
            showHoleInfo()
        }
    }

    private fun observeState() {
        viewModel.state.launchAndCollectIn(CoroutineScope(Dispatchers.Main)) { state ->
            state.hole?.let { hole ->
                tvHoleName.text = "${hole.name} Буровая ${hole.drillingRigNumber}"
            }
        }
    }

    private fun observeEvents() {
        viewModel.event.launchAndCollectIn(CoroutineScope(Dispatchers.Main)) { event ->
            when (event) {
                is HoleEvent.ShowError -> {
                    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
                }
                HoleEvent.ShowLoading -> {
                    // Показать индикатор загрузки
                }
                HoleEvent.HideLoading -> {
                    // Скрыть индикатор загрузки
                }
            }
        }
    }

    private fun showHoleInfo() {
        // Здесь будет показ информации по скважине
        val info = "Информация по скважине будет здесь"
        copyToClipboard("Информация по скважине", info)
    }

    private fun copyToClipboard(label: String, text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Информация скопирована в буфер обмена", Toast.LENGTH_SHORT).show()
    }
}
