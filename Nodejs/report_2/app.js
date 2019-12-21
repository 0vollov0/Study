var express = require('express')
var app = express()
var router = require('./router/router')
var bodyParser = require('body-parser')

app.listen(8080,function(){
    console.log('server start!!!')
})

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended:false}))

app.use(router)