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
      // infowindow: null,
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
    ...mapState(mapStore, ["lng", "lat", "type", "name"]),
  },
  watch: {
    lng() {
      this.markerPositions = [];
      this.markerPositions.push([this.lat, this.lng]);
      this.displayMarker(this.markerPositions);
      for (let index = 0; index < this.lng.length; index++) {
        this.markerPositions.push([this.lat[index], this.lng[index]]);
      }
      console.log(this.markerPositions);
      this.displayMarker(this.markerPositions);
    },
    // 주변 상가 가져오기
    type() {
      this.displayType();
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
      console.log("mark : " + markerPositions);

      // 1. 현재 표시되어있는 마커들이 있으면 marker에 등록된 map을 없애준다.
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      // 마커 이미지 설정
      const imgSrc = require("@/assets/apt1.png");
      const imgSize = new kakao.maps.Size(24, 24);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 2. 마커 표시하기
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        // this.markers = positions.map(
        //   (position) =>
        //     new kakao.maps.Marker({
        //       map: this.map,
        //       position, // 마커 위치
        //       title: "", // 마우스 오버시 표시할 제목
        //       image: markerImage, // 마커 이미지
        //     })
        // );
        const marker = [];
        for (let index = 0; index < positions.length; index++) {
          marker.push(
            new kakao.maps.Marker({
              map: this.map,
              position: positions[index], // 마커 위치
              title: this.name[index], // 마우스 오버시 표시할 제목
              image: markerImage, // 마커 이미지
            })
          );
        }

        // kakao.maps.event.addListener(marker, "mouseover", () => {
        //   infowindow.open(this.map, marker);
        // });
        // kakao.maps.event.addListener(marker, "mouseout", () => {
        //   infowindow.close(this.map, marker);
        // });

        this.markers.push(marker);

        //  3. 지도 이동
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
        // this.markers.setMap(this.map);
      }
    },
    displayType() {
      // var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      var ps = new kakao.maps.services.Places(this.map);

      ps.categorySearch("CS2", this.placesSearchCB, { useMapBounds: true });
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          this.markerPositions.push([data[i].y, data[i].x]);
        }
        this.displayMarker(this.markerPositions);
      }
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
