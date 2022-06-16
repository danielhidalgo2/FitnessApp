package com.example.fitnessapp.ui.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.data.models.Perdidapeso
import com.example.fitnessapp.databinding.ActivityPerdidaPesoBinding
import com.example.fitnessapp.ui.view.holders.PerdidaPesoHolder
import com.example.fitnessapp.ui.view.holders.listaf
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class GananciaMuscular : AppCompatActivity() {
    lateinit var adapter: PerdidaPesoHolder
    private lateinit var binding: ActivityPerdidaPesoBinding

    private val db = FirebaseFirestore.getInstance()
    lateinit var datos:MutableList<Perdidapeso>

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerdidaPesoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)
         datos= mutableListOf<Perdidapeso>(
            Perdidapeso("POLLO PICATE CON CUSCÚS","https://blogscdn.thehut.net/app/uploads/sites/450/2020/01/Pollo-picante-con-cusc%C3%BAs_1578482471.jpg",
                        "Esta receta de pollo picante con cuscús es una deliciosa y sencilla alternativa al típico arroz con pollo de siempre. Podrás prepararla el domingo por la tarde en pocos minutos y tendrás comidas para toda la semana.\n"+
                        " INGREDIENTES:\n" +
                        "1 cucharada de pasta de curry\n" +
                        "1 cucharada de chutney de mango\n" +
                        "½ cucharadita de cúrcuma\n" +
                        "Sal al gusto\n" +
                        "50 ml de aceite de oliva\n" +
                        "4 pechugas de pollo\n" +
                        "300 g de cuscús\n" +
                        "350 ml de caldo de verduras\n"
                ,"400 kcl","35 min"),

            Perdidapeso("KEBAB FITNESS","https://www.recetas.fitness/wp-content/uploads/2022/03/kebab-fitness.jpg",
                "A pesar de que podemos preparar un kebab en casa, aliñando nuestra propia carne, la carne de kebab es tan característica que es lo que le da ese toque especial al plato. Esta se elabora a base de carne de pollo, ternera, cordero o pavo, aliñada y prensada para poder encajar en los asadores de los restaurantes. \n" +
                        "INGREDIENTES:\n" +
                        "2 pechugas de pollo, " +
                        "125 gr de queso batido o yogurt natural, " +
                        "200 ml de leche desnatada, " +
                        "2 cucharaditas de mostaza sin sal, " +
                        "15 ml de aceite de oliva virgen, " +
                        "1 limón, " +
                        "2 cucharaditas de ajo granulado, " +
                        "2 cucharaditas de orégano, " +
                        "2 cucharaditas de pimienta negra molida, "+
                        "1 cucharada de comino, "+
                        "1 cucharada de pimentón dulce o picante, "+
                        "1 cucharadita de jengibre, "+
                        "2 tomates, "+
                        "1 cebolla morada o cebolleta, "+
                        "Lechuga al gusto, "+
                        "Sal al gusto y "+
                        "Tortillas de harina, maíz o pan de pita"
                        ,"450 Kcl","50 mins"),
            Perdidapeso("POLLO A LA PLANCHA CON HIERBAS Y PASTA AL PESTO","https://www.recetas.fitness/wp-content/uploads/2020/12/Pollo-a-la-plancha-con-hierbas-y-pasta-al-pesto-.jpg",
                "Esta es una receta que no te tomará más de 40 minutos realizar, y todo se facilitará aun más si haces uso de estas Planchas eléctricas, sumamente prácticas y con características que te encantarán. Tienen un diseño exclusivo, muy fáciles de limpiar y con diversos beneficios para la cocina. \n" +
                        "INGREDIENTES:\n" +
                        "2 filetes de pechuga o contramuslo de pollo, " +
                        "½ kilo de pasta, " +
                        "5 diente ajos, " +
                        "Hojas de albahaca, " +
                        "Hojas de orégano, " +
                        "Hojas de romero, " +
                        "3 cucharadas de queso parmesano, " +
                        "1 taza de aceite de oliva, " +
                        "Un chorrito de vinagre blanco, " +
                        "Zumo de un limón y " +
                        "Sal y pimienta al gusto. "

                        ,"330 Klc","20mins"),
            Perdidapeso("SMOOTHIE DE MORA AZUL, PLÁTANO Y NUECES","https://www.recetas.fitness/wp-content/uploads/2019/08/antioxidant-beverage-blended-blueberries-blueberry-blueberry-smoothie-1453523-pxhere.com_.jpg",
                "El Smoothie de Mora Azul dentro de nuestra dieta, rica en estos frutos, nos proporciona una fuente importante de antioxidantes, antiinflamatorios y polifenoles. Además de que previene enfermedades degenerativas. asociadas al síndrome metabólico, enfermedades cardiobasculares, y diabetes. \n" +
                        "INGREDIENTES:\n" +
                        "½ taza de Nueces peladas \n" +
                        "1 Plátano\n " +
                        "1 Taza de leche de coco \n " +
                        "1 taza de moras azules\n " +
                        "½ cucharada de chía\n "

                ,"210 Klc","10mins"),
            Perdidapeso("MAGDALENAS DE AVENA FITNESS","https://www.recetas.fitness/wp-content/uploads/2020/08/magdalenas-de-avena-fitness.jpg",
                "Las magdalenas de avena son, sin lugar a dudas, una de las mejores formas de poder disfrutar de un delicioso postre saludable. Lo mejor de todo esto es que su preparación no requiere de mucho esfuerzo y es muy sencilla de realizar. \n" +
                        "INGREDIENTES:\n" +
                        "2 tazas de avena de tu preferencia \n" +
                        "1/3 De semillas de linaza (opcional)\n " +
                        "½ taza de edulcorante natural\n " +
                        "2 huevos\n " +
                        "1 plátano\n "+
                        "½ cucharadita de bicarbonato de sodio (opcional)\n "+
                        "½ taza de almendras (opcional)\n "+
                        "1 taza de yogurt (preferiblemente sin sabor)\n "+
                        "1 cucharadita de polvo de hornear\n "

                ,"180 Klc","25mins")
        )

        adapter= PerdidaPesoHolder(this,datos)
        val lista = binding.recyclerview2
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(this)

        val numero= Random().ints()

        val idejercicio45=numero.toString()


        if (0 in listaf) {
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos").document(idejercicio45).set(
                hashMapOf("titulo" to datos[0].titulo, "foto" to datos[0].foto,"informacion" to datos[0].informacion, "calorias" to datos[0].calorias, "tiempo" to datos[0].tiempo)

            )

        }

        if(1 in listaf){
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos").document(idejercicio45).set(
                hashMapOf("titulo" to datos[1].titulo, "foto" to datos[1].foto,"informacion" to datos[1].informacion, "calorias" to datos[1].calorias, "tiempo" to datos[1].tiempo)

            )
        }

        if (2 in listaf) {
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos").document(idejercicio45).set(
                hashMapOf("titulo" to datos[2].titulo, "foto" to datos[2].foto,"informacion" to datos[2].informacion, "calorias" to datos[2].calorias, "tiempo" to datos[2].tiempo)

            )

        }

        if (3 in listaf) {
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos")
                .document(idejercicio45).set(
                    hashMapOf(
                        "titulo" to datos[3].titulo,
                        "foto" to datos[3].foto,
                        "informacion" to datos[3].informacion,
                        "calorias" to datos[3].calorias,
                        "tiempo" to datos[3].tiempo
                    )

                )
        }

        if (4 in listaf) {
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos").document(idejercicio45).set(
                hashMapOf("titulo" to datos[4].titulo, "foto" to datos[4].foto,"informacion" to datos[4].informacion, "calorias" to datos[4].calorias, "tiempo" to datos[4].tiempo)

            )

        }



    }

    //con esta funcion controlamos la interacion con el menu y las distintas pantallas con sus funciones
    private val menuseleccion =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                com.example.fitnessapp.R.id.opcion1 -> {
                    val intent= Intent(this,Perfil::class.java)
                    startActivity(intent)
                    finish()

                }
                com.example.fitnessapp.R.id.opcion2 -> {
                    val intent= Intent(this,Home::class.java)
                    startActivity(intent)
                    item.isVisible=true
                    finish()
                }
                com.example.fitnessapp.R.id.opcion3 ->{
                    val intent= Intent(this,Ajustes::class.java)
                    startActivity(intent)
                    item.isVisible=true
                    finish()

                }
            }

            true
        }


    override fun onStop() {
        super.onStop()
        val numero= Random().ints()

        val idejercicio45=numero.toString()


        if (0 in listaf) {
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos").document(idejercicio45).set(
                hashMapOf("titulo" to datos[0].titulo, "foto" to datos[0].foto,"informacion" to datos[0].informacion, "calorias" to datos[0].calorias, "tiempo" to datos[0].tiempo)

            )

        }

        if(1 in listaf){
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos").document(idejercicio45).set(
                hashMapOf("titulo" to datos[1].titulo, "foto" to datos[1].foto,"informacion" to datos[1].informacion, "calorias" to datos[1].calorias, "tiempo" to datos[1].tiempo)

            )
        }

        if (2 in listaf) {
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos").document(idejercicio45).set(
                hashMapOf("titulo" to datos[2].titulo, "foto" to datos[2].foto,"informacion" to datos[2].informacion, "calorias" to datos[2].calorias, "tiempo" to datos[2].tiempo)

            )

        }

        if (3 in listaf) {
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos")
                .document(idejercicio45).set(
                    hashMapOf(
                        "titulo" to datos[3].titulo,
                        "foto" to datos[3].foto,
                        "informacion" to datos[3].informacion,
                        "calorias" to datos[3].calorias,
                        "tiempo" to datos[3].tiempo
                    )

                )
        }

        if (4 in listaf) {
            db.collection("Users").document(email.toString()).collection("RecetasFavoritos").document(idejercicio45).set(
                hashMapOf("titulo" to datos[4].titulo, "foto" to datos[4].foto,"informacion" to datos[4].informacion, "calorias" to datos[4].calorias, "tiempo" to datos[4].tiempo)

            )

        }
    }

    //setContentView(R.layout.activity_ganancia_muscular)
    }
