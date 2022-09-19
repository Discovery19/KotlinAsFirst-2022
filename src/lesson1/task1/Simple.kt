@file:Suppress("UNUSED_PARAMETER")

package lesson1.task1

import java.math.RoundingMode
import kotlin.math.*
import kotlin.math.sqrt
// Урок 1: простые функции
// Максимальное количество баллов = 5
// Рекомендуемое количество баллов = 4

/**
 * Пример
 *
 * Вычисление квадрата целого числа
 */
fun sqr(x: Int) = x * x

/**
 * Пример
 *
 * Вычисление квадрата вещественного числа
 */
fun sqr(x: Double) = x * x

/**
 * Пример
 *
 * Вычисление дискриминанта квадратного уравнения
 */
fun discriminant(a: Double, b: Double, c: Double) = sqr(b) - 4 * a * c

/**
 * Пример
 *
 * Поиск одного из корней квадратного уравнения
 */
fun quadraticEquationRoot(a: Double, b: Double, c: Double) =
    (-b + sqrt(discriminant(a, b, c))) / (2 * a)

/**
 * Пример
 *
 * Поиск произведения корней квадратного уравнения
 */
fun quadraticRootProduct(a: Double, b: Double, c: Double): Double {
    val sd = sqrt(discriminant(a, b, c))
    val x1 = (-b + sd) / (2 * a)
    val x2 = (-b - sd) / (2 * a)
    return x1 * x2 // Результат
}

/**
 * Пример главной функции
 */
fun main() {
    val x1x2 = quadraticRootProduct(1.0, 13.0, 42.0)
    println("Root product: $x1x2")
}


fun timer(hours: Int,minutes:Int,seconds:Int): Int {
    return hours * 3600 + minutes * 60 + seconds

}

/**
 * Тривиальная (3 балла).
 *
 * Задача имеет повышенную стоимость как первая в списке.
 *
 * Пользователь задает время в часах, минутах и секундах, например, 8:20:35.
 * Рассчитать время в секундах, прошедшее с начала суток (30035 в данном случае).
 */
fun seconds(hours: Int, minutes: Int, seconds: Int): Int = timer(hours,minutes,seconds)


/**
 * Тривиальная (1 балл)
 *
 * Пользователь задает длину отрезка в саженях, аршинах и вершках (например, 8 саженей 2 аршина 11 вершков).
 * Определить длину того же отрезка в метрах (в данном случае 18.98).
 * 1 сажень = 3 аршина = 48 вершков, 1 вершок = 4.445 см.
 */
fun length(sagenes: Int,arshins: Int,vershoks: Int):Double{
    return sagenes * 48 * 0.04445 + arshins * 16 * 0.04445 + vershoks * 0.04445
}
fun lengthInMeters(sagenes: Int, arshins: Int, vershoks: Int): Double = length(sagenes,arshins,vershoks)

/**
 * Тривиальная (1 балл)
 *
 * Пользователь задает угол в градусах, минутах и секундах (например, 36 градусов 14 минут 35 секунд).
 * Вывести значение того же угла в радианах (например, 0.63256).
 */
fun angle(deg: Int,min: Int,sec: Int):Double{
    val gg = (deg.toDouble() + min.toDouble() / 60 + sec.toDouble() / 3600) * PI / 180
    return gg
}
fun angleInRadian(deg: Int, min: Int, sec: Int): Double = angle(deg, min, sec)

/**
 * Тривиальная (1 балл)
 *
 * Найти длину отрезка, соединяющего точки на плоскости с координатами (x1, y1) и (x2, y2).
 * Например, расстояние между (3, 0) и (0, 4) равно 5
 */

fun line(x1:Double,y1: Double,x2: Double,y2: Double): Double{
    return sqrt((x1 - x2).pow(2.0) + (y1 - y2).pow(2.0))
}
fun trackLength(x1: Double, y1: Double, x2: Double, y2: Double): Double = line(x1, y1, x2, y2)

/**
 * Простая (2 балла)
 *
 * Пользователь задает целое число, больше или равно 100 (например, 3801).
 * Определить третью цифру справа в этом числе (в данном случае 8).
 */
fun lastnumb(number: Int):Int{
    return number / 100 % 10
}
fun thirdDigit(number: Int): Int = lastnumb(number)

/**
 * Простая (2 балла)
 *
 * Поезд вышел со станции отправления в h1 часов m1 минут (например в 9:25) и
 * прибыл на станцию назначения в h2 часов m2 минут того же дня (например в 13:01).
 * Определите время поезда в пути в минутах (в данном случае 216).
 */
fun train(hoursDepart: Int,minutesDepart: Int,hoursArrive: Int,minutesArrive: Int): Int{
    return hoursArrive * 60 + minutesArrive - hoursDepart * 60 - minutesDepart
}
fun travelMinutes(hoursDepart: Int, minutesDepart: Int, hoursArrive: Int, minutesArrive: Int): Int = train(hoursDepart,minutesDepart,hoursArrive,minutesArrive)

/**
 * Простая (2 балла)
 *
 * Человек положил в банк сумму в s рублей под p% годовых (проценты начисляются в конце года).
 * Сколько денег будет на счету через 3 года (с учётом сложных процентов)?
 * Например, 100 рублей под 10% годовых превратятся в 133.1 рубля
 */
fun bank(initial: Int,percent: Int):Double{
    val g1 = initial + initial * (percent.toDouble() / 100)
    val g2 = g1 + g1 * percent.toDouble() / 100
    val g3 = g2 + g2 * percent.toDouble() / 100
    return g3
}
fun accountInThreeYears(initial: Int, percent: Int): Double = bank(initial,percent)


/**
 * Простая (2 балла)
 *
 * Пользователь задает целое трехзначное число (например, 478).
 * Необходимо вывести число, полученное из заданного перестановкой цифр в обратном порядке (например, 874).
 */
fun replacer(number: Int):Int{
    val f = number % 10
    val s = number / 10 % 10
    val t = number / 100
    return f * 100 + s * 10 + t
}
fun numberRevert(number: Int): Int = replacer(number)
