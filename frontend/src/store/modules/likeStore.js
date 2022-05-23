import { getLikeApt, getLikeArea } from "@/api/like.js";

const likeStore = {
  namespaced: true,
  state: {
    likeArea: null,
    likeApt: null,
  },
  getters: {},
  mutations: {
    SET_LIKE_AREA: (state, areas) => {
      state.likeArea = areas;
    },
    SET_LIKE_APT: (state, apts) => {
      state.likeApt = apts;
    },
  },
  actions: {
    getLikeArea: ({ commit }, id) => {
      console.log(id);
      getLikeArea(
        id,
        ({ data }) => {
          console.log(data);
          commit("SET_LIKE_AREA", data);
        },
        () => {}
      );
    },
    getLikeApt: ({ commit }, id) => {
      getLikeApt(
        id,
        ({ data }) => {
          commit("SET_LIKE_APT", data);
        },
        () => {}
      );
    },
  },
};

export default likeStore;
