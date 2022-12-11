@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import ru.spbstu.wheels.PositiveInfinity
import java.lang.IndexOutOfBoundsException

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */

fun date(str: String): String {
    try {
        val list = str.split(" ")
        val year = listOf<String>(
            "января",
            "февраля",
            "марта",
            "апреля",
            "мая",
            "июня",
            "июля",
            "августа",
            "сентября",
            "октября",
            "ноября",
            "декабря"
        )
        val n = list[0].toInt()
        if (list.size < 3) return ""
        val mounth = year.indexOf(list[1]) + 1
        if (n > daysInMonth(mounth, list[2].toInt()) || mounth == 0) return ""
        return twoDigitStr(n) + "." + twoDigitStr(mounth) + "." + list[2]
    } catch (e: NumberFormatException) {
        return ""
    }
}

fun dateStrToDigit(str: String): String = date(str)

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */

fun dateDigitToStr(digital: String): String = TODO()

/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String = TODO()

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun jump(jumps: String): Int {
    var max = -1
    val regex = Regex("""[1-9][0-9]*( ([-%]|([1-9][0-9]*)*))*""")
    if (!jumps.matches(regex)) return -1
    for (i in jumps.split(Regex("""[\s\-%]"""))) {
        if (i.isNotEmpty() && i.toInt() > max) max = i.toInt()
    }
    return max
}

fun bestLongJump(jumps: String): Int = jump(jumps)

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */

fun bestHighJump(jumps: String): Int {
    val regex = Regex("""([0-9]+\s+[+%-]+)(\s+[0-9]+ [+%-]+)*""")
    if (!jumps.matches(regex)) return -1
    var res = -1
    val work = jumps.split(Regex("""[ ]+"""))
    for (i in work.indices) {
        if (work[i].isNotEmpty() && work[i].all { it.isDigit() }
            && work[i].toInt() > res
            && work[i + 1].contains("+")) res = work[i].toInt()
    }
    return res
}

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */


fun plusMinus(expression: String): Int {
    val regex = Regex("""[0-9]*(\s+([+-]\s+[0-9]+))*""")
    if (!expression.matches(regex)) throw IllegalArgumentException()
    val list = expression.split(Regex("""\s+"""))
    println(list)
    var res = list[0].toInt()
    for (i in 2 until list.size step 2) {
        if (list[i - 1] == "+") res += list[i].toInt()
        else res -= list[i].toInt()
    }
    return res
}

//    val str = expression.split(" ")
//    var res = 0
//    if (str[0].all { it.isDigit() }) res = str[0].toInt()
//    else throw IllegalArgumentException()
//    for (i in 1 until str.size) {
//        if (!str[i].all { it.isDigit() } && (str[i] != "+" && str[i] != "-")) throw IllegalArgumentException()
//    }
//
//    for (i in 2 until str.size step 2) {
//        if (str[i - 1] == "+") res += str[i].toInt()
//        else res -= str[i].toInt()
//    }
//    return res
/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun words(str: String): Int {
    val regex = Regex("""[A-zА-яё](\s[A-zА-яё])*""")
    //if (str.isEmpty()) return -1
    if (!str.matches(regex)) return -1
    var res = 0
    val list = str.toLowerCase().split(" ")
    println(list)
    if (list.size == 1) return -1
    var word = 0
    for (i in list.indices) {
        try {
            if (list[i] == list[i + 1]) {
                word = i
                break
            }
        } catch (e: IndexOutOfBoundsException) {
            return -1
        }
    }
    for (i in 0 until word) {
        res += list[i].length + 1
    }
    return res
}

fun firstDuplicateIndex(str: String): Int = words(str)

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String = TODO()

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

fun my(carPetrol: Map<String, String>, gasStation: String): Map<String, String> {
    val regex = Regex("""([\wА-Яа-я]+:)((( [А-я]+ \d+)|( [А-я]+)) - ((\d+;)|(\d+\.\d+;)))*""")
    var result = mutableMapOf<String, String>()
    for (j in carPetrol.keys) {
        var resName = ""
        var minPrice = Double.POSITIVE_INFINITY

        for (i in gasStation.split("\n")) {
            if (!i.matches(regex)) throw java.lang.IllegalArgumentException()
            var str = i.split(":")
            val name = str[0]
            val petrol = str[1].split(Regex("""[-;]"""))
            println(petrol)
            for (k in petrol.indices) {
                if (petrol[k].trim() == carPetrol.getValue(j) && petrol[k + 1].toDouble() <= minPrice) {
                    minPrice = petrol[k + 1].toDouble()
                    resName = name
                }
            }
        }
        if (minPrice == Double.POSITIVE_INFINITY) throw java.lang.IllegalStateException()
        result.put(j, resName)
    }
    println(result)
//    var toReturn=""
//    for (i in result.keys){
//        toReturn+=i.toString()+" - "+result.getValue(i).to
//    }
    return result
}

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
//!!!
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    TODO()
}
//    val check = listOf<Char>('>', '<', '[', ']', ' ')
//    var i = cells / 2
//    var counter = 0
//    val list = MutableList(cells) { 0 }
//    for (counter in 0..commands.length - 1) {
//        if (commands[counter].toChar() !in check)
//            throw Exception("IllegalArgumentException")
//    }
//    if ('[' in commands && ']' !in commands || '[' !in commands && ']' in commands)
//        throw Exception("IllegalArgumentException")
//    while (counter <= commands.length || counter != limit) {
//        when {
//            commands[counter] == '>' -> i++
//            commands[counter] == '<' -> i--
//            commands[counter] == '+' -> list[i] = list[i] + 1
//            commands[counter] == '-' -> list[i] = list[i] - 1
//            commands[counter] == ' ' -> list[i] = list[i]
//        }
//        counter++
//        var index=0
//        if (commands[counter] == '[') {
//            val k = i
//            val j = commands.subSequence(commands.lastIndexOf(']')-index-1,
//                commands.lastIndexOf(']')-index)
//            while (list[k] != 0 && list[j] == 0) {
//                when {
//                    commands[counter] == '>' -> i++
//                    commands[counter] == '<' -> i--
//                    commands[counter] == '+' -> list[i] = list[i] + 1
//                    commands[counter] == '-' -> list[i] = list[i] - 1
//                    commands[counter] == ' ' -> list[i] = list[i]
//                }
//            }
//            index++
//        }
//
//
//    }
//    return list
//}
//

fun myFun(table: Map<String, Int>, taxes: String): List<String> {
    var res = mutableMapOf<String, Double>()
    val regex = Regex("""([A-z ]+)-([A-z ]+)-( \d+)""")

    for (i in taxes.split("\n")) {
        if (!i.matches(regex)) throw java.lang.IllegalArgumentException()
        val str = i.split("-")
        val name = str[0].trim()
        val type = str[1].trim()
        val cash = str[2].trim()
        var nalog = 0.0
        var percent = 0.0
        if (table.contains(type)) percent = table.getValue(type).toDouble() / 100
        else percent = 0.13
        if (res.containsKey(name)) res.put(name, nalog + cash.toDouble() * percent)
        else res.put(name, cash.toDouble() * percent)
    }
    println(res)
    val result = mutableListOf<String>()
    for (i in res.toList().sortedByDescending { (key, value) -> value }.toMap().keys) result.add(i)
    return result
}

fun movePets(movers: List<String>, pets: List<String>, limit: Int): List<String> {
    val regex = Regex("""([A-zА-я]+:)( [A-zА-я]+\s+-\s+\d+|;)*""")
    var res = mutableListOf<String>()
    for (i in movers) {
        if (!i.matches(regex)) throw java.lang.IllegalArgumentException()
        val str = i.replace(Regex("""[;:-]"""), "").split(Regex("""\s+"""))
        var sumPet = 0
        var counterPets = 0
        for (k in str.indices) {
            if (str[k] in pets) {
                sumPet += str[k + 1].toInt()
                counterPets += 1
            }
        }
        if (sumPet <= limit && counterPets == pets.size) res.add(str[0])
    }
    return res
}

//fun nalog2(taxes: String, money: Int): Int {
//    val regex = Regex("""(\d+ у\.е\. - \d+%; )*(else - \d+%)""")
//    if (!taxes.matches(regex)) throw java.lang.IllegalArgumentException()
//    val str = taxes.replace(Regex("""[у\-.е%;]"""), "").split(Regex("""\s+"""))
//    println(str)
//    var cash = money
//    var i = 0
//    var result = 0
//    for (i in 0..str.size-1 step 2){
//        if (str[i].toInt()<=cash)
//    }
//    return result
//
//}