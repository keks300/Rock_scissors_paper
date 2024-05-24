package com.pashkov.rock_scissors_paper

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var userChoice: ImageView
    private lateinit var computerChoice: ImageView
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Привязка переменных к элементам интерфейса
        userChoice = findViewById(R.id.user_choice)
        computerChoice = findViewById(R.id.computer_choice)
        resultText = findViewById(R.id.result_text)
    }

    // Метод, который вызывается при нажатии на одну из кнопок выбора
    fun onUserChoice(view: View) {
        // Определение выбранной пользователем кнопки по её ID
        val userChoiceId = view.id
        // Определение изображения для выбора пользователя на основе нажатой кнопки
        val userChoiceImage = when (userChoiceId) {
            R.id.rock_button -> R.drawable.rock
            R.id.paper_button -> R.drawable.paper
            R.id.scissors_button -> R.drawable.scissors
            R.id.lizard_button -> R.drawable.lizard
            R.id.spock_button -> R.drawable.spock
            else -> throw IllegalArgumentException("Некорректный выбор")
        }
        // Установка изображения выбора пользователя
        userChoice.setImageResource(userChoiceImage)

        // Генерация случайного выбора для компьютера (от 0 до 4)
        val computerChoiceId = Random.nextInt(5)
        // Определение изображения для выбора компьютера на основе случайного числа
        val computerChoiceImageRes = when (computerChoiceId) {
            0 -> R.drawable.rock
            1 -> R.drawable.paper
            2 -> R.drawable.scissors
            3 -> R.drawable.lizard
            4 -> R.drawable.spock
            else -> throw IllegalStateException("Некорректный случайный выбор")
        }
        // Установка изображения выбора компьютера
        computerChoice.setImageResource(computerChoiceImageRes)

        // Определение результата игры на основе выбора пользователя и компьютера
        val result = determineWinner(userChoiceId, computerChoiceId)
        // Установка текста результата
        resultText.text = result
    }

    // Метод для определения победителя
    private fun determineWinner(userChoice: Int, computerChoice: Int): SpannableString {
        // Определение результата на основе выбора пользователя и компьютера
        val result = when (userChoice) {
            R.id.rock_button -> if (computerChoice == 2 || computerChoice == 3) "Вы выиграли!" else if (computerChoice == 1 || computerChoice == 4) "Компьютер выиграл!" else "Ничья!"
            R.id.paper_button -> if (computerChoice == 0 || computerChoice == 4) "Вы выиграли!" else if (computerChoice == 3 || computerChoice == 2) "Компьютер выиграл!" else "Ничья!"
            R.id.scissors_button -> if (computerChoice == 1 || computerChoice == 3) "Вы выиграли!" else if (computerChoice == 4 || computerChoice == 0) "Компьютер выиграл!" else "Ничья!"
            R.id.lizard_button -> if (computerChoice == 4 || computerChoice == 1) "Вы выиграли!" else if (computerChoice == 0 || computerChoice == 2) "Компьютер выиграл!" else "Ничья!"
            R.id.spock_button -> if (computerChoice == 0 || computerChoice == 2) "Вы выиграли!" else if (computerChoice == 1 || computerChoice == 3) "Компьютер выиграл!" else "Ничья!"
            else -> throw IllegalArgumentException("Некорректный выбор")
        }

        // Создание SpannableString для изменения цвета текста результата
        val spannableResult = SpannableString(result)

        // Определение цвета текста на основе результата
        val color = when (result) {
            "Вы выиграли!" -> Color.GREEN
            "Компьютер выиграл!" -> Color.RED
            else -> Color.BLACK
        }

        // Применение цвета к тексту результата
        spannableResult.setSpan(ForegroundColorSpan(color), 0, result.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Возврат текста с примененным цветом
        return spannableResult
    }
}
