package com.example.drilling.presentation

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drilling.R
import com.example.drilling.domain.model.GeologicalHole
import com.example.drilling.presentation.hole.HoleListViewModel
import com.example.drilling.presentation.hole.adapter.HoleAdapter
import com.example.drilling.presentation.hole.state.HoleEvent
import com.example.drilling.presentation.hole.state.HoleIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var rvHoles: RecyclerView
    private lateinit var holeAdapter: HoleAdapter
    private lateinit var btnAddHole: Button
    private lateinit var btnDeleteAllHoles: Button
    private lateinit var btnCopyAllInfo: Button

    private val viewModel: HoleListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupRecyclerView()
        setupListeners()
        observeState()
        observeEvents()
    }

    private fun initViews() {
        rvHoles = findViewById(R.id.rvHoles)
        btnAddHole = findViewById(R.id.btnAddHole)
        btnDeleteAllHoles = findViewById(R.id.btnDeleteAllHoles)
        btnCopyAllInfo = findViewById(R.id.btnCopyAllInfo)
    }

    private fun setupRecyclerView() {
        holeAdapter = HoleAdapter(
            onEditClick = { hole -> editHole(hole) },
            onDeleteClick = { hole -> showDeleteConfirmation(hole) },
            onItemClick = { hole -> viewHole(hole) },
            onCopyClick = { hole -> copyHoleInfo(hole) }
        )
        rvHoles.layoutManager = LinearLayoutManager(this)
        rvHoles.adapter = holeAdapter
    }

    private fun setupListeners() {
        btnAddHole.setOnClickListener {
            val intent = Intent(this, AddEditHoleActivity::class.java)
            startActivity(intent)
        }

        btnDeleteAllHoles.setOnClickListener {
            showDeleteAllConfirmation()
        }

        btnCopyAllInfo.setOnClickListener {
            copyAllHolesInfo()
        }
    }

    private fun observeState() {
        viewModel.state.launchAndCollectIn(CoroutineScope(Dispatchers.Main)) { state ->
            holeAdapter.updateHoles(state.holes)

            if (state.isLoading) {
                // Показать индикатор загрузки
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

    private fun editHole(hole: GeologicalHole) {
        val intent = Intent(this, AddEditHoleActivity::class.java).apply {
            putExtra("hole_id", hole.id)
        }
        startActivity(intent)
    }

    private fun viewHole(hole: GeologicalHole) {
        val intent = Intent(this, HoleDetailActivity::class.java).apply {
            putExtra("hole_id", hole.id)
        }
        startActivity(intent)
    }

    private fun showDeleteConfirmation(hole: GeologicalHole) {
        AlertDialog.Builder(this)
            .setTitle("Удалить скважину")
            .setMessage("Вы уверены, что хотите удалить скважину \"${hole.name}\" и все связанные данные?")
            .setPositiveButton("Да") { _, _ ->
                viewModel.processIntent(HoleIntent.DeleteHole(hole))
            }
            .setNegativeButton("Нет", null)
            .show()
    }

    private fun showDeleteAllConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Удалить все скважины")
            .setMessage("Вы уверены, что хотите удалить все скважины и связанные данные?")
            .setPositiveButton("Да") { _, _ ->
                viewModel.processIntent(HoleIntent.DeleteAllHoles)
            }
            .setNegativeButton("Нет", null)
            .show()
    }

    private fun copyHoleInfo(hole: GeologicalHole) {
        val info = buildHoleInfo(hole)
        copyToClipboard("Информация по скважине ${hole.name}", info)
    }

    private fun copyAllHolesInfo() {
        // Реализация копирования всех скважин
        Toast.makeText(this, "Функция копирования всех скважин будет реализована", Toast.LENGTH_SHORT).show()
    }

    private fun buildHoleInfo(hole: GeologicalHole): String {
        // Реализация форматирования информации по скважине
        return "${hole.name} Буровая ${hole.drillingRigNumber}"
    }

    @SuppressLint("ServiceCast")
    private fun copyToClipboard(label: String, text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Информация скопирована в буфер обмена", Toast.LENGTH_SHORT).show()
    }
}

// Extension function для удобного сбора Flow
fun <T> kotlinx.coroutines.flow.Flow<T>.launchAndCollectIn(
    scope: CoroutineScope,
    action: suspend (T) -> Unit
) {
    scope.launch {
        collect {
            action(it)
        }
    }
}