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
    val closureDepth: Double? = null, // Глубина закрытия, если скважина закрыта
    val columnSet: Double? = null, //Колонковый нобор
    val deathMeasurement: Double? = null, //Мертвый замер, есди скважина закрыта
    val workingMeasurement: Double? = null, //Рабочий замер, если скважина закрыта
    val countHQ: Int? = null //Количество буровых штанг, считается после закрытия скважины
) {
    /**
     * Проверяет валидность данных скважины
     */
    fun validate(): Boolean {
        return name.isNotBlank() && drillRigNumber.isDigitsOnly()
    }

    /**
     * Расчет контрольного замера для закрытой скважины
     * Формула: (3 * количество_штанг_HQ + колонковый_набор) - мертвый_замер - рабочий_замер
     *
     * @return значение контрольного замера или null если скважина не закрыта
     *         или отсутствуют необходимые данные
     */
    fun calculationControlMeasurement(): Double? {
        if (!isClosed) return null

        val countHQ = countHQ ?: return null
        val columnSet = columnSet ?: return null
        val deathMeasurement = deathMeasurement ?: return null
        val workingMeasurement = workingMeasurement ?: return null

        return (3 * countHQ + columnSet) - deathMeasurement - workingMeasurement
    }
}
