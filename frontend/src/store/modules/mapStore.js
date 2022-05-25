const mapStore = {
  namespaced: true,
  state: {
    lng: 128.413952,
    lat: 36.1081964,
    type: 0,
  },
  getters: {},
  mutations: {
    SET_LNG: (state, lng) => {
      state.lng = lng;
    },
    SET_LAT: (state, lat) => {
      state.lat = lat;
    },
    SET_TYPE: (state, type) => {
      state.type = type;
    },
  },
  actions: {},
};

export default mapStore;
