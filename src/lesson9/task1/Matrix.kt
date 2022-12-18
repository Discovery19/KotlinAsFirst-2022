@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

import kotlin.IllegalArgumentException

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> =
    if (height<=0 || width<=0) throw IllegalArgumentException()
else MatrixImpl(height,width,e)

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E> ( override val height: Int,override val width: Int,e: E): Matrix<E> {
    private val matrix = mutableMapOf<Cell,E>()

    init {
        for (row in 0 until height) {
            for (column in 0 until width) {
                matrix[Cell(row,column)]=e
            }
        }
    }

    override fun get(row: Int, column: Int): E =
        if (row !in 0..width && column !in 0..height) throw IllegalArgumentException()
        else matrix.getValue(Cell(row, column))

    override fun get(cell: Cell): E =
        if (cell.row !in 0..width && cell.column !in 0..height) throw IllegalArgumentException()
        else matrix.getValue(cell)

    override fun set(row: Int, column: Int, value: E) {
        matrix[Cell(row, column)] = value
    }

    override fun set(cell: Cell, value: E) {
        matrix[cell] = value
    }

    override fun equals(other: Any?) = TODO()

    override fun toString(): String {
        for (row in 0 until width) {
            for (column in 0 until height){
                val cell=Cell(row, column)
                if (matrix.containsKey(cell)) return "$cell"
            }
        }
        return ""
    }
}