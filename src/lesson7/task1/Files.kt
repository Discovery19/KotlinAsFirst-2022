@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import lesson4.task1.string
import ru.spbstu.wheels.out
import java.io.File
import java.lang.StringBuilder
import kotlin.collections.MutableMap.MutableEntry
import kotlin.math.absoluteValue
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex.Empty

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun marker(inputName: String, outputName: String) {
    var line = ""
    val writer = File(outputName).bufferedWriter()
    fun readFile(fileName: String): List<String> = File(fileName).readLines()
    val spisok = readFile(inputName)
    for (i in 0..spisok.size - 1) {
        line = spisok[i]
        if (line.isBlank() == true) writer.newLine()
        else if (line.first() != '_') {
            writer.write(line)
            writer.newLine()
        }
    }
    writer.close()
}

fun deleteMarked(inputName: String, outputName: String) {
    marker(inputName, outputName)
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *println( spisok.windowed(substrings[i].length))
 * s = spisok.count { it.toString() == substrings[i] }
result.put(substrings[i], s)
 */
fun readFile(fileName: String): List<String> = File(fileName).readLines()
fun substr(inputName: String, substrings: List<String>): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    var s = 0
    val spisok = readFile(inputName).joinToString().toLowerCase()
    println(spisok)
    for (i in 0..substrings.size - 1) {
        var index: Int = spisok.indexOf(substrings[i].toLowerCase(), 0)
        while (index > -1) {
            s += 1
            index = spisok.indexOf(substrings[i].toLowerCase(), index + 1)
        }
        result.put(substrings[i], s)
        s = 0
    }
    return result
}

fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> = substr(inputName, substrings)


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val file = readFile(inputName).toList()
    val filework = file.map { it.trimStart().trimEnd() }
    val max = filework.maxOfOrNull { it.length } ?: 0
    File(outputName).bufferedWriter().use { writer ->
        for (i in filework) {
            val n = (max - i.length) / 2
            writer.write(" ".repeat(n) + i)
            writer.newLine()
        }
    }
}


//fun sqr(x: Int) = x * x
//fun cube(x: Int) = x * x * x
//
//fun sqrList(list: MutableList<Int>) = modifyList(list, ::sqr)
//fun cubeList(list: MutableList<Int>) = modifyList(list, ::cube)
//
//fun absList(list: MutableList<Int>) = modifyList(list) { it.absoluteValue }
//
//fun modifyList(list: MutableList<Int>, f: (Int) -> Int) {
//    for (i in list.indices) {
//        list[i] = f(list[i])
//    }
//}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
//!!!
fun alignFileByWidth(inputName: String, outputName: String) {
    val text = readFile(inputName)
    val writer = File(outputName).bufferedWriter()
    val workText = text.map { it.trim().replace("  ", " ") }
    val m = workText.maxOfOrNull { it.length } ?: 0
    for (i in workText) {
        if (i.isBlank()) {
            writer.newLine()
            continue
        }
        if (i.length == m) {
            writer.write(i.trim())
            writer.newLine()
        } else {
            var j = m - i.trim().length
            var str = i.split(" ").toMutableList()
            var c = 0
            while (j > 0) {
                str[c] += " "
                if (c < str.size - 2) c++
                else c = 0
                j--
            }
            println(str)
            writer.write(str.joinToString(" ").trim())
            writer.newLine()
        }
    }
    writer.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 * var index: Int = line.indexOf(fin[i], 0)
while (index > -1) {
s += 1
index = line.indexOf(fin[i], index + 1)
}
 */
fun top20Words2(inputName: String): Map<String, Int> {
    var resstr = mutableListOf<Pair<String, Int>>()
    val res = mutableMapOf<String, Int>()
    var line = readFile(inputName).joinToString()
    line = line.replace(regex = Regex("""[^A-Za-zА-Яа-яёЁ]"""), " ")
    line = line.replace("  ", " ")
    line = line.toLowerCase()
    if (line == "") return res
    var fin = line.split(" ")
    for (i in 0..fin.size - 1) {
        if (fin[i] != "") {
            resstr.add(Pair(fin[i], fin.count { it == fin[i] }))
        }
    }
    resstr.sortByDescending { it.second }
    var resstr2 = mutableMapOf<String, Int>()
    for (i in resstr) {
        resstr2.put(i.first, i.second)
    }
    var count = 0
    var k = 0
    while (true) {
        for (i in resstr2) {
            if (count <= 19) {
                res.put(i.key, i.value)
                count += 1
                k = i.value
            } else if (k == i.value) {
                res.put(i.key, k)
            }
        }
        break
    }
    return res
}

fun top20Words(inputName: String): Map<String, Int> = top20Words2(inputName)


/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
//!!!
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}
//{
//    var list = readFile(inputName)
//    var counter = 0
//    val writer = File(outputName).bufferedWriter()
//    var work = mutableListOf<String>()
//    for (c in list) {
//        work = c.split("").toMutableList()
//        for (i in dictionary) {
//            for (k in 0..work.size - 1) {
//                if (work[k].toLowerCase() == i.key.toLowerCase().toString()) work[k] =
//                    work[k].replace(work[k], i.value.toLowerCase())
//            }
//        }
//        counter++
//        work.removeIf { it!!.isBlank() }
//        work = work.joinToString("").split(" ").toMutableList()
//        if (counter <= 1 && dictionary.size > 0 && work[0].toLowerCase() in dictionary.keys.toString()) work[0] =
//            work[0].capitalize()
//        for (i in work) {
//            writer.write(i)
//            writer.newLine()
//        }
//    }
//    writer.close()
//}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun findDuplicates(values: List<String>): Set<String> {
    return values.groupingBy { it }.eachCount().filter { it.value > 1 }.keys
}

fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    var list = mutableListOf<String>()
    var res = mutableListOf<String>()
    var m = 0
    for (line in File(inputName).readLines()) {
        list.add(line)
    }
    for (i in list) {
        val name = i.toLowerCase().split("")
        val repeated = findDuplicates(name)
        if (repeated.size == 1 && name.size >= m) {
            res.add(i)
            m = name.size
        }
    }
    var res2 = mutableListOf<String>()
    for (i in res) {
        if (i.length >= m - 2) res2.add(i)
    }
    val line = res2.joinToString(", ")
    File(outputName).writeText(line)
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
//fun replace(str: String): String {
//    var res = str.replace("**", "%")
//    res = res.replace("*", "/")
//    return res.replace("~~", "~")
//}

fun html(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var file = File(inputName).readLines()
    writer.write("<html><body><p>")
    //var p = true
    for (k in file) {
//        if (k.isEmpty() && p == false) {
//            writer.write("<p>")
//            p = true
//            continue
//        } else
        if (k.isEmpty()) {
            writer.write("</p>")
            writer.write("<p>")
            continue
        }

//        var str = replace(k)
        var list = k.split("").toMutableList()
        var f = false
        for (i in list.indices) {
            if (list[i] == "~" && list[i + 1] == "~" && !f) {
                list[i] = "<s>"
                list[i + 1] = ""
                f = true
            } else if (list[i] == "~" && list[i + 1] == "~" && f) {
                list[i] = "</s>"
                list[i + 1] = ""
                f = false
            }
        }
        f = false
        for (i in list.indices) {
            if (list[i] == "*" && list[i + 1] == "*" && !f) {
                list[i] = "<b>"
                list[i + 1] = ""
                f = true
            } else if (list[i] == "*" && list[i + 1] == "*" && f) {
                list[i] = "</b>"
                list[i + 1] = ""
                f = false
            }
        }
        f = false
        for (i in list.indices) {
            if (list[i] == "*" && list[i + 1] != "*" && !f) {
                list[i] = "<i>"
                f = true
            } else if (list[i] == "*" && list[i + 1] != "*" && f) {
                list[i] = "</i>"
                f = false
            }
        }
        writer.write(list.joinToString(""))
    }
    writer.write("</p></body></html>")
    writer.close()
}

//    var b=false
//    var index=0
//    for (j in str){
//        if (j=='%' && b==false) {
//            index=str.indexOf("%")
//            StringBuilder(str).apply { insert(index, "<b>") }.toString()
//            b=true
//        }
//        else if (j=='%' && b==true){
//            index=str.indexOf("%")
//            StringBuilder(str).apply { insert(index, "</b>") }.toString()
//            b=false
//        }
//    }
//    println(str)


fun markdownToHtmlSimple(inputName: String, outputName: String) {
    html(inputName, outputName)
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 * var num = (lhv / rhv).toString().toMutableList()
var line = ""
var chislo = lhv.toString().toMutableList()
var ost = mutableListOf<Int>()
val writer = File(outputName).bufferedWriter()
writer.write("$lhv / $rhv")
writer.newLine()
var i = 0
var k = 0
while (k / rhv <= 0) {
k = k * 10 + chislo[i].toInt()
i += 1
}
// первые две строки
line = "-" + (k).toString()
writer.write(line + "     " + "$rhv")
writer.newLine()
writer.write("-".repeat(line.length))
k = k - rhv * num.first().toInt()
writer.newLine()

//центр
for (j in 1..num.size - 1) {
try {
println(chislo[i])
} catch (e: IndexOutOfBoundsException) {
break
}
while (k / rhv <= 0) {
k = k * 10 + chislo[i].toInt()
i += 1
writer.write(k.toString())
writer.newLine()
line = "-" + (rhv * num[j].toInt()).toString()
writer.write(line)
writer.newLine()
writer.write("-".repeat(line.length))
writer.newLine()
}
}
//заключающая строка (остаток)
line = (k - rhv * num.last().toInt()).toString()
writer.write(line)
writer.close()
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}
//    val digits = lhv.toString().split("").toMutableList()
//    val writer = File(outputName).bufferedWriter()
//    val delit = mutableListOf<Int>()
//    val ost = mutableListOf<Int>()
//    var k = 0
//    var con = 0
//    println(digits)
//    digits.removeIf{it!!.isBlank()}
//    for (i in 0..digits.size - 1) {
//        con = con * 10 + digits[i].toInt()
//        println(digits[i])
//        println(con)
//        k = i
//        if (con / rhv > 0) {
////            delit.add((con / rhv) * rhv)
////            ost.add(con - (con / rhv) * rhv)
//            break
//        }
//    }
//    println("pizda "+k)
//    println("suka "+delit)
//    println("blyat "+ ost)
//    con = con - con / rhv * rhv
//    for (i in k..digits.size - 1) {
//        while (digits[i] != digits.last()) {
//            con = con * 10 + digits[i].toInt()
//            k = i
//            delit.add(con / rhv * rhv)
//            ost.add(con - con / rhv * rhv)
//            if (con / rhv > 0) {
//                con = con - con / rhv * rhv
//                break
//            }
//        }
//    }
//
//    if (delit[0]==0) {
//        delit.removeAt(0)
//        ost.removeAt(0)
//    }
//    println("eb "+digits)
//    println("a "+delit)
//    println("t "+ost)
//    // первые две строки
//    val otv=lhv/rhv
//    writer.write(" "+lhv+" | "+rhv)
//    writer.newLine()
//    var line = "-" + delit[0].toString()
//    writer.write(line + "     " + "$otv")
//    writer.newLine()
//    writer.write("-".repeat(line.length))
//    k = line.length-1
//    writer.newLine()
//
//    for (i in 0..ost.size-2){
//        val snos=digits[k]
//        val tpc=ost[i]
//        val dlc=delit[i+1]
//        line=" ".repeat(k)+"-"+"$dlc"
//        if (delit[i]<rhv) writer.write(" ".repeat(k)+"$tpc"+"$snos")
//        else writer.write(" ".repeat(k)+"$tpc"+"$snos")
//        writer.newLine()
//        if (("$tpc"+"$snos").length>"$dlc".length)  {
//            writer.write(" ".repeat(k)+"-"+"$dlc")
//            writer.newLine()
//            writer.write(" ".repeat(k)+"-".repeat(line.length-k))
//        }
//        else {
//            writer.write(" ".repeat(k-1)+"-"+"$dlc")
//            writer.newLine()
//            writer.write(" ".repeat(k-1)+"-".repeat(line.length-k))
//        }
//        writer.newLine()
//        k = line.length-1
//    }
//    writer.close()
//}


