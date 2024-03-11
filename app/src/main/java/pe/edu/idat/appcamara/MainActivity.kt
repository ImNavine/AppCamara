package pe.edu.idat.appcamara

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import pe.edu.idat.appcamara.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var file: File
    private var rutaFotoActual=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btncompartir.setOnClickListener(this)
        binding.btntomarfoto.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btntomarfoto -> tomarfoto()
            R.id.btncompartir -> compartirfoto()
        }
    }

    private fun compartirfoto() {
        TODO("Not yet implemented")
    }

    private fun tomarfoto() {
    //abrircamara.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            it.resolveActivity(packageManager).also {
                componente ->
                crearArchivoFoto()
                val fotoUri: Uri=
                FileProvider.getUriForFile(
                    applicationContext,
                    "pe.edu.idat.appcamara.fileprovider",
                    file
                )
                it.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri)
            }
        }
        abrircamara.launch(intent)
    }

    private val abrircamara = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        result ->
        if (result.resultCode== RESULT_OK){
            /*val data = result.data!!
            val imagenBitmap = data.extras!!.get("data") as Bitmap*/
            binding.ivfoto.setImageBitmap(obtenerImagenBitMap())
        }
    }
    private fun crearArchivoFoto(){
        val directorioImg = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", directorioImg)
        rutaFotoActual=file.absolutePath
    }
    private fun obtenerImagenBitMap(): Bitmap{
        return BitmapFactory.decodeFile(file.toString())
    }
}