package com.example.drilling.domain.model

/**
 * Domain модель геологического интервала
 * @param id уникальный идентификатор интервала
 * @param boreholeId идентификатор скважины, к которой относится интервал
 * @param startInterval начало интервала (в метрах)
 * @param endInterval конец интервала (в метрах)
 * @param lithology литология (песчаник, глина, известняк и т.д.)
 * @param stratigraphy стратиграфия (J, karst, Cm и т.д.)
 */
data class GeologicalInterval(
    val id: Long = 0,
    val boreholeId: Long,
    val startInterval: Double,
    val endInterval: Double,
    val lithology: String,
    val stratigraphy: String
) {
    /**
     * Проверяет валидность данных геологического интервала
     */
    fun validate(): Boolean {
        return startInterval >= 0 &&
                endInterval > startInterval &&
                lithology.isNotBlank() &&
                stratigraphy.isNotBlank()
    }
}
