const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  port: 3000,
  proxy: {
      '/api/*': {
          target: 'http://localhost:8081',
          changeOrigin: true
      }
  }
})
