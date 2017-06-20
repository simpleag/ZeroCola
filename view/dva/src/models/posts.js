import * as postService from '../services/posts';

export default {
  namespace: 'posts',
  state: {
    postList: [],
    post: {}
  },
  reducers: {
    updatePostList(state, action) {
      const {postList} = action.payload;
      return { ...state, postList };
    },
    updatePost(state, action) {
      const {post} = action.payload;
      console.log(action);
      return { ...state, post };
    },
  },
  effects: {
    *fetch({ payload: { page, pageSize } }, { call, put }) {
      const response = yield call(postService.fetch, { page, pageSize });
      const data = response.data.data.content;
      yield put({
        type: 'updatePostList',
        payload: {
          postList: data
        }
      });
    },
  },
  subscriptions: {
    setup({ dispatch, history }) {
      return history.listen(({ pathname }) => {
        if (pathname === '/admin/posts') {
          dispatch({ type: 'fetch', payload: { page: 1, pageSize: 10 } });
        }
      });
    },
  },
};
