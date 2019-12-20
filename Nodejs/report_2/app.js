var express = require('express')
var app = express()
var router = require('./router/router')

app.listen(8080,function(){
    console.log('server start!!!')
})

app.use(router)