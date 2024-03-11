package pe.edu.idat.appcamara

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import pe.edu.idat.appcamara.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
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
        abrircamara.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }
    private val abrircamara = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        result ->
        if (result.resultCode== RESULT_OK){
            val data = result.data!!
            val imagenBitmap = data.extras!!.get("data") as Bitmap
            binding.ivfoto.setImageBitmap(imagenBitmap)
        }
    }
}