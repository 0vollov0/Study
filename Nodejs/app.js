var express = require('express')
var app = express()
var bodyParser = require('body-parser')
var router = require('./router/index')

var main = require('./router/main')
var email = require('./router/email')

app.listen(3000,function(){
    console.log('start!222');
});

app.use(express.static('public'))
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended:true}))
app.set('view engine','ejs')

app.use(router)
app.use('/main',main)
app.use('/email',email)
