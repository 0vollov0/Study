var express = require('express')
var app = express()
var router = express.Router();
var path = require('path')//상대경로

//url routing
router.get('/',function(req,res){
    console.log('ppp')
    res.sendFile(path.join(__dirname,'../public/main.html'))
});

module.exports = router;