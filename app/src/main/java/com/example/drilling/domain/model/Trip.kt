package com.example.drilling.domain.model

/**
 * Domain модель рейса буровых работ
 * @param id уникальный идентификатор рейса
 * @param boreholeId идентификатор скважины, к которой относится рейс
 * @param startInterval начало интервала рейса (в метрах)
 * @param endInterval конец интервала рейса (в метрах)
 * @param coreLength проходка в метрах (длина полученного керна)
 */
data class Trip(
    val id: Long = 0,
    val boreholeId: Long,
    val startInterval: Double,
    val endInterval: Double,
    val coreLength: Double
) {
    /**
     * Вычисляет процент выхода керна
     * @return процент выхода керна или null если интервал некорректен
     */
    fun calculateCoreRecoveryPercentage(): Double? {
        val intervalLength = endInterval - startInterval
        return if (intervalLength > 0 && coreLength >= 0) {
            (coreLength / intervalLength) * 100
        } else {
            null
        }
    }

    /**
     * Проверяет валидность данных рейса
     */
    fun validate(): Boolean {
        return startInterval >= 0 &&
                endInterval > startInterval &&
                coreLength >= 0 &&
                coreLength <= (endInterval - startInterval)
    }
}
