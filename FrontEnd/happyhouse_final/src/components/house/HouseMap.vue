<template>
  <div>
    <div id="map" style="width: 550px; height: 400px; margin: auto"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";
export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=97abf8e6c4f646e94f2cc259bd151662&libraries=services&autoload=false";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  watch: {
    houses: function () {
      this.makeMarkers();
    },
  },
  methods: {
    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

      var lat = 37.501297;
      var lng = 127.039661;
      var level = 4;

      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(lat, lng), //지도의 중심좌표.
        level: level, //지도의 레벨(확대, 축소 정도)
      };

      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // 시작위치를 멀티캠퍼스 역삼 + 마커 + 인포위도우
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(37.501297, 127.039661),
        title: "멀티캠퍼스 역삼",
      });

      var infowindow = new kakao.maps.InfoWindow({
        content:
          '<div style="width:200px;text-align:center;padding:6px 0;">' +
          "멀티캠퍼스 역삼 " +
          "</div>",
        position: new kakao.maps.LatLng(37.501297, 127.039661),
        removable: true,
      });

      marker.setMap(this.map);
      infowindow.open(this.map, marker);
    },
    makeMarkers() {
      var bounds = new kakao.maps.LatLngBounds();
      for (var i = 0; i < this.houses.length; i++) {
        var position = new kakao.maps.LatLng(
          this.houses[i].lat,
          this.houses[i].lng
        );
        bounds.extend(position);
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: position,
          title: this.houses[i].aptName,
        });

        var infowindow = new kakao.maps.InfoWindow({
          content:
            '<div style="width:200px;text-align:center;padding:6px 0;">' +
            this.houses[i].aptName +
            "</div>",
          position: position,
          removable: true,
        });
        marker.setMap(this.map);
        infowindow.open(this.map, marker);
      }
      this.map.setBounds(bounds);
    },
  },
};
</script>

<style></style>
