   

const webpack = require('webpack');

module.exports = {
  entry: { main: "./src/index.tsx" },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: "ts-loader",
        exclude: /node_modules/,
      },
      { test: /\.xml$/, loader: "xml-loader" },
      {
        test: /\.(png|jpe?g|gif|jp2|webp)$/,
        loader: "file-loader",
        options: {
          name: "[name].[ext]",
        },
      },
    ],
  },
  resolve: {
    extensions: [".tsx", ".ts", ".js"],
  },
  plugins: [
    new webpack.DefinePlugin({
      'process.env.API_BASE_URL': JSON.stringify(process.env.API_BASE_URL),
      'process.env.JWT_SECRET': JSON.stringify(process.env.JWT_SECRET),
      'process.env.ALGOLIA_APP_ID': JSON.stringify(process.env.ALGOLIA_APP_ID),
      'process.env.ALGOLIA_API_KEY': JSON.stringify(process.env.ALGOLIA_API_KEY),
      'process.env.ALGOLIA_INDEX_NAME': JSON.stringify(process.env.ALGOLIA_INDEX_NAME),
      'process.env.ALGOLIA_USAGE': JSON.stringify(process.env.ALGOLIA_USAGE),
    }),
  ],
};
