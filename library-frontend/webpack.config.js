/*
Command line to start, build project
npm start for running the localhost
npm run dev for building development bundle
npm run prod for building production bundle
PLUGINS:
npm install --save-dev html-webpack-plugin - creation of HTML files to serve your webpack bundles
npm install --save-dev clean-webpack-plugin - cleans build folder before bundling the prod build
KNOWN ISSUE:
https://github.com/jantimon/html-webpack-plugin/issues/895
*/
const path = require('path');
const webpack = require('webpack');
const HtmlWebPackPlugin = require("html-webpack-plugin");
const CleanWebpackPlugin = require('clean-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const EncodingPlugin = require('webpack-encoding-plugin');
const devMode = process.env.NODE_ENV !== 'production';

module.exports = {
    entry: {
        polyfills: './polyfill.js',
        vendors: ['react', 'react-dom'],
        app: './src/index.js'
    },
    output: {
        path: path.resolve(__dirname, './build'),
        filename: '[name].bundle.js'
        , publicPath: '../resources/'
    },
    module: {
        rules: [
            //JAVASCRIPT
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        plugins: [
                            "@babel/plugin-syntax-dynamic-import",
                            "@babel/plugin-proposal-class-properties"
                        ]
                    }
                }
            }, {
                test: /\.(svg|eot|woff|woff2|ttf)$/,
                loader: "url-loader",
            },

            //SCSS and CSS
            {
                test: /\.(sa|sc|c)ss$/,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader
                    },
                    {
                        loader: 'css-loader',
                        options: {
                            sourceMap: true
                        }
                    },
                    {
                        loader: 'sass-loader',
                        options: {
                            implementation: require("sass"),
                            sourceMap: true
                        }
                    }
                ]
            },
            //Images Loader
            {
                test: /\.(png|jpe?g|gif)$/i,
                use: [{
                    loader: 'url-loader',
                },
                ]
            },
        ]
    },
    //Create chunks
    optimization: {
        splitChunks: {
            cacheGroups: {
                commons: {
                    chunks: "initial",
                    minChunks: 2,
                    maxInitialRequests: 5,
                    minSize: 0
                },
                vendors: {
                    test: /[\\/]node_modules[\\/]/,
                    name: 'vendors',
                    chunks: 'initial',
                    priority: 10,
                    enforce: true
                }
            }
        },
        //Uglify JavaScript and minify CSS
        minimizer: [
            new UglifyJsPlugin({
                test: /\.(js|jsx)$/i,
                exclude: /\/node_modules/,
                cache: false,
                extractComments: false,
                parallel: 4,
                sourceMap: true,
                uglifyOptions: {
                    compress: {},
                    output: {
                        comments: false
                    }
                }
            }),
            new OptimizeCSSAssetsPlugin({
                filename: "./styles/components.css"
            })
        ]
    },
    plugins: [
        new CleanWebpackPlugin(['build']),
        new HtmlWebPackPlugin({
            template: path.resolve(__dirname, "src", "index.html"),
            filename: "index.html",
            inject: true,
            xhtml: true
        }),
        new MiniCssExtractPlugin({
            filename: "./styles/components.css",
            chunkFilename: "./styles/[id].css"
        }),
        new webpack.SourceMapDevToolPlugin({
            module: false,
            noSources: false,
            exclude: ['./styles/components.css', 'vendors.bundle.js']
        }),
        new EncodingPlugin({
            test: /(\.js|\.css)($|\?)/i,
            encoding: 'utf-8',
            exclude: /\/node_modules/
        })
    ],
    devtool: devMode ? 'eval-source-map' : 'source-map',
    devServer: {
        contentBase: [path.join(__dirname, 'src/styles/scss'), path.join(__dirname, 'src/components')],
        watchContentBase: true,
        disableHostCheck: true
    }
};