package com.qw.kronerapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.qw.kronerapp.classes.AppLifeCycleHelper
import com.qw.kronerapp.classes.SoundMethod
import com.qw.kronerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerActivityLifecycleCallbacks(AppLifeCycleHelper())
        //Firebase.database.setPersistenceEnabled(true)

        val randomText = arrayOf("To a", "Disi lega", "After u Patici", "Love and Drinking that's what I'm thinking", "Fun and interactive", "kroner kroner kroner", "Korpak vara",
                "Idemo na jedno pivo", "Burek u El Panu", "Dupli pelin, pepeljara i šifra od wifija", "Mother of Dragons < Zmajka \uD83D\uDC09", "www.dominikgubo.com", "Vježbaj pi*ka ti materina",
                "Može dupli pelin za ovu gospodu", "Dva metra od mene", "Never gonna give you up..", "Šta ti, kosi ti",
                "I KO SMO SMI I KO SMO MI I KOSI TI I KOSI TI", "Rockatiki pucnjava \uD83D\uDD2B", "Ti i dalje pričaš svima kakvo tijelo Selma ima",
                "Moj Ivane, pobratime mio", "Znaš me, znam te", "Dule Savić", "Čepin geto zona \uD83D\uDEAB", "Grand theft auto Višnjevac \uD83D\uDE97", "Кали́нка, кали́нка, кали́нка моя́",
                "Pare nisu problem, para nema", "Nije mene dušo ubilo", "Možda pijan, ali nisam jako", "Ide gas", "Ej, od kad sam se rodio", "Nesmem s tobom kume ćelave ti gume",
                "Ja ne svijetlim, ja sijevam", "Pan je smeće, zlatni pan je ok", "Jim Bean > Jack Daniels \uD83E\uDD43", "Namazan u kocki", "Jedna strana, druga strana, jedan auto...",
                "Ja Ena nisam tvoj sin", "R.I.P Jozo Trovač", "00:00h pečena štrudla", "Uzalud vam trud svirači", "Evo mene moji ljudi", "Nije u šoldima sve", "Kafanica laganica", "Calen Bite",
                "Ima žena nema broja", "Svi su tu a tebe nema", "Preko tjedna gospoda vikendom smo gamad", "Volim piti i ljubiti", "Oči boje kestena", "Tu tu tu netko te zove", "Drska ženo plava",
                "Bolje biti pijan nego star", "Evo zore, evo zore Bogu da se pomolim", "Vic dva kostura \uD83D\uDC80", "Burger u Cityu \uD83C\uDF54", "\uD83D\uDCDE Osječki taxi 031 200 200",
                "Tamara koga si noćas varala", "Sibirski plavče zalij ga ledenim čajem", "Sinoć sam pola kafane popio", "To mi nije trebalo",
                "Funfact: postoji rakija od paprike \uD83C\uDF36", "žesta > sva ostala pića", "VROOM kad me opije", "https://bit.ly/3PSjT9k",
                "stari Magazin > novi Magazin", "Previše suza bilo je u mome pivu \uD83C\uDF7A", "Vatrogasac Mirko", "U birtiju kad dođem ja",
                "Antique Pelinkovac \uD83D\uDD1D", "E pa šta", "Eto ga na", "Nema me, noćas nema me", "Evo banke cigane moj", "Prsten i zlatni lanac", "乾杯 (かんぱい)",
                "Samo jako", "Ljeto već je gotovo", "Glavobolja od vina, a nigdje aspirina", "Udri jače manijače", "Pelin i med", "Dotako sam dno života", "Teška vremena, prijatelju moj",
                "Idi, idi nesanice", "Fenomenomenalno osjećam se", "Tek je 12 sati", "Tekla je rakija cijele noći venama", "Sve poštivam, svoje uživam", "Bolje biti Kroner™ nego star",
                "Prokleta je Amerika i zlato što sja", "Bombe devedesetih", "Bobo smrade", "Bacam ruže", "Ne može", "Ala nam je dobro", "May the force be with you", "Vescere bracis meis",
                "Demum, veniunt porci", "prodByLone music", "https://spoti.fi/3RMlf6g", "O Bella Ciao", "Kalashnikov, kalashnikov, kalashnikov",
                "Da smo živi, zdravi još godina sto", "365 je prošlo dana", "Trezan a za sebe ne znam", "\uD83D\uDCAA Imaš mišiće da otvoriš vrata", "Ja sam genije čudo prirode", "A ono..."
        )

        val randIndex = (0..randomText.size-1).random()
        binding.welcome.text=randomText[randIndex]
        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.welcome)
        binding.toNewGame.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
            var intent = Intent(this, ModeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
         binding.toRules.setOnClickListener{
             SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
            var intent = Intent(this, RulesActivity::class.java)
            startActivity(intent)
             overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
             finish()
        }
        binding.toGlobalStatistics.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://tranquil-cuchufli-dae6fb.netlify.app/dist/"))
            startActivity(intent)
        }
        binding.toOpcije.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
            var intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
   /* override fun onPause() {
        super.onPause()
        if (MusicMethod.player != null && AppLifeCycleHelper.isApplicationInForeground()==true) {
            MusicMethod.player.pause()
            Log.e("KOKOS", "sssssd")
        }
    }

    override fun onResume() {
        super.onResume()
        if (MusicMethod.player != null) {
            MusicMethod.player.start()
        }
    }*/


}