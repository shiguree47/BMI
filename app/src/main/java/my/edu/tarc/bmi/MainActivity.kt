package my.edu.tarc.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)
        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)
        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener{
            if(editTextWeight.text.isBlank() || editTextWeight.text.toString().equals("0")) {
                editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            if(editTextHeight.text.isBlank() || editTextHeight.text.toString().equals("0")) {
                editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }
            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()
            var bmi = weight / (height/100).pow(2)

            if(bmi < 18.5){ //Underweight
                imageViewBMI.setImageResource(R.drawable.under)
                //Body Mass Index: XX.XX
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                //Status
                textViewStatus.text = String.format("%s : %s" ,getString(R.string.status),getString(R.string.under))
            }
            if(bmi in 18.5..24.9){ //Normal
                imageViewBMI.setImageResource(R.drawable.normal)
                //Body Mass Index: XX.XX
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                //Status
                textViewStatus.text = String.format("%s : %s" ,getString(R.string.status),getString(R.string.normal))
            }
            if(bmi >24.9){ //Overweight
                imageViewBMI.setImageResource(R.drawable.over)
                //Body Mass Index: XX.XX
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), bmi)
                //Status
                textViewStatus.text = String.format("%s : %s" ,getString(R.string.status),getString(R.string.over))
            }
        }

        buttonReset.setOnClickListener {
            imageViewBMI.setImageResource(R.drawable.empty)
            editTextWeight.text.clear()
            editTextHeight.text.clear()
            textViewBMI.text = String.format("%s ", getString(R.string.bmi))
            textViewStatus.text = String.format("%s ", getString(R.string.status))
        }
    }
}