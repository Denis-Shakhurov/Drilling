package com.example.drilling.presentation.hole.state

import com.example.drilling.domain.model.GeologicalHole
import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.model.Run

data class HoleListState(
    val isLoading: Boolean = false,
    val holes: List<GeologicalHole> = emptyList(),
    val error: String? = null
)

data class HoleDetailState(
    val isLoading: Boolean = false,
    val hole: GeologicalHole? = null,
    val runs: List<Run> = emptyList(),
    val intervals: List<GeologicalInterval> = emptyList(),
    val error: String? = null
)

sealed class HoleEvent {
    data class ShowError(val message: String) : HoleEvent()
    object ShowLoading : HoleEvent()
    object HideLoading : HoleEvent()
}

sealed class HoleIntent {
    object LoadHoles : HoleIntent()
    data class DeleteHole(val hole: GeologicalHole) : HoleIntent()
    data class EditHole(val hole: GeologicalHole) : HoleIntent()
    data class ViewHole(val hole: GeologicalHole) : HoleIntent()
    object AddHole : HoleIntent()
    object DeleteAllHoles : HoleIntent()
    data class CopyHoleInfo(val hole: GeologicalHole) : HoleIntent()
}