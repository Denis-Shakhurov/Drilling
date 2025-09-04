package com.example.drilling.presentation.hole

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drilling.domain.usecase.GetHoleByIdUseCase
import com.example.drilling.domain.usecase.GetIntervalsByHoleIdUseCase
import com.example.drilling.domain.usecase.GetRunsByHoleIdUseCase
import com.example.drilling.presentation.hole.state.HoleDetailState
import com.example.drilling.presentation.hole.state.HoleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoleDetailViewModel @Inject constructor(
    private val getHoleByIdUseCase: GetHoleByIdUseCase,
    private val getRunsByHoleIdUseCase: GetRunsByHoleIdUseCase,
    private val getIntervalsByHoleIdUseCase: GetIntervalsByHoleIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HoleDetailState())
    val state: StateFlow<HoleDetailState> = _state.asStateFlow()

    private val _event = Channel<HoleEvent>()
    val event: Flow<HoleEvent> = _event.receiveAsFlow()

    fun loadHoleData(holeId: Long) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                //Загружаем данные скважины
                val hole = getHoleByIdUseCase(holeId)
                _state.value = _state.value.copy(hole = hole)

                //Загружаем рейсы и интервалы параллельно
                val runsFlow = getRunsByHoleIdUseCase(holeId)
                val intervalsFlow = getIntervalsByHoleIdUseCase(holeId)

                combine(runsFlow, intervalsFlow) { runs, intervals ->
                    _state.value = _state.value.copy(
                        isLoading = true,
                        runs = runs,
                        intervals = intervals,
                        error = null
                    )
                }.catch { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message
                    )
                    _event.send(HoleEvent.ShowError(exception.message ?: "Ошибка загрузки данных"))
                }.launchAndCollectIn(this)
            } catch (exception: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message
                )
                _event.send(HoleEvent.ShowError(exception.message ?: "Ошибка загрузки скважины"))
            }
        }
    }

    private fun <T> Flow<T>.launchAndCollectIn(scope: CoroutineScope) {
        scope.launch {
            collect()
        }
    }
}