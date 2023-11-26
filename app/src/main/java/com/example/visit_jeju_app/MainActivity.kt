package com.example.visit_jeju_app

import ImageSliderAdapter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.visit_jeju_app.databinding.ActivityMainBinding
import com.example.visit_jeju_app.mainAdapter.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    //액션버튼 토글
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        //드로워화면 액션버튼 클릭 시 드로워 화면 나오게 하기
        toggle =
            ActionBarDrawerToggle(this@MainActivity, binding.drawerLayout,R.string.open, R.string.close)

        binding.drawerLayout.addDrawerListener(toggle)
        //화면 적용하기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //버튼 클릭스 동기화 : 드로워 열어주기
        toggle.syncState()

        // 리사이클러 뷰 붙이기
        val datasHotel = mutableListOf<String>()
        for(i in 1..5) {
            datasHotel.add("제주 숙박 : $i")
        }
        val datasRestaurant = mutableListOf<String>()
        for(i in 1..5) {
            datasRestaurant.add("제주 맛집 : $i")
        }
        val datasTour = mutableListOf<String>()
        for(i in 1..5) {
            datasTour.add("제주 투어 : $i")
        }
        val datasFestival = mutableListOf<String>()
        for(i in 1..5) {
            datasFestival.add("제주 축제 : $i")
        }
        val datasShopping = mutableListOf<String>()
        for(i in 1..5) {
            datasShopping.add("제주 쇼핑 : $i")
        }

        // 제주 숙박
        val horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //val linearLayoutManager = LinearLayoutManager(this)

        // 리사이클러 뷰 속성 옵션에 출력 옵션 붙이기
        binding.viewRecyclerHotel.layoutManager = horizontalLayoutManager
        // 리사이클러뷰 속성 옵션에 데이터를 붙이기 , 어댑터 를 연결한다.
        val customAdapter1 = RecyclerView(datasHotel)
        binding.viewRecyclerHotel.adapter = customAdapter1

       // 제주 맛집
        val restaurantLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.viewRecyclerRestaurant.layoutManager = restaurantLayoutManager
        val customAdapter2 = RecyclerView(datasRestaurant)
        binding.viewRecyclerRestaurant.adapter = customAdapter2

        // 제주 투어
        val tourLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.viewRecyclerTour.layoutManager = tourLayoutManager
        val customAdapter3 = RecyclerView(datasTour)
        binding.viewRecyclerTour.adapter = customAdapter3

        // 제주 축제
        val festivalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.viewRecyclerFestival.layoutManager = festivalLayoutManager
        val customAdapter4 = RecyclerView(datasFestival)
        binding.viewRecyclerFestival.adapter = customAdapter4

        // 제주 쇼핑
        val shoppingLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.viewRecyclerShopping.layoutManager = shoppingLayoutManager
        val customAdapter5 = RecyclerView(datasShopping)
        binding.viewRecyclerShopping.adapter = customAdapter5



       /* // 메인 슬라이더
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        // 뷰페이저2 어댑터 설정하기
        val images = listOf(R.drawable.jeju_apec01, R.drawable.jeju_apec02, R.drawable.jeju_apec03, R.drawable.jeju_apec04)
        val imageSliderAdapter = ImageSliderAdapter(images)
        viewPager.adapter = imageSliderAdapter*/



    } //onCreate

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_toolbar_search)
        // menuItem 의 형을 SearchView 타입으로 변환, 형변환
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 마다, 실행될 로직을 추가.
                Log.d("kmk","텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(this@MainActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}

