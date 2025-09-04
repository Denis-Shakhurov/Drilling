package com.example.drilling.presentation.hole

import android.net.ProxyInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drilling.domain.model.GeologicalHole
import com.example.drilling.domain.usecase.DeleteAllHoleUseCase
import com.example.drilling.domain.usecase.DeleteHoleUseCase
import com.example.drilling.domain.usecase.DeleteIntervalsByHoleIdUseCase
import com.example.drilling.domain.usecase.DeleteRunsByHoleIdUseCase
import com.example.drilling.domain.usecase.GetAllHolesUseCase
import com.example.drilling.domain.usecase.InsertHoleUseCase
import com.example.drilling.presentation.hole.state.HoleEvent
import com.example.drilling.presentation.hole.state.HoleIntent
import com.example.drilling.presentation.hole.state.HoleListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoleListViewModel @Inject constructor(
    private val insertHoleUseCase: InsertHoleUseCase,
    private val getAllHolesUseCase: GetAllHolesUseCase,
    private val deleteHoleUseCase: DeleteHoleUseCase,
    private val deleteAllHolesUseCase: DeleteAllHoleUseCase,
    private val deleteRunsByHoleIdUseCase: DeleteRunsByHoleIdUseCase,
    private val deleteIntervalsByHoleIdUseCase: DeleteIntervalsByHoleIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HoleListState())
    val state: StateFlow<HoleListState> = _state.asStateFlow()

    private val _event = Channel<HoleEvent>()
    val event: Flow<HoleEvent> = _event.receiveAsFlow()

    init {
        processIntent(HoleIntent.LoadHoles)
    }

    fun processIntent(intent: HoleIntent) {
        when (intent) {
            is HoleIntent.LoadHoles -> loadHoles()
            is HoleIntent.DeleteHole -> deleteHole(intent.hole)
            is HoleIntent.DeleteAllHoles -> deleteAllHoles()
            is HoleIntent.AddHole -> TODO()
            is HoleIntent.CopyHoleInfo -> TODO()
            is HoleIntent.EditHole -> TODO()
            is HoleIntent.ViewHole -> TODO()
        }
    }

    private fun loadHoles() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getAllHolesUseCase()
                .catch { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message
                    )
                    _event.send(HoleEvent.ShowError(exception.message ?: "Ошибка загрузки"))
                }
                .collect { holes ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        holes = holes,
                        error = null
                    )
                }
        }
    }

    private fun deleteHole(hole: GeologicalHole) {
        viewModelScope.launch {
            try {
                //удаляем связанные данные
                deleteRunsByHoleIdUseCase(hole.id)
                deleteIntervalsByHoleIdUseCase(hole.id)
                //удаляем саму скважину
                deleteHoleUseCase(hole)
            } catch (exception: Exception) {
                _event.send(HoleEvent.ShowError(exception.message ?: "Ошибка удаления"))
            }
        }
    }

    private fun deleteAllHoles() {
        viewModelScope.launch {
            try {
                deleteAllHolesUseCase()
            } catch (exception: Exception) {
                _event.send(HoleEvent.ShowError(exception.message ?: "Ошибка удаления всех скважин"))
            }
        }
    }
}