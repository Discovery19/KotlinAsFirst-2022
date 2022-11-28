@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import java.lang.StringBuilder
import kotlin.math.pow


// Урок 5: ассоциативные массивы и множества
// Максимальное количество баллов = 14
// Рекомендуемое количество баллов = 9
// Вместе с предыдущими уроками = 33/47

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая (2 балла)
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val gradeMap = mutableMapOf<Int, MutableList<String>>()
    for ((name, num) in grades) {
        if (num !in gradeMap.keys) {
            gradeMap.put(num, mutableListOf<String>(name))
        } else {
            gradeMap.getValue(num) += name
        }
    }
    return gradeMap
}

/**
 * Простая (2 балла)
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
//(key1 in b) &&

fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    if (a.isEmpty()) return true
    for ((key1) in a) if (a.getValue(key1) == b[key1] ?: return false) return true
    return false
}

/**
 * Простая (2 балла)
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */

fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    for ((key, value) in b) {
        if (key in a.keys) {
            if (a[key] == value) a.remove(key)
        }
    }
}

/**
 * Простая (2 балла)
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяющихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
//fun who(a: List<String>, b: List<String>): List<String> {
//    var list = mutableListOf<String>()
//
//    for (name in a) {
//        for (name2 in b) {
//            if (name == name2) list.add(name)
//        }
//    }
//    return list.distinct()
//}

//надеюсь вы это имели в виду
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = a.intersect(b).toList()

/**
 * Средняя (3 балла)
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */

fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> = TODO()
//обсудил, потом перепишу
//{
//    val phone = (mapA.asSequence() + mapB.asSequence()).distinct().groupBy({ it.key }, { it.value })
//        .mapValues { (_, names) -> names.joinToString(", ") }
//    return phone
//}

/**
 * Средняя (4 балла)
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */

fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val middle = mutableMapOf<String, Double>()
    val xy = mutableMapOf<String, Int>()
    for ((first, second) in stockPrices) {
        if (first !in middle.keys) {
            middle[first] = second
            xy[first] = 1
        } else {
            val x = middle.getValue(first)
            val y = xy.getValue(first)
            middle[first] = x + second
            xy[first] = y + 1
        }
    }
    for ((key) in middle) {
        val x2 = middle[key]!!
        val y2 = xy[key]!!
        middle[key] = x2 / y2
    }
    return middle
}

/**
 * Средняя (4 балла)
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */

//fun shop2(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
//    var min = 0.0
//    var res: String? = ""
//    val stuff2 = stuff.toMutableMap()
//    min = 9999.0
//
//    for (name in stuff2.values) {
//        if (name.second < min && name.first == kind) {
//            min = name.second
//
//            res = stuff2.filter { min == name.second }!!.keys.first()
//        }
//        if (name.second < min && name.first != kind) {
//            res = null
//        }
//    }
//    return res
//}

fun find(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var min = Double.POSITIVE_INFINITY
    var res: String? = null
    var check = mutableListOf<String>()
    for ((first) in stuff.values) {
        check.add("$first")
    }
    //println(check)
    //if (kind !in check) return null
    //if (stuff.size == 1) return stuff.keys.joinToString()
    for ((name, para) in stuff) {
        if (para.second < min && para.first == kind) {
            min = para.second
            res = name
        }
    }
    return res
}

fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? = find(stuff, kind)
//{
//    var min = stuff.values.maxBy { it.second }.second
//    var res = StringBuilder()
//    var check = StringBuilder()
//    for ((first) in stuff.values) {
//        check.append("$first ")
//    }
//    println(check)
//    if (kind !in check.split(" ")) return null
//    for ((name, para) in stuff) {
//        if (kind !in para.first) return null
//        if (para.second < min && para.first == kind) {
//            min = para.second
//            res.clear()
//            res.append(name)
//        }
//    }
//    if (res.isEmpty() && kind != "") return null
//    return res.toString()
//}

/**
 * Средняя (3 балла)
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean = TODO()

/**
 * Средняя (4 балла)
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Для заданного списка слов определить, содержит ли он анаграммы.
 * Два слова здесь считаются анаграммами, если они имеют одинаковую длину
 * и одно можно составить из второго перестановкой его букв.
 * Скажем, тор и рот или роза и азор это анаграммы,
 * а поле и полено -- нет.
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean = TODO()

/**
 * Сложная (5 баллов)
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 *
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Оставлять пустой список знакомых для людей, которые их не имеют (см. EvilGnome ниже),
 * в том числе для случая, когда данного человека нет в ключах, но он есть в значениях
 * (см. GoodGnome ниже).
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta"),
 *       "Friend" to setOf("GoodGnome"),
 *       "EvilGnome" to setOf()
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat"),
 *          "Friend" to setOf("GoodGnome"),
 *          "EvilGnome" to setOf(),
 *          "GoodGnome" to setOf()
 *        )
 */

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> = TODO()

/**
 * Сложная (6 баллов)
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
//fun sumtwo(list: List<Int>, number: Int): Pair<Int, Int> {
//    var map = mutableMapOf<Int, Pair<Int, Int>>()
//    var k = 0
//    for (i in 1 until list.size) {
//        map.put(list[k] + list[i], (Pair(k, i)))
//    }
//    if (map.get(number) == null) return Pair(-1, -1)
//    return map.get(number)!!
//}
//
//fun sumtwo2(list: List<Int>, number: Int): Pair<Int, Int> {
//    val map = mutableMapOf<Int, Int>()
//    for (i in list.indices) {
//        map[i] = list[i]
//    }
//    println(map)
//    for ((key, value) in map) {
//        if (map.contains(number - value) && number - value != key)
//            return Pair(map[number - value]!!, key)
//    }
//    return Pair(-1, -1)
//}
//
//fun sumtwo3(list: List<Int>, number: Int): Pair<Int, Int> {
//    val map = mutableMapOf<Int, Int>()
//    for (i in list.indices) {
//        map[list[i]] = i
//    }
//    for ((key, value) in map) {
//        if (map.contains(number - key) && map[number - key] != map[key])
//            return Pair(value, map[number - key]!!)
//    }
//    return Pair(-1, -1)
//}

fun sum(list: List<Int>, number: Int): Pair<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    for (i in list.indices) {
        if (map.contains(number - list[i])) {
            return Pair(map.getValue(number - list[i]), i)
        }
        map[list[i]] = i
    }
    return Pair(-1, -1)
}

fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> = sum(list, number)


//    for (numb in list.indices) {
//        val s = number - list[numb]
//        if (number - list[numb] == s && s in list && list.indexOf(s) != numb) return Pair(numb, list.indexOf(s))
//    }
//    return Pair(-1, -1)


/**
 * Очень сложная (8 баллов)
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun chest(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    var m = 0
    var mweight = 0
    var i = 0
    var spisok = setOf<String>()
    val list = treasures.keys.toList()
    while (i in 0..list.size - 1) {
        for (numb in treasures.values) {
            println("fist max $m")
            println(numb.first)
            println(numb.second)
            if (numb.first <= capacity) {
                if (numb.first + mweight <= capacity && numb.second + m >= m) {
                    mweight += numb.first
                    m += numb.second
                    spisok += list[i]
                } else if (numb.second >= m) {
                    spisok = setOf(list[i])
                    m = numb.second
                    mweight = numb.first
                    println("max $m")
                }
            }
            i += 1
            println("spisok $spisok")
        }
    }
    println("finish $spisok")
    return spisok
}

//fun chest2(treasures: Map<String, Pair<Int, Int>>, capacity: Int):Set<String>{
//    var m=0
//
//    var maxweight=0
//    var name= mutableListOf<String>()
//    var weight= mutableListOf<Int>()
//    var price=mutableListOf<Int>()
//    var name2= mutableListOf<String>()
//    var weight2= mutableListOf<Int>()
//    var price2=mutableListOf<Int>()
//    var result= mutableSetOf<String>()
//    for (i in treasures.keys){
//        name.add(i)
//    }
//    for (i in treasures.values){
//        weight.add(i.first)
//        price.add(i.second)
//    }
//    for (i in 1..(2.0).pow(price.size).toInt()) {
//        name2=name
//        price2=price
//        weight2=weight
//        while (price.size != 0) {
//
//            if (price.max() < m && maxweight + weight[price.indexOf(price.max())] <= capacity) {
//                result.add(name[price.indexOf(price.max())])
//                maxweight += weight[price.indexOf(price.max())]
//                m += price.max()
//            }
//            if (capacity - maxweight == 0) break
//            price.remove(price.max())
//            weight.removeAt(price.indexOf(price.max()))
//            name.removeAt(price.indexOf(price.max()))
//        }
//    }
//    return result
//}
//fun chest3(treasures: Map<String, Pair<Int, Int>>, capacity: Int):Set<String>{
//    var set= mutableSetOf<String>()
//    val weight= mutableListOf<Int>()
//    val price= mutableListOf<Int>()
//    for (i in treasures){
//        weight.add(treasures.values.first().first)
//        price.add(treasures.values.first().second)
//    }
//    val weightvar= weight
//    val pricevar= price
//    for (i in 0..weight.size-2){
//        var j=i+1
//        while (j <=weight.size-1){
//            weightvar.add(weight[i]+weight[j])
//            j+=1
//        }
//    }
//    for (i in 0..price.size-2){
//        for (j in 1..price.size-1){
//            pricevar.add(price[i]+price[j])
//        }
//    }
//    var m=0
//    for (i in 0..weightvar.size-1){
//        if (weightvar[i]<=capacity && pricevar[i]>=m){
//            m=pricevar[i]
//        }
//    }
//}

fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> = TODO()
