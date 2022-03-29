# VaccinationCenterMap

코로나 예방접종센터 정보를 지도에서 확인할수있는 어플입니다. 

**주요 업무**

- Splash 화면에 ProgressBar 추가하여 데이터를 다 가져왔으면 2초동안 프로그래스바 동작
- 데이터를 다가져오지 못했다면 ProgressBar를 80%에서  데이터 가져오기가 완료될 때 까지 대기
- 데이터 가져오기 완료시 ProgressBar를 0.7초 안에 100%로 동작
- Room 에 데이터 저장후 지도화면으로 이동
- 저장된 리스트 데이터를 통해 마커 생성
- centerType에 따라 마커 색상 구분
- 마커 클릭시 해당 마커의 정보를 alert으로 생성


**스킬 및 사용 툴**

kotlin, Retrofit2, ViewModel, LiveData, DataBinding, Room, NaverMapApi, Marker, ProgressBar, git, Dagger, hilt, Flow, UseCase
