package com.example.centerMap.presentation.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.centerMap.R
import com.example.centerMap.data.retrofit.dto.VaccinationCenterData
import com.example.centerMap.databinding.ActivityMainBinding

import com.naver.maps.map.NaverMapSdk.NaverCloudPlatformClient
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*

import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : ViewModelVC by viewModels()

    companion object{
        const val CLIENT_ID = "1sefg3dtp2"
        const val CENTERTYPE1 = "지역"
        const val CENTERTYPE2 = "중앙/권역"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNaverMap()
    }

    private fun initNaverMap() {
        NaverMapSdk.getInstance(this).client = NaverCloudPlatformClient(CLIENT_ID)
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
       viewModel.getVCDataFromDB().observe(this){
           val vcDataList = it

           vcDataList.forEach {
               initMarker(it, naverMap, vcDataList)
           }
       }
    }

    private fun initMarker(
        it: VaccinationCenterData,
        naverMap: NaverMap,
        vcDataList: List<VaccinationCenterData>
    ) {
        var markerList : MutableList<Marker> = mutableListOf()
        val data = it
        val marker = Marker()
        marker.position = LatLng(data.lat.toDouble(), data.lng.toDouble())
        marker.icon = makeColor(data)
        marker.width = Marker.SIZE_AUTO
        marker.height = Marker.SIZE_AUTO
        marker.map = naverMap
        markerList.add(marker)

        marker.setOnClickListener( Overlay.OnClickListener (){
           initAlertDialog(markerList, it, vcDataList)

            return@OnClickListener true
        })
    }

    private fun initAlertDialog(
        markerList: MutableList<Marker>,
        clickedMarker: Overlay,
        vcDataList: List<VaccinationCenterData>
    ) {
        for (i in markerList.indices){
            if (markerList[i]===clickedMarker){
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)

                builder.setTitle(vcDataList[i].centerName).setMessage(vcDataList[i].toString())
                builder.setPositiveButton("ok", null)

                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }
    }

    private fun makeColor(it : VaccinationCenterData): OverlayImage {
        if (it.centerType==CENTERTYPE1){
          return MarkerIcons.RED
        }
        if (it.centerType==CENTERTYPE2){
            return MarkerIcons.BLUE
        }
        return MarkerIcons.BLACK
    }


}

