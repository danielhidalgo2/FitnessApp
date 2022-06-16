package com.example.fitnessapp.ui.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.data.models.Perdidapeso
import com.example.fitnessapp.databinding.ActivityAltasProteinasBinding
import com.example.fitnessapp.ui.view.holders.PerdidaPesoHolder
import com.example.fitnessapp.ui.view.holders.listaf
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AltasProteinas : AppCompatActivity() {
    private lateinit var binding:ActivityAltasProteinasBinding
    lateinit var adapter: PerdidaPesoHolder
    lateinit var datos:MutableList<Perdidapeso>

    private val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAltasProteinasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)


         datos= mutableListOf<Perdidapeso>(
            Perdidapeso("HUEVOS REVUELTOS CON HESPINACAS","https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2020/06/receta-huevos-altos-en-proteina1.jpg","Ingredientesenviar por correo, se abrirá en otra pestañaimprimir , se abrirá en otra pestaña\n" +
                    "2 huevos ligeramente batidos \n" +
                    "2 cucharadas de queso parmesano \n" +
                    "1 taza de espinacas cinceladas \n" +
                    "1 cucharadita de aceite de oliva \n" +
                    "¼ cebolla finamente picada \n" +
                    "2 dientes de ajo finamente picado \n" +
                    "¼ taza de quínoa cocida \n" +
                    "Sal y pimienta al gusto .\n"+"PREPARACIÓN\n" +
                    "\n" +
                    "CALIENTA una sartén, agrega el aceite de oliva y sofríe la cebolla con el ajo.\n" +
                    "AGREGA los huevos y cocina por dos minutos, moviendo constantemente; adiciona la espinaca, el queso parmesano, la quínoa, la sal y la pimienta. \n" +
                    "COCINA por cinco minutos y sirve estos deliciosos huevos revueltos altos en proteína con espinacas.","180 klc","15 mins")
        ,
        Perdidapeso("Pechuga de pollo al Horno","https://www.recetasconpollo.org/wp-content/uploads/2019/10/pechuga-de-pollo-al-horno-al-limon-.jpg","Retirar los posibles excesos de grasa de las pechugas y secar con papel de cocina. Colocar una fuente amplia llena de agua y disolver la sal. Añadir las pechugas procurando que queden completamente cubiertas. Llevar a la nevera como mínimo 30 minutos, mejor una o dos horas.\n" +
                "\n" +
                "Precalentar el horno a 190ºC. Enjuagar las pechugas con agua fría y secar con papel de cocina. Cubrir el fondo de una fuente apta para horno con el zumo de limón. Disponer el pollo y masajear con el aceite de oliva. Cubrir bien por ambos lados con las especias.","164 kcl","50 mins"),
            Perdidapeso("SALMON AL HORNO CON PATATAS","https://t1.uc.ltmcdn.com/es/posts/5/6/6/como_hacer_salmon_al_horno_con_patatas_41665_orig.jpg","INGREDIENTES PARA HACER SALMÓN AL HORNO\n" +
                    "2 lomos de salmón o filetes de salmón: \n" +
                    "1 cebolla, " +
                    "2 dientes de ajo, " +
                    "2 patatas grandes, " +
                    "Aceite de oliva virgen extra, " +
                    "Sal, " +
                    "Pimentón dulce, " +
                    "200 ml de vino blanco.\n"+"CÓMO HACER SALMÓN AL HORNO:\n" +
                    "1.- Comenzamos haciendo un majado en un mortero. Machacamos los dientes de ajo con un poco de sal. A esto le agregamos una cucharadita de pimentón y el vaso de vino de blanco.\n" +

                    "2.- Cortamos las patatas en rodajas y la cebolla en juliana y las ponemos en una fuente de horno. Las patatas cortadas en rodajas  de medio centímetro de grosor.\n"+"3.- Introducimos al horno a 180º durante 20 minutos para que se vayan cocinando.\n" +

                    "4.- Mientras se hacen las patatas, preparamos el salmón. Lo salpimentamos.\n" +

                    "5.- Sacamos la bandeja de horno y ponemos el salmón encima de las patatas y cebol.","245 kcl","40 mins"),
            Perdidapeso("BATIDO DE FRESAS, PLATANO Y NUECES","https://www.naturalcastello.com/wp-content/uploads/2019/08/batido-fresa-platano.jpg","Las proteínas de este batido las aporta principalmente la leche y las nueces. Además, su alto contenido de fruta nos permite obtener una dosis interesante de vitaminas, minerales y antioxidantes. \n"+
            "Ingredientes:\n" +
                    "5 fresas\n" +
                    "5 nueces\n" +
                    "Miel (25 g)\n" +
                    "1 plátano en su punto justo de maduración\n" +
                    "1 vaso de leche desnatada o un vaso de alguna bebida vegetal (200 ml).\n"+"Preparación:\n" +
                    "Empezaremos limpiando bien las fresas y cortándolas por la mitad. Después, ya en la batidora, solo tendrás que incluir el vaso de leche desnatada, el plátano cortado a trocitos, las nueces troceadas y esa cucharada de miel.\n" +
                    "Bate hasta que los ingredientes estén bien integrados.","248 kcl","20 mins"),
            Perdidapeso("PAN PROTEICO CON HUEVOS Y SIN HARINA","https://resizer.glanacion.com/resizer/eIjmp3btyQIzDvHKV0IBxoLpdJY=/768x0/filters:format(webp):quality(80)/cloudfront-us-east-1.images.arcpublishing.com/lanacionar/ZOVLUZPDIJFTLDNDTL5ECE2FEA.jpg",
                "Ingredientes:\n" +
                        "3 huevos, " +
                        "210 g de salvado de avena, " +
                        "400 g de queso batido, " +
                        "16 g de levadura, " +
                        "5 g de sal.\n"+"Preparación\n" +
                        "Separar las yemas y las claras de los huevos.\n" +
                        "Echar una pizca de sal a las claras y batirlas a punto nieve. Reservar.\n" +
                        "Añadir el resto de sal a las yemas de los huevos.\n" +
                        "Incorporar el queso batido a las yemas y mezclar.\n" +
                        "A continuación, agregar el salvado de avena y batir bien la mezcla.\n" +
                        "Para terminar la masa, agregar las clara a punto nieve a las yemas mezcladas con los otros ingredientes y unir toda la preparación\n" +
                        "Una vez esté la masa lista, colocar los panecillos sobre una bandeja.\n" +
                        "Introducir el pan en el horno a 180 °C de temperatura durante 55 minutos.","300 kcl","60 mins")
        )

        adapter= PerdidaPesoHolder(this,datos)
        val lista = binding.recyclerview3
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
                    finish()
                }
                com.example.fitnessapp.R.id.opcion3 ->{
                    val intent= Intent(this,Ajustes::class.java)
                    startActivity(intent)
                    finish()

                }
            }

            true
        }


    @RequiresApi(Build.VERSION_CODES.N)
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
}