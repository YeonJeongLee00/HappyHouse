const mapStore = {
  state: {
    lng: null,
    lat: null,
  },
  getters: {},
  mutations: {
    SET_LNG: (state, lng) => {
      state.lng = lng;
    },
    SET_LAT: (state, lat) => {
      state.lat = lat;
    },
  },
  actions: {},
};

export default mapStore;
