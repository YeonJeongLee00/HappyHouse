const mapStore = {
  namespaced: true,
  state: {
    lng: 128.413952,
    lat: 36.1081964,
    name: "SSAFY",
  },
  getters: {},
  mutations: {
    SET_LNG: (state, lng) => {
      state.lng = lng;
    },
    SET_LAT: (state, lat) => {
      state.lat = lat;
    },
    SET_TITLE_INFO: (state, name) => {
      state.name = name;
    },
  },
  actions: {},
};

export default mapStore;
