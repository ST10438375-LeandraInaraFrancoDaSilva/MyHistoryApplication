package com.example.myhistoryapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    enum class HistoricalFigures (val age: Int, val description: String) {
        Figure1(76,"Albert Einstein when he died. A scientist forever marked in physics history"),
        Figure2(51,"Michael Jackson when he died. The eternal king of pop"),
        Figure3(22,"Aaliyah when she died. A famous singer in the 2000's"),
        Figure4(95,"Nelson Mandela when he died. One of the most important personalities in South Africa"),
        Figure5(82,"Pele when he died. The most famous socker player in Brasil's history"),
        Figure6(36,"Princess Diana when she died. The most loved princess in England"),
        Figure7(40,"John Lenon when he died. American musician"),
        Figure8(91,"Harriet Tubman when she died. American activist"),
        Figure9(63,"J. Robert Oppenheimer when he died. Creator of the atomic bomb"),
        Figure10(27,"Amy Winehouse when she died. English musician with one of the most unique tones in the industry")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Getting layout components
        val edtUserAge = findViewById<TextView>(R.id.edtAge)
        val btnResult = findViewById<Button>(R.id.buttonHistory)
        val btnClear = findViewById<Button>(R.id.buttonClear)
        val txtResult = findViewById<TextView>(R.id.textResult)

        // If user presses the Display Results Button
        btnResult?.setOnClickListener () {
            val userAge = edtUserAge.text.toString().toInt()
            if (userAge != null && userAge in 20.. 100) {
                // Grabbing the values of the years in the list of historical figures
                val FiguresAges= HistoricalFigures.values().map { it.age }
                val Figures = when (userAge)
                {
                    //This statement will be run if the users age matches exactly
                    // the age of the historical figure
                    in FiguresAges -> {
                        val Figure = HistoricalFigures.values().find { it.age == userAge }
                        listOf("You are the same age as ${Figure?.description ?: "figure"}")
                    }
                    // Map function will transform each num constant into its corresponding
                    // age value
                    // This statement will run if the user is 3 years younger than
                    // the Historical figure
                    in FiguresAges.map { it -3 } -> {
                        val Figure = HistoricalFigures.values().find { it.age == userAge + 3 }
                        listOf("You are 3 years younger than ${Figure?.description ?: "figures"}")
                    }
                    //This statement will run if the user is 3 years older than
                    //the Historical figure
                    in FiguresAges.map { it + 3 } -> {
                        val Figure = HistoricalFigures.values().find { it.age == userAge - 3 }
                        listOf("You are 3 years older than ${Figure?.description ?: "figures"}")
                    }
                    else -> listOf("No Historical figures found at $userAge")

                }
                txtResult.text = Figures.joinToString("\n")
            }
            else
            {
                txtResult.text = "No figure has been found at $userAge."
            }
        }
        // If the user presses Clear button
        btnClear?.setOnClickListener() {
            edtUserAge.text = ""
            txtResult.text = ""


        }

    }
}
