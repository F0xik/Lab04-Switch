package g324.tanasov.lab04switch
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import g324.tanasov.lab04switch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    val priceArray = arrayOf(10.25f, 20.10f, 5.99f, 12.50f)
    var sum : Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        setPrice()
    }

    fun setPrice()
    {
        bindingClass.etSetApple.setText("10.25")
        bindingClass.etSetStrawberry.setText("20.10")
        bindingClass.etSetBlueberry.setText("5.99")
        bindingClass.etSetPotatoes.setText("12.50")
    }
    fun onClickChkBoxes(view: View) {
        try {
            sum = 0f
            if(bindingClass.chbApple.isChecked) { sum += (bindingClass.etApple.text.toString().toFloat() * priceArray[0])}
            if(bindingClass.chbStrawberry.isChecked) { sum += (bindingClass.etStrawberry.text.toString().toFloat() * priceArray[1])}
            if(bindingClass.chbBlueberry.isChecked) { sum += (bindingClass.etBlueberry.text.toString().toFloat() * priceArray[2])}
            if (bindingClass.chbPotatoes.isChecked) { sum += (bindingClass.etPotatoes.text.toString().toFloat() * priceArray[3])}
        }
        catch (e: Exception)
        {
        }
    }
    fun onClickCalc(view: View)
    {
        onClickChkBoxes(view)
        if(bindingClass.swAnswer.isChecked) {
            val dialogBinding = layoutInflater.inflate(R.layout.fin_dialog, null)
            val myDialog = Dialog(this)
            myDialog.setContentView(dialogBinding)
            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()
            var finalSum = dialogBinding.findViewById<TextView>(R.id.twSum)
            finalSum.setText(sum.toString())
            val btnOkay = dialogBinding.findViewById<Button>(R.id.btOkay)
            btnOkay.setOnClickListener()
            {
                myDialog.dismiss()
            }
        }
        else {
            Toast.makeText(
                applicationContext,
                "Итоговая стоимость: " + sum,
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun onClickApply (view : View)
    {
        try {
        priceArray[0] = bindingClass.etSetApple.text.toString().toFloat()
        priceArray[1] = bindingClass.etSetStrawberry.text.toString().toFloat()
        priceArray[2] = bindingClass.etSetBlueberry.text.toString().toFloat()
        priceArray[3] = bindingClass.etSetPotatoes.text.toString().toFloat()
    }
    catch (e: Exception)
    {
        Toast.makeText(
            applicationContext,
            "Ошибка при вводе цены за ед. товара. Введите правильную цену!",
            Toast.LENGTH_SHORT
        ).show()
        setPrice()

    }
    }
}
