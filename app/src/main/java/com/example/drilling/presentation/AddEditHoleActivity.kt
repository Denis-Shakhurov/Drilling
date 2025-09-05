package com.example.drilling.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.drilling.R
import com.example.drilling.domain.model.GeologicalHole
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditHoleActivity : AppCompatActivity() {
    private lateinit var etHoleName: EditText
    private lateinit var etDrillingRig: EditText
    private lateinit var cbHoleClosed: CheckBox
    private lateinit var etClosingDepth: EditText
    private lateinit var cbGeologicalWorkDone: CheckBox
    private lateinit var btnSaveHole: Button
    private lateinit var btnCancel: Button

    private var holeId: Long = 0L
    private var existingHole: GeologicalHole? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_hole)

        holeId = intent.getLongExtra("hole_id", 0L)

        initViews()
        setupListeners()
        loadExistingHole()
        setupVisibilityListener()
    }

    private fun initViews() {
        etHoleName = findViewById(R.id.etHoleName)
        etDrillingRig = findViewById(R.id.etDrillingRig)
        cbHoleClosed = findViewById(R.id.cbHoleClosed)
        etClosingDepth = findViewById(R.id.etClosingDepth)
        cbGeologicalWorkDone = findViewById(R.id.cbGeologicalWorkDone)
        btnSaveHole = findViewById(R.id.btnSaveHole)
        btnCancel = findViewById(R.id.btnCancel)
    }

    private fun setupListeners() {
        btnSaveHole.setOnClickListener {
            saveHole()
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun setupVisibilityListener() {
        cbHoleClosed.setOnCheckedChangeListener { _, isChecked ->
            etClosingDepth.isEnabled = isChecked
            cbGeologicalWorkDone.isEnabled = isChecked
        }
    }

    private fun loadExistingHole() {
        if (holeId > 0) {
            // Здесь будет загрузка существующей скважины
            // Пока оставим заглушку
            setTitle("Редактировать скважину")
        } else {
            setTitle("Добавить скважину")
            etClosingDepth.isEnabled = false
            cbGeologicalWorkDone.isEnabled = false
        }
    }

    private fun saveHole() {
        val name = etHoleName.text.toString().trim()
        val drillingRig = etDrillingRig.text.toString().trim()
        val isClosed = cbHoleClosed.isChecked
        val geologicalWorkDone = cbGeologicalWorkDone.isChecked

        if (name.isEmpty() || drillingRig.isEmpty()) {
            Toast.makeText(this, "Заполните все обязательные поля", Toast.LENGTH_SHORT).show()
            return
        }

        var closingDepth = 0.0
        if (isClosed) {
            try {
                closingDepth = etClosingDepth.text.toString().trim().toDouble()
                if (closingDepth <= 0) {
                    Toast.makeText(this, "Глубина закрытия должна быть положительной", Toast.LENGTH_SHORT).show()
                    return
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Введите корректную глубину закрытия", Toast.LENGTH_SHORT).show()
                return
            }
        }

        // Здесь будет сохранение скважины через ViewModel
        Toast.makeText(this, "Скважина сохранена", Toast.LENGTH_SHORT).show()
        finish()
    }
}