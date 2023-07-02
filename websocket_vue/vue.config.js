// 跨域配置
module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      '/api': {
        target: 'http://localhost:8088',
        changeOrigin: true,
        pathRewrite: {
          '/api': ''
        }
      }
    }
  },
  configureWebpack: {
    resolve: {
      alias: {
        'assets': '@/assets',
        // 'common': '@/common',
        'components': '@/components',
        'api': '@/api',
        'views': '@/views',
        'plugins': '@/plugins'
      }
    }
  }
}
