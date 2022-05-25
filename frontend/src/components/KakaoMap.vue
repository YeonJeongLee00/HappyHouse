<template>
  <div>
    <div id="map" class="position-relative"></div>
    <!-- <button @click="displayMarker(markerPositions)">버튼!!</button> -->
  </div>
</template>

<script>
import { mapState } from "vuex";

const mapStore = "mapStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      // 화면에 표시할 지점 설정
      markerPositions: [],
      //  화면에 표시된 marker들을 저장
      markers: [],
      infowindow: null,
    };
  },
  created() {
    this.markerPositions = [];
    this.markerPositions.push([this.lat, this.lng]);
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      // 스크립트 객체 생성
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=54eb590ff2f217ee89f42f58c253277a&libraries=services,clusterer,drawing";
      // document의 head에 script 추가
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(mapStore, ["lng", "lat"]),
  },
  watch: {
    lng() {
      this.markerPositions = [];
      this.markerPositions.push([this.lat, this.lng]);
      this.displayMarker(this.markerPositions);
    },
    lat() {
      this.markerPositions = [];
      this.markerPositions.push([this.lat, this.lng]);
      this.displayMarker(this.markerPositions);
    },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.lat, this.lng),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      // this.markers.setMap(this.map);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    // 마커 표시 메소드
    displayMarker(markerPositions) {
      console.log(markerPositions);
      // 1. 현재 표시되어있는 마커들이 있으면 marker에 등록된 map을 없애준다.
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      // 마커 이미지 설정
      // const imgSrc = require("@/assets/apt1.png");
      // const imgSize = new kakao.maps.Size(24, 35);
      // const markerImage = new kakao.maps.markerImage(imgSrc, imgSize);

      // 2. 마커 표시하기
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position, // 마커 위치
              title: "", // 마우스 오버시 표시할 제목
              // image: markerImage, // 마커 이미지
            })
        );
        //  3. 지도 이동
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
        // this.markers.setMap(this.map);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 92vh;
  z-index: 1;
}
</style>
