package com.example.drilling.domain.model

import androidx.core.text.isDigitsOnly

/**
 * Domain модель геологической скважины
 * @param id уникальный идентификатор скважины (генерируется автоматически)
 * @param name название скважины
 * @param isClosed флаг, указывающий закрыта ли скважина
 * @param drillRigNumber номер бурового станка
 * @param totalDepth общая глубина скважины (вычисляемое поле)
 * @param closureDepth глубина закрытия скважины (если закрыта)
 */
data class Borehole(
    val id: Long = 0,
    val name: String,
    val isClosed: Boolean = false,
    val drillRigNumber: String,
    val totalDepth: Double = 0.0, // Вычисляется на основе рейсов
    val closureDepth: Double? = null // Глубина закрытия, если скважина закрыта
) {
    /**
     * Проверяет валидность данных скважины
     */
    fun validate(): Boolean {
        return name.isNotBlank() && drillRigNumber.isDigitsOnly()
    }
}
