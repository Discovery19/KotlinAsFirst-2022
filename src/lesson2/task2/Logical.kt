@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr

import kotlin.math.abs


/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая (2 балла)
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */

fun isNumberHappy(num: Int): Boolean = num % 10 + num / 10 % 10 == num / 100 % 10 + num / 1000

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */


fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    (x1 == x2 || y1 == y2 || abs(x1 - x2) == abs(y1 - y2))

/**
 * Простая (2 балла)
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun year(month: Int, year: Int): Int {
    var s: Int = 0
    when {
        year % 4 == 0 && month == 2 -> s = 29
        month == 12 -> s = 31
        month == 8 -> s = 31
        month == 9 -> s = 30
        month == 11 -> s = 30
    }
    if (year % 4 == 0) if (month == 2) s = 29
    else
        when {
            month % 2 == 0 -> s = 30
            month % 2 > 0 -> s = 31
        }
    else if (month == 2) s = 28 else if (month == 12) s = 31 else when {
        month % 2 == 0 -> s = 30
        month % 2 > 0 -> s = 31
    }
    return s
}

fun daysInMonth(month: Int, year: Int): Int = TODO()

/**
 * Простая (2 балла)
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean = TODO()

/**
 * Средняя (3 балла)
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */

fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    if ((a <= r && b <= s) || (b <= r && a <= s) ||
        (a <= r && c <= s) || (c <= r && a <= s) ||
        (c <= r && b <= s) || (b <= r && c <= s)
    ) return true
    return false
}