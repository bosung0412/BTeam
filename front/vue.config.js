const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
})
module.exports = {

  devServer: {
    port: 8081, // 사용하고자 하는 포트로 변경
  },
};
  configureWebpack: {
    devtool: 'source-map'
  }


