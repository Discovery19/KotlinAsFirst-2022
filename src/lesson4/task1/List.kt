@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1


import lesson1.task1.discriminant
import lesson5.task1.removeFillerWords
import ru.spbstu.wheels.NullableMonad.filter
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
//fun isPalindrome(str: String): Boolean {
//    val lowerCase = str.lowercase().filter { it != ' ' }
//    for (i in 0..lowerCase.length / 2) {
//        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
//    }
//    return true
//}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun vector(list: List<Double>): Double {
    var s = 0.0
    for (element in list) {
        s += element * element
    }
    return sqrt(s)
}

fun abs(v: List<Double>): Double = vector(v)

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun ari(list: List<Double>): Double {
    return if (list.size == 0) 0.0 else list.sum() / list.size
}

fun mean(list: List<Double>): Double = ari(list)

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun izmena(list: MutableList<Double>): MutableList<Double> {
    val s = list.sum() / list.size
    if (list.size == 0) return list else
        for (i in 0..list.size - 1) {
            list[i] = list[i] - s
        }
    return list
}

fun center(list: MutableList<Double>): MutableList<Double> = izmena(list)

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun scal(a: List<Int>, b: List<Int>): Int {
    var s = 0
    for (i in 0..a.size - 1) {
        s += a[i] * b[i]
    }
    return s
}

fun times(a: List<Int>, b: List<Int>): Int = scal(a, b)

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */


/** Есть два варианта и ни один не проходит по времени. Не знаю как оптимизировать */
fun easynumber(n: Int): String {
    var list = mutableListOf<Int>()
    var k = 0
    k = n
    var i = 2
    while (i * i <= k) {
        while (n % i == 0) {
            list.add(i)
            k = k / i
        }
        i += 1
    }
    if (n > 1) list.add(k)

    print(list)
    var string = ""
    //if (list[list.last()]!=list[list.last()-1])
    for (s in list - 1) {
        string = string + s + "*"
    }
    return string.substring(0, string.length - 1)
}

fun easynumber2(n: Int): String {
    var list = mutableListOf<Int>()
    var k = 0
    k = n
    while (k > 0) {
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            if (k % i == 0) list.add(i)
            if (k % i == 0) k = k / i
        }
        if (list.size == 0) list.add(n)
    }
    print(list)
    var string = ""

    for (s in list - 1) {
        string = string + s + "*"
    }
    return string.substring(0, string.length - 1)
}

fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun chislo(digits: List<Int>, base: Int): Int {
    var k = 0
    var l = 0
    var s = 0.0
    var i = 0
    if (digits.size == 1 && base == 2 && digits[0] == 1) return 1 else if (digits.size == 1 && base == 2 && digits[0] == 0) return 0
    for (i in digits) {
        println(digits[k])
        k += 1
    }
    i = k
    while (i != 0) {
        s += digits[l].toDouble() * (base.toDouble()).pow(i - 1)
        l += 1
        i -= 1
    }

    return s.toInt()
}

fun decimal(digits: List<Int>, base: Int): Int = chislo(digits, base)

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun findIndex(values: List<Char>, item: Char): Int {
    for (i in values.indices) {
        if (values[i] == item) {
            return i
        }
    }
    return -1
}

fun stroka(str: String, base: Int): Int {
    var chars = str.toList()
    var s = 0.0
    var k = 0
    var i = 0
    var l = 0
    var h = 0.0
    println(chars)
    var alphabet = mutableListOf<Char>()
    if (chars.size == 1 && base == 2 && chars[0] == '1') return 1 else if (chars.size == 1 && base == 2 && chars[0] == '0') return 0
    for (p in '0'..'9') {
        alphabet.add(p)
    }
    for (p in 'a'..'z') {
        alphabet.add(p)
    }
    for (p in chars) {
        k += 1
    }
    i = k
    while (i != 0) {
        println("chislo chislo")
        h = findIndex(alphabet, chars[l]).toDouble()
        s += (h) * (base.toDouble()).pow(i - 1)
        l += 1
        i -= 1
    }
    if (chars.size == 1 && base == 2) s = 1.0 else if (chars.size == 1 && base == 2) s = 0.0
    return s.toInt()
}


fun decimalFromString(str: String, base: Int): Int = stroka(str, base)

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun rome(n: Int): String {
    val numblist = listOf<Int>(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val romelist = listOf<String>("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    var result = ""
    var num = n
    for (d in 0..numblist.size - 1) {
        for (k in d..romelist.size - 1) {
            while (num >= numblist[d]) {
                result += romelist[k]
                num -= numblist[d]
            }
        }
    }
    return result
}

fun roman(n: Int): String = rome(n)

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun russian(n: Int): String {
    val dig1 = arrayOf(
        "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"
    )
    val dig01 = arrayOf(
        "", "одна", "две", "три", "четыре"
    )
    val dig10 = arrayOf(
        "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
        "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    )
    val dig20 = arrayOf(
        "", "", "двадцать", "тридцать", "сорок", "пятьдесят",
        "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    )
    val dig100 = arrayOf(
        "", "сто", "двести", "триста", "четыреста", "пятьсот",
        "шестьсот", "семьсот", "восемьсот", "девятьсот"
    )
    val leword = arrayOf(
        "тысяча", "тысячи", "тысяч"
    )
    var i = n
    var res = ""
    val len = n.toString().length
    while (i > 0) {
        if (i % 100 in 10..19) {
            res = dig10[i % 10] + res
            i /= 100
        } else {
            res = dig1[i % 10] + res
            i /= 10
            res = dig20[i % 10] + " " + res
            i /= 10
        }
        res = dig100[i % 10] + " " + res
        i /= 10
        if (i % 100 in 10..19) {
            res = dig10[i % 10] + " " + leword[2] + " " + res
            i /= 10
        } else {
            if (i % 10 == 1) res = dig01[i % 10] + " " + leword[0] + " " + res else
                if (i % 10 in 2..4) res = dig01[i % 10] + " " + leword[1] + " " + res else
                    if (len >= 4) res = dig1[i % 10] + " " + leword[2] + " " + res

            i /= 10
        }
        if (len >= 5) {
            println(i)
            res = dig20[i % 10] + " " + res
            println(i)
            i /= 10
        }

        println(i)
        println(i % 10)
        if (len == 6) res = dig100[i % 10] + " " + res
        break
    }
    while ("  " in res) {
        res = res.replace("  ", " ")
    }
    return res.trimEnd().trimStart()

}