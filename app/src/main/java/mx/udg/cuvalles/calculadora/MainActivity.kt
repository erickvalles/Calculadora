package mx.udg.cuvalles.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/*
* Lo que hacemos es implementar la interfaz
* OnclickListener de la clase View, para poder "escuchar los clicks"
* en ciertos elementos
* */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    /*
    * La variable operación nos servirá para establecer qué operación se realizará entre los
    * operandos*/
    var operacion = ""
    /*
    * Esta variable nos servirá para saber si existe una operación pendiente o no,
    * lo cual nos ayuda a determinar si estamos ante el operando 1 o el operando 2
    * */
    var operacionPendiente = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //llamada al método initUI
        initUI()
    }

    /*
    * El método nos sirve para inicializar nuestros listeners,
    * es completamente opcional
    * */
    private fun initUI(){
        /*
        * Asignamos los listeners a los botones*/
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        btnIgual.setOnClickListener(this)
    }

    /*
    * La interfaz onclick sí necesita estar implementada debido al gran número de
    * botones con los que cuenta específicamente esta app
    * */

    override fun onClick(p0: View?) {
        /*La estructura de control when es el equivalente al switch*/
        when(p0!!.id){//utilizamos el id del elemento al que se le haya dado click, el cual, recibimos como argumento en el método


            R.id.btn1->{ // con funciones de flecha damos funcionalidad a cada botón
                if(operacionPendiente){
                    etOperando2.text.append("1")
                }else{
                    etOperando1.text.append("1")
                }

            }
            R.id.btn2->{
                if(operacionPendiente){
                    etOperando2.text.append("2")
                }else{
                    etOperando1.text.append("2")
                }
            }
            R.id.btn3->{
                if(operacionPendiente){
                    etOperando2.text.append("3")
                }else{
                    etOperando1.text.append("3")
                }
            }
            /*
            * Cuando es un botón de operador, cambiamos la operación y establecemos el hecho
            * de que existe una operación pendiente
            * */
            R.id.btnPlus-> {
                tvOperacion.setText("+")
                operacion = "+"
                operacionPendiente = true
                if(etOperando1.text.isEmpty()){
                    etOperando1.text.append("0")
                }

            }
            /*Cuando el botón que se presiona es el igual =
            * Realizamos otro when basándonos en la operación seleccionada y realizamos la operación
            * */
            R.id.btnIgual->{
                var operando1: Float
                var operando2: Float
                if(etOperando1.text.isNotEmpty() and etOperando2.text.isNotEmpty()){
                    operando1 = etOperando1.text.toString().toFloat()
                    operando2 = etOperando2.text.toString().toFloat()
                }else{
                    operando1 = 0f
                    operando2 = 0f
                }

                var resultado = 0f
                when(operacion){
                    "+"-> resultado = operando1+operando2
                    "-"-> resultado = operando1-operando2
                }

                operacionPendiente = false //quitamos la bandera y reestablecemos los editText
                etOperando1.text.clear()
                etOperando2.text.clear()
                tvResultado.setText("$resultado")//mostramos el resultado
            }

            /*
            * Método de borrar pantalla, debería dejar todo en su estado normal*/

        }


    }
}
