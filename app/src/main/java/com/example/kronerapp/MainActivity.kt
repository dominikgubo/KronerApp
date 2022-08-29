package com.example.kronerapp

import android.content.Intent
import android.net.Uri
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.kronerapp.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val randomText = arrayOf("To a", "Disi lega","After u Patici","Love and Drinking that's what I'm thinking","Fun and interactive", "kroner kroner kroner", "Korpak vara",
                "Idemo na jedno pivo", "Burek u El Panu", "Dupli pelin, pepeljara i šifra od wifija", "Mother of Dragons < Zmajka \uD83D\uDC09", "www.dominikgubo.com", "Vježbaj pi*ka ti materina",
                "Može dupli pelin za ovu gospodu", "Dva metra od mene", "Never gonna give you up..", "Šta ti, kosi ti",
                "I KO SMO SMI I KO SMO MI I KOSI TI I KOSI TI", "Rockatiki pucnjava \uD83D\uDD2B", "Ti i dalje pričaš svima kakvo tijelo Selma ima",
                "Moj Ivane, pobratime mio", "Znaš me, znam te", "Dule Savić", "Čepin geto zona \uD83D\uDEAB", "Grand theft auto Višnjevac \uD83D\uDE97","Кали́нка, кали́нка, кали́нка моя́",
                "Pare nisu problem, para nema", "Nije mene dušo ubilo", "Možda pijan, ali nisam jako","Ide gas","Ej, od kad sam se rodio", "Nesmem s tobom kume ćelave ti gume",
                "Ja ne svijetlim, ja sijevam", "Pan je smeće, zlatni pan je ok", "Jim Bean > Jack Daniels \uD83E\uDD43", "Namazan u kocki", "Jedna strana, druga strana, jedan auto...",
                "Ja Ena nisam tvoj sin", "R.I.P Jozo Trovač", "00:00h pečena štrudla", "Uzalud vam trud svirači", "Evo mene moji ljudi", "Nije u šoldima sve", "Kafanica laganica", "Calen Bite",
                "Ima žena nema broja", "Svi su tu a tebe nema", "Preko tjedna gospoda vikendom smo gamad", "Volim piti i ljubiti","Oči boje kestena", "Tu tu tu netko te zove", "Drska ženo plava",
                "Bolje biti pijan nego star", "Evo zore, evo zore Bogu da se pomolim","Vic dva kostura \uD83D\uDC80", "Burger u Cityu \uD83C\uDF54","\uD83D\uDCDE Osječki taxi 031 200 200",
                "Tamara koga si noćas varala", "Sibirski plavče zalij ga ledenim čajem", "Sinoć sam pola kafane popio", "To mi nije trebalo",
                "Funfact: postoji rakija od paprike \uD83C\uDF36", "žesta > sva ostala pića", "VROOM kad me opije", "https://bit.ly/3PSjT9k",
                "stari Magazin > novi Magazin", "Previše suza bilo je u mome pivu \uD83C\uDF7A", "Vatrogasac Mirko", "U birtiju kad dođem ja",
                "Antique Pelinkovac \uD83D\uDD1D", "E pa šta", "Eto ga na", "Nema me, noćas nema me", "Evo banke cigane moj", "Prsten i zlatni lanac", "乾杯 (かんぱい",
                "Samo jako", "Ljeto već je gotovo", "Glavobolja od vina, a nigdje aspirina", "Udri jače manijače", "Pelin i med", "Dotako sam dno života", "Teška vremena, prijatelju moj",
                "Idi, idi nesanice", "Fenomenomenalno osjećam se", "Tek je 12 sati", "Tekla je rakija cijele noći venama", "Sve poštivam, svoje uživam"
                )
        
        val randIndex = (0..randomText.size-1).random()
        binding.welcome.text=randomText[randIndex]
        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.welcome)
        binding.toNewGame.setOnClickListener{
            //perform an operation
            //send_Data is the button's id
            var intent = Intent(this,ModeActivity::class.java)
            startActivity(intent)
        }
         binding.toRules.setOnClickListener{
            var intent = Intent(this,RulesActivity::class.java)
            startActivity(intent)
        }
        binding.toGlobalStatistics.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://tranquil-cuchufli-dae6fb.netlify.app/dist/"))
            startActivity(intent)
        }
    }


}