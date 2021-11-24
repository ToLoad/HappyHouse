<template>
  <div>
    <b-button-group size="sm">
      <b-button
        v-for="(btn, idx) in buttons"
        :key="idx"
        :pressed.sync="btn.state"
        :id="btn.id"
        variant="secondary"
        @click="clickCategory(btn.id, btn.state)"
      >
        {{ btn.caption }}
      </b-button>
    </b-button-group>
    <div id="map" style="width: 600px; height: 800px; margin: auto"></div>
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
      placeOverlay: null,
      contentNode: null,
      customOverlay: null,
      customContent: null,
      markers: [],
      currCategory: "",
      ps: null,
      buttons: [
        { caption: "대형마트", id: "MT1", state: false },
        { caption: "편의점", id: "CS2", state: false },
        { caption: "주차장", id: "PK6", state: false },
        { caption: "주유소", id: "OL7", state: false },
        { caption: "지하철역", id: "SW8", state: false },
        { caption: "은행", id: "BK9", state: false },
        { caption: "음식점", id: "FD6", state: false },
        { caption: "카페", id: "CE7", state: false },
        { caption: "병원", id: "HP8", state: false },
        { caption: "약국", id: "PM9", state: false },
      ],
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

      // 커스텀 오버레이 셋팅
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.customOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.contentNode = document.createElement("div");
      this.customContent = document.createElement("div");
      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.contentNode.className = "placeinfo_wrap";
      this.customContent.className = "aptinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap
      );
      addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap
      );
      addEventHandle(
        this.customContent,
        "mousedown",
        kakao.maps.event.preventMap
      );
      addEventHandle(
        this.customContent,
        "touchstart",
        kakao.maps.event.preventMap
      );
      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
          target.addEventListener(type, callback);
        } else {
          target.attachEvent("on" + type, callback);
        }
      }
      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);
      this.customOverlay.setContent(this.customContent);

      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.map);

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
    },
    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }
      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);
      this.customOverlay.setMap(null);
      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      console.log(places);
      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x)
        );
        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        (function (marker, place, contentNode, placeOverlay, map) {
          kakao.maps.event.addListener(marker, "click", function () {
            displayPlaceInfo(place, contentNode, placeOverlay, map);
          });
        })(marker, places[i], this.contentNode, this.placeOverlay, this.map);
      }
      // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
      function displayPlaceInfo(place, contentNode, placeOverlay, map) {
        var content =
          '<div class="placeinfo">' +
          '   <a class="title" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          "</a>";

        if (place.road_address_name) {
          content +=
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            "</span>" +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ")</span>";
        } else {
          content +=
            '    <span title="' +
            place.address_name +
            '">' +
            place.address_name +
            "</span>";
        }

        content +=
          '    <span class="tel">' +
          place.phone +
          "</span>" +
          "</div>" +
          '<div class="after"></div>';

        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(map);
      }
    },
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position) {
      var imageSrc = require(`@/assets/marker/${this.currCategory}.png`), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(25, 50) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      var marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });
      console.log(this.currCategory);

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    clickCategory(id, state) {
      if (state === false) {
        this.removeMarker();
        this.placeOverlay.setMap(null);
      } else {
        // 선택한 것 이외에 다른 것들은 다 off 처리
        for (var i = 0; i < this.buttons.length; i++) {
          if (this.buttons[i].id !== id) this.buttons[i].state = false;
        }
        this.currCategory = id;
        this.searchPlaces();
      }
    },
    makeMarkers() {
      var bounds = new kakao.maps.LatLngBounds(); // 해당 마커가 모여있는 곳을 보여주기위함
      for (var i = 0; i < this.houses.length; i++) {
        var position = new kakao.maps.LatLng(
          this.houses[i].lat,
          this.houses[i].lng
        );
        bounds.extend(position);

        var imageSrc = require("@/assets/marker/home.png"), // 마커이미지의 주소입니다
          imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기입니다
          imageOption = { offset: new kakao.maps.Point(25, 50) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption
        );

        var marker = new kakao.maps.Marker({
          map: this.map,
          position: position,
          title: this.houses[i].aptName,
          image: markerImage,
        });
        marker.setMap(this.map);
        (function (marker, house, customContent, customOverlay, map) {
          kakao.maps.event.addListener(marker, "click", function () {
            customContent = null;
            customContent = document.createElement("div");
            customContent.className = "aptinfo_wrap";
            customOverlay.setContent(customContent);
            displayAptInfo(house, customContent, customOverlay, map);
          });
        })(
          marker,
          this.houses[i],
          this.customContent,
          this.customOverlay,
          this.map
        );
      }
      function displayAptInfo(house, customContent, customOverlay, map) {
        console.log(customContent);
        var aptinfo = document.createElement("div");
        aptinfo.className = "aptinfo";
        var after = document.createElement("div");
        after.className = "after";

        var title = document.createElement("div");
        title.className = "title";
        title.appendChild(document.createTextNode(house.aptName));
        title.onclick = function () {
          customOverlay.setMap(null);
        };

        var address = document.createElement("span");
        address.appendChild(
          document.createTextNode(
            house.sidoName +
              " " +
              house.gugunName +
              " " +
              house.dongName +
              " " +
              house.jibun
          )
        );

        var recentprice = document.createElement("span");
        recentprice.appendChild(
          document.createTextNode(house.recentPrice + " 만원")
        );

        aptinfo.appendChild(title);
        aptinfo.appendChild(address);
        aptinfo.appendChild(recentprice);
        customContent.appendChild(aptinfo);
        customContent.appendChild(after);

        customOverlay.setPosition(new kakao.maps.LatLng(house.lat, house.lng));
        customOverlay.setMap(map);
      }
      // var aptinfo = document.getElementById("aptinfo");
      // aptinfo.onclick = function () {
      //   this.customOverlay.setMap(null);
      // };
      this.map.setBounds(bounds);
    },
  },
};
</script>

<style>
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
.aptinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.aptinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.aptinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.aptinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.aptinfo a,
.aptinfo a:hover,
.aptinfo a:active {
  color: #fff;
  text-decoration: none;
}
.aptinfo a,
.aptinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.aptinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.aptinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #5950d9;
  background: #5950d9
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.aptinfo .recentprice {
  color: #0f7833;
}
.aptinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
