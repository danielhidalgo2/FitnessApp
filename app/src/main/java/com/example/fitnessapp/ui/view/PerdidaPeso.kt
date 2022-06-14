package com.example.fitnessapp.ui.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.data.Perdidapeso
import com.example.fitnessapp.databinding.ActivityPerdidaPesoBinding

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

val favoritos= mutableListOf<Perdidapeso>()
class PerdidaPeso : AppCompatActivity() {
    lateinit var adapter: PerdidaPesoHolder
    private lateinit var binding: ActivityPerdidaPesoBinding

    private val db = FirebaseFirestore.getInstance()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerdidaPesoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instanciamos el menu de navegacion
        val bottomNav = binding.toolbar
        //le damos soporte con la funcion creada
        bottomNav.setOnNavigationItemSelectedListener(menuseleccion)

        val datos= mutableListOf<Perdidapeso>(
            Perdidapeso("ENSALADA SENCILLA Y SALUDABLE CON AGUACATE","https://www.elmueble.com/medio/2020/03/26/plato-de-ensalada-00436238_e39a64c1_1200x1800.jpg",
            "Fresas y aguacates son los ingredientes estrella de este plato que mejora la salud porque las primeras aportan vitamina C, entre otras, y los segundos son el conocido como \"oro verde\"." +
                    " Su potasio ayuda a reducir la presión arterial. INGREDIENTES: 4 PERSONAS / 35 MIN\n" +
                    "1 bolsa de brotes tiernos\n" +
                    "8 fresones\n" +
                    "32 fresas\n" +
                    "16 tomates cherry\n" +
                    "1 aguacate\n" +
                    "1 dátil\n" +
                    "1/2 limón\n" +
                    "1 naranja\n" +
                    "1 cucharada de aceite de oliva\n" +
                    "1 cucharada de semillas de amapola\n" +
                    "Pimienta negra recién molida.","400 kcl","35 min"),

            Perdidapeso("CREMA DE GUISANTES","https://www.elmueble.com/medio/2020/03/26/recetas-mejorar-salud-crema-guisantes-00377437_e10968fb_1200x1703.jpg",
            "INGREDIENTES: 4 PERSONAS / 40 MINUTOS\n" +
                    "600g de guisantes," +
                    "2 patatas," +
                    "2 puerros," +
                    "30g de mantequilla," +
                    "100g de gorgonzola," +
                    "2 rebanadas de pan de hogaza," +
                    "2 cucharadas de piñones," +
                    "2 cucharadas de aceite de oliva," +
                    "Perejil," +
                    "Sal y pimienta"+"\n"+"PREPARACIÓN:\n" +
                    "1. Pela las patatas y córtalas a rodajas.\n" +
                    "2. Limpia los puerros y córtalos en discos.\n" +
                    "3. Corta el pan a daditos y tuéstalo en el horno, 10 min a 180 ° (opcional).\n" +
                    "4. Tuesta los piñones en una sartén, a fuego suave.\n" +
                    "5. Calienta la mantequilla en una olla y añade el puerro. Póchalo a fuego suave durante 10 min. Luego, añade la patata y los guisantes, cúbrelos con 1 litro de agua, tápalos y cuécelos 20 min.\n" +
                    "6. Salpimenta y tritura.\n" +
                    "7. Sirve la crema bien caliente, acompañada del pan, los piñones, el gorgonzola a dados y un chorrito de aceite de oliva.","350 Kcl","40 mins"),
            Perdidapeso("DORADA CON AJITOS","https://www.clara.es/medio/2018/02/13/dorada-con-ajitos_af79eb5c_600x900.jpg",
            "Corta rodajas de patata y calabacín y hornéalas en una fuente refractaria durante unos 20 minutos a 180º. Luego, incorpora las doradas lavadas y sin espinas (u otro pescado que te guste), y hornea 10 minutos más.\n" +
                    "\n" +
                    " *Para completar el plato, añádele unas láminas de ajito dorado en aceite.","280 Klc","30mins"),
            Perdidapeso("BROWNIE DE COCO","https://gastronomicainternacional.com/wp-content/uploads/2019/12/negocio-de-postres-vegetarianos-brownies-300x300.jpg",
                    "Ingredientes, " +
                    "14 g  Mantequilla, 12 piezas de Huevo, 15 ml Vainilla, 2 g Sal, 60 g Yogurt natural sin azúcar, 96 g Azúcar de coco, 90 ml Leche light ,60 g Cacao en polvo, 2 g Polvo de hornear, 90 g Harina de todo uso y 42 g Chocolate amargo\n"+
                    "Elaboración paso a paso, " +
                    "Derretir mantequilla y reservar, " +
                    "Picar chocolate en trozos, " +
                    "Cernir harina y polvo para hornear y reservar, " +
                    "En un recipiente mezclar la mantequilla, los huevos, vainilla y sal, incorporar perfectamente y después agregar yogurt, azúcar, leche y coco en polvo, poco a poco agregar harina de trigo y polvo para hornear, incorporar perfectamente con una miserable e incorporar los trozos de chocolate. \n" +
                    "Derretir mantequilla y reservar, " +
                    "Picar chocolate en trozos, " +
                    "Cernir harina y polvo para hornear y reservar, " +
                    "En un recipiente mezclar la mantequilla, los huevos, vainilla y sal, incorporar perfectamente y después agregar yogurt, azúcar, leche y coco en polvo, poco a poco agregar harina de trigo y polvo para hornear, incorporar perfectamente con una miserable e incorporar los trozos de chocolate. Hornear en una charola antiadherente o usar papel mantequilla por 15 minutos a una temperatura de 300 ºF.",
                "236 Kcl","25 mins"),
            Perdidapeso("GALLETAS CON MANTEQUILLA DE CACAHUATE Y CHOCOLATE","https://gastronomicainternacional.com/wp-content/uploads/2020/03/pexels-photo-310575-300x300.jpeg","La combinación de cacahuate y chocolate es imperdible y que mejor hacer uso de este fruto seco rico en grasas monoinsaturadas y proteína. Además de ser un sustituto perfecto para bajar las calorías en tus recetas, esta idea te va a encartar.\n"+
            "Ingredientes:" +
                    "118 g Mantequilla de cacahuate (Peanut Butter / Crema de cacahuate), 30 ml Aceite de coco, 2 pzs Azúcar de coco, 15 ml Vainilla, 8 g Bicarbonato de Sodio, 5 g Canela en polvo, 2 g Sal, 118 g harina de coco y 118 g chocolate amargo\n" +
                    "Elaboración paso a paso: Precalentar el horno a 180 ºC, " +
                    "Batir la mantequilla de cacahuate, el aceite de coco derretido y azúcar de coco, agregar los huevos y la vainilla," +
                    "Agregar poco a poco la harina, sal y canela, finalmente el chocolate picado," +
                    "Poner en una charola para horno con papel encerado y hornear 7 minutos.","171 Kcl","20 mins")
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

                }
                com.example.fitnessapp.R.id.opcion2 -> {
                    val intent= Intent(this,Home::class.java)
                    startActivity(intent)
                    item.isVisible=true
                }
                com.example.fitnessapp.R.id.opcion3 ->{
                    val intent= Intent(this,Ajustes::class.java)
                    startActivity(intent)
                    item.isVisible=true

                }
            }

            true
        }


}