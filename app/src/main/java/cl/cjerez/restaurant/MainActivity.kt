package cl.cjerez.restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cantidadPastel = findViewById<EditText>(R.id.inputCantidadPastel)
        val cantidadCazuela = findViewById<EditText>(R.id.inputCantidadCazuela)
        val pastel = findViewById<TextView>(R.id.precioPastel)
        val cazuela = findViewById<TextView>(R.id.precioCazuela)
        val comida = findViewById<TextView>(R.id.montoComida)
        val propina = findViewById<TextView>(R.id.montoPropina)
        val total = findViewById<TextView>(R.id.montoTotal)
        val swPropina = findViewById<SwitchCompat>(R.id.propina)

        val cuentaMesa = CuentaMesa()

        fun MonedaChilena(cambiar: Int): String {
            var formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
            return formato.format(cambiar)
        }

        fun comida(N_plato: Int) {
            comida.setText(MonedaChilena(N_plato))
        }

        fun propina(valor: Int) {
            if (valor == 1) {
                propina.setText(MonedaChilena(cuentaMesa.calcularPropina()))
            } else {
                propina.setText(MonedaChilena(valor))
            }
        }

        fun modificaTotal() {
            total.setText(MonedaChilena(cuentaMesa.calcularTotalConPropina()))
        }

        fun recalcularPropina() {
            if (swPropina.isChecked) {
                cuentaMesa.aceptaPropina = true
                propina(1)
            } else {
                cuentaMesa.aceptaPropina = false
                propina(0)
            }
            modificaTotal()
        }

        cantidadPastel.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                var cantidadP = p0.toString() // cantidad de pasteles
                var cantidadI = 0 // cantidad inicial
                if (cantidadP != "") {
                    cantidadI = cantidadP.toInt()
                }
                var menu = ItemMenu("Pastel", "12000")
                var mesa = ItemMesa(cantidadI, menu)
                pastel.setText(MonedaChilena(mesa.calcularSubtotal()))

                cuentaMesa.agregarItem(menu, cantidadI)
                recalcularPropina()

                comida(cuentaMesa.calcularTotalSinPropina())
            }

            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int
            ) {
            }
        })

        cantidadCazuela.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                var cantidadC = p0.toString() //cantidad de cazuelas
                var cantidadI = 0 //cantidad inicial
                if (cantidadC != "") {
                    cantidadI = cantidadC.toInt()
                }
                var menu = ItemMenu("Cazuela", "10000")
                var mesa = ItemMesa(cantidadI, menu)
                cazuela.setText(MonedaChilena(mesa.calcularSubtotal()))

                cuentaMesa.agregarItem(mesa)
                recalcularPropina()

                comida(cuentaMesa.calcularTotalSinPropina())
            }

            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int
            ) {
            }
        })

        swPropina.setOnClickListener {
            recalcularPropina()
        }
    }
}







